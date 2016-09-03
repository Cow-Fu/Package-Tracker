package rocks.cow.PackageTracker.Package.Carrier.CarrierTypes;

import rocks.cow.PackageTracker.Package.Carrier.Carrier;

public class CarrierType {
    private String id;

    public CarrierType() {}

    public CarrierType(String id) {
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
