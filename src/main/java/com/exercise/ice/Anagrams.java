package com.exercise.ice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 
Problem:
Write me a program that accepts a block of text (English words), 
and outputs the groups of words that are anagrams of each other.  
For example, “apt”, “tap” and “pat” are anagrams of each other.
*/

public class Anagrams {

	// convert a given word into a string of its sorted characters
	// e.g. "cat", "act", "tac" => "act"
	public static String sortCharacter(String word) {
		word.trim();
		// select anyone of the following three sorting implementations
		return sortCharacter1(word);
	}

	// Java 8 stream and sorted
	public static String sortCharacter1(String word) {
		return Stream.of(word.split("")).sorted().collect(Collectors.joining());
	}

	// Java collection sort() function
	public static String sortCharacter2(String word) {
		char[] cword = word.toCharArray();
		java.util.Arrays.sort(cword);
		return String.valueOf(cword);
	}

	// insertion sorting
	public static String sortCharacter3(String word) {
		char[] cword = word.toCharArray();
		for (int i = 0; i < (cword.length - 1); i++) {
			for (int j = i + 1; j > 0; j--) {
				if (cword[j] < cword[j - 1]) {
					char temp = cword[j - 1];
					cword[j - 1] = cword[j];
					cword[j] = temp;
				}
			}
		}
		return String.valueOf(cword);
	}


	// Add word to HashMap, using its sorted characters string as key,
	// so all Anagrams of the sorted characters string are in Value by the same key 
	public static void addToMap2(Map<String, StringBuilder> anagramMap, String sortedChars, String word) {
		if (!anagramMap.containsKey(sortedChars)) { // new sorted characters
			anagramMap.put(sortedChars, (new StringBuilder(word)));
		} else { // sorted characters string already in map
			anagramMap.put(sortedChars, anagramMap.get(sortedChars).append(", " + word));
			// its a anagram group when there's more than word
			anagramGroupSet.add(sortedChars);
		}
	}

	final static String WordFileName = "src/main/resources/EnglishWords.txt";
	final static Map<String, StringBuilder> anagramMap = new HashMap<String, StringBuilder>();
	final static Set<String> anagramGroupSet = new TreeSet<String>(); 	// unique set of anagram group index

	
	public static void main(String[] args) throws IOException {
		int wordcnt = 0;
		for (String word : java.nio.file.Files.readAllLines(Paths.get(WordFileName), StandardCharsets.UTF_8)) {
			addToMap2(anagramMap, sortCharacter(word), word);
			wordcnt +=1;
		}

		for (String anagramIndex : anagramGroupSet) {
			System.out.println(anagramIndex + " - " + anagramMap.get(anagramIndex));
		}

		System.out.println("Total number of words         : " + wordcnt);
		System.out.println("Total number of anagram groups: " + anagramGroupSet.size());
	}

}
