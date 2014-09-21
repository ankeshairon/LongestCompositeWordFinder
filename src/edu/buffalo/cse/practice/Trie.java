package edu.buffalo.cse.practice;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode(' ');
    }

    public void addWord(String word) {
        TrieNode currentNode = root;
        TrieNode nextNode;
        for (int i = 0; i < word.length(); i++) {
            nextNode = currentNode.getChild(word.charAt(i));
            if (nextNode == null) {
                nextNode = currentNode.addChild(word.charAt(i));
            }
            currentNode = nextNode;
        }
        currentNode.markEndOfWord(true);
    }

    public boolean isACompositeWord(String word) {
        TrieNode currentNode = root;

        for (int hop = 0; hop < word.length() - 1; hop++) {
            if ((currentNode = currentNode.getChild(word.charAt(hop))) == null) {
                //next character node not found, hence not a word
                return false;
            }
            if (currentNode.isEndOfAWord() && isAWordOrCombination(word.substring(hop + 1, word.length()))) {
                //it's a composite word
                return true;
            }
        }
        //either not a composite word or not a word, either way it's false
        return false;
    }

    private boolean isAWordOrCombination(String word) {
        TrieNode currentNode = root;
        TrieNode nextNode;
        int hop = 0;

        for (; hop < word.length() - 1; hop++) {
            if ((nextNode = currentNode.getChild(word.charAt(hop))) == null) {
                //next character node not found
                if (currentNode.isEndOfAWord()) {
                    return isAWordOrCombination(word.substring(hop, word.length()));
                } else {
                    return false;
                }
            }
            currentNode = nextNode;
            if (currentNode.isEndOfAWord() && isAWordOrCombination(word.substring(hop + 1, word.length()))) {
                return true;
            }
        }

        //reached last char
        if ((nextNode = currentNode.getChild(word.charAt(hop))) == null) {
            return false;
        }
        currentNode = nextNode;
        return currentNode.isEndOfAWord();
    }

    public void markRootAsNotAWord() {
        root.markEndOfWord(false);
    }
}