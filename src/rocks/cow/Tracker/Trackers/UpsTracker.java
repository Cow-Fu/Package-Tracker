package rocks.cow.Tracker.Trackers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import rocks.cow.Package.Package;
import rocks.cow.Tracker.Tracker;
import rocks.cow.Util.Tracking.TrackerUtils;
import rocks.cow.Util.Tracking.WebPageHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class UpsTracker implements Tracker {
    private WebPageHandler webHandler;

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


    public HashMap<String, ArrayList<? extends String>> track(Package p) {

        WebPageHandler webHandler = new WebPageHandler() {
            @Override
            public WebDriver loadPageSource(WebDriver webDriver) {
                webDriver.findElement(By.cssSelector("h4.btnlnkL")).click();

                Wait<WebDriver> wait = new WebDriverWait(webDriver, 10);
                wait.until(webDriver1 -> ((HtmlUnitDriver) webDriver).executeScript("return document.readyState").equals("complete"));

                return webDriver;
            }
        };

        ((HtmlUnitDriver) webHandler.getDriver()).setJavascriptEnabled(true);

        Document doc = Jsoup.parse(webHandler.getPageSource(p.getCarrier().getUrl() + p.getTrackingNum()));


        // try {
        //     new FileWriter("ups.html").write(doc.html());
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        Elements element = doc.body().select("table.dataTable").select("tbody").first().children();
        element.remove(0);

        for (Element element1 : element) {
            Elements e = element1.children();

            dateTime.add(String.format("%s %s", e.get(1).text(), e.get(2).text()));
            status.add(e.get(3).text());
            location.add(e.get(0).text());
        }

        HashMap<String, ArrayList<? extends String>> dataMap = new HashMap<>();

        dataMap.put("dateTime", dateTime);
        dataMap.put("location", TrackerUtils.fillBlanks(location));
        dataMap.put("status", status);

        return dataMap;
    }
}
