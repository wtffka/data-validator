package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate<Object>> checks = new ArrayList<>();

    public abstract BaseSchema required();

    public final boolean isValid(Object object) {
        final List<Boolean> resultOfChecks = new ArrayList<>();

        if (checks.size() == 0) {
            return true;
        }

        for (Predicate<Object> check : checks) {
            resultOfChecks.add(check.test(object));
        }

        return !resultOfChecks.contains(false);
    }

    protected final void addCheck(Predicate<Object> predicate) {
        checks.add(predicate);
    }
}
