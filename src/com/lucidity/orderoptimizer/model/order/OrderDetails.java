package com.lucidity.orderoptimizer.model.order;

import java.util.Objects;

public class OrderDetails {

    private final String id;
    private final RestaurantDetails restaurantDetails;
    private final ConsumerDetails consumerDetails;

    private OrderDetails(Builder builder) {
        this.id = builder.id;
        this.restaurantDetails = builder.restaurantDetails;
        this.consumerDetails = builder.consumerDetails;
    }

    public String getId() {
        return id;
    }

    public RestaurantDetails getRestaurantDetails() {
        return restaurantDetails;
    }

    public ConsumerDetails getConsumerDetails() {
        return consumerDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(restaurantDetails, that.restaurantDetails) && Objects.equals(consumerDetails, that.consumerDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantDetails, consumerDetails);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id='" + id + '\'' +
                ", restaurantDetails=" + restaurantDetails +
                ", consumerDetails=" + consumerDetails +
                '}';
    }

    public static class Builder {
        private String id;
        private RestaurantDetails restaurantDetails;
        private ConsumerDetails consumerDetails;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder restaurantDetails(RestaurantDetails restaurantDetails) {
            this.restaurantDetails = restaurantDetails;
            return this;
        }

        public Builder consumerDetails(ConsumerDetails consumerDetails) {
            this.consumerDetails = consumerDetails;
            return this;
        }

        public OrderDetails build() {
            return new OrderDetails(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
