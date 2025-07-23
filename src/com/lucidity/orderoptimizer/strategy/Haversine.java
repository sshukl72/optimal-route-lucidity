package com.lucidity.orderoptimizer.strategy;

import com.lucidity.orderoptimizer.model.geo.Coordinate;
import com.lucidity.orderoptimizer.strategy.impl.IDistanceCalculatorStrategy;

import static java.lang.Math.toRadians;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.asin;
import static java.lang.Math.sqrt;

import static com.lucidity.orderoptimizer.utils.Constants.RADIUS_OF_EARTH_IN_METER;

public class Haversine implements IDistanceCalculatorStrategy {

    /*
     * This method is used to calculate the time taken by any entity from point A to point B
     * This uses haversine formula to calculate great circle the shortest distance over the earth surface */
    @Override
    public Double calculateDistance(Coordinate x, Coordinate y, Double speedOfDelivery) {
        double SPEED_IN_METER_PER_MINUTE = (speedOfDelivery * 1000) / 60;
        return haversineFormula(x, y) / SPEED_IN_METER_PER_MINUTE;
    }

    /*
     * This method is used to calculate the great circle distance(the shortest distance over the earth surface)  */
    private static Double haversineFormula(Coordinate x, Coordinate y) {

        double lattitudeDifference = toRadians(y.getLatitude() - x.getLatitude());
        double longitudeDifference = toRadians(y.getLongitude() - x.getLongitude());

        double lattitude_x = toRadians(x.getLatitude());
        double lattitude_y = toRadians(y.getLatitude());

        double haversine = pow(sin(lattitudeDifference / 2), 2)
                + cos(lattitude_x) * cos(lattitude_y) * pow(sin(longitudeDifference) / 2, 2);

        return 2 * RADIUS_OF_EARTH_IN_METER * asin(sqrt(haversine));
    }
}
