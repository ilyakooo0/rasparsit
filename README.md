# rasparsit

## A type safe, declarative, composable and extensible command line argument parser for Java.

I was pretty sure at some point that you can use `ArgumentParser` in places of `SimpleParser` and `ComplexParser` and it would pretty much work as intended. This is what I meant by composable.

### Examples 

```java
final ArgumentParser<SimpleParsable<Integer>, ArgumentParser<SimpleParsable<Integer>, ArgumentParser<ComplexParsable<Socket>, ArgumentParser>>> parser =
	new ArgumentParser<>(new SimpleParsable<Integer>(IntParser.parser, n -> n > 0, "You did not provide a valid positive list half-size."),
		    new ArgumentParser<>(new SimpleParsable<Integer>(IntParser.parser, m -> m > 0, "You did not provide a valid positive integer upper-bound."),
		            new ArgumentParser<>(new ComplexParsable<>(SocketParser.parser), null)));

Pair<String, List<String>> result = parser.parse(Arrays.asList(args));

if (result.fst != null) {
	System.out.println(result.fst);
	System.exit(1);
}

final int maxInt = parser.value.value;
final int intCount = parser.next.value.value;
final Socket socket = parser.next.next.value.value;
```

```java
final SimpleParsable<Integer> parser = new SimpleParsable<Integer>(IntParser.parser,
            p -> p >= 0 && p <= 65535,
            "Could not parse port");

final Pair<String, List<String>> patseResult = parser.parse(new ArrayList<>(Arrays.asList(args)));

if (patseResult.fst != null) {
    System.out.println(patseResult.fst);
    System.exit(1);
}
```