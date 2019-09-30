package com.kodilla.kodilla.diplomaBackend.service;

import com.kodilla.kodilla.diplomaBackend.domain.*;
import com.kodilla.kodilla.diplomaBackend.repository.LogHistoryRepository;
import com.kodilla.kodilla.diplomaBackend.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentService {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    LogHistoryService logHistoryService;

    public Rent makeReservation(Rent rent){
        rent.setStatus(RentStatus.BOOKED);
        logHistoryService.saveLog(rent.getUser(), "Booking car (" + rent.getCarRented().getId() + ")");
        return rentRepository.save(rent);
    }

    public Rent confirmRent(Rent rent){
        rent.setStatus(RentStatus.RENTED);
        logHistoryService.saveLog(rent.getUser(), "Renting car (" + rent.getCarRented().getId() + ")");
        return rentRepository.save(rent);
    }

    public Rent cancelReservation(Rent rent){
        rent.setStatus(RentStatus.CANCELED);
        logHistoryService.saveLog(rent.getUser(), "Cancelling reservation number " + rent.getId());
        return rentRepository.save(rent);
    }

    public Rent confirmReturn(Rent rent){
        rent.setStatus(RentStatus.FINISHED);
        logHistoryService.saveLog(rent.getUser(), "Car (" + rent.getCarRented().getId() + ") return");
        return rentRepository.save(rent);
    }

    public Rent applyPenalty(Penalty penalty){
        penalty.getRent().getToBePaid().add(penalty.getToBePaid());
        penalty.getRent().getListOfPenalties().add(penalty);
        return rentRepository.save(penalty.getRent());
    }

    public Rent recallPenalty(Penalty penalty){
        penalty.getRent().getToBePaid().subtract(penalty.getToBePaid());
        penalty.getRent().getListOfPenalties().remove(penalty);
        return rentRepository.save(penalty.getRent());
    }

    public List<Rent> fetchUserRents(User user){

        if(user.getRole().equals("ADMIN")){
            return rentRepository.findAll();
        }
        else {
            return rentRepository.getRentsByUserId(user.getId());
        }
    }

    public void accountFor(Rent rent){

        BigDecimal pricePerDay = rent.getCarRented().getCategory().getPricePerDay();
        if(rent.isWithExtraCarTrunk()){
            pricePerDay.add(new BigDecimal(10));
        }
        if(rent.isWithInsurance()){
            pricePerDay.add(new BigDecimal(4));
        }

        BigDecimal durationOfRent = new BigDecimal(ChronoUnit.DAYS.between(rent.getStartDay(), rent.getEndDay()));

        rent.setToBePaid(pricePerDay.multiply(durationOfRent));
        logHistoryService.saveLog(rent.getUser(), "Price to be paid has been updated. Rent id: " + rent.getId());
        rentRepository.save(rent);
    }

    public Rent findRent(long id){
        return rentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
