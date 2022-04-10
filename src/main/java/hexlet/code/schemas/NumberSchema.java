package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    @Override
    public final NumberSchema required() {
        addCheck(x -> x != null);
        return this;
    }

    public final NumberSchema positive() {
        addCheck(x -> {
            if (x instanceof Integer) {
                return (Integer) x >= 0;
            }
            if (x instanceof Double) {
                return (Double) x >= 0;
            }
            return x == null;
        });
        return this;
    }

    public final NumberSchema range(int startOfRange, int endOfRange) {
        addCheck(x -> {
            if (x instanceof Integer) {
                return (Integer) x >= startOfRange && (Integer) x <= endOfRange;
            }
            if (x instanceof Double) {
                return (Double) x >= startOfRange && (Double) x <= endOfRange;
            }
            return false;
        });
        return this;
    }

}
