package rocks.cow.Package.Carrier;

import rocks.cow.Tracker.Track;
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
    private Class<? extends Track> trackingClass;


    CarrierType(String id, String url, Class<? extends Track> trackingClass) {
        this.id = id;
        this.url = url;
        this.trackingClass = trackingClass;
    }

    public static Optional<CarrierType> getType(String id) {
        for (CarrierType carrierType : CarrierType.values()) {
            if (carrierType.getID().equals(id.toUpperCase())) {
                return Optional.of(carrierType);
            }
        }
        return Optional.empty();
    }

    public String getID() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }

    public Class<? extends Track> getTrackingClass() {
        return this.trackingClass;
    }
}




