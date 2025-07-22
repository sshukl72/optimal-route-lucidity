package com.lucidity.orderoptimizer.model.order;

import com.lucidity.orderoptimizer.model.geo.LocationDetails;

import java.util.Objects;

public class DeliveryPartnerDetails {

    private final String deliveryPartnerName;
    private final LocationDetails deliveryPartnerLocationDetails;
    private final Double speed;

    private DeliveryPartnerDetails(Builder builder) {
        this.deliveryPartnerName = builder.deliveryPartnerName;
        this.deliveryPartnerLocationDetails = builder.deliveryPartnerLocationDetails;
        this.speed = builder.speed;
    }


    public String getDeliveryPartnerName() {
        return deliveryPartnerName;
    }

    public LocationDetails getDeliveryPartnerLocationDetails() {
        return deliveryPartnerLocationDetails;
    }
    public Double getSpeed(){ return  speed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryPartnerDetails that)) return false;
        return Objects.equals(deliveryPartnerName, that.deliveryPartnerName) && Objects.equals(deliveryPartnerLocationDetails, that.deliveryPartnerLocationDetails) && Objects.equals(speed, that.speed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryPartnerName, deliveryPartnerLocationDetails, speed);
    }

    @Override
    public String toString() {
        return "DeliveryPatnerDetails{" +
                "deliveryPartnerName='" + deliveryPartnerName + '\'' +
                ", deliveryPartnerLocationDetails=" + deliveryPartnerLocationDetails +
                ", speed=" + speed +
                '}';
    }

    public static class Builder {

        private String deliveryPartnerName;
        private LocationDetails deliveryPartnerLocationDetails;
        private Double speed;

        public Builder deliveryPartnerName(String deliveryPartnerName) {
            this.deliveryPartnerName = deliveryPartnerName;
            return this;
        }

        public Builder deliveryPartnerLocationDetails(LocationDetails deliveryPartnerLocationDetails) {
            this.deliveryPartnerLocationDetails = deliveryPartnerLocationDetails;
            return this;
        }

        public Builder speed(Double speed){
            this.speed = speed;
            return this;
        }

        public DeliveryPartnerDetails build() {
            return new DeliveryPartnerDetails(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
