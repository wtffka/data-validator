package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    @Override
    public final MapSchema required() {
        addCheck(x -> x instanceof Map);
        return this;
    }

    public final MapSchema sizeOf(int amount) {
        addCheck(map -> ((Map<?, ?>) map).size() == amount);
        return this;
    }
}
