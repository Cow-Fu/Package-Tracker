package rocks.cow.ArgumentParser.internal.ParserResult;

import rocks.cow.ArgumentParser.internal.ArgumentValue;

import java.util.HashMap;

public class ParserResult {
    private HashMap<String, ArgumentValue> map = new HashMap<>();

    public ParserResult(HashMap<String, ArgumentValue> map) {
        this.map = map;
    }

    public ArgumentValue get(String id) {
        return map.get(id);
    }
}
