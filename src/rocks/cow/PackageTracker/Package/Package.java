package rocks.cow.PackageTracker.Package;

import rocks.cow.PackageTracker.Package.Carrier.CarrierTypes.CarrierType;

public class Package {
    private String description;
    private String trackingId;
    private CarrierType carrier;

    public Package(String description, String trackingId, CarrierType carrier) {
        this.description = description;
        this.trackingId = trackingId;
        this.carrier = carrier;
    }

    public String getTrackingId() {
        return this.trackingId;
    }

    public Package setTrackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public Package setDescription(String description) {
        this.description = description;
        return this;
    }

    public CarrierType getCarrier() {
        return carrier;
    }

    public Package setCarrier(CarrierType carrier) {
        this.carrier = carrier;
        return this;
    }

    public Package setAll(String id, String trackingId, CarrierType carrier) {
        return this.setDescription(id).setTrackingId(trackingId).setCarrier(carrier);
    }

}
