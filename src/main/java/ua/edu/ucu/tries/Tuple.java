package ua.edu.ucu.tries;

import ua.edu.ucu.utils.Queue;

public final class Tuple {
    public final String term;
    public final int weight;
    public final Queue children = new Queue();

    public Tuple(String term, int weight) {
        this.term = term;
        this.weight = weight;
    }  
}