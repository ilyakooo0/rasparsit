package app.arguments;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class InetParser implements ComplexParser<InetAddress> {
    public static final InetParser parser = new InetParser();

    private InetParser() {}

    @Override
    public @NotNull Tripplet<String, InetAddress, List<String>> apply(List<String> strings) {
        if (strings.size() < 1) {
            return new Tripplet<>("Could not parse inet address.", null, strings);
        }

        SimpleParsable<String> hostParser =
                new SimpleParsable<>(StringParser.parser,
                        "Could not parse host.");

        Pair<String, List<String>> result = hostParser.parse(strings);

        if (result.fst != null) {
            return new Tripplet<>(result.fst, null, result.snd);
        }

        InetAddress address;

        try {
            address = InetAddress.getByName(hostParser.value);
        } catch (UnknownHostException e) {
            return new Tripplet<>("Could not initialize the inet address", null, result.snd);
        }

        return new Tripplet<>(null, address, result.snd);
    }
}
