package rocks.cow.Tracker.TrackerBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import rocks.cow.Tracker.TrackingInfo.TrackingInfo;


public abstract class Tracker implements Track {
    protected TrackingInfo trackingInfo = new TrackingInfo();

    protected String getPageSource(String url) {
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get(url);

        String src = webDriver.getPageSource();
        webDriver.quit();

        return src;
    }
}