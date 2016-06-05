package rocks.cow.Package.Carrier;

import java.util.Optional;

public enum CarrierType {
    UPS("UPS", "https://wwwapps.ups.com/WebTracking/processInputRequest?&sort_by=status&tracknums_displayed=1&TypeOfInquiryNumber=T&loc=en_US&InquiryNumber1="),
    USPS("USPS", "https://tools.usps.com/go/TrackConfirmAction?qtc_tLabels1="),
    FEDEX("FEDEX", "Fillter");

    private String id;
    private String url;

    CarrierType(String id, String url) {
        this.id = id;
        this.url = url;
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




