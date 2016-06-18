package rocks.cow.Package;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class PackageManager extends ArrayList<Package> {
    private ArrayList<Package> packages = new ArrayList<>();

    public Optional<ArrayList<Package>> getPackage(String id) {
        ArrayList<Package> packageList = this.filterPackage(id);

        if (packageList.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(packageList);
    }

    private ArrayList<Package> filterPackage(String id) {
        ArrayList<Package> matchingPacakges = new ArrayList<>();
        id = id.toLowerCase();

        final Pattern descPattern = Pattern.compile(String.format(".*%s.*", id));
        final Pattern trackPattern = Pattern.compile(String.format("(^%s|%s$)", id, id));

        for (Package pack: packages) {
            if (descPattern.matcher(pack.getDescription()).find()) {
                matchingPacakges.add(pack);
                continue;
            }
            if (trackPattern.matcher(pack.getTrackingNum()).find()) matchingPacakges.add(pack);
        }
        return matchingPacakges;
    }
}
