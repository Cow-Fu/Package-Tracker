package rocks.cow.PackageTracker.Tracker.TrackingManager;

import rocks.cow.PackageTracker.Package.Carrier.Carrier;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Tracker.TrackerBase.Tracker;
import rocks.cow.PackageTracker.Tracker.TrackingInfo.TrackingInfo;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class TrackingManager {
    private HashMap<String, Class<Carrier>> indexedTrackers = new HashMap<>();

    public TrackingManager () {}

    public TrackingManager (Set<Class<? extends Tracker>> trackers) {
        setTrackers(trackers);
    }

    public HashMap<String, Class<Carrier>> getCarriers() {
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

            if (!indexedTrackers.containsKey(trackerInst.getId())) {
                indexedTrackers.put(trackerInst.getId(), trackerInst.getClass());
            }
        }
    }

    public Optional<TrackingInfo> track(Package p) {
        Optional<TrackingInfo> info = Optional.empty();
        Optional<Tracker> tracker = Optional.empty();

        try {
            tracker = Optional.of(indexedTrackers.get(p.getCarrier()).newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (tracker.isPresent()) {
            info = Optional.of(tracker.get().track(p));
        }
        return info;
    }
}
