package rocks.cow.Tracker.TrackerBase;

import rocks.cow.Package.Package;
import rocks.cow.Tracker.TrackingInfo.TrackingInfo;


public interface Track {
    TrackingInfo track(Package p);
}