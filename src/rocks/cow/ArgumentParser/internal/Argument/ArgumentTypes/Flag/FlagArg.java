package rocks.cow.ArgumentParser.internal.Argument.ArgumentTypes.Flag;

import rocks.cow.ArgumentParser.internal.Argument.Argument;

public class FlagArg extends Argument<FlagArg> {
    private String defaultValue;
    private int maxCount = 1;

    @Override
    public FlagArg setNargs(int nargs) {
        if (nargs < 0 && nargs != Argument.ONE_OR_MORE && nargs != Argument.ZERO_OR_MORE) try {
            throw new Exception("Value can't be lower than 1!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.nargs = nargs;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public FlagArg setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public FlagArg setMaxCount(int maxCount) {
        this.maxCount = maxCount;
        return this;
    }
}
