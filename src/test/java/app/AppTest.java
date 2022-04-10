package app;

import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class AppTest {

    private final Validator validator = new Validator();
    private final StringSchema stringSchema = validator.string();
    private final NumberSchema numberSchema = validator.number();
    private final MapSchema mapSchema = validator.map();

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

        boolean result = numberSchema.isValid(0);
        assertThat(result).isEqualTo(true);

        result = numberSchema.isValid(null);
        assertThat(result).isEqualTo(false);

        result = numberSchema.isValid("5");
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

        numberSchema.range(1, 2);

        boolean result = numberSchema.isValid(0);
        assertThat(result).isEqualTo(false);

        result = numberSchema.isValid(1);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void testIsValidBeforeMapSchemaRequired() {
        boolean result = mapSchema.isValid(0);
        assertThat(result).isEqualTo(true);

        Map<String, String> map = new HashMap<>();

        map.put("1", "Slava");

        result = mapSchema.isValid(map);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void testIsValidAfterMapSchemaRequired() {
        mapSchema.required();

        Map<String, String> map = new HashMap<>();

        map.put("1", "Slava");

        boolean result = mapSchema.isValid(map);
        assertThat(result).isEqualTo(true);

        result = mapSchema.isValid("");
        assertThat(result).isEqualTo(false);
    }

    @Test
    void testSizeOfMethod() {
        mapSchema.required();

        mapSchema.sizeof(2);

        Map<String, String> map = new HashMap<>();

        map.put("1", "Slava");

        boolean result = mapSchema.isValid(map);
        assertThat(result).isEqualTo(false);

        map.put("2", "Balakhonov");

        result = mapSchema.isValid(map);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void testShapeMethod() {
        mapSchema.required();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required().contains("ya"));
        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 1);

        boolean result = mapSchema.isValid(human1);
        assertThat(result).isEqualTo(true);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        mapSchema.isValid(human2);

        result = mapSchema.isValid(human2);
        assertThat(result).isEqualTo(true);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -1);

        result = mapSchema.isValid(human4);
        assertThat(result).isEqualTo(false);

    }
}
