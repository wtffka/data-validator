package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapSchema extends BaseSchema {

    @Override
    public final MapSchema required() {
        addCheck(x -> x instanceof Map);
        return this;
    }

    public final MapSchema sizeof(int amount) {
        addCheck(map -> ((Map<?, ?>) map).size() == amount);
        return this;
    }

    public final void shape(final Map<String, BaseSchema> map) {
        addCheck(x -> {
            List<Boolean> resultOfShape = new ArrayList<>();
            for (Map.Entry<String, BaseSchema> entry : map.entrySet()) {
                resultOfShape.add(entry.getValue().isValid(((Map<?, ?>) x).get(entry.getKey())));
            }
            return !resultOfShape.contains(false);
        });
    }
}
