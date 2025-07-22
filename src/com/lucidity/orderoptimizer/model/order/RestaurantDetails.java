package com.lucidity.orderoptimizer.model.order;

import com.lucidity.orderoptimizer.model.geo.LocationDetails;

import java.util.Objects;

public class RestaurantDetails {

    private final String restaurantName;
    private final LocationDetails restaurantLocationDetails;
    private final Double preparationTime;

    private RestaurantDetails(Builder builder) {
        this.restaurantName = builder.restaurantName;
        this.restaurantLocationDetails = builder.restaurantLocationDetails;
        this.preparationTime = builder.preparationTime;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public LocationDetails getRestaurantLocation() {
        return restaurantLocationDetails;
    }

    public Double getPreparationTime() {
        return preparationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantDetails that)) return false;
        return Objects.equals(restaurantName, that.restaurantName) && Objects.equals(restaurantLocationDetails, that.restaurantLocationDetails) && Objects.equals(preparationTime, that.preparationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantName, restaurantLocationDetails, preparationTime);
    }

    @Override
    public String toString() {
        return "RestaurantDetails{" +
                "restaurantName='" + restaurantName + '\'' +
                ", restaurantLocationDetails=" + restaurantLocationDetails +
                ", preparationTime=" + preparationTime +
                '}';
    }

    public static class Builder {

        private String restaurantName;
        private LocationDetails restaurantLocationDetails;
        private Double preparationTime;

        public Builder restaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
            return this;
        }

        public Builder restaurantLocation(LocationDetails restaurantLocationDetails) {
            this.restaurantLocationDetails = restaurantLocationDetails;
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
