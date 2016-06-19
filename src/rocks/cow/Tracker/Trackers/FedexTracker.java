package rocks.cow.Tracker.Trackers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rocks.cow.Package.Package;
import rocks.cow.Tracker.Tracker;
import rocks.cow.Util.TrackerUtils;

import java.util.ArrayList;
import java.util.HashMap;

public final class FedexTracker extends TrackerUtils implements Tracker {

    private final String javascript = "!function(){var e=e||1429342486,n=function(e,n,t){if(n=\"undefined\"!=typeof n?\"[\"+n+\"] \":\"\",t=\"undefined\"!=typeof t?\"[\"+t+\"] \":\"\",window.fdx_config&&fdx_config.debug&&fdx_config.debug.enabled===!0){window.console&&console.log?console.log(n+t+e):window.opera&&opera.postError&&opera.postError(n+t+e);try{if(fdx_config.debug.track&&fdx_config.debug.track===!0){var a=\"undefined\"!=typeof i(\"fdx_debug\")?i(\"fdx_debug\").split(\",\"):[],d={wwwsbox:\"L1S\",wwwbase:\"L1\",wwwdev:\"L2\",wwwdrt:\"L3\",wwwstress:\"L4\",wwwbit:\"L5\",wwwtest:\"L6\",www:\"P\"},c=d[document.location.hostname.split(\".\")[0]],r=\"undefined\"!=typeof c?c:document.location.hostname.split(\".\")[0],l=(new Date).getTime(),f=\"undefined\"!=typeof s&&s.pageName?s.pageName:document.location.href,u=l+\"|\"+n+t+e+\"|\"+r+\"|\"+f;a.push(u.replace(/,/g,\"##\")),o(\"fdx_debug\",a,1,\"/\",\".fedex.com\")}}catch(p){}}},t=function(){try{var e=window.location.protocol;return e.toLowerCase().indexOf(\"https\")>-1?\"https\":\"http\"}catch(t){n(t,\"CIM\",\"fdx_getProtocol\")}},i=function(e){try{for(var t,i={},o=document.cookie,a=o.split(\"; \"),d=0;d<a.length;d++)t=a[d].split(\"=\"),i[t[0]]=unescape(t[1]);return i[e]}catch(c){n(c,\"CIM\",\"fdx_getCookie\")}},o=function(e,t,i,o,a){try{if(null!==t){var d=escape(t);if(\"\"!==i){var c=new Date;c.setDate(c.getDate()+i),d+=null===i?\"\":\"; expires=\"+c.toUTCString()}d+=\"undefined\"!=typeof o&&null!==o?\"; path=\"+o:\"\",d+=\"undefined\"!=typeof a&&null!==a?\"; domain=\"+a:\"\",document.cookie=e+\"=\"+d}}catch(r){n(r,\"CIM\",\"fdx_setCookie\")}},a=function(e){try{e=e.replace(/[\\[]/,\"\\\\[\").replace(/[\\]]/,\"\\\\]\");var t=\"[\\\\?&]\"+e+\"=([^&#]*)\",i=new RegExp(t),o=i.exec(window.location.href);return null==o?\"\":o[1]}catch(a){n(a,\"CIM\",\"fdx_getUrlParam\")}},d=function(e,t,i,o,a){try{var d=document.createElement(\"script\");if(d.language=\"javascript\",d.type=\"text/javascript\",d.charset=\"utf-8\",(\"undefined\"==typeof a||a!==!0)&&(d.async=\"true\"),d.id=e,d.src=t,\"undefined\"!=typeof i&&null!==i&&\"function\"==typeof i&&(d.onloadDone=!1,d.onload=function(){d.onloadDone||(d.onloadDone=!0,i())},d.onreadystatechange=function(){\"loaded\"!==d.readyState&&\"complete\"!==d.readyState||d.onloadDone||(d.onloadDone=!0,i())}),\"undefined\"!=typeof o&&null!==o&&\"object\"==typeof o)try{for(var c in o)o.hasOwnProperty(c)&&d.setAttribute(c,o[c])}catch(r){n(r)}var l=document.getElementsByTagName(\"HEAD\").item(0);l.appendChild(d)}catch(r){n(r,\"CIM\",\"fdx_loadScript\")}},c=function(e){try{var t;return document.createEvent?(t=document.createEvent(\"Event\"),t.initEvent(e,!1,!1)):t=e,t}catch(i){n(i,\"CIM\",\"fdx_createCustomEvent\")}},r=function(e){try{document.createEvent?document.dispatchEvent(e):\"undefined\"!=typeof document.documentElement[e]?document.documentElement[e]++:document.documentElement[e]=0}catch(t){n(t,\"CIM\",\"fdx_dispatchCustomEvent\")}},l=function(e){try{var t=c(e);r(t)}catch(i){n(i,\"CIM\",\"fdx_fireCustomEvent\")}},f=function(){var e={},t=1;return{addListener:function(i,o,a,d){try{if(document.addEventListener){var c=function(){o(a)};return a.addEventListener(i,c,d),e[t]={eventName:i,handler:c,element:a,capture:d},t++}if(document.attachEvent){if(i.match(/click|dblclick|mousedown|mousemove|mouseup|mouseover|mouseout|keydown|keypress|keyup|submit|unload/g)){var c=function(){o(a)};a.attachEvent(\"on\"+i,c),e[t]={eventName:i,handler:c,element:a,capture:d}}else{var c=function(e){e.propertyName==i&&o()};a=document.documentElement,a.attachEvent(\"onpropertychange\",c),e[t]={eventName:\"onpropertychange\",handler:c,element:a,capture:d}}return t++}}catch(r){n(r,\"CIM\",\"fdx_eventHandler:addEventListener\")}},removeListener:function(t){try{if(t in e){var i=e[t];document.removeEventListener?i.element.removeEventListener(i.eventName,i.handler,i.capture):document.detachEvent&&i.element.detachEvent(i.eventName,i.handler),delete e[t]}}catch(o){n(o,\"CIM\",\"fdx_eventHandler:removeEventListener\")}}}}(),u=function(e,t,i,o){try{return i=i||document,o=o||!1,f.addListener(e,t,i,o)}catch(a){n(a,\"CIM\",\"fdx_subscribeToCustomEvent\")}},p=function(e,t){try{var i=(new Date).getTime();if(null!==e){var o=e.getTime();if(o>i)return!1}if(null!==t){var a=t.getTime();if(i>a)return!1}}catch(d){return n(d,\"CIM\",\"fdx_isLive\"),!1}return!0},m=function(){var e=[\"us\",\"en\",\"en\",\"us\",\"english\"];try{var t=window.fdx_config.languages||[],i=fdx_locale.split(\"_\")[0],o=fdx_locale.split(\"_\")[1].toLowerCase();e[0]=o,e[1]=i,e[2]=i,fdx_config.regions.us.indexOf(o)>-1?e[3]=\"us\":fdx_config.regions.CA.indexOf(o)>-1?e[3]=\"CA\":fdx_config.regions.LAC.indexOf(o)>-1?e[3]=\"LAC\":fdx_config.regions.EMEA.indexOf(o)>-1?e[3]=\"EMEA\":fdx_config.regions.APAC.indexOf(o)>-1&&(e[3]=\"APAC\"),\"zh_CN\"==fdx_locale?(e[4]=\"chinese_sim\",e[2]=\"zhs\"):\"zh_HK\"==fdx_locale?(e[4]=\"chinese_trad\",e[2]=\"zht\"):\"zh_TW\"==fdx_locale?(e[4]=\"chinese_trad_tw\",e[2]=\"zht\"):t[i]&&(e[4]=t[i])}catch(a){return n(a,\"CIM\",\"fdx_getRegLangInfo\"),e}return e},_=function(e){try{var t=m(),i=t[0],o=t[1],a=t[2],d=t[3],c=t[4];return e.replace(/\\$cc\\$/g,i).replace(/\\$lang_two\\$/g,o).replace(/\\$lang_three\\$/g,a).replace(\"$region$\",d).replace(\"$language$\",c)}catch(r){return n(r,\"CIM\",\"fdx_getRegLangInfo\"),e}},v=function(e,t,i,o){try{o=o||window,\"undefined\"!=typeof o[e]&&\"function\"==typeof o[e]?o[e].apply(this,i):u(t,function(){try{o[e].apply(this,i)}catch(t){n(t,\"CIM\",\"fdx_wireUpFunction:subscribed\")}})}catch(a){n(a,\"CIM\",\"fdx_wireUpFunction\")}},x={appRootPath:t()+\"://images.fedex.com/templates/components/apps/contentim\",setIM_Props:function(e,n){v(\"fdx_setIM_Props\",\"IMInitialized\",arguments,x)},makeSureJQuery:function(e,n){v(\"fdx_makeSureJQuery\",\"IMInitialized\",arguments,x)},loadIMScript:function(e,n){v(\"fdx_loadIMScript\",\"IMInitialized\",arguments,x)},processCurrentPage:function(e){v(\"fdx_processCurrentPage\",\"IMInitialized\",arguments,x)},log:function(e,n){v(\"fdx_log\",\"IMInitialized\",arguments,x)},setflashIsReady:function(e){v(\"fdx_setflashIsReady\",\"IMInitialized\",arguments,x)},getIsContainerReady:function(){v(\"fdx_getIsContainerReady\",\"IMInitialized\",arguments,x)},setIsContainerReady:function(){v(\"fdx_setIsContainerReady\",\"IMInitialized\",arguments,x)},getcookie:i,setCookie:o,getUrlParam:a,rotateCreatives:function(){v(\"fdx_rotateCreatives\",\"IMInitialized\",arguments,x)},im_build_dfa:function(e,n){v(\"fdx_im_build_dfa\",\"IMInitialized\",arguments,x)},extend:function(e,t){try{var i,o;for(var a in t)i=e[a],o=t[a],i!==o&&(e[a]=o);return e}catch(d){n(d,\"IM\",\"extend\")}},init:function(){try{window.IM&&!x.core&&n(i,\"IM\",\"init:IM.core-undefined\");var e=\"\",t=\"\";try{e=typeof x,Object.keys||(Object.keys=function(e){var n=[];for(var t in e)e.hasOwnProperty(t)&&n.push(t);return n}),t=Object.keys(x).length}catch(i){n(\"TYPE:\"+e+\"; SIZE:\"+t+\"; \"+i,\"IM\",\"init:sizeBefore\")}try{x.extend(x,x.core)}catch(i){n(i,\"IM\",\"init:IMExtendObject\")}try{x.initVars()}catch(i){var o=\"\";try{o=Object.keys(x).length}catch(i){n(i,\"IM\",\"init:sizeAfter\")}n(\"SB=\"+t+\";SA=\"+o+\";\"+i,\"IM\",\"init:IMinitVars\")}try{l(\"IMInitialized\"),x.processCurrentPage(!0)}catch(i){n(i,\"IM\",\"init:IMProcessCurrentPage\")}}catch(i){n(i,\"IM\",\"init\")}},initManifest:function(){window.imManifestInitialized=!0,l(\"IMManifestInitialized\")}},w=function(o){window.fdx_config=o,window.fdx_cbid=i(\"fdx_cbid\")||\"\",window.fdx_locale=i(\"fdx_locale\")||\"en_US\",window.fdx_locale_data=i(\"fdx_locale_data\")||\"\";try{window.fx_device_type=window.fx_device_type?fx_device_type:null}catch(a){window.fx_device_type=null,n(a,\"CIM\",\"fdx_initContentIM:fx_device_type\")}try{window.fdx_snap_point=window.fx_responsivedata?fx_responsivedata.split(\":\")[0]:null}catch(a){window.fdx_snap_point=null,n(a,\"CIM\",\"fdx_initContentIM:fdx_snap_point\")}try{window.fdx_is_disabled=fdx_config.fdx_is_disabled?fdx_config.fdx_is_disabled:!1;for(var c=0;c<o.isLive.length;c++){var r=o.isLive[c].id?o.isLive[c].id:null,l=o.isLive[c].disabledflag?o.isLive[c].disabledflag:null,s=o.isLive[c].disableddevices?o.isLive[c].disableddevices:null,f=o.isLive[c].disabledsnappoints?o.isLive[c].disabledsnappoints:null,u=o.isLive[c].starttime&&null!==o.isLive[c].starttime?new Date(fdx_config.isLive[c].starttime):null,m=o.isLive[c].stoptime&&null!==o.isLive[c].stoptime?new Date(o.isLive[c].stoptime):null;if(null!==r&&null!==l){if(window[l]=!p(u,m),null!==fx_device_type&&null!==s)try{var v=s.split(\",\");for(var x in v)if(v[x]===fx_device_type){window[l]=!0;break}}catch(a){n(a,\"CIM\",\"fdx_initContentIM:isLive:devices\")}if(null!==fdx_snap_point&&null!==f)try{var w=f.split(\",\");for(var g in w)if(w[g]===fdx_snap_point){window[l]=!0;break}}catch(a){n(a,\"CIM\",\"fdx_initContentIM:isLive:snaps\")}}}}catch(a){n(a,\"CIM\",\"fdx_initContentIM:isLive\")}try{for(var c=0;c<o.includes.length;c++){var r=o.includes[c].id?o.includes[c].id:null,h=o.includes[c].path?_(o.includes[c].path):null,y=o.includes[c].controller?o.includes[c].controller:null,l=o.includes[c].disabledflag?o.includes[c].disabledflag:null,I=o.includes[c].callback?o.includes[c].callback:null,M=o.includes[c].attributes?o.includes[c].attributes:null,C=o.includes[c].sync?o.includes[c].sync:null,b=o.includes[c].domains?o.includes[c].domains:null;if(null!==r&&null!==h&&(null==y||y==e)){if(null!==l&&window[l]===!0)continue;var L;if(null!==b)try{var E=b.split(\",\");L=!1;for(var k in E)window.location.hostname.indexOf(E[k])>-1&&(L=!0);if(!L)continue}catch(a){n(a,\"CIM\",\"fdx_initContentIM:includes:domains\")}h=fdx_min_off?h.replace(\"-min.js\",\".js\"):h,d(r,t()+\"://images.fedex.com\"+h,I,M,C)}}}catch(a){n(a,\"CIM\",\"fdx_initContentIM:includes\")}},g=function(){try{if(window.fdx_suppressController)return void(window.fdx_suppressController=!1);if(!window.fdx_config){window.fdx_config={},window.fdx_min_off=\"off\"===a(\"min\")||\"off\"===i(\"contentim-min\");var e=\"\",o=\"\";try{o=document.location.pathname.split(\"/\"),e=\"&refresh_uri=%2F\"+o[1]+\"%2F\"}catch(c){n(c,\"CIM\",\"fdx_loadContentIM:localeURIRefresh\")}var r=Math.round((new Date).getTime()/1e3),l=t()+\"://www.fedex.com/templates/components/apps/contentim/contentim-min.json\",s=t()+\"://www.fedex.com/templates/components/apps/contentim/contentim_internal-min.json\";l=fdx_min_off?l.replace(\"-min.json\",\".json\"):l,s=fdx_min_off?s.replace(\"-min.json\",\".json\"):s;try{null!==document.getElementById(\"contentim-internal\")?d(\"contentim_internal-json\",s+\"?cb=\"+r+e,null,null):d(\"contentim-json\",l+\"?cb=\"+r+e,null,null)}catch(c){n(c,\"CIM\",\"fdx_loadContentIM:internal-json\")}}}catch(c){n(c,\"CIM\",\"fdx_loadContentIM\")}};w(),g()}();";

