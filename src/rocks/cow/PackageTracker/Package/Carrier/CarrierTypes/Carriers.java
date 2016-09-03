package rocks.cow.PackageTracker.Package.Carrier.CarrierTypes;

import rocks.cow.PackageTracker.Package.Carrier.Carrier;

import java.util.ArrayList;
import java.util.Optional;

public class Carriers extends ArrayList<Carrier> {
    public Optional<Carrier> getById (String id) {
        id = id.toUpperCase();
        for (Carrier carrier : this) {
            if (carrier.getId().equals(id)) {
                return Optional.of(carrier);
            }
        }
        return Optional.empty();
    }

    public CarrierTypes getCarrierTypes () {
        CarrierTypes carrierTypes = new CarrierTypes();
        this.forEach(item -> carrierTypes.add(new CarrierType(item.getId())));
        return carrierTypes;
    }
}
