package app.arguments;

import java.util.List;

public class StringParser implements SimpleParser<String> {

    public static final StringParser parser = new StringParser();

    private StringParser() {}

    @Override
    public Pair<String, List<String>> apply(List<String> strings) {
        if (strings.size() < 1) {
            return null;
        } else {
            return new Pair<>(null, strings.subList(1, strings.size()));
        }
    }
}
