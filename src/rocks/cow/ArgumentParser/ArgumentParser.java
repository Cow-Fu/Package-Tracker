package rocks.cow.ArgumentParser;

import rocks.cow.ArgumentParser.Argument.Argument;

import java.util.ArrayList;
import java.util.HashMap;

public class ArgumentParser {
    public ArrayList<Argument> argumentList;

    public void addArgument(String name, boolean optional) {
        argumentList.add(new Argument(name, optional));
    }
    public HashMap parseArgs(ArrayList list) {
        return new HashMap();
    }
}
