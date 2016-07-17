package rocks.cow.ArgumentParser.internal;

public class ArgumentValue {
    private Object obj;

    public ArgumentValue (Object obj) {
        this.obj = obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getAsObj() {
        return obj;
    }

    public int getAsInt() {
        return (int) obj;
    }

    public String getAsString() {
        return (String) obj;
    }
}
