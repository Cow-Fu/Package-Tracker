package rocks.cow.ArgumentParser.Argument;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public abstract class Argument<T> {
    public static final int ONE_OR_MORE = -100;
    public static final int ZERO_OR_MORE = -200;

    protected ArrayList<String> names;
    protected String description;
    protected int nargs = 1;

    public ArrayList<String> getNames() {
        return names;
    }

    public T addNames(String names) {
        this.names.add(names);
        return (T) this;
    }

    public T addNames(ArrayList<String> names) {
        this.names.addAll(names);
        return (T) this;
    }

    public String getDescription() {
        return description;
    }


    public T setDescription(String description) {
        this.description = description;
        return (T) this;
    }

    public int getNargs() {
        return nargs;
    }

    public T setNargs(int nargs) {
        if (nargs < 1 && nargs != Argument.ONE_OR_MORE && nargs != Argument.ZERO_OR_MORE) try {
            throw new Exception("Value can't be lower than 1!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.nargs = nargs;
        return (T) this;
    }
}
