package rocks.cow.PackageTracker.Tracker.TrackingManager;

import org.reflections.Reflections;
import rocks.cow.PackageTracker.Package.Carrier.Carrier;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Tracker.TrackerBase.Tracker;
import rocks.cow.PackageTracker.Tracker.TrackingInfo.TrackingInfo;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class TrackingManager {
    private HashMap<String, Class<? extends Tracker>> indexedTrackers = new HashMap<>();

    public TrackingManager () {
    }

    public TrackingManager (Set<Class<? extends Tracker>> trackers) {
        setTrackers(trackers);
    }

    public HashMap<String, Class<? extends Tracker>> getCarriers() {
        return indexedTrackers;
    }

    public void setTrackers(Set<Class<? extends Tracker>> trackers) {
        indexTrackers(trackers);
    }

    private void indexTrackers(Set<Class<? extends Tracker>> trackers) {
        for (Class<? extends Tracker> tracker : trackers) {
            Tracker trackerInst = null;
            try {
                trackerInst = tracker.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
            String id = trackerInst.getCarrierInfo().getId();

            if (!indexedTrackers.containsKey(id)) {
                indexedTrackers.put(id, trackerInst.getClass());
            }
        }
    }

    public Optional<TrackingInfo> track(Package p) {
        return Optional.of(p.getCarrier().getTracker().track(p));
    }
}
