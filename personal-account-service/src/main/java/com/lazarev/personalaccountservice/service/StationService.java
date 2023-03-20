package com.lazarev.personalaccountservice.service;

import com.lazarev.personalaccountservice.entity.Station;
import com.lazarev.personalaccountservice.exception.NoSuchStationException;
import com.lazarev.personalaccountservice.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository;

    public Station getStationByName(String stationName){
        return stationRepository.findStationByName(stationName)
                .orElseThrow(()->new NoSuchStationException("Station with name='%s' not found".formatted(stationName)));
    }
}
