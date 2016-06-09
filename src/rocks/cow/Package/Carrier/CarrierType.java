package rocks.cow.Package.Carrier;

import rocks.cow.Tracker.Tracker;
import rocks.cow.Tracker.Trackers.UpsTracker;

import java.util.Optional;

public enum CarrierType {
    UPS(
            "UPS",
            "https://wwwapps.ups.com/WebTracking/processInputRequest?&sort_by=status&tracknums_displayed=1&TypeOfInquiryNumber=T&loc=en_US&InquiryNumber1=",
            UpsTracker.class
    ),

    USPS(
            "USPS",
            "https://tools.usps.com/go/TrackConfirmAction?qtc_tLabels1="
    ),

    FEDEX(
            "FEDEX",
            "Fillter"
    );

    private String id;
    private String url;
    private Class<? extends Tracker> aClass;

    @Deprecated  // will be removed soon
    CarrierType(String id, String url) {
        this.id = id;
        this.url = url;
        this.aClass = null;
    }

    CarrierType(String id, String url, Class<? extends Tracker> aClass) {
        this.id = id;
        this.url = url;
        this.aClass = aClass;
    }

    public String getID() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
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




