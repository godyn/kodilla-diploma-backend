package com.kodilla.kodilla.diplomaBackend.controller;

import com.kodilla.kodilla.diplomaBackend.domain.Penalty;
import com.kodilla.kodilla.diplomaBackend.domain.PenaltyDto;
import com.kodilla.kodilla.diplomaBackend.mapper.PenaltyMapper;
import com.kodilla.kodilla.diplomaBackend.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping(name="/v1")
public class PenaltyController {

    @Autowired
    PenaltyService penaltyService;

    @Autowired
    PenaltyMapper penaltyMapper;

    //ADMIN ONLY
    @PostMapping(name="/penalty", consumes = APPLICATION_JSON_VALUE )
    public void declarePenalty(@RequestBody PenaltyDto penaltyDto){
        penaltyService.chargePenalty(penaltyMapper.mapToPenalty(penaltyDto));
    }
}
