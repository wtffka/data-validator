package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    @Override
    public final StringSchema required() {
        addCheck(x -> {
            if (x != null) {
                return !x.toString().isEmpty();
            }
            return false;
        });
        return this;
    }

    public final StringSchema contains(String subString) {
        addCheck(x -> {
            if (x != null) {
                return x.toString().contains(subString);
            }
            return false;
        });
        return this;
    }

    public final StringSchema minLength(int length) {
        addCheck(x -> {
            if (x != null) {
                return x.toString().length() >= length;
            }
            return false;
        });
        return this;
    }
}
