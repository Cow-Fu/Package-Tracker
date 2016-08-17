package rocks.cow.PackageTracker.Util.Package;

import com.google.gson.*;
import rocks.cow.PackageTracker.Package.Package;
import rocks.cow.PackageTracker.Util.FileMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PackageWriter {
    private static Gson gson = new Gson();

    public static void savePackages(String file, ArrayList<Package> packages) throws FileNotFoundException {
        savePackages(new File(file), packages);
    }

    public static void savePackages(File file, ArrayList<Package> packages) throws FileNotFoundException {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new FileMethods(file).write(gson.toJson(packages));
    }
}
