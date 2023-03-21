package com.lazarev.analyticsservice.mapper;

import com.lazarev.analyticsservice.entity.Flight;
import com.lazarev.model.analytics.FlightParametersCalcRequest;
import com.lazarev.model.analytics.RjdCalculatorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    RjdCalculatorRequest toRjdCalculatorRequest(FlightParametersCalcRequest flightParametersCalcRequest);

    @Mapping(target = "beginDate", source = "flightParametersCalcRequest.orderBeginDate")
    Flight toFlight(FlightParametersCalcRequest flightParametersCalcRequest);
}
