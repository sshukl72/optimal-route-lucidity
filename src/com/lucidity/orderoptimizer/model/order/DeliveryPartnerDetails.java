package com.lucidity.orderoptimizer.model.order;

import com.lucidity.orderoptimizer.model.geo.Coordinate;

import java.util.Objects;

public class DeliveryPartnerDetails {

    private final String deliveryPartnerName;
    private final Coordinate deliveryPartnerCoordinate;
    private final Double speed;

    private DeliveryPartnerDetails(Builder builder) {
        this.deliveryPartnerName = builder.deliveryPartnerName;
        this.deliveryPartnerCoordinate = builder.deliveryPartnerCoordinate;
        this.speed = builder.speed;
    }


    public String getDeliveryPartnerName() {
        return deliveryPartnerName;
    }

    public Coordinate getDeliveryPartnerLocationDetails() {
        return deliveryPartnerCoordinate;
    }
    public Double getSpeed(){ return  speed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryPartnerDetails that)) return false;
        return Objects.equals(deliveryPartnerName, that.deliveryPartnerName) && Objects.equals(deliveryPartnerCoordinate, that.deliveryPartnerCoordinate) && Objects.equals(speed, that.speed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryPartnerName, deliveryPartnerCoordinate, speed);
    }

    @Override
    public String toString() {
        return "DeliveryPatnerDetails{" +
                "deliveryPartnerName='" + deliveryPartnerName + '\'' +
                ", deliveryPartnerLocationDetails=" + deliveryPartnerCoordinate +
                ", speed=" + speed +
                '}';
    }

    public static class Builder {

        private String deliveryPartnerName;
        private Coordinate deliveryPartnerCoordinate;
        private Double speed;

        public Builder deliveryPartnerName(String deliveryPartnerName) {
            this.deliveryPartnerName = deliveryPartnerName;
            return this;
        }

        public Builder deliveryPartnerLocationDetails(Coordinate deliveryPartnerCoordinate) {
            this.deliveryPartnerCoordinate = deliveryPartnerCoordinate;
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
