package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {
	
	// prevent instantiation
	private Trie() { }
	
	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	public static TrieNode buildTrie(String[] allWords) {
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		// return null;
		
		TrieNode root = new TrieNode ( /*new Indexes (0, new Integer(0).shortValue(), new Integer(0).shortValue())*/ 
							null , null, null  ) ; 
		String newsub = new String ( "~" ) ; 
		String sl = new String ("") ; 
		String ss = new String ("") ;
		String s1 = new String ("") ; 
		TrieNode ptr = null ; 
		TrieNode prev = null ; 
		if ( allWords.length == 0 ) {
			return root; 
		}
		
		for ( int i = 0 ; i < allWords.length ; i ++ ) {
			String s = new String ( allWords [i]) ; 
			String sub = new String ( s ) ;
			System.out.println("initial sub: " + sub );
			int count = 1 ;
			
			if ( allWords.length == 1 ) {
				prev = null ; ptr = root ; 
				Indexes x = new Indexes ( 0 , new Integer(0).shortValue(), 
									new Integer(allWords[i].length()- 1).shortValue() ) ; 
				root.firstChild = new TrieNode ( x , null , null ) ; 
				return root; 
			}
			if ( root.firstChild == null ) {
				Indexes x1 = new Indexes ( i ,  
						new Integer(0).shortValue() , 
						new Integer(allWords[i].length() -1 ).shortValue() 
						) ; 
//				System.out.println( allWords[i].length() - 1 );
				System.out.println( "newNode 0: " + allWords[i].substring (0,allWords[i].length() ));
				System.out.println( "root0: ");
				print ( root, allWords ) ; 
				root.firstChild = new TrieNode ( x1 , null , null ) ; 
				prev = root ; ptr = root.firstChild; 
				continue;
			} else { prev = root ; ptr = root.firstChild;}
			System.out.println( "ptr at a.firstChild: " + allWords [ ptr.substr.wordIndex] ) ;

//			prev = null ; ptr = root; 
			
			while ( ptr!=null ) {
				newsub = new String ("~") ; 
//				if ( root.firstChild == null ) {
//					Indexes x1 = new Indexes ( i ,  
//							new Integer(0).shortValue() , 
//							new Integer(allWords[i].length() -1).shortValue() 
//							) ; 
//					root.firstChild = new TrieNode ( x1 , null , null ) ; 
//					prev = root ; ptr = root.firstChild; 
//					System.out.println( "ptr check root.firstChild: " + allWords [ ptr.substr.wordIndex] ) ;
//					
//				} else {
//					if ( count == 1 ) {
//						prev = root ; ptr = root.firstChild ;	count -- ; 
//					}
					
					System.out.println("ptr location: " + allWords[ptr.substr.wordIndex]);
					s1 = new String ( allWords [ ptr.substr.wordIndex ].substring 
							(ptr.substr.startIndex , ptr.substr.endIndex + 1 ) ) ; 
					System.out.println("s1: " + s1);
					if ( sub.length() < s1.length () ) {
						ss = new String ( sub ) ; 
						sl = new String ( s1 ) ; 
					} else {
						ss = new String ( s1 ) ; 
						sl = new String ( sub ) ; 
					}
					
					for ( int j = 0 ; j < ss.length () ; j ++ ) {
						if ( ss.charAt(j) == sl.charAt ( j ) ) {
							if ( newsub.equals("~") ) {
								newsub = new String ( Character.toString (ss.charAt(j) )) ;
							} else {
								newsub += Character.toString (ss.charAt(j));
							}
						} else {
							break ;
						}
					}
					System.out.println("newsub: " + newsub);

					if ( sub.startsWith( newsub )) {

						if ( ptr.firstChild == null ) {
							ptr.substr.endIndex = new Integer (ptr.substr.startIndex + newsub.length() - 1).shortValue(); 
							ptr.firstChild = new TrieNode ( 
									new Indexes ( ptr.substr.wordIndex, 
											new Integer (ptr.substr.endIndex + 1).shortValue() ,
											new Integer (allWords [ptr.substr.wordIndex].length() - 1).shortValue() ) , 
									null , 
									null 							
							);
							System.out.println( "newNode 1: " + allWords[ptr.substr.wordIndex].substring (ptr.substr.endIndex + 1,allWords [ptr.substr.wordIndex].length()  ));
							System.out.println();
							System.out.println( "root1: ");
							print ( root, allWords ) ; 
							prev = ptr ; ptr = ptr.firstChild ; 
//							ptr.sibling = new TrieNode ( new Indexes ( i , 
//													new Integer (ptr.substr.startIndex).shortValue() , 
//													new Integer (s.length() -1).shortValue()  )  , 
//											null,
//											null); 
//							System.out.println( "newNode 2: " + allWords[i].substring (ptr.substr.startIndex,s.length()  ));
//							System.out.println();
							prev = ptr; 
							ptr = ptr.sibling; 
							break ; 
						} else {
//							if ( allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex, ptr.substr.endIndex+1).startsWith(newsub)) {
							if ( newsub.length() < (ptr.substr.endIndex + 1 - ptr.substr.startIndex ) ) {
//							if ( allWords[ptr.substr.wordIndex].substring (ptr.substr.startIndex, ptr.substr.endIndex + 1).indexOf( newsub ) == 0  
//									&& newsub.length() < (ptr.substr.endIndex + 1 - ptr.substr.startIndex )) {
								if (prev.firstChild != ptr ) {
									TrieNode temp = new TrieNode ( new Indexes ( ptr.substr.wordIndex , 
											new Integer(ptr.substr.startIndex).shortValue(), //0
											new Integer(0 + newsub.length() -1  ).shortValue() ) , //0 + newsub.length()
											ptr,
											null
											);
									System.out.println("ptr for longer child: " + allWords[ptr.substr.wordIndex].
											substring(ptr.substr.startIndex, ptr.substr.endIndex +1 ));
				//					prev.firstChild = temp; 
									prev.sibling = temp;
									ptr.substr.startIndex = new Integer(ptr.substr.startIndex + newsub.length()).shortValue(); 
	//								prev=ptr; ptr = ptr.firstChild;
	//								ptr = prev.firstChild; 
				//					prev = prev.firstChild; 
									prev = prev.sibling ;

									while (ptr != null ) {
										prev = ptr ; 
										ptr = ptr.sibling; 
									}
									break ; 
									
									
								} else {
									TrieNode temp = new TrieNode ( new Indexes ( ptr.substr.wordIndex , 
											new Integer(ptr.substr.startIndex).shortValue(), //0
											new Integer(0 + newsub.length() -1  ).shortValue() ) , //0 + newsub.length()
											ptr,
											null
											);
									System.out.println("ptr for longer child: " + allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex, ptr.substr.endIndex +1 ));
									prev.firstChild = temp; 
									
									ptr.substr.startIndex = new Integer(ptr.substr.startIndex + newsub.length()).shortValue(); 
	//								prev=ptr; ptr = ptr.firstChild;
	//								ptr = prev.firstChild; 
									prev = prev.firstChild; 
									
									while (ptr != null ) {
										prev = ptr ; 
										ptr = ptr.sibling; 
									}
									break ; 
								}
								
							}
							prev = ptr ; ptr = ptr.firstChild ;
							sub = new String (sub.substring( 0 + newsub.length() ) ); 
							System.out.println( "sub after shift down to firstChild: " + sub);
						}
//						prev = ptr ; ptr = ptr.firstChild; 
					
					} else if ( !s.startsWith ( newsub ) ) {
						prev = ptr ; ptr = ptr.sibling ; 
						
					}
					
//				}
			} // end of while loop 
			System.out.println( "root2: ");
			print ( root, allWords ) ; 
			System.out.println( sub );
			System.out.println(prev.substr.startIndex);
			System.out.println();
			prev.sibling = new TrieNode ( new Indexes ( i , 
					new Integer(prev.substr.startIndex).shortValue() , 
					new Integer(allWords [i].length()-1).shortValue() ) , 
					null , 
					null ) ; 
			
			System.out.println( "newNode3: " + allWords[i].substring (prev.substr.startIndex,allWords [i].length() ));
			System.out.println();
			
			System.out.println( "root3: ");
			print ( root, allWords ) ; 
		}// end of for loop 

		/* Think of a word that changes multiple levels, if cannot, then stick with this method */
		/* Though same leve may have same start index and same column may have the same wordIndex,
		 * is this always true for all cases? Think about cases where these two conditions are not
		 * true. 
		 */

		return root; 
	}

	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public static ArrayList<TrieNode> completionList(TrieNode root,
										String[] allWords, String prefix) {
		
	
		if (root == null) { return null; } 
		
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		//return null;
		
		// 1. does this work for only 1 node?
		// 2. does this work for no node? 
		// 3. does this work for nodes passes through several layers? 
		TrieNode prev = root; 
		TrieNode ptr = root.firstChild; 
		
		ArrayList<TrieNode> a = new ArrayList<>() ; 
		String s1 = new String() ; 
		String s2 = new String() ; 
		String s3 = new String() ; 
		String sub = new String (prefix) ; 
		String comstr = new String () ; 
		int n1 = 0 ; 
		int n2 = 0 ;
		int n3 = 0 ; 
		int n4 = 0 ; 

		System.out.println("inital sub: " + sub);
		while ( !sub.equals("") ) {
			System.out.println("1: " +  allWords[ptr.substr.wordIndex] );
			System.out.println("2: " + ptr.substr.startIndex );
			
			s1 = new String (allWords[ptr.substr.wordIndex]) ; 
			n1 =  (int)ptr.substr.startIndex; 
			n2 = (int)ptr.substr.endIndex + 1 ; 
			System.out.println( "s1: " + s1 );
			
//			while ( ( allWords[ptr.substr.wordIndex].// try to assign each component into different int varaibles
//					substring((int)ptr.substr.startIndex,
//							(int)ptr.substr.endIndex + 1 ).
//					indexOf ( sub ) ) == -1 ) {
//			while ( s1.substring(n1, n2).indexOf (sub) == -1 ) {
			while ( !compare( sub, s1.substring(n1, n2) )) {// search whether at the same level there exist a common prefix
				prev = ptr; ptr = ptr.sibling; // does not have the index for possible, need to do a for loop
				System.out.println("scanning same level: " + allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex, ptr.substr.endIndex + 1 ));
				s1 = new String (allWords[ptr.substr.wordIndex]) ; 
				n1 =  (int)ptr.substr.startIndex; 
				n2 = (int)ptr.substr.endIndex + 1 ;
				System.out.println( "s1: " + s1 );
			}
			System.out.println("found ptr at same level : " + allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex, ptr.substr.endIndex + 1 ));

			if (ptr == null) { return a ; }
