package rocks.cow.Package.PackageManager;

import com.google.gson.*;
import rocks.cow.Package.Carrier.CarrierType;
import rocks.cow.Package.Package;
import rocks.cow.Util.FileMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class PackageManager extends ArrayList<Package> {
    private static Gson gson = new Gson();

    public void addNew(String description, String trackingNum, CarrierType carrierType) {
        this.add(new Package(description, trackingNum, carrierType));
    }

    public boolean remove(String id) {
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

    public void savePackages(String file) throws FileNotFoundException {
        savePackages(new File(file));
    }

    public void savePackages(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        new FileMethods(file).write(gson.toJson(this));
    }

    public void loadPackages(String file) throws FileNotFoundException {
        loadPackages(new File(file));
    }

    public void loadPackages(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        JsonElement json = new JsonParser().parse(new FileMethods(file).readFullFile());
        if (json.isJsonNull()) {
            throw new NullPointerException();
        }
        JsonObject jsonObj;
        Optional<CarrierType> type;

        if (json.isJsonArray()) {
            for (JsonElement j: json.getAsJsonArray()) {
                jsonObj = j.getAsJsonObject();
                type = CarrierType.getType(jsonObj.get("carrier").getAsString());

                if (!type.isPresent()) {
                    throw new Error("Invalid Carrier Type");
                }

                this.addNew(
                        jsonObj.get("description").getAsString(),
                        jsonObj.get("trackingId").getAsString(),
                        type.get()
                );
            }
            return;
        }

        if (json.isJsonObject()) {
            jsonObj = json.getAsJsonObject();
            type = CarrierType.getType(jsonObj.get("carrier").getAsString());

            if (!type.isPresent()) {
                throw new Error("Invalid Carrier Type");
            }

            this.addNew(
                    jsonObj.get("description").getAsString(),
                    jsonObj.get("trackingID").getAsString(),
                    type.get()
            );
            return;
        }

        throw new Error("No JsonObjects or JsonArray's were found in " + file.getPath());
    }

    private ArrayList<Package> filterPackage(String id) {
        ArrayList<Package> matchingPacakges = new ArrayList<>();
        id = id.toLowerCase();

        final Pattern descPattern = Pattern.compile(String.format("(.*%s.*)", id), Pattern.CASE_INSENSITIVE);
        final Pattern trackPattern = Pattern.compile(String.format("(^%s|%s$)", id, id), Pattern.CASE_INSENSITIVE);

        for (Package pack : this) {
            if (descPattern.matcher(pack.getDescription()).find()) {
                matchingPacakges.add(pack);
                continue;
            }
            if (trackPattern.matcher(pack.getTrackingId()).find()) matchingPacakges.add(pack);
        }
        return matchingPacakges;
    }
}
