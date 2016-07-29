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

    public Class<? extends Tracker> getTracker() {
        return tracker;
    }

    public Carrier setTracker(Class<? extends Tracker> tracker) {
        this.tracker = tracker;
        return this;
    }
}
