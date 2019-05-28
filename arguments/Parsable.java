package app.arguments;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Parsable {
    @NotNull Pair<String, List<String>> parse(@NotNull List<String> arguments);
}
