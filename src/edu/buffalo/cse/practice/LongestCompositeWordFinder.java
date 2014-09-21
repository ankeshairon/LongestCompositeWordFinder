package edu.buffalo.cse.practice;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Takes the name of the file as input argument which contains a list of words
 * Finds all composite words (made from combination of two words) from the list
 */
public class LongestCompositeWordFinder {


    public static void main(String[] args) throws IOException {
        final long start = System.currentTimeMillis();

        TreeMap<Integer, List<String>> wordLengthIndex = new TreeMap<Integer, List<String>>(Collections.reverseOrder());
        BufferedReader reader = new BufferedReader(new FileReader(new File("wordsforproblem.txt")));

        Trie trie = buildTrie(wordLengthIndex, reader);

        List<String> compositeWords = findAllCompositeWords(wordLengthIndex, trie);

        final long stop = System.currentTimeMillis();
        System.out.println(stop - start);
        System.out.println(compositeWords.size());

        writeOutputToFile(compositeWords);
    }

    private static void writeOutputToFile(List<String> compositeWords) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("output-ankesh.txt");
        StringBuilder builder = new StringBuilder();
        for (String compositeWord : compositeWords) {
            builder.append(compositeWord).append("\n");
        }
        writer.println(builder.toString());
        writer.close();
    }

    private static List<String> findAllCompositeWords(TreeMap<Integer, List<String>> wordLengthIndex, Trie trie) {
        List<String> compositeWords = new ArrayList<String>();
        List<String> words;
        for (Integer length : wordLengthIndex.keySet()) {
            words = wordLengthIndex.get(length);
            for (String word : words) {
                if (trie.isACompositeWord(word)) {
                    compositeWords.add(word);
                }
            }
        }
        return compositeWords;
    }

    private static Trie buildTrie(TreeMap<Integer, List<String>> wordLengthIndex, BufferedReader reader) throws IOException {
        Trie trie = new Trie();

        //this list to be used only if the problem statement asks to find the length of the longest composite word
        List<String> wordsOfLength;
        Integer wordLength;
        String word;

        while ((word = reader.readLine()) != null) {
        // build trie
            trie.addWord(word);
            wordLength = word.length();
            if ((wordsOfLength = wordLengthIndex.get(wordLength)) == null) {
                wordsOfLength = new ArrayList<String>();
                wordLengthIndex.put(wordLength, wordsOfLength);
            }
            wordsOfLength.add(word);
        }
        trie.markRootAsNotAWord();
        return trie;
    }
}

