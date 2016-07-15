package rocks.cow.ArgumentParser.Argument.PositionalArg;

import rocks.cow.ArgumentParser.Argument.Argument;

import java.util.ArrayList;

public class PositionalArg extends Argument<PositionalArg> {
    @Override
    public PositionalArg setNargs(int nargs) {
        if (nargs < 1 && nargs != Argument.ONE_OR_MORE && nargs != Argument.ZERO_OR_MORE) try {
            throw new Exception("Value can't be lower than 1!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.nargs = nargs;
        return this;
    }
}
