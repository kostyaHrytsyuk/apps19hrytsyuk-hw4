package ua.edu.ucu.tries;

import ua.edu.ucu.Checker;
import ua.edu.ucu.utils.Queue;

public class RWayTrie implements Trie {

    private Tuple root;
    private int treeSize;

    public RWayTrie() {
        this.root = new Tuple("");
    }
    
    @Override
    public void add(Tuple t) {
        Checker.checkNull(t);
        add(t.term, t.weight);
    }

    @Override
    public boolean contains(String word) {
        Checker.checkNull(word);
        return getWord(root, word, 0) != null;
    }

    @Override
    public boolean delete(String word) {
        Checker.checkNull(word);
        Tuple result = delete(this.root, word, 0);
        if (result != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        Queue nodes = new Queue();
        if (pref.indexOf('.') < 0) {
            Tuple t = getWord(this.root, pref, 0);
            collect(t, new StringBuilder(pref), nodes);
        } else {
            collect(this.root, new StringBuilder(), pref, nodes);
        }

        return nodes.toArray();
    }

    @Override
    public int size() {
        return this.treeSize;
    }

    private void collect(Tuple t, StringBuilder sb, Queue nodes) {
        if (t == null) {
            return;
        }
        if (t.term != null && !t.term.isEmpty()) {
            nodes.enqueue(sb.toString());
        }
        for (char i = 0; i < Tuple.R; i++) {
            sb.append(i);
            collect(t.children[i], sb, nodes);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private void collect(Tuple t, StringBuilder sb, String pattern, Queue nodes) {
        if(t == null) {
            return;
        }
        int len = sb.length();
        if (len <= pattern.length() && t.term != null && !t.term.isEmpty()) {
            nodes.enqueue(sb.toString());
        }
        if (len == pattern.length()) {
            return;
        }
        char c = pattern.charAt(len);
        if (c == '.') {
            for (char i = 0; i < Tuple.R; i++) {
                iterateOverChildren(t.children[i], sb, pattern, nodes, i);
            }
        } else {
            iterateOverChildren(t.children[c], sb, pattern, nodes, c);
        }
    }

    private void iterateOverChildren(Tuple child, StringBuilder sb, String pattern, Queue nodes, char i) {
        sb.append(i);
        collect(child, sb, pattern, nodes);
        sb.deleteCharAt(sb.length() - 1);
    }

    private Tuple getWord(Tuple node, String key, int position) {
        if (node == null) {
            return null;
        }
        if (position == key.length()) {
            return node;
        }
        char c = key.charAt(position);

        return getWord(node.children[c], key, position+1);
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
        if (t.children[c] == null) {
            t.children[c] = new Tuple(null);
        }
        t.children[c] = add(t.children[c], key, value, len+1);
        return t;
    }


    private Tuple delete(Tuple t, String key, int len) {
        if (t == null) {
            return null;
        }
        if (len == key.length()) {
            if (t.term != null) {
                this.treeSize--;
            }
            Tuple temp = new Tuple(null);
            System.arraycopy(t.children,0, temp.children, 0, t.children.length);
            t = temp;
        } else {
            char c = key.charAt(len);
            Tuple del = delete(t.children[c], key, len+1);
            if (del == null) {
                return del;
            } else {
                t.children[c] = del;
                return t;
            }
        }

        if (t.term != null && !t.term.isEmpty()) {
            return t;
        }
        for (int i = 0; i < Tuple.R; i++) {
            if (t.children[i] != null) {
                return t;
            }
        }
        return null;
    }
}
