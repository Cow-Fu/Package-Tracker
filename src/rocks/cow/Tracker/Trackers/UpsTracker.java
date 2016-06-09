package rocks.cow.Tracker.Trackers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import rocks.cow.Package.Package;
import rocks.cow.Tracker.Tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


public class UpsTracker implements Tracker {

    // TODO: 6/8/16 ask dark why this causes an out of mem error
    // @Override
    // public ArrayList<String> cleanUpLoc(ArrayList<String> loc) {
    //     Pattern emptyStr = Pattern.compile("^([ ]*)$");
    //
    //     for (int i = 0; i < loc.size() - 1; ++i) {
    //         if (emptyStr.matcher(loc.get(i)).find()) {
    //             loc.add(i, loc.get(i - 1)); // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    //         }
    //     }
    //     return loc;
    // }

    @Override
    public ArrayList<String> cleanUpLoc(ArrayList<String> loc) {
        ArrayList<String> temp = new ArrayList<>();
        Pattern emptyStr = Pattern.compile("^([ ]*)$");

        for (int i = 0; i < loc.size() - 1; ++i) {
            if (emptyStr.matcher(loc.get(i)).find()) {
                temp.add(temp.get(i - 1));
                continue;
            }
            temp.add(loc.get(i));
        }
        return temp;
    }

    @Override
    public String getPageSource(String url) {
        HtmlUnitDriver webDriver = new HtmlUnitDriver();
        webDriver.setJavascriptEnabled(true);

        webDriver.get(url);

        webDriver.findElement(By.cssSelector("h4.btnlnkL")).click();

        new WebDriverWait(webDriver, 10);

        String src = webDriver.getPageSource();
        webDriver.close();

        return src;
    }

    public HashMap<String, ArrayList<? extends String>> track(Package p) {
        ArrayList<Element> elementList = new ArrayList<>();
        Elements element;

        Document doc = Jsoup.parse(getPageSource(p.getCarrier().getUrl() + p.getTrackingNum()));
        element = doc.body().select("table.dataTable").select("tbody");
        // System.out.println(element.html());
        element = element.first().children();
        element.remove(0);

        for (Element element1: element) {
            Elements e = element1.children();

            dateTime.add(String.format("%s %s", e.get(1).text(), e.get(2).text()));
            status.add(e.get(3).text());

            if (e.get(0).text().equals(" ")) {
                location.add("N/A");
                continue;
            }
            location.add(e.get(0).text());
        }

        HashMap<String, ArrayList<? extends String>> dataMap = new HashMap<>();

        dataMap.put("dateTime", dateTime);
        dataMap.put("location", cleanUpLoc(location));
        dataMap.put("status", status);

        return dataMap;
    }
}


