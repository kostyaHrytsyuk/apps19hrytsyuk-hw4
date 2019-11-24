package ua.edu.ucu.tries;

public final class Tuple {
    private static final int R = 256;
    public final String term;
    public final int weight;
    public final Tuple[] children = new Tuple[R];

    public Tuple(String term) {
        this.term = term;
        if (this.term != null) {
            this.weight = term.length();
        } else {
            this.weight = 0;
        }
    }  
}