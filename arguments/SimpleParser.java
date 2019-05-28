package app.arguments;

import java.util.List;
import java.util.function.Function;

public interface SimpleParser<Self> extends Function<List<String>, Pair<Self, List<String>>> {}
