package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema {

    private boolean isRequired;
    private List<String> words = new ArrayList<>();
    private int requiredWordLength;

    public final boolean isValid(String string) {
        if (isRequired) {
            return string != null && !string.isEmpty() && isContain(string) && checkWordLength(string);
        }
        return true;
    }

    public final void required() {
        isRequired = true;
    }

    public final StringSchema contains(String subString) {
        words.add(subString);
        return this;
    }

    private boolean isContain(String string) {
        for (String word : words) {
            if (!string.contains(word)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkWordLength(String string) {
        return string.length() >= requiredWordLength;
    }

    public final StringSchema minLength(int length) {
        requiredWordLength = length;
        return this;
    }

}
