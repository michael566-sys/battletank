package app;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

	public static String delims = " \t*+-/()[]";
			
    /**
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created 
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     * 
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    public static void 
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD **/
    	/** DO NOT create new vars and arrays - they are already created before being sent in
    	 ** to this method - you just need to fill them in.
    	 **/
    	
//    	String expr = new String ( "arrayA [ arrayA [a] * (arrayA [b] + d) +1 ] - varx") ; 
        String sub = new String (expr) ; 
        sub = sub.replaceAll("\\s", "");
		System.out.println( "sub w/o space: " + sub );
		System.out.println();
		String tok = new String () ; 
		
		StringTokenizer st = new StringTokenizer( sub,  delims );
		
		
//	    while (st.hasMoreTokens()) {
//
//    	System.out.println(st.nextToken());
//	    }
	    while (st.hasMoreTokens()) {
	    	
	    	tok = new String ( st.nextToken () ) ; 
	    	System.out.println( "current tok: " + tok);
	    	boolean isDigit = false; 
	    	for (int i = 0; i < tok.length(); i++) {
	    	      if(Character.isDigit(tok.charAt(i))) {
	    	          isDigit = true;
	    	      }
	    	}
	    	if (isDigit) { continue; } 
	    	if (  (sub.indexOf(tok) + tok.length() - 1 ) == ( sub.length() - 1 ) ) {
	    		Variable b = new Variable ( tok ) ; 
	    	    if ( !vars.contains( b )) {
	    	    	vars.add (b) ;		        
			    }
		        continue; 
	    	}
	    	// 1. problem will occur if the varx occurs more than once in !!!!!!!!!!!!!!!!
	    	//     arrayA [ arrayA [a] * (arrayA [b] + d) +1 ] - varx
	    	//  (SOLVED) 2. how to resolve arrayA occurring three times in arrays ArrayList??????????
	    	//  maybe use a array methods or map to check for duplicates? 
	    	//  3. how to make the name of the ArrayList same as the name of the string? 
	
	    	
	    	if ( sub.charAt ( (sub.indexOf(tok) + tok.length() ) ) == '[' ) {
	    		System.out.println( "arrays index: "+ sub.charAt ( (sub.indexOf(tok) + tok.length() ) )) ;
	    		
		        Array a = new Array ( tok ) ; 
		        if ( !arrays.contains( a )) {
		        	arrays.add (a) ;		   
		        	sub = sub.substring ( sub.indexOf(tok) +  tok.length() ) ; 
		        	System.out.println("trancated sub after adding array: " + sub );
		        	System.out.println();
		        }
		        
	    	} else {  
		    	Variable b = new Variable ( tok ) ; 
		    	if ( !vars.contains( b )) {
			      	vars.add (b) ;		        
			    }
	    	}
	     }
	    
