package rocks.cow.PackageTracker.Package;

import rocks.cow.PackageTracker.Package.Carrier.Carrier;

public class Package {
    private String description;
    private String trackingId;
    private Carrier carrier;

    public Package(String description, String trackingId, Carrier carrier) {
        this.description = description;
        this.trackingId = trackingId;
        this.carrier = carrier;
    }

    // getters

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

    //setters

    public Package setDescription(String description) {
        this.description = description;
        return this;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public Package setCarrier(Carrier carrier) {
        this.carrier = carrier;
        return this;
    }

    public Package setAll(String id, String trackingId, Carrier carrier) {
        return this.setDescription(id).setTrackingId(trackingId).setCarrier(carrier);
    }

}
