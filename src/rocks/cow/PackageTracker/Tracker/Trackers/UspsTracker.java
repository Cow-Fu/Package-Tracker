package rocks.cow.PackageTracker.Tracker.Trackers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import rocks.cow.PackageTracker.Package.Carrier.Carrier;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Tracker.TrackerBase.Tracker;
import rocks.cow.PackageTracker.Tracker.TrackingInfo.TrackingInfo;
import rocks.cow.PackageTracker.Util.Tracking.TrackerUtils;

public final class UspsTracker implements Tracker {
    private String getPageSource (String url) {
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get(url);

        String src = webDriver.getPageSource();
        webDriver.quit();

        return src;
    }

    public Carrier getCarrierInfo() {
        return new Carrier()
                .setId("USPS")
                .setUrl("https://tools.usps.com/go/TrackConfirmAction?qtc_tLabels1=")
                .setTracker(this.getClass());
    }

    public TrackingInfo track(Package p) {
        TrackingInfo trackingInfo = new TrackingInfo();

        Document doc = Jsoup.parse(getPageSource(p.getCarrier().getUrl() + p.getTrackingId()));

        Elements element = doc.body().select("tbody.details");

        for (Element e : element.select("td.date-time")) {
            trackingInfo.addTime(e.text());
        }

        for (Element e : element.select("td.status")) {
            trackingInfo.addStatus(e.text());
        }

        for (Element e : element.select("td.location")) {
            trackingInfo.addLocation(e.text());
        }

        trackingInfo.setLocations(TrackerUtils.fillBlanks(trackingInfo.getLocations()));

        return trackingInfo;
    }
}