package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches() {
        this.trie = new RWayTrie();
    }

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        for (String word:strings) {
            if (word.length() >= 2) {
                String[] splitted = word.trim().split("\\s+");
                for (String s: splitted) {
                    for (char c: s.toCharArray()) {

                    }
                    Tuple temp = new Tuple(s);
                    this.trie.add(temp);
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean delete(String word) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
