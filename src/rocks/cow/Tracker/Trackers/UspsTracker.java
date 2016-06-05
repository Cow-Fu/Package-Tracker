package rocks.cow.Tracker.Trackers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import rocks.cow.Package.Package;
import rocks.cow.Tracker.Tracker;

import java.util.ArrayList;
import java.util.HashMap;

public class UspsTracker implements Tracker {

    @Override
    public HashMap<String, ArrayList<? extends String>> track(Package p) {
        HtmlPage page;

        ArrayList<String> dates;
        ArrayList<String> status;
        ArrayList<String> locations;

        HashMap<String, ArrayList<? extends String>> packageInfo = new HashMap<>();

        Document doc = null;
        Elements e;

        String url = p.getCarrier().getUrl() + p.getTrackingNum();

        try (final WebClient webClient = new WebClient()) {
            page = webClient.getPage(url);

            doc = Jsoup.parse(page.asXml());
        } catch (Exception ex) {
            ex.getMessage();
            ex.getCause();
            ex.printStackTrace();
        }

        if (doc == null) {
            throw new NullPointerException("'doc' has not been initialized!");
        }

        e = doc.body().getElementsByClass("details");


        // gather all information
        dates = Tracker.fillBlanks(Tracker.textFromElements(e.select(".date-time")));
        status = Tracker.fillBlanks(Tracker.textFromElements(e.select(".status")));
        locations = Tracker.fillBlanks(Tracker.textFromElements(e.select(".location")));

        dates.forEach(date -> date.replace(" ,", ","));

        packageInfo.put("date-time", dates);
        packageInfo.put("status", status);
        packageInfo.put("locations", locations);

        return packageInfo;
    }
}
