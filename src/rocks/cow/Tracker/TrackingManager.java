package rocks.cow.Tracker;

import rocks.cow.Package.Carrier.CarrierType;
import rocks.cow.Package.Package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class TrackingManager {

    public static HashMap<String, ArrayList<? extends String>> track(Package p) {
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
