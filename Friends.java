package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		ArrayList <String> shortest = new ArrayList <>();
		String p1a = new String (p1.toLowerCase()) ;  
		String p2a = new String (p2.toLowerCase()) ; 
		
		if ( p1a.equals(p2a)) {
			return shortest; 
		}
		if ( g == null || p1a == null || p2a == null ) {
			return shortest; 
		}
		 

		boolean visited [] = new boolean [g.members.length]; 
		for ( int k = 0 ; k < visited.length ; k ++ )  {
			visited [k] = false; 
		}
		
		int previous [] = new int [g.members.length] ; 
		for ( int l = 0 ; l < previous.length ; l ++ )  {
			previous [l] = -1 ; 
		}
		
		Queue <Integer> queue = new Queue <>() ; 

		for ( int i = 0 ; i < g.members.length; i ++ ) { 			
			if ( !visited [i] && g.members[i].name.toLowerCase().equals(p1a)) {
				
				if ( previous [i] == -1 ) {
					previous [i] = -1 ; 
				}
				
				visited [ i ] = true;  
				queue.enqueue ( i ) ; 

				while ( !queue.isEmpty() ) { 
					
					int a = queue.dequeue () ; 
					
					for ( Friend j = g.members[a].first ; j != null ; j = j.next ) {
						
						int num = j.fnum; 
						if ( !visited [num] )  {
							visited [num] = true; 
							queue.enqueue(num);
							previous [num] = a; 
							
						}
						if ( g.members[num].name.toLowerCase().equals (p2a) ) {
							shortest = add ( previous , g , p1a , p2a ) ;
							return shortest; 
						}
						
					}//inner for loop 

				}//while loop
			}//inner if loop 
		}//outer for loop 
				
		return shortest; 
		
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		//return null;
	}
	
	private static ArrayList <String> add ( int array[], Graph g, String p1, String p2) {
		ArrayList <String>shortest = new ArrayList <> (); 
		
		
		int start = 0 ; 
		int end = 0; 
		for ( int i = 0 ; i < g.members.length ; i ++ ) {
			if (g.members[i].name.toLowerCase().equals(p1) ) {
				start = i ; 
			}
			if (g.members[i].name.toLowerCase().equals(p2) ) {
				end = i ; 
			}
		}
		Stack <Integer> stack = new Stack () ; 
		stack.push (end);

		int a = end ; 
		a = array [a] ;
		
		while ( a != -1 ) {
			stack.push( a );
			a = array [a] ; 
		}
		int b = 0 ; 
		while (!stack.isEmpty()) {
			b = stack.pop() ; 
			shortest.add(g.members[b].name) ; 
		}
			
		return shortest; 
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		ArrayList <ArrayList <String>> answer = new ArrayList <>();
		
		if (school == null||g == null) {
			return answer; 
		}
		String sc = new String (school) ; 
		
		sc = sc.toLowerCase(); 
		
		boolean visited [] = new boolean [g.members.length]; 
		for ( int k = 0 ; k < visited.length ; k ++ )  {
			visited [k] = false; 
		}
		
//		int previous [] = new int [g.members.length] ; 
//		for ( int l = 0 ; l < previous.length ; l ++ )  {
//			previous [l] = -1 ; 
//		}
		
		
//		int previous [] = new int [g.members.length] ; 
//		for ( int l = 0 ; l < previous.length ; l ++ )  {
//			previous [l] = -1 ; 
//		}
		

//		Stack <Integer> stk = new Stack <>() ; 
//		boolean iteration = false; 

		for ( int i = 0 ; i < g.members.length ; i ++ ) { 			
			if ( !visited [i] && g.members[i].school!=null && g.members[i].school.toLowerCase().equals (sc) ) {
				
//				if ( previous [i] == -1 ) {
//					previous [i] = -1 ; 
//				}
				Queue <Integer> queue = new Queue <>() ; 
				ArrayList <String> cliq = new ArrayList <>();
				
				visited [ i ] = true;  
				queue.enqueue ( i ) ; 
				cliq.add(g.members[i].name) ;
				System.out.println("First add :" +g.members[i].name);
				while ( !queue.isEmpty() ) { 
					
					int a = queue.dequeue () ; 
//					System.out.print (a+ ": " + g.members[a].name);
//					System.out.println();
					//isConnected ( int a, int b, Graph g, String school ) 
					
					
					for ( Friend j = g.members[a].first ; j != null ; j = j.next ) {
						
						int num = j.fnum; 
						if ( !visited [num] && g.members[num].school!=null && g.members[num].school.toLowerCase().equals (sc))  {
							visited [num] = true; 
							queue.enqueue(num);
//							previous [num] = a; 
							cliq.add(g.members[num].name) ; 
							System.out.println("Second add :" +g.members[num].name);
						}
//						if ( g.members[num].name.equals (p2) ) {
//							cliq = add ( previous , g , p1 , p2 ) ;
//							return cliq; 
//						}
						
					}//inner for loop 
					
					
				}//while loop
				
				answer.add(cliq) ; 
				
			}//inner if loop 
		}//outer for loop 
				
		return answer; 
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		//return null;
		
	}
	
