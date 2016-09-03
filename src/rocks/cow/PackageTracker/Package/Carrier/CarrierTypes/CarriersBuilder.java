package rocks.cow.PackageTracker.Package.Carrier.CarrierTypes;

import org.reflections.Reflections;
import rocks.cow.PackageTracker.Tracker.TrackerBase.Tracker;

import java.util.Set;

public class CarriersBuilder {
    private Carriers carriers = new Carriers();
    private static final String defaultTrackerDir = "rocks.cow.PackageTracker.Tracker.Trackers";

    public CarriersBuilder() {
        this(defaultTrackerDir);
    }

    public CarriersBuilder(String trackerDir) {
        update(trackerDir);
    }

    public Carriers getCarriers() {
        return carriers;
    }

    public void update () {
        update(defaultTrackerDir);
    }

    public void update (String trackerDir) {
        Set<Class<? extends Tracker>> trackers = new Reflections(trackerDir).getSubTypesOf(Tracker.class);
        Tracker t;

        for (Class<? extends Tracker> tracker: trackers) {
            try {
                t = tracker.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }

            if (!carriers.contains(t.getCarrierInfo())) {
                carriers.add(t.getCarrierInfo());
            }
        }
    }
}
