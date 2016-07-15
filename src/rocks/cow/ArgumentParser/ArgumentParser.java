package rocks.cow.ArgumentParser;

import rocks.cow.ArgumentParser.Argument.ArgumentArray.ArgumentArray;
import rocks.cow.ArgumentParser.Argument.Errors.ArgumentNotFoundError;
import rocks.cow.ArgumentParser.Argument.Flag.FlagArg;
import rocks.cow.ArgumentParser.Argument.PositionalArg.PositionalArg;

import java.util.HashMap;

public class ArgumentParser {
    private ArgumentArray argumentArray = new ArgumentArray();
    private FlagArg tempFlag = new FlagArg();
    private PositionalArg posArg = new PositionalArg();

    public FlagArg addFlag() {
        return
    }

    public HashMap parseArgs(String[] args) throws ArgumentNotFoundError {
    }

}
