package rocks.cow.Package;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class PackageManager extends ArrayList<Package> {

    public ArrayList<Package> getPackage(String id) {
        ArrayList<Package> packageList = this.filterPackage(id);

        if (packageList.size() == 0) {
            return null;
        }
        return packageList;
    }

    private ArrayList<Package> filterPackage(String id) {
        ArrayList<Package> matchingPacakges = new ArrayList<>();
        id = id.toLowerCase();

        final Pattern descPattern = Pattern.compile(String.format("(.*%s.*)", id), Pattern.CASE_INSENSITIVE);
        final Pattern trackPattern = Pattern.compile(String.format("(?i)(^%s|%s$)", id, id), Pattern.CASE_INSENSITIVE);

        for (Package pack: this) {
            System.out.println(pack.getDescription());
            if (descPattern.matcher(pack.getDescription()).find()) {
                matchingPacakges.add(pack);
                continue;
            }
            if (trackPattern.matcher(pack.getTrackingNum()).find()) matchingPacakges.add(pack);
        }
        return matchingPacakges;
    }
}
