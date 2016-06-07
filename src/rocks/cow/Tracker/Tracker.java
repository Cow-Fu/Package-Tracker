package rocks.cow.Tracker;

import rocks.cow.Package.Package;

import java.util.ArrayList;
import java.util.HashMap;


public interface Tracker {
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> activity = new ArrayList<>();
    ArrayList<String> location = new ArrayList<>();

    HashMap<String, ArrayList<? extends String>> track(Package p);
}