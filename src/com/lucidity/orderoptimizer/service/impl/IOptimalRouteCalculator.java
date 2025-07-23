package com.lucidity.orderoptimizer.service.impl;

import com.lucidity.orderoptimizer.common.Response;
import com.lucidity.orderoptimizer.model.order.DeliveryPartnerDetails;
import com.lucidity.orderoptimizer.model.order.OrderDetails;

import java.util.List;

public interface IOptimalRouteCalculator {

    Response<String> findBestRoute(DeliveryPartnerDetails deliveryPartnerDetails, List<OrderDetails> orders);
}
