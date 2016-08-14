package rocks.cow.PackageTracker.Package.Carrier;

import rocks.cow.PackageTracker.Tracker.TrackerBase.Tracker;

public class Carrier {
    private String id;
    private String url;
    private Class<? extends Tracker> tracker;

    public String getId() {
        return id;
    }

    public Carrier setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Carrier setUrl(String url) {
        this.url = url;
        return this;
    }

    public Tracker getTracker() {
        Tracker trackerInst = null;
        try {
            trackerInst = tracker.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return trackerInst;
    }

    public Carrier setTracker(Class<? extends Tracker> tracker) {
        this.tracker = tracker;
        return this;
    }
}
