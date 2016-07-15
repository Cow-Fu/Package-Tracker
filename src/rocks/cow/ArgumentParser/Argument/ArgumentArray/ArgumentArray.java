package rocks.cow.ArgumentParser.Argument.ArgumentArray;

import rocks.cow.ArgumentParser.Argument.Flag.FlagArg;
import rocks.cow.ArgumentParser.Argument.PositionalArg.PositionalArg;

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
            if (flag.getNames().contains(id)) {
                return Optional.of(flag);
            }
        }
        return Optional.empty();
    }

    public Optional<PositionalArg> getPositional(String id) {
        for (PositionalArg pos : positionals) {
            if (pos.getNames().contains(id)) {
                return Optional.of(pos);
            }
        }
        return Optional.empty();
    }
}