//	private static ArrayList <ArrayList<String>>addString ( Stack<Integer> stk, ArrayList <ArrayList<String>>addString , Graph g, String school ) {
//		ArrayList <ArrayList<String>>addStr1 = addString; 
//		
////		int index = 0 ; 
////		for ( int i = 0  ; i < addStr1.size(); i ++ ) {
////			if ( addStr1.get(i).isEmpty() ) { 
////				index = i ; 
////				break ;
////			}
////		}
//		ArrayList <String> b = new ArrayList <String> () ; 
//
//		while (!stk.isEmpty() ) {
//			
//			int n = stk.pop();  
//			b.add( g.members[n].name ) ; 			
//		}
//		
//		addStr1.add(b) ; 
//		
//		return addStr1; 
//	}
	
//	private static boolean isConnected ( int a, int b, Graph g, String school ) {
//		
//		for ( Friend j = g.members[a].first ; j != null ; j = j.next ) {
//			
//			int num = j.fnum; 
//			
//			if ( num == b ) { 
//				return true; 
//			}
//			
//		}
//		return false; 
//	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		ArrayList<String> answer = new ArrayList<> () ; 
		if (g == null) {
			return answer; 
		}
		int [] dfsnum = new int [ g.members.length ] ; 
		for ( int i = 0 ; i < dfsnum.length ; i ++ ) {
			dfsnum [i] = 0 ; 
		}
		int [] back = new int [ g.members.length ] ; 
		for ( int i = 0 ; i < back.length ; i ++ ) {
			back [i] = 0 ; 
		}
		boolean [] visited = new boolean [g.members.length];
		for ( int i = 0 ; i < visited.length ; i ++ ) {
			visited [i] = false ; 
		}
		int count1 = 0 ; int count2 = 0 ; 
		for ( int i = 0 ; i < visited.length ; i ++ ) {
			if ( !visited [i] ) {
				visited [i] = true ;					
				answer = dfs ( i , count1, count2, dfsnum, back, visited, g , answer ) ;
					
			}
			
		}
		
