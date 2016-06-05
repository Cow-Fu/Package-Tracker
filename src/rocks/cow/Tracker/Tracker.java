package rocks.cow.Tracker;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.xpath.SourceTree;
import org.jsoup.select.Elements;
import rocks.cow.Package.Package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public interface Tracker {

    static HtmlPage getFullyLoadedPage (String url) {
        HtmlPage page = null;
        JavaScriptJobManager manager = null;

        try (final WebClient webClient = new WebClient()){
            page = webClient.getPage(url);

            System.out.println(page.asXml());

            manager = page.getEnclosingWindow().getJobManager();
        } catch (Exception e) {
            System.out.println("Get page error");
        }

        if (manager == null) {
           throw new NullPointerException();
        }
        while (manager.getJobCount() > 0) {
            System.out.println(manager.getJobCount());
            manager.waitForJobs(1000*15);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(page.asXml());
        return page;
    }

    static ArrayList<String> fillBlanks (ArrayList<String> list) {
        Pattern r = Pattern.compile("^([^A-z0-9]*)$");

        for (int i = 0; i < list.size(); ++i) {
            if (r.matcher(list.get(i)).find()) {
                list.set(i, "N/A");
            }
        }
        return list;
    }

    static ArrayList<String> textFromElements(Elements elements) {
        ArrayList<String> list = new ArrayList<>();
        elements.forEach(element -> list.add(element.text()));
        return list;
    }
    HashMap<String, ArrayList<? extends String>> track (Package p) throws InterruptedException;
}
