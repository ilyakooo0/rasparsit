package app.arguments;

public class Tripplet<T, H, U> {

    public final T fst;
    public final H snd;
    public final U thrd;

    Tripplet(T fst, H snd, U thrd) {
        this.fst = fst;
        this.snd = snd;
        this.thrd = thrd;
    }
}
