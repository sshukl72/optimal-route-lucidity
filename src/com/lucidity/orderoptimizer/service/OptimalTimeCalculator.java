package com.lucidity.orderoptimizer.service;

import com.lucidity.orderoptimizer.common.Response;
import com.lucidity.orderoptimizer.model.order.DeliveryPartnerDetails;
import com.lucidity.orderoptimizer.model.order.OrderDetails;
import com.lucidity.orderoptimizer.model.geo.CurrentLocationDetails;
import com.lucidity.orderoptimizer.service.impl.IOptimalRouteCalculator;
import com.lucidity.orderoptimizer.service.impl.IRoutePlanner;

import java.util.HashMap;
import java.util.List;

import static com.lucidity.orderoptimizer.utils.Constants.*;
import static com.lucidity.orderoptimizer.utils.ValidationUtil.*;


public class OptimalTimeCalculator implements IOptimalRouteCalculator {

    /*
     * This method is used to find the best route for the delivery partner to reach the destination in the shortest time and distance possible
     * This uses the haversine formula and speed to calculate the time taken
     * This method use backtracking to track all route possible with few condition such as :
     *  - The pickup of order should happen before delivery
     *  - We deliver the exact package to the consumer */

    @Override
    public Response<String> findBestRoute(DeliveryPartnerDetails deliveryPartnerDetails, List<OrderDetails> orders) {

        Double bestTime = Double.MAX_VALUE;

        try {
            // Delivery Partner details Validation
            objectNotNull(deliveryPartnerDetails, DELIVERY_PARTNER);
            objectNotNull(deliveryPartnerDetails.getDeliveryPartnerLocationDetails(), DELIVERY_PARTNER_LOCATION_DETAILS);
            isValidDoubleValue(deliveryPartnerDetails.getSpeed(), SPEED);
            isValidDoubleValue(deliveryPartnerDetails.getDeliveryPartnerLocationDetails().getLatitude(), DELIVERY_PARTNER_LATITUDE);
            isValidDoubleValue(deliveryPartnerDetails.getDeliveryPartnerLocationDetails().getLongitude(), DELIVERY_PARTNER_LONGITUDE);

            // Validate the orders details
            ValidateOrders(orders);

            HashMap<String, OrderDetails> orderMap = new HashMap<>();
            for (OrderDetails order : orders) {
                orderMap.put(order.getId().trim(), order);
            }

            CurrentLocationDetails currentState = new CurrentLocationDetails();
            currentState.setTime(INITIAL_START_TIME);
            currentState.setCurrentLocation(deliveryPartnerDetails.getDeliveryPartnerLocationDetails());

            IRoutePlanner routePlanner = new HaversineRoutePlanner();
            bestTime = routePlanner.findOptimalTime(currentState, orders.size(), orderMap, bestTime, deliveryPartnerDetails.getSpeed());

        } catch (NullPointerException | IllegalArgumentException exception) {
            return Response.failure(exception.getMessage());
        }
        return Response.success(SHORTEST_TIME_TAKEN_FOR_THIS_DELIVERY.concat(String.valueOf(bestTime.intValue())).concat(MINUTES).concat(String.valueOf((int) ((bestTime - bestTime.intValue()) * 60))).concat(SECOND));

    }

    private void ValidateOrders(List<OrderDetails> orders) {

        for (OrderDetails order : orders) {
            isValidString(order.getId(), ORDER_ID);
            objectNotNull(order.getConsumerDetails(), String.format(CONSUMER_DETAILS, order.getId()));
            objectNotNull(order.getRestaurantDetails(), String.format(RESTAURANT_DETAILS, order.getId()));
            objectNotNull(order.getConsumerDetails().getConsumerLocation(), String.format(CONSUMER_LOCATION_DETAILS, order.getId()));
            objectNotNull(order.getRestaurantDetails().getRestaurantLocation(), String.format(RESTAURANT_LOCATION_DETAILS, order.getId()));
            isValidDoubleValue(order.getRestaurantDetails().getRestaurantLocation().getLongitude(), String.format(RESTAURANT_LONGITUDE, order.getId()));
            isValidDoubleValue(order.getRestaurantDetails().getRestaurantLocation().getLatitude(), String.format(RESTAURANT_LATITUDE, order.getId()));
            isValidDoubleValue(order.getConsumerDetails().getConsumerLocation().getLongitude(), String.format(CONSUMER_LONGITUDE, order.getId()));
            isValidDoubleValue(order.getConsumerDetails().getConsumerLocation().getLatitude(), String.format(CONSUMER_LATITUDE, order.getId()));
        }
    }

}