//	    Array a = new Array (""); 
//	    System.out.println( "arrays list: ");
//        for (int i = 0 ; i < arrays.size() ; i ++ ) {
//        	System.out.print (  arrays.get(i).name + " " );
//        }
//        System.out.println();
//	    System.out.println( "variables list: ");
//        Variable b = new Variable (""); 
//        for (int i = 0 ; i < vars.size() ; i ++ ) {
//        	System.out.print ( vars.get(i).name + " " );
//        }
//        System.out.println();
	     
    }
    
    /**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
    public static void 
    loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
            	arr = arrays.get(arri);
            	arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;              
                }
            }
        }        
    }
    
    /**
     * Evaluates the expression.
     * 
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
    public static float 
    evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	String sub = new String ( expr ) ; 
        sub = sub.replaceAll("\\s", "");
        System.out.println( "initial sub w/o space: " + sub );
    	int miusIdx = -1 ; 
    	int plusIdx = -1 ; 
    	int timeIdx = -1 ; 
    	int diviIdx = -1 ; 
    	int checkbracket = 0 ; 
    	int checkparan = 0 ; 
    	
    	char ch = ' ' ;
    	
    	for ( int i = 0; i < sub.length() ; i ++ ) {
    		ch = sub.charAt(i) ; 
    		if ( checkparan == 0 && checkbracket == 0 && ch == '+' ) { 
    			plusIdx = i ;     			
    		} 
    		if ( checkparan == 0 && checkbracket == 0 && ch == '-' ) { 
    			miusIdx = i ;     			
    		} 
    		if ( checkparan == 0 && checkbracket == 0 && ch == '*' ) { 
    			timeIdx = i ;     			
    		} 
    		if ( checkparan == 0 && checkbracket == 0 && ch == '/' ) { 
    			diviIdx = i ;     			
    		}  			
    			
    		if ( ch == '(' ) { checkparan ++ ; }
    		if ( ch == ')' ) { checkparan -- ; }
    		if ( ch == '[' ) { checkbracket ++ ; }
    		if ( ch == ']' ) { checkbracket -- ; }
    		System.out.println( "miusIdx: "+ miusIdx + " plusIdx: " + plusIdx + " timeIdx: " + timeIdx + " diviIdx: " + diviIdx ) ;  
    	}
    	
    	
    	if ( plusIdx != -1 ) { 
    		String leftsub = new String ( sub.substring ( 0, plusIdx ) ) ; 
    		String rightsub = new String ( sub.substring ( plusIdx + 1 )) ; 
    		System.out.println( "leftsub: " + leftsub + "  " + "rightsub: " + rightsub );
    		
    		return evaluate( leftsub, vars, arrays) + evaluate( rightsub, vars, arrays) ;
    		
    	} else if ( miusIdx != -1 ) { 
    		String leftsub = new String ( sub.substring ( 0, miusIdx ) ) ; 
    		String rightsub = new String ( sub.substring ( miusIdx + 1 )) ; 
    		System.out.println( "leftsub: " + leftsub + "  " + "rightsub: " + rightsub );
    		
    		return evaluate( leftsub, vars, arrays) - evaluate( rightsub, vars, arrays) ;

    	} else if ( timeIdx != -1 ) { 
    		String leftsub = new String ( sub.substring ( 0, timeIdx ) ) ; 
    		String rightsub = new String ( sub.substring ( timeIdx + 1 )) ; 
    		System.out.println( "leftsub: " + leftsub + "  " + "rightsub: " + rightsub );
    		
    		return evaluate( leftsub, vars, arrays) * evaluate( rightsub, vars, arrays) ;

    	} else if ( diviIdx != -1 ) { 
    		String leftsub = new String ( sub.substring ( 0, diviIdx ) ) ; 
    		String rightsub = new String ( sub.substring ( diviIdx + 1 )) ; 
    		System.out.println( "leftsub: " + leftsub + "  " + "rightsub: " + rightsub );
    		
    		return evaluate( leftsub, vars, arrays) / evaluate( rightsub, vars, arrays) ;
    		
    	} else if ( sub.charAt(0) == '(' && sub.charAt ( sub.length()-1 ) == ')' ) {
    		System.out.println( "paran sub: " + sub.substring( 1 , sub.length() - 1 ) );

    		return evaluate( sub.substring( 1 , sub.length() - 1 ), vars, arrays) ;
    		
    	} else if ( sub.charAt(0) != '[' && sub.charAt ( sub.length()-1 ) == ']' &&
    		plusIdx == -1 && miusIdx == -1 && timeIdx == -1 && diviIdx == -1 ) { 
    		String a = new String ( sub.substring ( 0 , sub.indexOf ( "[" ) ) ) ; 
    		System.out.println( "bracket sub before: " + sub );
    		System.out.println( "bracket sub: " + sub.substring ( sub.indexOf ( "[" ) + 1, sub.lastIndexOf( "]" ) ) );
    		return arrays.get( findIndexOfArray( a , arrays ) /*arrays.indexOf( a )*/ ).values 
    		    [ (int) evaluate ( 
    		    		sub.substring ( sub.indexOf ( "[" ) + 1, sub.lastIndexOf( "]" )  ) , 
    		    		vars, arrays 
    		    ) ] ; 
    	} else if ( !(isNumeric ( sub )) ){
    		String b = new String ( sub ) ; 
    		float n1 = vars.get( findIndexOfVar( b, vars ) ).value ;
    		System.out.println( "value of basic variable " + sub + ": " + n1 );
    		return n1 ; 
        		    		
    	} else {
    		
    		return Float.parseFloat(sub) ; 
    		
    	}
    	
    	
//    	if ( (leftsub.length() == 1 && leftsub.charAt(0) < 0 && leftsub.charAt(0) > 9) ) {
//			leftsub = vars.get( vars.indexOf(  ) ).values 
//	    		    [ (int) evaluate ( 
//	    		    		sub.substring ( sub.indexOf ( "[" ) + 1, sub.indexOf ( "]" ) ) , 
//	    		    		vars, arrays 
//	    		    ) ] ; 
//		} 
//		if ( rightsub.length() == 1 && leftsub.charAt(0) < 0 && leftsub.charAt(0) > 9 ) {
//			
//		}     			
		

    	/** COMPLETE THIS METHOD **/
    	// following line just a placeholder for compilation
    	//return 0;
    }
    
    private static int findIndexOfVar( String element , ArrayList<Variable> vars ) {
    	for ( int i = 0 ; i < vars.size() ; i ++ ) { 
    		if ( element.equals(vars.get(i).name ) ) {
    			return i ; 
    			//return vars.get(i).value; 
    			//// why contains can actually detect the .name??? 
    		}
    	}
    	return 0; 
    }

    private static boolean isNumeric ( String element ) {
    	for ( int i = 0 ; i < element.length () ; i ++ ) {
    		if ( !Character.isDigit(element.charAt(i)) ) {
    			return false; 
    		}
    	}
    	return true; 
    }
	private static int findIndexOfArray( String element , ArrayList<Array> arrays) {
		for ( int i = 0 ; i < arrays.size() ; i ++ ) { 
			if ( element.equals(arrays.get(i).name ) ) {
				return i ; 
				//return arrays.get(i).values; 
				//// why contains can actually detect the .name??? 
			}
		}
		return 0; 
	}
}