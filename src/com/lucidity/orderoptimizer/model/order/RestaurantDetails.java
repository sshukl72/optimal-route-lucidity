package com.lucidity.orderoptimizer.model.order;

import com.lucidity.orderoptimizer.model.geo.Coordinate;

import java.util.Objects;

public class RestaurantDetails {

    private final String restaurantName;
    private final Coordinate restaurantCoordinate;
    private final Double preparationTime;

    private RestaurantDetails(Builder builder) {
        this.restaurantName = builder.restaurantName;
        this.restaurantCoordinate = builder.restaurantCoordinate;
        this.preparationTime = builder.preparationTime;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Coordinate getRestaurantLocation() {
        return restaurantCoordinate;
    }

    public Double getPreparationTime() {
        return preparationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantDetails that)) return false;
        return Objects.equals(restaurantName, that.restaurantName) && Objects.equals(restaurantCoordinate, that.restaurantCoordinate) && Objects.equals(preparationTime, that.preparationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantName, restaurantCoordinate, preparationTime);
    }

    @Override
    public String toString() {
        return "RestaurantDetails{" +
                "restaurantName='" + restaurantName + '\'' +
                ", restaurantLocationDetails=" + restaurantCoordinate +
                ", preparationTime=" + preparationTime +
                '}';
    }

    public static class Builder {

        private String restaurantName;
        private Coordinate restaurantCoordinate;
        private Double preparationTime;

        public Builder restaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
            return this;
        }

        public Builder restaurantLocation(Coordinate restaurantCoordinate) {
            this.restaurantCoordinate = restaurantCoordinate;
            return this;
        }

        public Builder preparationTime(Double preparationTime) {
            this.preparationTime = preparationTime;
            return this;
        }

        public RestaurantDetails build() {
            return new RestaurantDetails(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }
}
