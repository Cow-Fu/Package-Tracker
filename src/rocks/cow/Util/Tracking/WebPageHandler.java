package rocks.cow.Util.Tracking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebPageHandler {
    private WebDriver webDriver;

    public WebPageHandler() {
        this(new HtmlUnitDriver());
    }

    public WebPageHandler (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void setDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver loadPageSource(WebDriver webDriver) {
        return webDriver;
    }

    public String getPageSource(String url) {
        String src = "";
        try {
            webDriver.get(url);

            webDriver = loadPageSource(webDriver);
            src = webDriver.getPageSource();
            webDriver.close();
        }
        catch (Exception e){
            webDriver.close();
        }
        return src;
    }
}
