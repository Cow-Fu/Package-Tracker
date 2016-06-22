package rocks.cow.Tracker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import rocks.cow.Package.Package;

import java.util.ArrayList;
import java.util.HashMap;


public interface Tracker {
    ArrayList<String> dateTime = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> location = new ArrayList<>();

    HashMap<String, ArrayList<? extends String>> track(Package p);

    default String getPageSource(String url) {
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get(url);

        String src = webDriver.getPageSource();
        webDriver.quit();

        return src;
    }
}