//			else {
				s2 = new String (allWords[ptr.substr.wordIndex]) ; 
				n3 =  (int)ptr.substr.startIndex; 
				n4 = (int)ptr.substr.endIndex + 1 ; 
				s2 = new String ( s2.substring(n3, n4) ) ; 
				System.out.println("found ptr at same level s2: " + s2);
				comstr = new String ( commonStr ( sub, s2 ) ) ; 
				System.out.println("found comstr at same level s2: " + comstr);
				System.out.println("sub before trancation: " + sub );
//				sub = sub.substring ( ptr.substr.endIndex +1 - ptr.substr.startIndex ); 
				if ( sub.length() <= s2.length() && comstr.length() <= s2.length() ) {
					sub = new String ("") ; 
				} else {
					sub = sub.substring ( ptr.substr.endIndex +1 - ptr.substr.startIndex ); 
				}
				System.out.println( "trancated sub: " + sub );
					prev = ptr ; 
					ptr = ptr.firstChild ; 
				
				
//			}
		}// end of while loop
//		System.out.println("Identified ptr: " + allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex, ptr.substr.endIndex + 1 ));
		TrieNode ptr1 = null;
		if ( ptr == null ) { a.add(prev) ; return a; } 
		
		while (ptr!=null) {
			System.out.println( "Current ptr position: " + allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex, ptr.substr.endIndex + 1 ));
			ptr1 = ptr;
			while ( ptr1.firstChild != null ) {
				ptr1 = ptr1.firstChild; 
				
			}
			while ( ptr1 != null ) {
				a.add( ptr1 ) ; ; 
				System.out.println("added ptr1: " + allWords[ptr1.substr.wordIndex]);
				if ( ptr1.equals(ptr) ) { break ;} 
				ptr1 = ptr1.sibling ;
			}
			ptr = ptr.sibling;
			
		}/// DOESN'T WORK FOR POTT!!!! SOMETHING WRONG WITH TRANCATING SUB AND JUMP OUT OF THE WHILE LOOP 
		/// DOESN'T WORK FOR DOOM either
		
		
		
	
