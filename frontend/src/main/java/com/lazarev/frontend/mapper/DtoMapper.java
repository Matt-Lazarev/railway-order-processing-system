package com.lazarev.frontend.mapper;

import com.lazarev.model.ClientOrderCardDto;
import com.lazarev.model.analytics.FlightParametersCalcRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoMapper {
    @Mapping(target = "orderBeginDate", source = "orderBegin")
    FlightParametersCalcRequest toFlightParametersCalcRequest(ClientOrderCardDto clientOrderCardDto);
}
