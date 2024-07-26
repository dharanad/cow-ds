package ai.kunato;

import java.util.*;
import java.util.stream.Collectors;

public class Trie {
    private final List<TrieNode> versions;
    private int currentVersion;
    private TrieNode currentRoot; // Root as sentinel node

    public Trie() {
        this.versions = new ArrayList<>();
        currentRoot = new TrieNode(null, new HashMap<>());
    }
    public int insert(final String key, final Integer value) {
        final char[] keyArr = key.toCharArray();
        final TrieNode newRoot = insert(this.currentRoot, keyArr, 0, value);

        assert currentRoot.hashCode() != newRoot.hashCode();

        versions.add(this.currentRoot);

        this.currentRoot = newRoot;
        return ++currentVersion;
    }

    public Integer search(final String key, final int version) {
        assert version < versions.size();
        final char[] charArr = key.toCharArray();
        TrieNode ptr = this.versions.get(version);
        for (final char c : charArr) {
            final TrieNode child = ptr.getChild(c);
            if (child == null) {
                return null;
            }
            ptr = child;
        }
        return ptr.getValue();
    }

    private TrieNode insert(TrieNode root, char[] key, int idx, Integer value) {
        final Map<Character, TrieNode> childRefs = root != null ? copy(root.getChildren()) : new HashMap<>();
        if (idx == key.length) {
            return new TrieNode(value, childRefs);
        }
        childRefs.put(key[idx], insert(childRefs.get(key[idx]), key, idx + 1, value));
        return new TrieNode(root != null ? root.getValue() : null, childRefs);
    }

    private Map<Character, TrieNode> copy(final Map<Character, TrieNode> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
