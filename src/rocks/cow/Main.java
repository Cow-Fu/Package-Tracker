package rocks.cow;

import rocks.cow.Package.Carrier.CarrierType;
import rocks.cow.Package.Package;
import rocks.cow.Package.PackageManager;
import rocks.cow.Tracker.TrackingInfo.TrackingInfo;
import rocks.cow.Tracker.TrackingManager.TrackingManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;


public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        PackageManager packages = new PackageManager();

        packages.addNew("Fluffy", "9611804140247301704689", CarrierType.FEDEX);

        Optional<ArrayList<Package>> optPackageList = packages.get("fluffy");


        if (optPackageList.isPresent()) {
            ArrayList<Package> packageList = optPackageList.get();

            for (Package pack : packageList) {
                System.out.println(pack.getDescription() + ":");

                TrackingInfo packageInfo = TrackingManager.track(pack);

                System.out.println(packageInfo.getTimes());
                System.out.println(packageInfo.getStatus());
                System.out.println(packageInfo.getLocations());
            }
        }
    }
}
