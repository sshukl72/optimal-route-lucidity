package com.lucidity.orderoptimizer.service;

import com.lucidity.orderoptimizer.model.geo.CurrentLocationDetails;
import com.lucidity.orderoptimizer.model.geo.Coordinate;
import com.lucidity.orderoptimizer.model.order.OrderDetails;
import com.lucidity.orderoptimizer.service.impl.IRoutePlanner;
import com.lucidity.orderoptimizer.strategy.Haversine;
import com.lucidity.orderoptimizer.strategy.impl.IDistanceCalculatorStrategy;

import java.util.HashMap;

public class HaversineRoutePlanner implements IRoutePlanner {

    IDistanceCalculatorStrategy distanceCalculatorStrategy = new Haversine();
    /*
     * This method  backtrack and picks specific restaurant and deliver to consumer */
    @Override
    public Double findOptimalTime(CurrentLocationDetails currentState, Integer totalOrders, HashMap<String, OrderDetails> orderMap, Double bestTime, Double speedOfDelivery) {
        if (currentState.getDelivered().size() == totalOrders) {

            if (bestTime > currentState.getTime()) {

                bestTime = currentState.getTime();
            }
            return bestTime;
        }

        for (OrderDetails order : orderMap.values()) {

            if (!currentState.getPicked().contains(order.getId())) {

                Double movingTimeToRestaurant = distanceCalculatorStrategy.calculateDistance(currentState.getCurrentLocation(), order.getRestaurantDetails().getRestaurantLocation(), speedOfDelivery);
                Double arrivalTimeToRestaurant = currentState.getTime() + movingTimeToRestaurant;
                Double mealPreparationTime = Math.max(0, order.getRestaurantDetails().getPreparationTime() - arrivalTimeToRestaurant);
                Double newTime = arrivalTimeToRestaurant + mealPreparationTime;

                if (newTime >= bestTime)
                    continue;

                currentState.getPicked().add(order.getId());
                Coordinate previousCoordinate = currentState.getCurrentLocation();
                Double prevTime = currentState.getTime();
                currentState.setCurrentLocation(order.getRestaurantDetails().getRestaurantLocation());
                currentState.setTime(newTime);

                bestTime = findOptimalTime(currentState,totalOrders,orderMap,bestTime,speedOfDelivery);

                currentState.getPicked().remove((order.getId()));
                currentState.setCurrentLocation(previousCoordinate);
                currentState.setTime(prevTime);

            }

            if (currentState.getPicked().contains(order.getId()) && !currentState.getDelivered().contains(order.getId())) {

                Double movingTimeToConsumer = distanceCalculatorStrategy.calculateDistance(currentState.getCurrentLocation(), order.getConsumerDetails().getConsumerLocation(), speedOfDelivery);
                Double newTime = currentState.getTime() + movingTimeToConsumer;

                if (newTime >= bestTime)
                    continue;

                currentState.getDelivered().add(order.getId());
                Coordinate previousCoordinate = currentState.getCurrentLocation();
                Double prevTime = currentState.getTime();
                currentState.setCurrentLocation(order.getConsumerDetails().getConsumerLocation());
                currentState.setTime(newTime);

                bestTime = findOptimalTime(currentState,totalOrders,orderMap,bestTime,speedOfDelivery);

                currentState.getDelivered().remove(order.getId());
                currentState.setCurrentLocation(previousCoordinate);
                currentState.setTime(prevTime);

            }
        }
        return bestTime;
    }
}
