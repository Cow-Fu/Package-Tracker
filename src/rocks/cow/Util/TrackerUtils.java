package rocks.cow.Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TrackerUtils {

    protected ArrayList<String> fillBlanks(ArrayList<String> list) {
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

    protected WebDriver loadpageSource(WebDriver webDriver) {
        return webDriver;
    }

    protected String getPageSource(String url) {
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get(url);

        webDriver = this.loadpageSource(webDriver);

        String src = webDriver.getPageSource();
        webDriver.close();

        return src;
    }
}
