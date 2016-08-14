package rocks.cow.PackageTracker.Commands;

import rocks.cow.PackageTracker.Package.Carrier.Carrier;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Package.PackageManager.PackageManager;

import java.util.Optional;

public class Commands {
    private PackageManager packageManager = new PackageManager();

    public boolean add(Package p) {
        return packageManager.add(p);
    }
    public boolean add (String description, String trackingID, Carrier carrier) {
        return add(new Package(description, trackingID, carrier));
    }
    public boolean add (String description, String trackingID, String carrierType) {
        // Optional<CarrierType> carrierTypeOptional = CarrierType.getType(carrierType);
        // if (carrierTypeOptional.isPresent()) {
        //     return add(new Package(description, trackingID, carrierTypeOptional.get()));
        // }
        return false;

    }

    public boolean remove (Package p) {
        return packageManager.remove(p.getTrackingId());
    }
}
