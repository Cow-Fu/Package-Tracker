package rocks.cow.Tracker;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJob;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;
import com.gargoylesoftware.htmlunit.javascript.host.event.*;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rocks.cow.Package.Package;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UpsTracker implements Tracker {

    private static HtmlPage getFullyLoadedPage (String url) {
        HtmlPage page = null;
        JavaScriptJobManager manager = null;

        try (final WebClient webClient = new WebClient()){
            page = webClient.getPage(url);
            page.getBody().getElementsByAttribute("h4", "class", "expandIcon").forEach(element -> {
                try {
                    System.out.println("clicked " + element.getNodeName());
                    element.click();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

//            System.out.println(page.asXml());

            manager = page.getEnclosingWindow().getJobManager();
        } catch (Exception e) {
            System.out.println("Get page error");
        }

        if (manager == null) {
            throw new NullPointerException();
        }
        manager.waitForJobs(1000*15);
        while (manager.getJobCount() > 0) {
            System.out.println(manager.getJobCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(page.asXml());
        return page;
    }

    @Override
    public HashMap<String, ArrayList<? extends String>> track(Package p) {
        HtmlPage page = null;

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> status=  new ArrayList<>();
        ArrayList<String> locations = new ArrayList<>();

        HashMap<String, ArrayList<? extends String>> packageInfo = new HashMap<>();

        Document doc = null;
        Elements e;

        String url = p.getCarrier().getUrl() + p.getTrackingNum();
//        HtmlPage page = null;

        try (final WebClient webClient = new WebClient()){
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            webClient.waitForBackgroundJavaScript(10000);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            page = webClient.getPage(url);

            DomElement element = page.getElementById("collapse3").getFirstElementChild();
            element.click();


            for (int i = 0; i < 20; i++) {
                if (!element.getAttribute("class").equals("btnlnkL expandIcon")) {
                    break;
                }
                synchronized (page) {
                    page.wait(500);
                }
            }

            System.out.println(element.getAttribute("class"));

        } catch (Exception ex) {
            System.out.println("Get page error");
        }


        System.out.println(page.asXml());

        doc = Jsoup.parse(page.asXml());

//        doc = Jsoup.parse(getFullyLoadedPage(url).asXml());


//        try (final WebClient webClient = new WebClient()) {
//            page = webClient.getPage(url);
//
//            page.getElementById("collapse3").fireEvent("64");
//
////            HtmlForm form;
////
////            page.getBody().getElementsByAttribute("h4", "class", "expandIcon").forEach(element -> {
////                try {
////                    System.out.println("clicked " + element.getNodeName());
////                    element.click();
////                } catch (IOException e1) {
////                    e1.printStackTrace();
////                }
////            });
//
//            System.out.println("Rawr");
//
//            System.out.println(page.asXml());
//
//            doc = Jsoup.parse(page.asXml());
//        } catch (Exception ex) {
//            ex.getMessage();
//            ex.getCause();
//            ex.printStackTrace();
//        }

        if (doc == null) {
            throw new NullPointerException("'doc' has not been initialized!");
        }

//        e = doc.body().select(".dataTable").select("tbody");
////        System.out.println(e.html());
//
//        // FIXME: 6/3/16 change this to use selectors, using "get()" causes errors
//
//        for (Element element: e.select("td")) {
//            if (e.first().equals(element)) {
//                continue;
//            }
//
//            locations.add(element.child(0).text());
//            dates.add(String.format("%s, %s", element.child(1).text(), element.child(2).text()));
//            status.add(element.child(3).text());
//        }


        packageInfo.put("date-time", dates);
        packageInfo.put("status", status);
        packageInfo.put("locations", locations);

        return packageInfo;
    }
}
