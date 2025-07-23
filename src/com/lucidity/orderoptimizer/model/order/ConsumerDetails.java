package com.lucidity.orderoptimizer.model.order;

import com.lucidity.orderoptimizer.model.geo.Coordinate;

import java.util.Objects;

public class ConsumerDetails {

    private final String consumerName;
    private final Coordinate consumerCoordinate;


    private ConsumerDetails(Builder builder) {
        this.consumerName = builder.customerName;
        this.consumerCoordinate = builder.customerCoordinate;

    }

    public String getConsumerName() {
        return consumerName;
    }

    public Coordinate getConsumerLocation() {
        return consumerCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumerDetails consumerDetails)) return false;
        return Objects.equals(consumerName, consumerDetails.consumerName) && Objects.equals(consumerCoordinate, consumerDetails.consumerCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumerName, consumerCoordinate);
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "consumerName='" + consumerName + '\'' +
                ", consumerLocation=" + consumerCoordinate +
                '}';
    }

    public static class Builder {

        private String customerName;
        private Coordinate customerCoordinate;

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder customerLocation(Coordinate customerCoordinate) {
            this.customerCoordinate = customerCoordinate;
            return this;
        }

        public ConsumerDetails build() {
            return new ConsumerDetails(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
