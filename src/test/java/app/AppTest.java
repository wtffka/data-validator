package app;

import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AppTest {

    private final Validator validator = new Validator();
    private final StringSchema stringSchema = validator.string();
    private final NumberSchema numberSchema = validator.number();
    private final int endOfRange = 4;

    @Test
    void testIsValidBeforeStringSchemaRequired() {
        boolean result = stringSchema.isValid("");
        assertThat(result).isEqualTo(true);

        result = stringSchema.isValid("rock");
        assertThat(result).isEqualTo(true);
    }

    @Test
    void testIsValidAfterStringSchemaRequired() {
        stringSchema.required();

        boolean result = stringSchema.isValid("rock");
        assertThat(result).isEqualTo(true);

        result = stringSchema.isValid("");
        assertThat(result).isEqualTo(false);
    }

    @Test
    void testIsContainsMethod() {
        stringSchema.required();

        stringSchema.contains("ro");

        boolean result = stringSchema.isValid("rock");
        assertThat(result).isEqualTo(true);

        stringSchema.contains("cro");

        result = stringSchema.isValid("rock");
        assertThat(result).isEqualTo(false);
    }

    @Test
    void testMinLength() {
        stringSchema.required();

        stringSchema.minLength(2);

        boolean result = stringSchema.isValid("u");
        assertThat(result).isEqualTo(false);

        result = stringSchema.isValid("me");
        assertThat(result).isEqualTo(true);
    }

    @Test
    void testIsValidBeforeNumberSchemaRequired() {
        boolean result = numberSchema.isValid(0);
        assertThat(result).isEqualTo(true);

        result = numberSchema.isValid(null);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void testIsValidAfterNumberSchemaRequired() {
        numberSchema.required();

        numberSchema.positive();

        boolean result = numberSchema.isValid(0);
        assertThat(result).isEqualTo(true);

        result = numberSchema.isValid(null);
        assertThat(result).isEqualTo(false);
    }

    @Test
    void testIsPositiveMethod() {
        numberSchema.required();

        numberSchema.positive();

        boolean result = numberSchema.isValid(1);
        assertThat(result).isEqualTo(true);

        result = numberSchema.isValid(-1);
        assertThat(result).isEqualTo(false);
    }

    @Test
    void isNumberInRangeMethod() {
        numberSchema.required();

        numberSchema.range(1, endOfRange);

        boolean result = numberSchema.isValid(0);
        assertThat(result).isEqualTo(false);

        result = numberSchema.isValid(1);
        assertThat(result).isEqualTo(true);
    }
}
