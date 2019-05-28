package app.arguments;

public class Pair<T, H> {
    public final T fst;
    public final H snd;

    Pair(T fst, H snd) {
        this.fst = fst;
        this.snd = snd;
    }
}
