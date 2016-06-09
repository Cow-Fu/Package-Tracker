package rocks.cow.Tracker.Trackers;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import rocks.cow.Package.Package;
import rocks.cow.Tracker.Tracker;
import com.gargoylesoftware.htmlunit.WebClient;

import java.util.ArrayList;
import java.util.HashMap;

public class UspsTracker implements Tracker {

            @Override
            public HashMap<String, ArrayList<? extends String>> track(Package p) {
                HtmlPage page;
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

                packageInfo.put("dateTime", dates);
                packageInfo.put("status", status);
                packageInfo.put("location", locations);

                return packageInfo;
            }
        }
    }
}
