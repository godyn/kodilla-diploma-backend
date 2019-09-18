package com.kodilla.kodilla.diplomaBackend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentDto {

    private long id;
    private UserDto user;
    private CarDto carRented;
    private LocalDate startDay;
    private LocalDate endDay;
    private long fuelLevel;
    private boolean	withInsurance;
    private boolean	withExtraCarTrunk;
    private List<Penalty> listOfPenalties;
    private BigDecimal toBePaid;
    private RentStatus	status;

    public static class RentDtoBuilder{
        private long id;
        private UserDto user;
        private CarDto carRented;
        private LocalDate startDay;
        private LocalDate endDay;
        private long fuelLevel;
        private boolean	withInsurance;
        private boolean	withExtraCarTrunk;
        private List<Penalty> listOfPenalties;
        private BigDecimal toBePaid;
        private RentStatus	status;

        public RentDtoBuilder id(long id){
            this.id=id;
            return this;
        }

        public RentDtoBuilder user(UserDto user){
            this.user=user;
            return this;
        }

        public RentDtoBuilder carRented(CarDto carRented){
            this.carRented=carRented;
            return this;
        }

        public RentDtoBuilder fuelLevel(long fuelLevel){
            this.fuelLevel=fuelLevel;
            return this;
        }

        public RentDtoBuilder startDay(LocalDate startDay){
            this.startDay=startDay;
            return this;
        }

        public RentDtoBuilder endDay(LocalDate endDay){
            this.endDay=endDay;
            return this;
        }

        public RentDtoBuilder withInsurance(boolean withInsurance){
            this.withInsurance=withInsurance;
            return this;
        }

        public RentDtoBuilder withExtraCarTrunk(boolean withExtraCarTrunk){
            this.withExtraCarTrunk=withExtraCarTrunk;
            return this;
        }

        public RentDtoBuilder penalty(Penalty penalty){
            this.listOfPenalties.add(penalty);
            return this;
        }

        public RentDtoBuilder penalties(List<Penalty> listOfPenalties){
            this.listOfPenalties=listOfPenalties;
            return this;
        }

        public RentDtoBuilder toBePaid(BigDecimal toBePaid){
            this.toBePaid=toBePaid;
            return this;
        }

        public RentDtoBuilder status(RentStatus status){
            this.status=status;
            return this;
        }

        public RentDto build(){
            return new RentDto(id, user, carRented, startDay, endDay, fuelLevel, withInsurance, withExtraCarTrunk, listOfPenalties, toBePaid, status);
        }

    }


    private RentDto(long id, UserDto user, CarDto carRented, LocalDate startDay, LocalDate endDay, long fuelLevel, boolean withInsurance, boolean withExtraCarTrunk, List<Penalty> listOfPenalties, BigDecimal toBePaid, RentStatus status) {
        this.id = id;
        this.user = user;
        this.carRented = carRented;
        this.startDay = startDay;
        this.endDay = endDay;
        this.fuelLevel = fuelLevel;
        this.withInsurance = withInsurance;
        this.withExtraCarTrunk = withExtraCarTrunk;
        this.listOfPenalties = new ArrayList<>(listOfPenalties);
        this.toBePaid = toBePaid;
        this.status = status;
    }

    public RentDto() {
    }

    public long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public CarDto getCarRented() {
        return carRented;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public long getFuelLevel() {
        return fuelLevel;
    }

    public boolean isWithInsurance() {
        return withInsurance;
    }

    public boolean isWithExtraCarTrunk() {
        return withExtraCarTrunk;
    }

    public List<Penalty> getListOfPenalties() {
        return listOfPenalties;
    }

    public BigDecimal getToBePaid() {
        return toBePaid;
    }

    public RentStatus getStatus() {
        return status;
    }
}
