package rocks.cow.PackageTracker.Package;

public class Package {
    private String description;
    private String trackingId;
    private String carrier;

    public Package(String description, String trackingId, String carrier) {
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

    public String getCarrier() {
        return carrier;
    }

    public Package setCarrier(String carrier) {
        this.carrier = carrier;
        return this;
    }

    public Package setAll(String id, String trackingId, String carrier) {
        return this.setDescription(id).setTrackingId(trackingId).setCarrier(carrier);
    }

}
