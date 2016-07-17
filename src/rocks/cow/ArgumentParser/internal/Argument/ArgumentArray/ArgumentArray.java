package rocks.cow.ArgumentParser.internal.Argument.ArgumentArray;

import rocks.cow.ArgumentParser.internal.Argument.ArgumentTypes.Flag.FlagArg;
import rocks.cow.ArgumentParser.internal.Argument.ArgumentTypes.PositionalArg.PositionalArg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ArgumentArray {
    private ArrayList<FlagArg> flags = new ArrayList<>();
    private ArrayList<PositionalArg> positionals = new ArrayList<>();

    public void addFlag(FlagArg flagArgs) {
        flags.add(flagArgs);
    }

    public void addFlag(Collection<FlagArg> flagArgs) {
        flags.addAll(flagArgs);
    }

    public void addPositional (PositionalArg positionalArg) {
        positionals.add(positionalArg);
    }

    public void addPositional (Collection<PositionalArg> positionalArgs) {
        positionals.addAll(positionalArgs);
    }

    public void removeFlag (FlagArg flagArg) {
        flags.remove(flagArg);
    }
    public void removeFlag (int index) {
        flags.remove(index);
    }

    public void removePositional (PositionalArg positionalArg) {
        positionals.remove(positionalArg);
    }

    public void removePositional (int index) {
        positionals.remove(index);
    }

    public Optional<FlagArg> getFlag(String id) {
        for (FlagArg flag : flags) {
            if (flag.getShortName().equals(id)) {
                return Optional.of(flag);
            }
            if (flag.getLongName().equals(id)) {
                return Optional.of(flag);
            }
        }
        return Optional.empty();
    }

    public Optional<PositionalArg> getPositional(String id) {
        for (PositionalArg pos : positionals) {
            if (pos.getShortName().equals(id)) {
                return Optional.of(pos);
            }
            if (pos.getLongName().equals(id)) {
                return Optional.of(pos);
            }
        }
        return Optional.empty();
    }
}
