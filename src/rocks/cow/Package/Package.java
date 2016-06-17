package rocks.cow.Package;


import rocks.cow.Package.Carrier.CarrierType;

import java.util.Optional;

public class Package {
    private String id;
    private String trackingNum;
    private CarrierType carrier;

    public Package(String id, String trackingNum, CarrierType carrier) {
        this.id = id;
        this.trackingNum = trackingNum;
        this.carrier = carrier;
    }

    // getters

    public String getTrackingNum() {
        return this.trackingNum;
    }

    public Package setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
        return this;
    }

    public String getId() {
        return this.id;
    }

    //setters

    public Package setId(String id) {
        this.id = id;
        return this;
    }

    public CarrierType getCarrier() {
        return carrier;
    }

    public Package setCarrier(CarrierType carrier) {
        this.carrier = carrier;
        return this;
    }

    public Package setAll(String id, String trackingNum, String carrier) {
        return this.setId(id).setTrackingNum(trackingNum).setCarrier(carrier);
    }

    public Package setAll(String id, String trackingNum, CarrierType carrier) {
        return this.setId(id).setTrackingNum(trackingNum).setCarrier(carrier);
    }

    public Package setCarrier(String carrier) {
        Optional<CarrierType> carrierType = CarrierType.getType(carrier);
        if (!carrierType.isPresent()) {
            throw new Error("Not A Valid Carrier!");
        }

        return this.setCarrier(carrierType.get());
    }
}
