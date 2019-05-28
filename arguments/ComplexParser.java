package app.arguments;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public interface ComplexParser<Self> extends Function<List<String>, @NotNull Tripplet<String, Self, List<String>>> {}
