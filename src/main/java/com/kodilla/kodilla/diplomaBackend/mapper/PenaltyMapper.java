package com.kodilla.kodilla.diplomaBackend.mapper;

import com.kodilla.kodilla.diplomaBackend.domain.Penalty;
import com.kodilla.kodilla.diplomaBackend.domain.PenaltyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PenaltyMapper {

    @Autowired
    RentMapper rentMapper;

    public PenaltyDto mapToPenaltyDto(Penalty penalty){
        return new PenaltyDto.PenaltyDtoBuilder()
                .id(penalty.getId())
                .reason(penalty.getReason())
                .details(penalty.getDetails())
                .toBePaid(penalty.getToBePaid())
                .rentDto(rentMapper.mapToRentDto(penalty.getRent()))
                .build();
    }

    public Penalty mapToPenalty(PenaltyDto penaltyDto){
        return new Penalty(penaltyDto.getId(),
                penaltyDto.getReason(),
                penaltyDto.getDetails(),
                penaltyDto.getToBePaid(),
                rentMapper.mapToRent(penaltyDto.getRentDto()));
    }
}
