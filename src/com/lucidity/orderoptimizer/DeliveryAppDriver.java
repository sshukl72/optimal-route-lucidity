package com.lucidity.orderoptimizer;

import com.lucidity.orderoptimizer.common.Response;
import com.lucidity.orderoptimizer.model.geo.Coordinate;
import com.lucidity.orderoptimizer.model.order.ConsumerDetails;
import com.lucidity.orderoptimizer.model.order.DeliveryPartnerDetails;
import com.lucidity.orderoptimizer.model.order.OrderDetails;
import com.lucidity.orderoptimizer.model.order.RestaurantDetails;
import com.lucidity.orderoptimizer.service.OptimalTimeCalculator;
import com.lucidity.orderoptimizer.service.impl.IOptimalRouteCalculator;

import java.util.Arrays;
import java.util.List;

public class DeliveryAppDriver {
    public static void main(String[] args) {
        Coordinate deliveryBoyCurrentLocation = Coordinate.builder().latitude(12.9352).longitude(77.6141).build();
        DeliveryPartnerDetails deliveryBoy = DeliveryPartnerDetails.builder().deliveryPartnerName("SHUBHAM").deliveryPartnerLocationDetails(deliveryBoyCurrentLocation).speed(20.0).build();

        Coordinate firstRestaurantCurrentLocation = Coordinate.builder().latitude(12.936).longitude(77.610).build();
        RestaurantDetails firstRestaurantDetail = RestaurantDetails.builder().restaurantName("Behroz Biryani").restaurantLocation(firstRestaurantCurrentLocation).preparationTime(10.0).build();

        Coordinate secondRestaurantCurrentLocation = Coordinate.builder().latitude(12.937).longitude(77.611).build();
        RestaurantDetails secondRestaurantDetail = RestaurantDetails.builder().restaurantName("KFC").restaurantLocation(secondRestaurantCurrentLocation).preparationTime(8.0).build();

        Coordinate firstConsumerCurrentLocation = Coordinate.builder().latitude(12.940).longitude(77.620).build();
        ConsumerDetails firstConsumerDetail = ConsumerDetails.builder().customerName("Ashish").customerLocation(firstConsumerCurrentLocation).build();

        Coordinate secondConsumerCurrentLocation = Coordinate.builder().latitude(12.941).longitude(77.622).build();
        ConsumerDetails secondConsumerDetail = ConsumerDetails.builder().customerName("Shivam").customerLocation(secondConsumerCurrentLocation).build();


        List<OrderDetails> orders = Arrays.asList(
                OrderDetails.builder().id("1").consumerDetails(firstConsumerDetail).restaurantDetails(firstRestaurantDetail).build(),
                OrderDetails.builder().id("2").consumerDetails(secondConsumerDetail).restaurantDetails(secondRestaurantDetail).build()
        );

        IOptimalRouteCalculator planner = new OptimalTimeCalculator();
        Response<String> response = planner.findBestRoute(deliveryBoy, orders);

        if (response.hasError()) {
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getData());
        }

    }
}
