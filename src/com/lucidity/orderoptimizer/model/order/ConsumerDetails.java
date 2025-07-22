package com.lucidity.orderoptimizer.model.order;

import com.lucidity.orderoptimizer.model.geo.LocationDetails;

import java.util.Objects;

public class ConsumerDetails {

    private final String consumerName;
    private final LocationDetails consumerLocationDetails;


    private ConsumerDetails(Builder builder) {
        this.consumerName = builder.customerName;
        this.consumerLocationDetails = builder.customerLocationDetails;

    }

    public String getConsumerName() {
        return consumerName;
    }

    public LocationDetails getConsumerLocation() {
        return consumerLocationDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumerDetails consumerDetails)) return false;
        return Objects.equals(consumerName, consumerDetails.consumerName) && Objects.equals(consumerLocationDetails, consumerDetails.consumerLocationDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumerName, consumerLocationDetails);
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "consumerName='" + consumerName + '\'' +
                ", consumerLocation=" + consumerLocationDetails +
                '}';
    }

    public static class Builder {

        private String customerName;
        private LocationDetails customerLocationDetails;

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder customerLocation(LocationDetails customerLocationDetails) {
            this.customerLocationDetails = customerLocationDetails;
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