    @Override
    public String getPageSource(String url) {
        HtmlUnitDriver webDriver = new HtmlUnitDriver() {
            @Override
            protected WebClient newWebClient(BrowserVersion version) {
                WebClient webClient = super.newWebClient(version);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                webClient.getOptions().setJavaScriptEnabled(true);
                webClient.getCookieManager().setCookiesEnabled(true);
                webClient.getOptions().setRedirectEnabled(true);
                webClient.waitForBackgroundJavaScript(3000);
                return webClient;
            }
        };
        // webDriver

        webDriver.setJavascriptEnabled(true);
        webDriver.get(url);

        // execute modified fedex script
        // the one the page executes isn't compatible with the Rhino javascript engine
        webDriver.executeScript(javascript);

        new WebDriverWait(webDriver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dp_travel_history_area")));

        String src = webDriver.getPageSource();
        webDriver.close();

        return src;
    }

    @Override
    public HashMap<String, ArrayList<? extends String>> track(Package p) {
        Document doc = Jsoup.parse(getPageSource(p.getCarrier().getUrl() + p.getTrackingNum()));

        Elements elements = doc.body().select(".dp_travel_history_area").select(".content_area").first().select("tr");

        boolean isFirstElement = true;

        for (Element element : elements) {
            if (isFirstElement) {
                isFirstElement = false;
                continue;
            }
            for (Element e : element.children()) {
                if (e.hasClass("date_time")) {
                    dateTime.add(e.text());
                    continue;
                }
                if (e.hasClass("location")) {
                    location.add(e.text());
                    continue;
                }
                if (e.hasClass("status")) {
                    status.add(e.text());
                }
            }
        }

        HashMap<String, ArrayList<? extends String>> dataMap = new HashMap<>();

        dataMap.put("dateTime", dateTime);
        dataMap.put("location", fillBlanks(location));
        dataMap.put("status", status);

        return dataMap;
    }
}
