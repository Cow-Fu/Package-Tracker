package rocks.cow.Tracker;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import rocks.cow.Package.Package;

import java.util.ArrayList;
import java.util.HashMap;


public interface Tracker {
    ArrayList<String> dateTime = new ArrayList<String>();
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> location = new ArrayList<>();

    HashMap<String, ArrayList<? extends String>> track(Package p);

    default ArrayList<String> cleanUpDateTime(ArrayList<String> dateTime) {
        return null;
    }

    default ArrayList<String> cleanUpStatus(ArrayList<String> status) {
        return null;
    }

    default ArrayList<String> cleanUpLoc(ArrayList<String> loc) {
        return null;
    }

    default String getPageSource(String url) {
        HtmlUnitDriver webDriver = new HtmlUnitDriver();
        webDriver.setJavascriptEnabled(true);

        webDriver.get(url);
        String src = webDriver.getPageSource();
        webDriver.close();

        return src;
    }
}