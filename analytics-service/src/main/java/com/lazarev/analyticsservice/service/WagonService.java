package com.lazarev.analyticsservice.service;

import com.lazarev.analyticsservice.entity.Wagon;
import com.lazarev.analyticsservice.repository.WagonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WagonService {
    private final WagonRepository wagonRepository;

    @Transactional(readOnly = true)
    public List<Wagon> getFreeWagons(){
        return wagonRepository.findAllFreeWagons();
    }

    @Transactional(readOnly = true)
    public List<Wagon> getAllWagonsByClientOrderId(Integer clientOrderId) {
        List<Wagon> ws = wagonRepository.findAllWagonsByClientOrderId(clientOrderId);
        return ws;
    }

    @Transactional
    public void updateWagonsStatus(List<Wagon> wagons, String newStatus){
        List<Integer> ids = wagons.stream().map(Wagon::getId).toList();
        wagonRepository.updateWagonStatus(ids, newStatus);
    }
}
