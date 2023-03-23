package com.lazarev.analyticsservice.mapper;

import com.lazarev.analyticsservice.entity.Flight;
import com.lazarev.model.analytics.FlightParametersCalcRequest;
import com.lazarev.model.analytics.RjdCalculatorRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T15:13:11+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public RjdCalculatorRequest toRjdCalculatorRequest(FlightParametersCalcRequest flightParametersCalcRequest) {
        if ( flightParametersCalcRequest == null ) {
            return null;
        }

        String sourceStation = null;
        String destStation = null;
        String cargo = null;
        Integer totalVolume = null;

        sourceStation = flightParametersCalcRequest.sourceStation();
        destStation = flightParametersCalcRequest.destStation();
        cargo = flightParametersCalcRequest.cargo();
        totalVolume = flightParametersCalcRequest.totalVolume();

        RjdCalculatorRequest rjdCalculatorRequest = new RjdCalculatorRequest( sourceStation, destStation, cargo, totalVolume );

        return rjdCalculatorRequest;
    }

    @Override
    public Flight toFlight(FlightParametersCalcRequest flightParametersCalcRequest) {
        if ( flightParametersCalcRequest == null ) {
            return null;
        }

        Flight flight = new Flight();

        flight.setBeginDate( flightParametersCalcRequest.orderBeginDate() );
        flight.setCargo( flightParametersCalcRequest.cargo() );
        flight.setSourceStation( flightParametersCalcRequest.sourceStation() );
        flight.setDestStation( flightParametersCalcRequest.destStation() );
        flight.setTotalVolume( flightParametersCalcRequest.totalVolume() );
        flight.setClientOrderId( flightParametersCalcRequest.clientOrderId() );

        return flight;
    }
}
