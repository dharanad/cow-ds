package ai.kunato;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void testTrieNode() {
        final TrieNode SUT = new TrieNode(1, new HashMap<>());
        assertNotNull(SUT);
        assertEquals(Integer.valueOf(1), SUT.getValue());
        assertEquals(SUT.getChildren().size(), 0);
        assertNull(SUT.getChild('a'));
    }

    @Test
    public void testTrieInsert() {
        final Trie SUT = new Trie();
        int prevVersion = 0;
        final List<String> keys = List.of("cat", "dog", "Pet", "ZOO");
        int counter = 100;
        for (final String key : keys) {
            int newVersion = SUT.insert(key, ++counter);
            assertTrue(newVersion > prevVersion);
            prevVersion = newVersion;
        }
    }

    @Test
    public void testTrieSearch() {
        final Trie SUT = new Trie();
        final List<String> keys = List.of("cat", "dog", "Pet", "ZOO");
        int counter = 100;
        for (final String key : keys) {
            SUT.insert(key, ++counter);
        }

        Integer got = SUT.search("cat", 1);
        assertEquals(Integer.valueOf(101), got);

        got = SUT.search("dog", 1);
        assertNull(got);

        got = SUT.search("Pet", 3);
        assertEquals(Integer.valueOf(103), got);
    }
}