
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

import java.util.Collections;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;
    private String[] words = {"abc", "abce", "abcd", "abcde", "abcdef"};

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load(words);
    }

    @Test
    public void testTrueDelete() {
        String del = "abcde";

        assertTrue(pm.delete(del));
    }

    @Test
    public void testFalseDelete() {
        String del = "sgl#";
        assertFalse(pm.delete(del));
    }

    @Test(expected = NullPointerException.class)
    public void testNullDelete() {
        pm.delete(null);
    }

    @Test
    public void testTrueContains() {
        String word = "abcd";
        assertTrue(pm.contains(word));
    }

    @Test
    public void testFalseContains() {
        String word = "sgl#";
        assertFalse(pm.contains(word));
    }

    @Test(expected = NullPointerException.class)
    public void testNullContains() {
        pm.contains(null);
    }

    @Test
    public void testEmptyWordsWithPrefixK() {
        assertEquals(Collections.emptyList(), this.pm.wordsWithPrefix("z", 5));
    }

    @Test
    public void testSize() {
        int expectedValue = this.words.length;
        assertEquals(expectedValue , this.pm.size());
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }



    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

}
