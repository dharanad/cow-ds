package ai.kunato;

import java.util.Map;

public final class TrieNode {
    final Integer value;
    final Map<Character, TrieNode> children;

    public TrieNode(Integer value, Map<Character, TrieNode> children) {
        this.value = value;
        this.children = children;
    }

    public Integer getValue() {
        return value;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public TrieNode getChild(final char c) {
        return this.children.get(c);
    }
}
