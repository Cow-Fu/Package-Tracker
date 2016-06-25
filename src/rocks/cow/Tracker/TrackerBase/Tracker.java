package rocks.cow.Tracker.TrackerBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;

public abstract class Tracker implements Track{
    protected ArrayList<String> dateTime = new ArrayList<>();
    protected ArrayList<String> status = new ArrayList<>();
    protected ArrayList<String> location = new ArrayList<>();


    protected String getPageSource(String url) {
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get(url);

        String src = webDriver.getPageSource();
        webDriver.quit();

        return src;
    }
}