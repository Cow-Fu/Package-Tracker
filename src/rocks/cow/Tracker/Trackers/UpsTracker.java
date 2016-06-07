package rocks.cow.Tracker.Trackers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import rocks.cow.Package.Package;
import rocks.cow.Tracker.Tracker;

import java.util.ArrayList;
import java.util.HashMap;

public class UpsTracker implements Tracker {
    public HashMap<String, ArrayList<? extends String>> track(Package p) {
        ArrayList<Element> elementList = new ArrayList<>();

        // create driver
        HtmlUnitDriver webDriver = new HtmlUnitDriver();
        // make sure javascript is enabled
        webDriver.setJavascriptEnabled(true);

        // open the url
        webDriver.get(p.getCarrier().getUrl() + p.getTrackingNum());

        // select appropriate element
        WebElement webElement = webDriver.findElement(By.className("module3"));

        // click button to load javascript
        webElement.findElement(By.cssSelector("h4.btnlnkL")).click();

        // wait for javascript
        new WebDriverWait(webDriver, 10);

        // reassign var element to appropriate element
        webElement = webDriver.findElement(By.className("module3"));

        // parse the inner html for use with Jsoup

        Document doc = Jsoup.parse(webElement.findElement(By.tagName("table")).getAttribute("innerHtml"));

        // close webdriver
        webDriver.close();

        // get the first (and only) element
        Elements element = doc.body().child(0).child(0).children();
        element.remove(0);


        // added each element to our list of elements
        for (Element element1: element) {
            elementList.add(element1);
        }

        return new HashMap<>();

    }
}


