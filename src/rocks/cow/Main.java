package rocks.cow;

import rocks.cow.Package.Carrier.CarrierType;
import rocks.cow.Package.Package;
import rocks.cow.Tracker.TrackingManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;


public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException {
       java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
       System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        Package p = new Package("Fluffy", "1Z7R0E530201047672", CarrierType.UPS);

        HashMap<String, ArrayList<? extends String>> packageInfo = TrackingManager.track(p);

        System.out.println(packageInfo.get("dateTime"));
        System.out.println(packageInfo.get("status"));
        System.out.println(packageInfo.get("location"));
    }
}
