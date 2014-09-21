package edu.buffalo.cse.practice;

public class TrieNode {

    private char value;
    private TrieNode[] children;
    private boolean endOfWord;

    public TrieNode(char value){
        this.value = value;
        //index to children for faster access. 26 elements for 26 alphabets. Reduced search time to half for the given list of words
        children = new TrieNode[26];
        endOfWord = false;
    }


    public TrieNode addChild(char c) {
        TrieNode newNode = new TrieNode(c);
        children[getArrayPosition(c)] = newNode;
        return newNode;
    }

    public TrieNode getChild(char c) {
        return children[getArrayPosition(c)];
    }

    public void markEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public boolean isEndOfAWord() {
        return endOfWord;
    }

    public int getArrayPosition (char c) {
        return c - 97;
    }

    public String toString(){
        return String.valueOf(value);
    }
}