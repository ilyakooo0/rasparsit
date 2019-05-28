package app.arguments;

import java.util.List;

public class IntParser implements SimpleParser<Integer> {
    public static final IntParser parser = new IntParser();

    private IntParser() {}

    @Override
    public Pair<Integer, List<String>> apply(List<String> arguments) {
        if (arguments.size() < 1) {
            return null;
        }

        try {
            return new Pair<>(Integer.parseInt(arguments.get(0)), arguments.subList(1, arguments.size()));
        } catch (Exception ignored) {
            return null;
        }
    }
}
