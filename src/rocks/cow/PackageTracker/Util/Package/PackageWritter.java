package rocks.cow.PackageTracker.Util.Package;

import com.google.gson.*;
import rocks.cow.PackageTracker.Package.Carrier.CarrierType;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Util.FileMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

public class PackageWritter {
    public ArrayList<Package> loadPackages(String file) throws FileNotFoundException {
        return loadPackages(new File(file));
    }

    public ArrayList<Package> loadPackages(File file) throws FileNotFoundException {
        ArrayList<Package> packages = new ArrayList<>();

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        JsonElement json = new JsonParser().parse(new FileMethods(file).readFullFile());
        if (json.isJsonNull()) {
            throw new NullPointerException();
        }

        if (!json.isJsonArray() && !json.isJsonObject()) {
            throw new Error("No JsonObjects or JsonArray's were found in " + file.getPath());

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

                packages.add(new Package(
                        jsonObj.get("description").getAsString(),
                        jsonObj.get("trackingId").getAsString(),
                        type.get()
                ));
            }
        } else if (json.isJsonObject()) {
            jsonObj = json.getAsJsonObject();
            type = CarrierType.getType(jsonObj.get("carrier").getAsString());

            if (!type.isPresent()) {
                throw new Error("Invalid Carrier Type");
            }

            packages.add(new Package(
                    jsonObj.get("description").getAsString(),
                    jsonObj.get("trackingId").getAsString(),
                    type.get()
            ));
        }
        return packages;
    }
}
