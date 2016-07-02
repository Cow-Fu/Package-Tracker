package rocks.cow.PackageTracker.Tracker.Trackers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Tracker.TrackerBase.Tracker;
import rocks.cow.PackageTracker.Tracker.TrackingInfo.TrackingInfo;
import rocks.cow.PackageTracker.Util.Tracking.TrackerUtils;

public final class UspsTracker extends Tracker {
    @Override
    public TrackingInfo track(Package p) {

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