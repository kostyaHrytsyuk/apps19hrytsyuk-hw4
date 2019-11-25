package ua.edu.ucu.tries;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class RWayTrieTest {

    private RWayTrie trie;
    private String[] words = {"abc", "abce", "abcd", "abcde", "abcdef"};

    @Before
    public void setUp() {
        trie = new RWayTrie();
        for (String w : words) {
            trie.add(new Tuple(w, w.length()));
        }
    }

    @Test
    public void testWords() {
        Iterable<String> result = this.trie.words();

        assertThat(result, containsInAnyOrder(this.words));
    }

    @Test
    public void testSize() {
        assertEquals(this.words.length, this.trie.size());
    }
}