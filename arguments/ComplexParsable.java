package app.arguments;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class ComplexParsable<Self> implements Parsable {
    @NotNull
    private ComplexParser<Self> parseValue;

    public Self value;
    private final @NotNull Predicate<@NotNull Self> predicate;

    @Override
    public @NotNull Pair<String, List<String>> parse(@NotNull List<String> arguments) {
        Tripplet<String, @NotNull Self, @NotNull List<@NotNull String>> result = parseValue.apply(arguments);

        if (result.fst == null) {
            value = result.snd;

            if (predicate.test(value)) {
                return new Pair<>(null, result.thrd);
            }
        }

        return new Pair<>(result.fst, result.thrd);

    }

    public ComplexParsable(@NotNull ComplexParser<Self> parseValue,
                           @NotNull Predicate<@NotNull Self> predicate) {
        this.parseValue = parseValue;
        this.predicate = predicate;
    }

    public ComplexParsable(@NotNull ComplexParser<Self> parseValue) {
        this(parseValue, i -> true);
    }
}
