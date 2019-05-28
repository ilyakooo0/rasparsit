package app.arguments;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class SimpleParsable<Self> implements Parsable {
    @NotNull
    private SimpleParser<Self> parseValue;

    public Self value;

    private final @NotNull Predicate<@NotNull Self> predicate;
    private final Self def;
    private final @NotNull String message;

    @NotNull
    @Override
    public Pair<String, List<String>> parse(@NotNull List<String> arguments) {
        Pair<@NotNull Self, @NotNull List<@NotNull String>> result = parseValue.apply(arguments);

        if (result != null) {
            value = result.fst;

            if (predicate.test(value)) {
                return new Pair<>(null, result.snd);
            }
        }

        if (def != null) {
            value = def;

            if (predicate.test(value)) {
                return new Pair<>(null, arguments);
            }
        }

        return new Pair<>(message, arguments);
    }

    public SimpleParsable(
            @NotNull SimpleParser<Self> parseValue,
            Self def,
            @NotNull Predicate<@NotNull Self> predicate,
            @NotNull String message) {
        this.parseValue = parseValue;
        this.predicate = predicate;
        this.def = def;
        this.message = message;
    }

    public SimpleParsable(@NotNull SimpleParser<Self> parseValue,
                          @NotNull Predicate<@NotNull Self> predicate,
                          @NotNull String message) {
        this(parseValue, null, predicate, message);
    }


    public SimpleParsable(@NotNull SimpleParser<Self> parseValue, Self def, @NotNull String message) {
        this(parseValue, def, (i) -> true, message);
    }

    public SimpleParsable(@NotNull SimpleParser<Self> parseValue, @NotNull String message) {
        this(parseValue, null, (i) -> true, message);
    }
}
