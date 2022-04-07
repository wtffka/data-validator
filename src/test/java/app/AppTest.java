package app;

import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    void testIsValidBeforeSchemaRequired() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        boolean result = schema.isValid("");
        assertThat(result).isEqualTo(true);

        result = schema.isValid("rock");
        assertThat(result).isEqualTo(true);
    }

    @Test
    void testIsValidAfterSchemaRequired() {
        Validator validator1 = new Validator();
        StringSchema schema = validator1.string();

        schema.required();

        boolean result = schema.isValid("rock");
        assertThat(result).isEqualTo(true);

        boolean result1 = schema.isValid("");
        assertThat(result1).isEqualTo(false);
    }

    @Test
    void testContainsMethod() {
        Validator validator2 = new Validator();
        StringSchema schema = validator2.string();

        schema.required();

        schema.contains("ro");

        boolean result = schema.isValid("rock");
        assertThat(result).isEqualTo(true);

        schema.contains("cro");

        result = schema.isValid("rock");
        assertThat(result).isEqualTo(false);
    }

    @Test
    void testMinLength() {
        Validator validator2 = new Validator();
        StringSchema schema = validator2.string();

        schema.required();

        schema.minLength(2);

        boolean result = schema.isValid("u");
        assertThat(result).isEqualTo(false);

        result = schema.isValid("me");
        assertThat(result).isEqualTo(true);
    }

}
