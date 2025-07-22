package com.lucidity.orderoptimizer.service;

import com.lucidity.orderoptimizer.model.geo.CurrentLocationDetails;
import com.lucidity.orderoptimizer.model.geo.LocationDetails;
import com.lucidity.orderoptimizer.model.order.OrderDetails;

import java.util.HashMap;

import static com.lucidity.orderoptimizer.util.GeoUtils.timeTakenToTravelBetweenTwoPoint;

public class PathPLanner {

    /*
     * This method  backtrack and picks specific restaurant and deliver to consumer */
    public Double allPossibleRoutesWithLeastTime(CurrentLocationDetails currentState, Integer totalOrders, HashMap<String, OrderDetails> orderMap, Double bestTime, Double speedOfDelivery) {
        if (currentState.getDelivered().size() == totalOrders) {

            if (bestTime > currentState.getTime()) {

                bestTime = currentState.getTime();
            }
            return bestTime;
        }

        for (OrderDetails order : orderMap.values()) {

            if (!currentState.getPicked().contains(order.getId())) {

                Double movingTimeToRestaurant = timeTakenToTravelBetweenTwoPoint(currentState.getCurrentLocation(), order.getRestaurantDetails().getRestaurantLocation(), speedOfDelivery);
                Double arrivalTimeToRestaurant = currentState.getTime() + movingTimeToRestaurant;
                Double mealPreparationTime = Math.max(0, order.getRestaurantDetails().getPreparationTime() - arrivalTimeToRestaurant);
                Double newTime = arrivalTimeToRestaurant + mealPreparationTime;

                if (newTime >= bestTime)
                    continue;

                currentState.getPicked().add(order.getId());
                LocationDetails previousLocationDetails = currentState.getCurrentLocation();
                Double prevTime = currentState.getTime();
                currentState.setCurrentLocation(order.getRestaurantDetails().getRestaurantLocation());
                currentState.setTime(newTime);

                bestTime = allPossibleRoutesWithLeastTime(currentState, totalOrders, orderMap, bestTime, speedOfDelivery);

                currentState.getPicked().remove((order.getId()));
                currentState.setCurrentLocation(previousLocationDetails);
                currentState.setTime(prevTime);

            }

            if (currentState.getPicked().contains(order.getId()) && !currentState.getDelivered().contains(order.getId())) {

                Double movingTimeToConsumer = timeTakenToTravelBetweenTwoPoint(currentState.getCurrentLocation(), order.getConsumerDetails().getConsumerLocation(), speedOfDelivery);
                Double newTime = currentState.getTime() + movingTimeToConsumer;

                if (newTime >= bestTime)
                    continue;

                currentState.getDelivered().add(order.getId());
                LocationDetails previousLocationDetails = currentState.getCurrentLocation();
                Double prevTime = currentState.getTime();
                currentState.setCurrentLocation(order.getConsumerDetails().getConsumerLocation());
                currentState.setTime(newTime);

                bestTime = allPossibleRoutesWithLeastTime(currentState, totalOrders, orderMap, bestTime, speedOfDelivery);

                currentState.getDelivered().remove(order.getId());
                currentState.setCurrentLocation(previousLocationDetails);
                currentState.setTime(prevTime);

            }
        }
        return bestTime;
    }
}
