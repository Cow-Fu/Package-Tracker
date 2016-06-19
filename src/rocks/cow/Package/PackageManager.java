package rocks.cow.Package;

import rocks.cow.Package.Carrier.CarrierType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Pattern;

public class PackageManager extends ArrayList<Package> {

    public void addNew (String description, String trackingNum, CarrierType carrierType) {
        this.add(new Package(description, trackingNum, carrierType));
    }

    public boolean remove (String id) {
        ArrayList<Package> packageList = this.filterPackage(id);
        int listSize = packageList.size();

        if (listSize >= 2 || listSize == 0) {
            return false;
        }
        this.remove(packageList.get(0));

        return true;
    }

    public Optional<ArrayList<Package>> get(String id) {
        ArrayList<Package> packageList = this.filterPackage(id);

        if (packageList.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(packageList);
    }

    private ArrayList<Package> filterPackage(String id) {
        ArrayList<Package> matchingPacakges = new ArrayList<>();
        id = id.toLowerCase();

        final Pattern descPattern = Pattern.compile(String.format("(.*%s.*)", id), Pattern.CASE_INSENSITIVE);
        final Pattern trackPattern = Pattern.compile(String.format("(^%s|%s$)", id, id), Pattern.CASE_INSENSITIVE);

        for (Package pack: this) {
            if (descPattern.matcher(pack.getDescription()).find()) {
                matchingPacakges.add(pack);
                continue;
            }
            if (trackPattern.matcher(pack.getTrackingNum()).find()) matchingPacakges.add(pack);
        }
        return matchingPacakges;
    }
}
