package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    @Override
    public final MapSchema required() {
        isRequiredOn();
        addCheck(x -> x instanceof Map);
        return this;
    }

    public final MapSchema sizeof(int amount) {
        addCheck(map -> ((Map<?, ?>) map).size() == amount);
        return this;
    }

    public final void shape(final Map<String, BaseSchema> map) {
        addCheck(x -> {
            for (Map.Entry<String, BaseSchema> entry : map.entrySet()) {
                if (!entry.getValue().isValid(((Map<?, ?>) x).get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        });
    }
}
