package rocks.cow.PackageTracker.Tracker.TrackingManager;

import rocks.cow.PackageTracker.Package.Carrier.CarrierType;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Tracker.TrackerBase.Track;
import rocks.cow.PackageTracker.Tracker.TrackingInfo.TrackingInfo;

import java.util.Optional;

public class TrackingManager {

    public static TrackingInfo track(Package p) {
        Optional<Track> tracker = Optional.empty();

        for (CarrierType type : CarrierType.values()) {
            if (p.getCarrier().equals(type)) {
                try {
                    tracker = Optional.of(type.getTrackingClass().newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        if (!tracker.isPresent()) {
            throw new NullPointerException("Unable to find tracking class");
        }

        return tracker.get().track(p);
    }

}
