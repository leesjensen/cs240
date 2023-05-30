package spell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {

    private ITrie trie;

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        trie = new Trie();
        File f = new File(dictionaryFileName);
        Scanner scanner = new Scanner(f);

        HashSet<String> test = new HashSet<>();
        while (scanner.hasNext()) {
            String n = scanner.next();
            trie.add(n);

            generateInsertion(n, test);
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        inputWord = inputWord.toLowerCase();

        var result = trie.find(inputWord);
        if (result != null) {
            return inputWord;
        }
        return "";

    }

    private final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();


    //    Insertion Distance: A string s has an insertion distance 1 from another string t if and only if t has a deletion distance of 1 from s. The only strings that are an insertion distance of 1 from “ask” are “aask”, “bask”, “cask”, … “zask”, “aask”, “absk”, “acsk”, … “azsk”, “asak”, “asbk”, “asck”, … “aszk”, “aska”, “askb”, “askc”, … “askz”. Note that if a string s has an insertion distance of 1 from another string t then |s| = |t|+1. Also, there are exactly 26* (|t| + 1) strings that are an insertion distance of 1 from t. The dictionary may contain 0 to n of the strings one insertion distance from t.
    private void generateInsertion(String inputWord, HashSet<String> values) {
        var chars = inputWord.toCharArray();
        for (var i = 0; i <= chars.length; i++) {
            var prefix = new StringBuilder();
            for (var pi = 0; pi < i; pi++) {
                prefix.append(chars[pi]);
            }
            var p = prefix.toString();
            var suffix = new StringBuilder();
            for (var si = i; si < chars.length; si++) {
                suffix.append(chars[si]);
            }
            var s = suffix.toString();

            for (var c : alphabet) {
                System.out.printf("%s%c%s%n", p, c, s);
            }
        }
    }

//    Deletion Distance: A string s has a deletion distance 1 from another string t if and only if t is equal to s with one character removed. The only strings that are a deletion distance of 1 from “bird” are “ird”, “brd”, “bid”, and “bir”. Note that if a string s has a deletion distance of 1 from another string t then |s| = |t| -1. Also, there are exactly |t| strings that are a deletion distance of 1 from t. The dictionary may contain 0 to n of the strings one deletion distance from t.
//    Transposition Distance: A string s has a transposition distance 1 from another string t if and only if t is equal to s with two adjacent characters transposed. The only strings that are a transposition Distance of 1 from “house” are “ohuse”, “huose”, “hosue” and “houes”. Note that if a string s has a transposition distance of 1 from another string t then |s| = |t|. Also, there are exactly |t| - 1 strings that are a transposition distance of 1 from t. The dictionary may contain 0 to n of the strings one transposition distance from t.
//    Alteration Distance: A string s has an alteration distance 1 from another string t if and only if t is equal to s with exactly one character in s replaced by a lowercase letter that is not equal to the original letter. The only strings that are an alternation distance of 1 from “top” are “aop”, “bop”, …, “zop”, “tap”, “tbp”, …, “tzp”, “toa”, “tob”, …, and “toz”. Note that if a string s has an alteration distance of 1 from another string t then |s| = |t|. Also, there are exactly 25* |t| strings that are an alteration distance of 1 from t. The dictionary may contain 0 to n of the strings one alteration distance from t.
}
