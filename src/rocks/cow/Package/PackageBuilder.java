package rocks.cow.Package;

import rocks.cow.Package.Carrier.CarrierType;

import java.util.Optional;


class PackageBuilder {
    private String id;
    private String trackingNum;
    private CarrierType carrier;

    public PackageBuilder setAll(String id, String trackingNum, String carrier) {
        return this.setId(id).setTrackingNum(trackingNum).setCarrier(carrier);
    }

    public PackageBuilder setAll(String id, String trackingNum, CarrierType carrier) {
        return this.setId(id).setTrackingNum(trackingNum).setCarrier(carrier);
    }


    public PackageBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public PackageBuilder setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
        return this;
    }

    public PackageBuilder setCarrier(String carrier) {
        Optional<CarrierType> carrierType = CarrierType.getType(carrier);
        if (!carrierType.isPresent()) {
            throw new Error("Not A Valid Carrier!");
        }

        return this.setCarrier(carrierType.get());
    }

    public PackageBuilder setCarrier(CarrierType carrier) {
        this.carrier = carrier;
        return this;
    }

    public Package createPackage() {
        return new Package(id, trackingNum, carrier);
    }
}