package rocks.cow.Tracker.TrackingManager;

import rocks.cow.Package.Carrier.CarrierType;
import rocks.cow.Package.Package;
import rocks.cow.Tracker.TrackerBase.Track;
import rocks.cow.Tracker.TrackingInfo.TrackingInfo;

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
