package rocks.cow.ArgumentParser;

import rocks.cow.ArgumentParser.internal.Argument.Argument;
import rocks.cow.ArgumentParser.internal.Argument.ArgumentArray.ArgumentArray;
import rocks.cow.ArgumentParser.internal.Argument.Errors.ArgumentNotFoundError;
import rocks.cow.ArgumentParser.internal.Argument.ArgumentTypes.Flag.FlagArg;
import rocks.cow.ArgumentParser.internal.Argument.ArgumentTypes.PositionalArg.PositionalArg;
import rocks.cow.ArgumentParser.internal.ArgumentValue;
import rocks.cow.ArgumentParser.internal.ParserResult.ParserResult;

import java.util.HashMap;
import java.util.Optional;

public class ArgumentParser {
    private ArgumentArray argumentArray = new ArgumentArray();
    private FlagArg tempFlag;
    private PositionalArg tempPos;

    public FlagArg createNewFlag() {
        return tempFlag = new FlagArg();
    }

    public void saveFlag() {
        argumentArray.addFlag(tempFlag);
    }

    public PositionalArg createNewPositional() {
        return tempPos = new PositionalArg();
    }

    public void savePositional() {
        argumentArray.addPositional(tempPos);
    }

    public ArgumentArray getArgumentArray() {
        return argumentArray;
    }

    public ParserResult parseArgs(String[] args) throws ArgumentNotFoundError {
        HashMap<String, ArgumentValue> map = new HashMap<>();

        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];

            if (arg.startsWith("-")) {
                Optional<FlagArg> optFlag = argumentArray.getFlag(arg);
                if (optFlag.isPresent()) {
                    if (!optFlag.get().getLongName().equals("")) {
                        map.put(optFlag.get().getLongName(), new ArgumentValue(args[i+1]));
                    } else {
                        map.put(optFlag.get().getShortName(), new ArgumentValue(args[i+1]));
                    }
                    ++i;
                }
                continue;
            }
            // parse positional arguments here

        }
        return null;
    }
}
