package rocks.cow.ArgumentParser.internal.Argument;

@SuppressWarnings("unchecked")
public abstract class Argument<T> {
    public static final int ONE_OR_MORE = -100;
    public static final int ZERO_OR_MORE = -200;

    protected String shortName;
    protected String longName;
    protected String description;
    protected int nargs = 1;

    public String getShortName() {
        return shortName;
    }

    public T setShortName(String shortName) {
        this.shortName = shortName;
        return (T) this;
    }

    public String getLongName() {
        return longName;
    }

    public T setLongName(String longName) {
        this.longName = longName;
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

    public abstract T setNargs(int nargs);
}
