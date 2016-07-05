package rocks.cow.ArgumentParser.Argument;

import rocks.cow.ArgumentParser.Argument.Property.Property;

import java.util.ArrayList;

public class Argument {
    private final String name;
    private final String helpMessage;
    private final ArrayList<Property> properties;

    public Argument(final String name, final String helpMessage, final ArrayList<Property> properties) {
        this.name = name;
        this.helpMessage = helpMessage;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public boolean hasProperty(Property property) {
        return this.properties.contains(property);
    }
}