//		if ( ( allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex,
//				ptr.substr.endIndex + 1 ).compareTo ( prefix ) ) == 0 ) {
//				s1 += allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex,
//						ptr.substr.endIndex + 1 ) ; 
//				prev
//				ptr = ptr.firstChild ; 
//				
//		}
		
		return a; 
	}
	private static String commonStr ( String prefix, String nodeStr ) {
		String ss = new String () ; 
		String sl = new String () ; 
		if ( prefix.length() < nodeStr.length () ) {
			ss = new String ( prefix ) ; 
			sl = new String ( nodeStr ) ; 
		} else {
			ss = new String ( nodeStr ) ; 
			sl = new String ( prefix ) ; 
		}
		String newsub = new String ("~") ; 
		
		for ( int j = 0 ; j < ss.length () ; j ++ ) {
			if ( ss.charAt(j) == sl.charAt ( j ) ) {
				if ( newsub.equals("~") ) {
					newsub = new String ( Character.toString (ss.charAt(j) )) ;
				} else {
					newsub += Character.toString (ss.charAt(j));
				}
			} else {
				break ; 
			}
		}
		return newsub; 
	}
	private static boolean compare( String prefix, String nodeStr ) {
		String ss = new String () ; 
		String sl = new String () ; 
		if ( prefix.length() < nodeStr.length () ) {
			ss = new String ( prefix ) ; 
			sl = new String ( nodeStr ) ; 
		} else {
			ss = new String ( nodeStr ) ; 
			sl = new String ( prefix ) ; 
		}
	//	String newsub = new String ("~") ; 
		
		for ( int j = 0 ; j < ss.length () ; j ++ ) {
			if ( ss.charAt(j) == sl.charAt ( j ) ) {
				continue; 
	//			if ( newsub.equals("~") ) {
	//				newsub = new String ( Character.toString (ss.charAt(j) )) ;
	//			} else {
	//				newsub += Character.toString (ss.charAt(j));
	//			}
			} else {
				return false; 
			}
		}
		return true; 
	}
	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	
	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
							.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
