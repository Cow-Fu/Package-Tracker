package rocks.cow.PackageTracker;

import rocks.cow.PackageTracker.Package.Carrier.Carrier;
import rocks.cow.PackageTracker.Package.Carrier.CarrierTypes.CarrierTypes;
import rocks.cow.PackageTracker.Package.Carrier.CarrierTypes.CarrierTypesBuilder;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Package.PackageManager.PackageManager;
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

        CarrierTypes carriers = new CarrierTypesBuilder().getCarriers();
        PackageManager packages = new PackageManager();

        // packages.addAll(new PackageReader().loadPackages("./test.json"));

        TrackingManager tracker = new TrackingManager();
        Optional<Carrier> fedex = carriers.getById("fedex");
        if (fedex.isPresent()) {
            packages.addNew("Fluffy", "9611804140247301704689", fedex.get());
            packages.addNew("luffy2", "9611804140247301704689", fedex.get());
            packages.savePackages("./test.json");

        }


        Optional<ArrayList<Package>> optPackageList = packages.get("fluffy");

        if (optPackageList.isPresent()) {
            ArrayList<Package> packageList = optPackageList.get();

            for (Package pack : packageList) {
                System.out.println(String.format("Fetching latest information for package \"%s\":",
                        pack.getDescription()));

                Optional<TrackingInfo> packageInfo = tracker.track(pack);

                if (!packageInfo.isPresent()) {
                    System.out.println("Unable to track package!");
                    continue;
                }

                System.out.println(packageInfo.get().getTimes());
                System.out.println(packageInfo.get().getStatus());
                System.out.println(packageInfo.get().getLocations());
            }
        }
    }
}
