package com.lucidity.orderoptimizer.strategy.impl;

import com.lucidity.orderoptimizer.model.geo.Coordinate;

public interface IDistanceCalculatorStrategy {

    Double calculateDistance(Coordinate x, Coordinate y, Double speedOfDelivery);
}
