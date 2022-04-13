package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private boolean isRequired;
    private final List<Predicate<Object>> checks = new ArrayList<>();

    public abstract BaseSchema required();

    public final boolean isValid(Object object) {

        if (Objects.equals(null, object) && !isRequired) {
            return true;
        }

        for (Predicate<Object> check : checks) {
            if (!check.test(object)) {
                return false;
            }
        }

        return true;
    }

    protected final void isRequiredOn() {
        isRequired = true;
    }

    protected final void addCheck(Predicate<Object> predicate) {
        checks.add(predicate);
    }
}
