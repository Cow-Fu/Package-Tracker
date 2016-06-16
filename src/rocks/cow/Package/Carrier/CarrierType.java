package rocks.cow.Package.Carrier;

import rocks.cow.Tracker.Tracker;
import rocks.cow.Tracker.Trackers.FedexTracker;
import rocks.cow.Tracker.Trackers.UpsTracker;
import rocks.cow.Tracker.Trackers.UspsTracker;

import java.util.Optional;

public enum CarrierType {
    UPS(
            "UPS",
            "https://wwwapps.ups.com/WebTracking/track?loc=en_US&track.x=Track&trackNums=",
            UpsTracker.class
    ),

    USPS(
            "USPS",
            "https://tools.usps.com/go/TrackConfirmAction?qtc_tLabels1=",
            UspsTracker.class
    ),

    FEDEX(
            "FEDEX",
            "https://www.fedex.com/apps/fedextrack/?action=track&action=track&tracknumber_list=",
            FedexTracker.class
    );

    private String id;
    private String url;
    private Class<? extends Tracker> trackingClass;


    CarrierType(String id, String url, Class<? extends Tracker> trackingClass) {
        this.id = id;
        this.url = url;
        this.trackingClass = trackingClass;
    }

    public String getID() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }

    public Class<? extends Tracker> getTrackingClass() {
        return this.trackingClass;
    }

    public static Optional<CarrierType> getType(String id) {
        for (CarrierType carrierType: CarrierType.values()) {
            if (carrierType.getID().equals(id.toUpperCase())) {
                return Optional.of(carrierType);
            }
        }
        return Optional.empty();
    }
}




