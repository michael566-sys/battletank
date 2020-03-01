package friends;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendsApp {
	static Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) 
	throws IOException {
		System.out.print("Enter words file name => ");
		String wordsFile = stdin.nextLine();
		Scanner sc = new Scanner(new File(wordsFile));
		Graph g = new Graph (sc) ; 
		System.out.println("Enter start name: ");
		String start = stdin.nextLine();

		System.out.println("Enter end name: ");
		String end = stdin.nextLine();
		
		ArrayList <String> ans1 = Friends.shortestChain ( g, start, end ) ; 
		if (ans1.isEmpty()) { 
			System.out.println("Emptylist");
		}
		for ( int i = 0 ; i < ans1.size(); i ++ ) { 
			if (i == ans1.size()-1 ) {
				System.out.print(ans1.get(i));
			} else {
				System.out.print(ans1.get(i) + "--");
			}
		}
		System.out.println();
		System.out.println("Enter school name: ");
		String school = stdin.nextLine();
		
		ArrayList <ArrayList<String>> ans2 = Friends.cliques ( g, school) ;
		if (ans2.isEmpty()) { 
			System.out.println("Emptylist");
		}
		for ( int i = 0 ; i < ans2.size(); i ++ ) { 
			System.out.print ("First clique: ");
//			System.out.print("size: " + ans2.get(i).size() );
			
			for ( int j = 0 ; j < ans2.get(i).size() ; j ++ ) {
				if (j == ans2.get(i).size()-1 ) {
					System.out.print(ans2.get(i).get(j) );
				} else {
					System.out.print(ans2.get(i).get(j) + "--"); 
				}
			}
			System.out.println();
		}
		
		System.out.println("All connectors: ");
		ArrayList <String> ans3 = Friends.connectors ( g  ) ; 
		System.out.println("All connectors results: ");
		if (ans3.isEmpty()) { 
			System.out.println("Emptylist");
		}
		for ( int i = 0 ; i < ans3.size(); i ++ ) { 
			if (i == ans3.size()-1 ) {
				System.out.println(ans3.get(i));
			} else {
				System.out.print(ans3.get(i) + "--");
			}
		}

//		// words appear one per line in input file
//		// first line has number of words
//		int numWords = Integer.parseInt(sc.nextLine());
//		String[] allWords = new String[numWords];
//		for (int i=0; i < allWords.length; i++) {
//			allWords[i] = sc.nextLine().trim().toLowerCase();
//		}
//		sc.close();
//		
//		// build Trie
//		TrieNode root = Trie.buildTrie(allWords);
//		// print it for verification
//		Trie.print(root, allWords);
//		// do completion lists
//		completionLists(root, allWords);
	}
//	
//	TrieNode root = Trie.buildTrie(allWords);
//	// print it for verification
//	Trie.print(root, allWords);
//	// do completion lists
//	completionLists(root, allWords);
//}
//
//	private static void shortestChain(TrieNode root, String[] allWords) {
//		System.out.print("\ncompletion list for (enter prefix, or 'quit'): ");
//		String prefix = stdin.nextLine().trim().toLowerCase();
//		while (!"quit".equals(prefix)) {
//			ArrayList<TrieNode> matches = Trie.completionList(root, allWords, prefix);
//			printMatches(matches, allWords);
//			System.out.print("\ncompletion list for: ");
//			prefix = stdin.nextLine().trim().toLowerCase();
//		}
//	}
//	
//	private static void cliques (Graph g, String allWords) {
////	(ArrayList<TrieNode> matches, String[] allWords) {
//		if (matches == null) {
//			System.out.println("No match");
//			return;
//		}
//		System.out.print(allWords[matches.get(0).substr.wordIndex]);
//		for (int i=1; i < matches.size(); i++) {
//			System.out.print(","+allWords[matches.get(i).substr.wordIndex]);
//		}
//		System.out.println();
//	}
//	
//	private static void connectors(Graph g) {
////	shortestChain(TrieNode root, String[] allWords) {
//		System.out.print("\ncompletion list for (enter prefix, or 'quit'): ");
//		String prefix = stdin.nextLine().trim().toLowerCase();
//		while (!"quit".equals(prefix)) {
//			ArrayList<TrieNode> matches = Trie.completionList(root, allWords, prefix);
//			printMatches(matches, allWords);
//			System.out.print("\ncompletion list for: ");
//			prefix = stdin.nextLine().trim().toLowerCase();
//		}
//	}
}