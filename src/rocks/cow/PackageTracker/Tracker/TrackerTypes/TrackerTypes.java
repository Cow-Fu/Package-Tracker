package rocks.cow.PackageTracker.Tracker.TrackerTypes;

import org.reflections.Reflections;
import rocks.cow.PackageTracker.Tracker.TrackerBase.Tracker;

import java.util.Set;

public class TrackerTypes {
    private Set<Class<? extends Tracker>> trackers;
    private static String trackerDir = "rocks.cow.PackageTracker.Tracker.Trackers";

    public TrackerTypes () {
        update();
    }

    public TrackerTypes (String trackerUrl) {
        update(trackerUrl);
    }

    public void update() {
        update(trackerDir);

    }

    public void update(String trackerDir) {
        trackers = new Reflections(trackerDir).getSubTypesOf(Tracker.class);
    }

    public Set<Class<? extends Tracker>> getTrackers() {
        return trackers;
    }
}
