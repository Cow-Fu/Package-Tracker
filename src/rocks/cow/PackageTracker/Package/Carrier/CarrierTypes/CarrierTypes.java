package rocks.cow.PackageTracker.Package.Carrier.CarrierTypes;

import rocks.cow.PackageTracker.Package.Carrier.Carrier;

import java.util.ArrayList;
import java.util.Optional;

public class CarrierTypes extends ArrayList<Carrier> {
    public Optional<Carrier> getById (String id) {
        id = id.toUpperCase();
        for (Carrier carrier : this) {
            if (carrier.getId().equals(id)) {
                return Optional.of(carrier);
            }
        }
        return Optional.empty();
    }
}
