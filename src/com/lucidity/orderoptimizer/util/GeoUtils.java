package com.lucidity.orderoptimizer.util;

import com.lucidity.orderoptimizer.model.geo.LocationDetails;

import static java.lang.Math.toRadians;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.asin;
import static java.lang.Math.sqrt;

import static com.lucidity.orderoptimizer.util.Constant.RADIUS_OF_EARTH_IN_METER;

public class GeoUtils {

    /*
     * This method is used to calculate the great circle distance(the shortest distance over the earth surface)  */
    private static Double haversineFormula(LocationDetails x, LocationDetails y) {

        Double lattitudeDifference = toRadians(y.getLatitude() - x.getLatitude());
        Double longitudeDifference = toRadians(y.getLongitude() - y.getLongitude());

        Double lattitude_x = toRadians(x.getLatitude());
        Double lattitude_y = toRadians(y.getLatitude());

        Double haversine = pow(sin(lattitudeDifference / 2), 2)
                + cos(lattitude_x) * cos(lattitude_y) * pow(sin(longitudeDifference) / 2, 2);

        return 2 * RADIUS_OF_EARTH_IN_METER * asin(sqrt(haversine));
    }

    /*
    * This method is used to calculate the time taken by any entity from point A to point B
    * This uses haversine formula to calculate great circle the shortest distance over the earth surface */
    public static Double timeTakenToTravelBetweenTwoPoint(LocationDetails x, LocationDetails y, Double speedOfDelivery) {
        double SPEED_IN_METER_PER_MINUTE = (speedOfDelivery * 1000) / 60;
        return haversineFormula(x, y) / SPEED_IN_METER_PER_MINUTE;
    }

    /*
    * returns true if the value is null, infinite  or NAN condition satisfy  */
    public static boolean isValidDoubleValue(Double value) {
        return value == null || Double.isNaN(value) || Double.isInfinite(value);
    }
    /*
     * returns true if the value is null, blank  or empty condition satisfy  */
    public static boolean isValidOrderId(String id) {
        return id == null || id.isBlank() || id.isEmpty();
    }

    public static boolean isValidObject(Object object){
        return object == null;
    }

}
