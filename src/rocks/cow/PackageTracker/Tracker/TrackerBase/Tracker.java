package rocks.cow.PackageTracker.Tracker.TrackerBase;

import rocks.cow.PackageTracker.Package.Carrier.Carrier;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Tracker.TrackingInfo.TrackingInfo;


public interface Tracker {
    Carrier getCarrierInfo();
    TrackingInfo track(Package p);
}