package com.lucidity.orderoptimizer.model.geo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CurrentLocationDetails {

    private Double time;
    private LocationDetails currentLocationDetails;
    private Set<String> picked = new HashSet<>();
    private Set<String> delivered = new HashSet<>();

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public LocationDetails getCurrentLocation() {
        return currentLocationDetails;
    }

    public void setCurrentLocation(LocationDetails currentLocationDetails) {
        this.currentLocationDetails = currentLocationDetails;
    }


    public Set<String> getPicked() {
        return picked;
    }

    public void setPicked(Set<String> picked) {
        this.picked = picked;
    }

    public Set<String> getDelivered() {
        return delivered;
    }

    public void setDelivered(Set<String> delivered) {
        this.delivered = delivered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentLocationDetails that)) return false;
        return Objects.equals(time, that.time) && Objects.equals(currentLocationDetails, that.currentLocationDetails) && Objects.equals(picked, that.picked) && Objects.equals(delivered, that.delivered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, currentLocationDetails, picked, delivered);
    }

    @Override
    public String toString() {
        return "DeliveryState{" +
                "time=" + time +
                ", currentLocation=" + currentLocationDetails +
                ", picked=" + picked +
                ", delivered=" + delivered +
                '}';
    }
}
