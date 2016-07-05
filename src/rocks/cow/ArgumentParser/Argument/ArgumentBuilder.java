package rocks.cow.ArgumentParser.Argument;

import rocks.cow.ArgumentParser.Argument.Property.Property;

import java.util.ArrayList;

public class ArgumentBuilder {
    private String name;
    private String helpMessage;
    private ArrayList<Property> properties;

    public ArgumentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ArgumentBuilder setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
        return this;
    }
    public ArgumentBuilder addProperty(Property property) {
        properties.add(property);
        return this;
    }

    public ArgumentBuilder addProperties(ArrayList<Property> property) {
        properties.addAll(property);
        return this;
    }

    public ArgumentBuilder setProperties(ArrayList<Property> properties) {
        this.properties = properties;
        return this;
    }

    public ArgumentBuilder removeProperty (Property property) {
        properties.remove(property);
        return this;
    }

    public ArgumentBuilder removeProperty (int index) {
        properties.remove(index);
        return this;
    }

    public ArgumentBuilder removeProperties (ArrayList<Property> properties) {
        this.properties.removeAll(properties);
        return this;
    }

    public Argument create() {
        return new Argument(name, helpMessage, properties);
    }

}
