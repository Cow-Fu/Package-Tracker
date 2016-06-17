package rocks.cow.Tracker;

import rocks.cow.Package.Carrier.CarrierType;
import rocks.cow.Package.Package;

import java.util.ArrayList;
import java.util.HashMap;

public class TrackingManager {

    public static HashMap<String, ArrayList<? extends String>> track(Package p) {
        Tracker tracker = null;

        for (CarrierType type : CarrierType.values()) {
            if (p.getCarrier().equals(type)) {
                try {
                    tracker = type.getTrackingClass().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        if (tracker == null) {
            throw new NullPointerException("Unable to find tracking class");
        }

        return tracker.track(p);
    }

}
