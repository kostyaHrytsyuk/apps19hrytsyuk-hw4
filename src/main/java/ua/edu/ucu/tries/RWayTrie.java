package ua.edu.ucu.tries;

import ua.edu.ucu.Checker;
import ua.edu.ucu.utils.Queue;

import java.util.Arrays;


public class RWayTrie implements Trie {

    private Queue tree;
    private Tuple root;
    private int treeSize;

    public RWayTrie() {
        this.tree = new Queue();
        this.root = new Tuple("");
    }

    public RWayTrie(Tuple root) {

    }

    @Override
    public void add(Tuple t) {
        Checker.checkNull(t);
        add(t.term, t.weight);
    }

    private void add(String key, Integer value) {
        this.root = add(this.root, key, value, 0);
    }

    private Tuple add(Tuple t, String key, int value, int len) {
        if(len == value) {
            if(t.term == null) {
                this.treeSize++;
            }
            t = new Tuple(key, value);
            return t;
        }
        char c = key.charAt(len);
//        if (t.children[c] == null) {
//            t.children[c] = new Tuple(t.term + c);
//        }
        if (t.children[c] == null) {
            t.children[c] = new Tuple(null);
        }
        t.children[c] = add(t.children[c], key, value, len+1);
        return t;
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
    public Iterable<String> wordsWithPrefix(String pref) {
        Queue nodes = new Queue();
        Tuple t = getWord(this.root, pref, 0);
        collect(t, new StringBuilder(pref), nodes);
        return nodes.toArray();
    }

    private void collect(Tuple t, StringBuilder sb, Queue nodes) {
        if (t == null) {
            return;
        }
        if (t.term != null) {
            nodes.enqueue(sb.toString());
        }
        for (char i = 0; i < 256; i++) {
            sb.append(i);
            collect(t.children[i], sb, nodes);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    @Override
    public int size() {
        return this.tree.size();
    }

    public String getWord(String key) {
        Checker.checkNull(key);
        Tuple res = getWord((Tuple) this.tree.dequeue(), key, 0);
        return res.term;
    }

    private Tuple getWord(Tuple node, String key, int position) {
//        Checker.checkNull(node);
        if (node == null) {
            return null;
        }
        if (position == key.length()) {
            return node;
        }
        char c = key.charAt(position);

        return getWord(node.children[c], key, position+1);
    }

}
