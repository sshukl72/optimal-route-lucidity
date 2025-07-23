package com.lucidity.orderoptimizer.model.geo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CurrentLocationDetails {

    private Double time;
    private Coordinate currentCoordinate;
    private Set<String> picked = new HashSet<>();
    private Set<String> delivered = new HashSet<>();

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Coordinate getCurrentLocation() {
        return currentCoordinate;
    }

    public void setCurrentLocation(Coordinate currentCoordinate) {
        this.currentCoordinate = currentCoordinate;
    }


    public Set<String> getPicked() {
        return picked;
    }

    public Set<String> getDelivered() {
        return delivered;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentLocationDetails that)) return false;
        return Objects.equals(time, that.time) && Objects.equals(currentCoordinate, that.currentCoordinate) && Objects.equals(picked, that.picked) && Objects.equals(delivered, that.delivered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, currentCoordinate, picked, delivered);
    }

    @Override
    public String toString() {
        return "DeliveryState{" +
                "time=" + time +
                ", currentLocation=" + currentCoordinate +
                ", picked=" + picked +
                ", delivered=" + delivered +
                '}';
    }
}
