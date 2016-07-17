package rocks.cow.ArgumentParser.internal.Argument.Errors;

import rocks.cow.ArgumentParser.internal.Argument.Argument;

public class ArgumentNotFoundError extends Exception {
    public ArgumentNotFoundError(String message) {
        super(message);
    }
    public ArgumentNotFoundError(Argument argument) {
        super("Unable to find value for Argument: "+ argument.getNames().get(0));
    }
}
