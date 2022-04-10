package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    @Override
    public final NumberSchema required() {
        addCheck(x -> x instanceof Integer || x instanceof Double);
        return this;
    }

    public final NumberSchema positive() {
        addCheck(x -> {
            if (x instanceof Integer) {
                return (int) x > 0;
            }
            if (x instanceof Double) {
                return (double) x > 0;
            }
            return x == null;
        });
        return this;
    }

    public final NumberSchema range(int startOfRange, int endOfRange) {
        addCheck(x -> {
            if (x instanceof Integer) {
                return (int) x >= startOfRange && (int) x <= endOfRange;
            }
            if (x instanceof Double) {
                return (double) x >= startOfRange && (double) x <= endOfRange;
            }
            return false;
        });
        return this;
    }

}
