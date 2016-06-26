package rocks.cow.Tracker.Trackers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rocks.cow.Package.Package;
import rocks.cow.Tracker.TrackerBase.Tracker;
import rocks.cow.Tracker.TrackingInfo.TrackingInfo;
import rocks.cow.Util.Tracking.TrackerUtils;

public final class UspsTracker extends Tracker {
    @Override
    public TrackingInfo track(Package p) {

        Document doc = Jsoup.parse(getPageSource(p.getCarrier().getUrl() + p.getTrackingNum()));

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