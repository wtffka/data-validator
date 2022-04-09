package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    @Override
    public final BaseSchema required() {
        addCheck(x -> x.toString() != null && !x.toString().isEmpty());
        return this;
    }

    public final StringSchema contains(String subString) {
        addCheck(x -> x.toString().contains(subString));
        return this;
    }

    public final StringSchema minLength(int length) {
        addCheck(x -> x.toString().length() >= length);
        return this;
    }
}
