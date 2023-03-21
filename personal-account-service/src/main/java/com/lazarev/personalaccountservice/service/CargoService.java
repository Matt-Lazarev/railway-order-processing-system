package com.lazarev.personalaccountservice.service;

import com.lazarev.personalaccountservice.entity.Cargo;
import com.lazarev.personalaccountservice.exception.NoSuchCargoException;
import com.lazarev.personalaccountservice.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;

    @Transactional(readOnly = true)
    public Cargo getCargoByName(String cargoName){
        return cargoRepository.findCargoByName(cargoName)
                .orElseThrow(()->new NoSuchCargoException("Cargo with name='%s' not found".formatted(cargoName)));
    }
}
