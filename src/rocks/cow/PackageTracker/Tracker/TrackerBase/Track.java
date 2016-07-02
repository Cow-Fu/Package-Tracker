package rocks.cow.PackageTracker.Tracker.TrackerBase;

import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Tracker.TrackingInfo.TrackingInfo;


public interface Track {
    TrackingInfo track(Package p);
}