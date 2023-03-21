package com.lazarev.analyticsservice.service;

import com.lazarev.analyticsservice.entity.Flight;
import com.lazarev.analyticsservice.entity.Wagon;
import com.lazarev.analyticsservice.httpclient.RjdClient;
import com.lazarev.analyticsservice.mapper.FlightMapper;
import com.lazarev.analyticsservice.repository.FlightRepository;
import com.lazarev.model.analytics.FlightParametersCalcRequest;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import com.lazarev.model.analytics.RjdCalculatorRequest;
import com.lazarev.model.analytics.RjdCalculatorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationService {
    private final FlightRepository flightRepository;
    private final RjdClient rjdClient;
    private final FlightMapper flightMapper;
    private final WagonService wagonService;

    @Transactional
    public FlightParametersCalcResponse calculateFlightParameters(FlightParametersCalcRequest request){
        String sourceStation = request.sourceStation();
        String destStation = request.destStation();
        String cargo = request.cargo();
        LocalDate orderBeginDate = request.orderBeginDate();

        List<Flight> flights = flightRepository.findAllFlightByStationsAndCargo(sourceStation, destStation, cargo);
        List<Wagon> freeWagons = wagonService.getFreeWagons();

        Flight flight = flightMapper.toFlight(request);

        LocalDate planOrderEndDate = calculatePlanOrderEndDate(flights, orderBeginDate);
        BigDecimal rate = calculateFlightRate(request);
        WagonInfo wagonInfo = calculateWagonAmountAndWagonVolume(freeWagons, request, flight);

        flight.setEndDate(planOrderEndDate);
        flight.setRate(rate);
        flight.setWagonAmount(wagonInfo.wagonAmount);
        flight.setWagonVolume(wagonInfo.wagonVolume);

        flightRepository.save(flight);

        return new FlightParametersCalcResponse(planOrderEndDate, wagonInfo.wagonAmount, wagonInfo.wagonVolume, rate);
    }

    private LocalDate calculatePlanOrderEndDate(List<Flight> flights, LocalDate orderBeginDate){
        double averageTravelDays = flights
                .stream()
                .mapToInt(flight -> Period.between(flight.getBeginDate(), flight.getEndDate()).getDays())
                .average()
                .orElse(0);

        int wholeTravelDays = (int) Math.ceil(averageTravelDays);
        return orderBeginDate.plusDays(wholeTravelDays);
    }

    private BigDecimal calculateFlightRate(FlightParametersCalcRequest request){
        RjdCalculatorRequest rjdCalculatorRequest = flightMapper.toRjdCalculatorRequest(request);

        RjdCalculatorResponse rjdCalculatorResponse;
        try{
            rjdCalculatorResponse = rjdClient.calculateFlightRate(rjdCalculatorRequest);
        }
        catch (Exception ex){
            return handleException(request.totalVolume());
        }

        return rjdCalculatorResponse.rate();
    }

    private BigDecimal handleException(Integer totalVolume) {
        int averageMinRate = 10_000;
        int averageMaxRate = 50_000;

        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new BigDecimal(random.nextInt(averageMinRate, averageMaxRate) * totalVolume);
    }

    private WagonInfo calculateWagonAmountAndWagonVolume(List<Wagon> freeWagons, FlightParametersCalcRequest request, Flight flight){
        Map<Integer, List<Wagon>> wagons = groupWagonsByVolume(freeWagons);
        Optional<Map.Entry<Integer, List<Wagon>>> wagonsEntry = wagons.entrySet()
                .stream()
                .filter(entry -> entry.getKey() * entry.getValue().size() >= request.totalVolume())
                .findAny();

        WagonInfo wagonInfo = new WagonInfo();

        wagonsEntry.ifPresent(entry -> {
            int wagonAmount = (int) Math.ceil((double) request.totalVolume() / entry.getKey());
            int wagonVolume = entry.getKey();

            List<Wagon> updatableWagons = entry.getValue().subList(0, wagonAmount);
            flight.setWagons(updatableWagons);

            wagonService.updateWagonsStatus(updatableWagons, "Зарезервирован");
            wagonInfo.wagonVolume = wagonVolume;
            wagonInfo.wagonAmount = wagonAmount;
        });

        return wagonInfo;
    }

    private Map<Integer, List<Wagon>> groupWagonsByVolume(List<Wagon> wagons){
        return wagons
                .stream()
                .collect(Collectors.groupingBy(Wagon::getVolume));
    }

    private static class WagonInfo{
        Integer wagonVolume;
        Integer wagonAmount;

        public WagonInfo(){}
    }
}
