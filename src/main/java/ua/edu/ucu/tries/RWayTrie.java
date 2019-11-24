package ua.edu.ucu.tries;

import ua.edu.ucu.Checker;
import ua.edu.ucu.utils.Queue;

import java.util.Arrays;

public class RWayTrie implements Trie {

    private Tuple root;
    private int treeSize;

    public RWayTrie() {}

    @Override
    public void add(Tuple t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Argument is null!");
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(String word) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> words() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue nodes = new Queue();
        Tuple t = getWord(this.root, s, 0);
        collect(t, new StringBuilder(s), nodes);
        return nodes;
    }

    private void collect(Tuple t, StringBuilder sb, Queue nodes) {
        if (t == null) {
            return;
        }
        if (t.term != null) {
            nodes.enqueue(sb.toString());
        }
        for (char i = 0; i < 5; i++) {

        }
    }

    @Override
    public int size() {
        return this.treeSize;
    }

    public String getWord(String key) {
        Checker.checkNull(key);
        Tuple res = getWord(this.root, key, 0);
        return res.term;
    }

    private Tuple getWord(Tuple node, String key, int position) {
        Checker.checkNull(node);
        if (position == key.length()) {
            return node;
        }
        // char c = key.charAt(position);
        node.children.dequeue();
        position++;
        return getWord(node, key, position);
    }

}
