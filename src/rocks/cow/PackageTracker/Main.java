package rocks.cow.PackageTracker;

import org.reflections.Reflections;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Package.PackageManager.PackageManager;
import rocks.cow.PackageTracker.Tracker.TrackerBase.Tracker;
import rocks.cow.PackageTracker.Tracker.TrackingInfo.TrackingInfo;
import rocks.cow.PackageTracker.Tracker.TrackingManager.TrackingManager;
import rocks.cow.PackageTracker.Util.Package.PackageReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        Reflections reflections = new Reflections("rocks.cow.PackageTracker");

        PackageManager packages = new PackageManager();
        packages.addAll(new PackageReader().loadPackages("./test.json"));

        TrackingManager tracker = new TrackingManager(reflections.getSubTypesOf(Tracker.class));

        // packages.addNew("Fluffy", "9611804140247301704689", CarrierType.FEDEX);
        // packages.addNew("luffy2", "9611804140247301704689", CarrierType.FEDEX);
        //
        // packages.savePackages("./test.json");

        Optional<ArrayList<Package>> optPackageList = packages.get("fluffy");

        if (optPackageList.isPresent()) {
            ArrayList<Package> packageList = optPackageList.get();

            for (Package pack : packageList) {
                System.out.println(pack.getDescription() + ":");

                Optional<TrackingInfo> packageInfo = tracker.track(pack);

                if (!packageInfo.isPresent()) {
                    continue;
                }

                System.out.println(packageInfo.get().getTimes());
                System.out.println(packageInfo.get().getStatus());
                System.out.println(packageInfo.get().getLocations());
            }
        }
    }
}
