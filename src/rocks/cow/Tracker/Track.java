package rocks.cow.Tracker;

import rocks.cow.Package.Package;

import java.util.ArrayList;
import java.util.HashMap;


public interface Track {
    HashMap<String, ArrayList<? extends String>> track(Package p);
}