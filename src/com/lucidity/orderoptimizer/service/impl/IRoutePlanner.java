package com.lucidity.orderoptimizer.service.impl;

import com.lucidity.orderoptimizer.model.geo.CurrentLocationDetails;
import com.lucidity.orderoptimizer.model.order.OrderDetails;

import java.util.HashMap;

public interface IRoutePlanner {

    Double findOptimalTime(CurrentLocationDetails currentState, Integer totalOrders, HashMap<String, OrderDetails> orderMap, Double bestTime, Double speedOfDelivery);

}