//		for ( int i = 0 ; i < answer.size () ; i++ ) {
//			Friend e = g.members [i].first;
//			System.out.println(  "Check name :" + g.members [i].name );
//			int count = 0 ; 
//			while (e!=null) {
//				e = e.next; 
//				count ++ ; 
//			}
//			if ( count == 1 ) {
//				answer.remove(i) ; 
//			}
//			
//		}
//		
//		for ( int i = 0 ; i < answer.size () ; i++ ) {
//			Friend e = g.members [i].first;
//			if (  g.members [i].first.next == null && !answer.contains( g.members[g.members[i].first.fnum].name)) {
//				answer.add(g.members[g.members[i].first.fnum].name) ; 
//			}
//		}
		return answer; 
		

		
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
//		 return null;
		
	}
	private static int visitednumber ( boolean visited [] ) { 
		int num = 0 ; 
		for ( int i = 0 ; i < visited.length; i ++ ) {
			if (visited[i]) num ++ ; 
		}
		return num; 
	}
	private static ArrayList<String> dfs ( int v, int count1 , int count2, int []dfsnum , int []back  ,boolean [] visited, Graph g , ArrayList<String> answer ) {
		String a = new String () ;
//		ArrayList <String> b = new ArrayList<>() ;
		count1 = visitednumber ( visited ); 
		count2  = visitednumber ( visited  ); 
		dfsnum[v] = count1  ; 
		back [v] = count2; 
		System.out.println( g.members[v].name + " " + dfsnum[v] + " " + back[v] ) ; 
		visited [v] = true ;
		 
		for ( Friend j = g.members[v].first;  j !=null ; j = j.next ) {
			if ( !visited [j.fnum] ) {
				visited [j.fnum] = true; 
				System.out.println( count1 + " " + count2 );
				
				answer = dfs ( j.fnum , count1, count2, dfsnum, back, visited, g, answer ) ; 
				System.out.println( "Current " + g.members[v].name + ": " + " dfsnum: " + dfsnum[v] + " back: " + back[v] );
				System.out.println( "Current " + g.members[j.fnum].name + ": " + " dfsnum: " + dfsnum[j.fnum] + " back: " + back[j.fnum] );

				if (dfsnum[v] > back[j.fnum] ) {
					back [v] = Math.min( back[v] , back [j.fnum] ) ; 
					System.out.println( "condition1: " + g.members[v].name + ": " + " dfsnum: " + dfsnum[v] + " back: " + back[v] );
				}
				
				if (dfsnum [v] <=back [j.fnum] /*&& (onlyNbr (j.fnum, v, g ))*/ ) {
//					if (dfsnum [v] <=back [j.fnum] /*&& (onlyNbr (j.fnum, v, g ))*/ ) {
//						System.out.println( "added name: " + g.members[v].name ) ; 
//					}
					if (Math.abs(dfsnum[v]-back[j.fnum]) < 1 && Math.abs(dfsnum[v]-dfsnum[j.fnum]) <=1 && back[j.fnum] ==1 && v == 0 ) {
//					if ( ( v == 0 ) && (onlyNbr ( v, j.fnum, /*j.fnum, v,*/ g ) )){
						continue; 
					}
	
					if (!checkdup ( answer , g.members[v].name ) && !checknoneone ( v , g ) ) {
						answer.add(g.members[v].name) ; 
						System.out.println( "added name: " + g.members[v].name ) ; 
					}	
				}
//				} else if  (dfsnum [v] <=back [j.fnum] /*&& (onlyNbr ( v, j.fnum, g ))*/ ) {
//					if (dfsnum [v] <=back [j.fnum] /*&& (onlyNbr ( v, j.fnum, g ))*/ ) {
//						System.out.println( "added name: " + g.members[j.fnum].name ) ; 
//					}
//					
//					if ( ( v == 0 ) && (onlyNbr (v, j.fnum, g ) ) {
//						continue; 
//					}
//							
//					if (!checkdup ( answer , g.members[j.fnum].name )) {
//						answer.add(g.members[j.fnum].name) ; 
//					}
//					
//					
//				}
				
				
				

			} else {
				back[v] = Math.min( back[v] , dfsnum [j.fnum] ) ;
				System.out.println( "condition2: " + g.members[v].name + ": " + " dfsnum: " + dfsnum[v] + " back: " + back[v] + " || " + "Current v: " + g.members[v].name + " Current j.fum: " + g.members[j.fnum].name );
//				if (dfsnum [v] <=back [j.fnum] && (onlyNbr (j.fnum, v, g )) ) {
//					if (dfsnum [v] <=back [j.fnum] && (onlyNbr (j.fnum, v, g )) ) {
//						System.out.println( "added name: " + g.members[v].name ) ; 
//					}
//					if (!checkdup ( answer , g.members[v].name )) {
//					answer.add(g.members[v].name) ; 
//					}
//				} else if  (dfsnum [v] <=back [j.fnum] && (onlyNbr ( v, j.fnum, g )) ) {
//					if (dfsnum [v] <=back [j.fnum] && (onlyNbr ( v, j.fnum, g )) ) {
//						System.out.println( "added name: " + g.members[j.fnum].name ) ; 
//					}
//					if (!checkdup ( answer , g.members[j.fnum].name )) {
//						answer.add(g.members[j.fnum].name) ; 
//					}
//				}
			}
//			if (dfsnum [v] <=back [j.fnum] && onlyNbr (j.fnum, v, g ) ) {
//				if (dfsnum [v] <=back [j.fnum] && onlyNbr (j.fnum, v, g )) {
//					System.out.println( "added name: " + g.members[v].name ) ; 
//				}
//				answer.add(g.members[v].name) ; 
//			}
			
		}
		
		return answer ; 
	}
	private static boolean onlyNbr ( int v, int fnum, Graph g ) {
		
		int count = 0 ; 
		for ( Friend i = g.members[v].first ; i!=null ; i = i.next ) {
			count ++ ;
		}
		if (count == 1 && g.members[v].first.fnum == fnum ) {
			return true; 
		}
		
		return false; 
	}
	private static boolean checknoneone ( int v , Graph g ) {
		int count = 0 ; 
		for ( Friend e = g.members [v].first ; 
				e!= null ; 
				e=e.next) {
			System.out.println("checknoneone : " + g.members[e.fnum].name);
				count ++ ; 
		}
		if ( count == 0 || count == 1 ) {
			return true; 
		}
		return false; 
	}
	private static boolean checkdup ( ArrayList<String> a , String name ) {
		for ( int i = 0 ; i < a.size() ; i ++ ) {
			if ( a.get(i).toLowerCase().equals(name.toLowerCase()) ) { 
				return true ; 
			}
		}
		return false ; 
	}
}

