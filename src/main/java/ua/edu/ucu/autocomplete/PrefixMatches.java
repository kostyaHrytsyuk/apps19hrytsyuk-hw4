package ua.edu.ucu.autocomplete;

import ua.edu.ucu.Checker;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.Collections;

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
                    Tuple temp = new Tuple(s, s.length());
                    this.trie.add(temp);
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return this.trie.contains(word);
    }

    public boolean delete(String word) {
        return this.trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        Checker.checkNull(pref);
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        } else {
            return Collections.emptyList();
        }
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        Checker.checkNull(pref);
        if (pref.length() >= 2) {
            StringBuilder prefBuilder = new StringBuilder(pref);
            for (int i = 0; i < k-1; i++) {
                prefBuilder.append(".");
            }
            pref = prefBuilder.toString();
            return wordsWithPrefix(pref);
        } else {
            return Collections.emptyList();
        }
    }

    public int size() {
        return this.trie.size();
    }
}
