package rocks.cow.Tracker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import rocks.cow.Package.Package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


public interface Tracker {
    ArrayList<String> dateTime = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> location = new ArrayList<>();

    HashMap<String, ArrayList<? extends String>> track(Package p);

    default ArrayList<String> cleanUp(ArrayList<String> list) {
        return null;
    }

    default WebDriver loadpageSource(WebDriver webDriver) {
        return webDriver;
    }

    default String getPageSource(String url) {
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get(url);

        webDriver = this.loadpageSource(webDriver);

        String src = webDriver.getPageSource();
        webDriver.close();

        return src;
    }

    static ArrayList<String> fillBlanks(ArrayList<String> list) {
        ArrayList<String> temp = new ArrayList<>();
        Pattern emptyStr = Pattern.compile("^([ ]*)$");

        for (int i = 0; i < list.size() - 1; ++i) {
            if (emptyStr.matcher(list.get(i)).find()) {
                temp.add(temp.get(i - 1));
                continue;
            }
            temp.add(list.get(i));
        }
        return temp;
    }
}