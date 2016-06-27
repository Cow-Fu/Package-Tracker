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
import rocks.cow.Tracker.TrackerBase.Tracker;
import rocks.cow.Tracker.TrackingInfo.TrackingInfo;
import rocks.cow.Util.Tracking.TrackerUtils;

public final class UpsTracker extends Tracker {

    @Override
    public String getPageSource(String url) {
        HtmlUnitDriver webDriver = new HtmlUnitDriver();
        webDriver.setJavascriptEnabled(true);

        webDriver.get(url);
        webDriver.findElement(By.cssSelector("h4.btnlnkL")).click();

        Wait<WebDriver> wait = new WebDriverWait(webDriver, 10);
        wait.until(webDriver1 -> webDriver.executeScript("return document.readyState").equals("complete"));

        String src = webDriver.getPageSource();
        webDriver.quit();
        return src;
    }

    public TrackingInfo track(Package p) {

        Document doc = Jsoup.parse(getPageSource(p.getCarrier().getUrl() + p.getTrackingNum()));

        Elements element = doc.body().select("table.dataTable").select("tbody").first().children();
        element.remove(0);

        for (Element element1 : element) {
            Elements e = element1.children();

            trackingInfo.addTime(String.format("%s %s", e.get(1).text(), e.get(2).text()));
            trackingInfo.addStatus(e.get(3).text());
            trackingInfo.addLocation(e.get(0).text());
        }

        trackingInfo.setLocations(TrackerUtils.fillBlanks(trackingInfo.getLocations()));

        return trackingInfo;
    }
}
