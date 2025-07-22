package com.lucidity.orderoptimizer.service;

import com.lucidity.orderoptimizer.common.Response;
import com.lucidity.orderoptimizer.model.order.DeliveryPartnerDetails;
import com.lucidity.orderoptimizer.model.order.OrderDetails;
import com.lucidity.orderoptimizer.model.geo.CurrentLocationDetails;

import java.util.HashMap;
import java.util.List;

import static com.lucidity.orderoptimizer.util.Constant.INVALID_ORDER_ID;
import static com.lucidity.orderoptimizer.util.Constant.INVALID_SPEED_FOR_DELIVERY_BOY;
import static com.lucidity.orderoptimizer.util.Constant.INVALID_LATITUDE_OR_LONGITUDE;
import static com.lucidity.orderoptimizer.util.Constant.SHORTEST_TIME_TAKEN_FOR_THIS_DELIVERY;
import static com.lucidity.orderoptimizer.util.Constant.CONSUMER_OR_RESTAURANT_OBJECT_IS_NULL;
import static com.lucidity.orderoptimizer.util.Constant.DELIVERY_PARTNER_OBJECT_IS_NULL;
import static com.lucidity.orderoptimizer.util.Constant.INITIAL_START_TIME;
import static com.lucidity.orderoptimizer.util.Constant.MINUTES;
import static com.lucidity.orderoptimizer.util.Constant.SECOND;
import static com.lucidity.orderoptimizer.util.GeoUtils.isValidDoubleValue;
import static com.lucidity.orderoptimizer.util.GeoUtils.isValidOrderId;
import static com.lucidity.orderoptimizer.util.GeoUtils.isValidObject;


public class DeliveryOptimizer {

    /*
     * This method is used to find the best route for the delivery partner to reach the destination in the shortest time and distance possible
     * This uses the haversine formula and speed to calculate the time taken
     * This method use backtracking to track all route possible with few condition such as :
     *  - The pickup of order should happen before delivery
     *  - We deliver the exact package to the consumer */
    public Response<String> findBestRoute(DeliveryPartnerDetails deliveryPartnerDetails, List<OrderDetails> orders) {

        if(isValidObject(deliveryPartnerDetails) || isValidObject(deliveryPartnerDetails.getDeliveryPartnerLocationDetails())){
            return Response.failure(DELIVERY_PARTNER_OBJECT_IS_NULL);
        }
        else if (isValidDoubleValue(deliveryPartnerDetails.getSpeed())) {
            return Response.failure(INVALID_SPEED_FOR_DELIVERY_BOY.concat(deliveryPartnerDetails.toString()));
        }
        else if(isValidDoubleValue(deliveryPartnerDetails.getDeliveryPartnerLocationDetails().getLatitude()) || isValidDoubleValue(deliveryPartnerDetails.getDeliveryPartnerLocationDetails().getLongitude())){
            return Response.failure(INVALID_LATITUDE_OR_LONGITUDE.concat(deliveryPartnerDetails.getDeliveryPartnerLocationDetails().toString()));
        }


        HashMap<String, OrderDetails> orderMap = new HashMap<>();

        for (OrderDetails order : orders) {
            if (isValidOrderId(order.getId())) {
                return Response.failure(INVALID_ORDER_ID.concat(order.toString()));
            } else if (isValidObject(order.getConsumerDetails()) || isValidObject(order.getRestaurantDetails())) {
                return Response.failure(CONSUMER_OR_RESTAURANT_OBJECT_IS_NULL.concat(order.getId()));
            } else if (isValidDoubleValue(order.getRestaurantDetails().getRestaurantLocation().getLongitude()) || isValidDoubleValue(order.getRestaurantDetails().getRestaurantLocation().getLatitude()) || isValidDoubleValue(order.getConsumerDetails().getConsumerLocation().getLongitude()) || isValidDoubleValue(order.getConsumerDetails().getConsumerLocation().getLatitude())) {
                return Response.failure(INVALID_LATITUDE_OR_LONGITUDE.concat("Order ID: ".concat(order.getId().trim())));
            } else {
                orderMap.put(order.getId().trim(), order);
            }
        }

        CurrentLocationDetails currentState = new CurrentLocationDetails();
        currentState.setTime(INITIAL_START_TIME);
        currentState.setCurrentLocation(deliveryPartnerDetails.getDeliveryPartnerLocationDetails());

        Double bestTime = Double.MAX_VALUE;
        PathPLanner pathPLanner = new PathPLanner();
        bestTime = pathPLanner.allPossibleRoutesWithLeastTime(currentState, orders.size(), orderMap, bestTime, deliveryPartnerDetails.getSpeed());

        return Response.success(SHORTEST_TIME_TAKEN_FOR_THIS_DELIVERY.concat(String.valueOf(bestTime.intValue())).concat(MINUTES).concat(String.valueOf((int) ((bestTime - bestTime.intValue()) * 60))).concat(SECOND));

    }

}
