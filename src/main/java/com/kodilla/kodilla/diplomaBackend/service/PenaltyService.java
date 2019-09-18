package com.kodilla.kodilla.diplomaBackend.service;

import com.kodilla.kodilla.diplomaBackend.domain.LogHistory;
import com.kodilla.kodilla.diplomaBackend.domain.Penalty;
import com.kodilla.kodilla.diplomaBackend.repository.LogHistoryRepository;
import com.kodilla.kodilla.diplomaBackend.repository.PenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenaltyService {

    @Autowired
    PenaltyRepository penaltyRepository;

    @Autowired
    RentService rentService;

    @Autowired
    LogHistoryService logHistoryService;

    public Penalty chargePenalty(Penalty penalty){
        rentService.applyPenalty(penalty);
        logHistoryService.saveLog(penalty.getRent().getUser(), "Penalty for: " + penalty.getReason());
        return penaltyRepository.save(penalty);
    }

}
