package ua.edu.ucu.tries;

public final class Tuple {
    private static final int R = 256;
    public final String term;
    public final int weight;
    public final Tuple[] children = new Tuple[R];

    public Tuple(String term) {
        this.term = term;
        this.weight = 0;
    }

    public Tuple(String term, int weight) {
        this.term = term;
        this.weight = weight;
    }
}