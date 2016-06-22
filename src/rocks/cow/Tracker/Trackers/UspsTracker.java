package rocks.cow.Tracker.Trackers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import rocks.cow.Package.Package;
import rocks.cow.Tracker.Tracker;
import rocks.cow.Util.Tracking.TrackerUtils;

import java.util.ArrayList;
import java.util.HashMap;

public final class UspsTracker implements Tracker {
    @Override
    public HashMap<String, ArrayList<? extends String>> track(Package p) {

        Document doc = Jsoup.parse(getPageSource(p.getCarrier().getUrl() + p.getTrackingNum()));

        Elements e = doc.body().select("tbody.details");

        e.select("td.date-time").forEach(date -> dateTime.add(date.text()));
        e.select("td.status").forEach(stat -> status.add(stat.text()));
        e.select("td.location").forEach(loc -> location.add(loc.text()));

        HashMap<String, ArrayList<? extends String>> dataMap = new HashMap<>();

        dataMap.put("dateTime", dateTime);
        dataMap.put("location", TrackerUtils.fillBlanks(location));
        dataMap.put("status", status);

        return dataMap;
    }
}