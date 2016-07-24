package rocks.cow.PackageTracker.Commands;

import org.apache.regexp.RE;
import rocks.cow.PackageTracker.Package.Carrier.CarrierType;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Package.PackageManager.PackageManager;

import java.util.Optional;

public class Commands {
    private PackageManager packageManager = new PackageManager();

    public boolean add(Package p) {
        if (packageManager.add(p)) {
            return true;
        }
        return false;
    }
    public boolean add (String description, String trackingID, CarrierType carrierType) {
        return add(new Package(description, trackingID, carrierType));
    }
    public boolean add (String description, String trackingID, String carrierType) {
        Optional<CarrierType> carrierTypeOptional = CarrierType.getType(carrierType);
        if (carrierTypeOptional.isPresent()) {
            return add(new Package(description, trackingID, carrierTypeOptional.get()));
        }
        return false;
    }

    public boolean remove (Package p) {
        return packageManager.remove(p.getTrackingId());
    }
}
