package com.lucidity.orderoptimizer.model.geo;

import java.util.Objects;

public class LocationDetails {

    private final Double latitude;
    private final Double longitude;

    private LocationDetails(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }


    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationDetails locationDetails)) return false;
        return Objects.equals(latitude, locationDetails.latitude) &&
                Objects.equals(longitude, locationDetails.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static class Builder {
        private Double latitude;
        private Double longitude;

        public Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public LocationDetails build() {
            return new LocationDetails(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
