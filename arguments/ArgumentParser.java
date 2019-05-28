package app.arguments;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArgumentParser<@NotNull T extends Parsable, R extends Parsable> implements Parsable {
    public final T value;
    public final R next;

    @NotNull
    @Override
    public Pair<String, List<String>> parse(@NotNull List<String> arguments) {
        final Pair<String, List<String>> result = value.parse(arguments);

        if (result.fst == null && next != null) {
            return next.parse(result.snd);
        }

        return result;
    }

    public ArgumentParser(T value, R next) {
        this.value = value;
        this.next = next;
    }

    public ArgumentParser(T value) {
        this.value = value;
        this.next = null;
    }
}
