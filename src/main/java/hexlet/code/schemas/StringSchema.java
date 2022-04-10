package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    @Override
    public final StringSchema required() {
        addCheck(x -> null != x.toString() && !x.toString().isEmpty());
        return this;
    }

    public final StringSchema contains(String subString) {
        addCheck(x -> null != x.toString() && x.toString().contains(subString));
        return this;
    }

    public final StringSchema minLength(int length) {
        addCheck(x -> null != x.toString() && x.toString().length() >= length);
        return this;
    }
}
