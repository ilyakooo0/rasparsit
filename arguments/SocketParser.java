package app.arguments;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class SocketParser implements ComplexParser<Socket> {
    public static final SocketParser parser = new SocketParser();

    private SocketParser() {}

    @Override
    public @NotNull Tripplet<String, Socket, List<String>> apply(List<String> strings) {
        SimpleParsable<Integer> portParser =
                new SimpleParsable<Integer>(IntParser.parser,
                        p -> p >= 0 && p <= 65535,
                        "Could not parse port");

        ArgumentParser<
                ComplexParsable<InetAddress>,
                @NotNull ArgumentParser<
                        SimpleParsable<Integer>, ArgumentParser>> parser =
                new ArgumentParser<>(new ComplexParsable<>(InetParser.parser),
                        new ArgumentParser<>(portParser));

        Pair<String, List<String>> result = parser.parse(strings);

        if (result.fst != null) {
            return new Tripplet<>(result.fst, null, result.snd);
        }

        Socket socket;

        try {
            socket = new Socket(parser.value.value, parser.next.value.value);
        } catch (IOException e) {
            return new Tripplet<>("Could not initialize socket.", null, result.snd);
        }

        return new Tripplet<>(null, socket, result.snd);
    }
}
