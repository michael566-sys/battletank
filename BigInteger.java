package bigint;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer with 
 * any number of digits, which overcomes the computer storage length limitation of 
 * an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if this is a negative integer
	 */
	boolean negative;
	
	/**
	 * Number of digits in this integer
	 */
	int numDigits;
	
	/**
	 * Reference to the first node of this integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
	 * For instance, the integer 235 would be stored as:
	 *    5 --> 3  --> 2
	 *    
	 * Insignificant digits are not stored. So the integer 00235 will be stored as:
	 *    5 --> 3 --> 2  (No zeros after the last 2)        
	 */
	DigitNode front;
	
	/**
	 * Initializes this integer to a positive number with zero digits, in other
	 * words this is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}
	
	/**
	 * Parses an input integer string into a corresponding BigInteger instance.
	 * A correctly formatted integer would have an optional sign as the first 
	 * character (no sign means positive), and at least one digit character
	 * (including zero). 
	 * Examples of correct format, with corresponding values
	 *      Format     Value
	 *       +0            0
	 *       -0            0
	 *       +123        123
	 *       1023       1023
	 *       0012         12  
	 *       0             0
	 *       -123       -123
	 *       -001         -1
	 *       +000          0
	 *       
	 * Leading and trailing spaces are ignored. So "  +123  " will still parse 
	 * correctly, as +123, after ignoring leading and trailing spaces in the input
	 * string.
	 * 
	 * Spaces between digits are not ignored. So "12  345" will not parse as
	 * an integer - the input is incorrectly formatted.
	 * 
	 * An integer with value 0 will correspond to a null (empty) list - see the BigInteger
	 * constructor
	 * 
	 * @param integer Integer string that is to be parsed
	 * @return BigInteger instance that stores the input integer.
	 * @throws IllegalArgumentException If input is incorrectly formatted
	 */
	public static BigInteger parse(String integer) 
		// @ author Songyuan Zhang
	throws IllegalArgumentException {
		if ( integer.equals("") ) { throw new IllegalArgumentException ("Empty string");} 
		BigInteger a = new BigInteger ();
		String sub1 = new String (integer);
		DigitNode ptr = a.front;
		if ( sub1.equals( "0" ) ) {
			a.front = null ; 
			a.numDigits = 0 ; 
			a.negative = false; 
			System.out.println( a.numDigits); 
			if ( a.negative == false ) {
				System.out.println( "false ");
			} else {
				System.out.println( "true ");
			}
			return a;
		}
		while ( (Character.toString ( sub1.charAt(0) ) .equals ( " " )) ) {
			sub1 = sub1.substring(1) ;
		}
		
		while ( (sub1.length() != 0) && (Character.toString ( sub1.charAt(0) ).equals ( "0" )) ) {
			sub1 = sub1.substring(1) ;
		}
//		System.out.println(sub1);
		if ( sub1.equals( "" ) ) {    // prevent "0000" case
			a.front = null; 
			a.numDigits = 0 ; 
			a.negative = false; 
			System.out.println( a.numDigits); 
			if ( a.negative == false ) {
				System.out.println( "false ");
			} else {
				System.out.println( "true ");
			}
			return a;
		}

		while ( (sub1.length() != 0) && (Character.toString ( sub1.charAt( sub1.length()-1 ) ).equals ( " " )) ) {
			sub1 = sub1.substring( 0, sub1.length()-1) ;
		}
		if ( sub1.equals( "" ) ) {   // prevent "0000   " case
			a.negative = false ;  
			a.front = null ;
			a.numDigits = 0 ;
			System.out.println( a.numDigits); 
			if ( a.negative == false ) {
				System.out.println( "false ");
			} else {
				System.out.println( "true ");
			}
			return a ; 
		}
		while ( Character.toString ( sub1.charAt(0) ).equals ( "+" ) ) {
			a.negative = false; 
			sub1 = sub1.substring(1) ;
		}
		while ( Character.toString ( sub1.charAt(0) ).equals ( "-" ) ) {
			a.negative = true; 
			sub1 = sub1.substring(1) ;
		}
		while ( (sub1.length() != 0) && (Character.toString ( sub1.charAt(0) ).equals ( "0" )) ) {
			sub1 = sub1.substring(1) ;
		}
		if ( sub1.equals( "" ) ) {   // prevent "+00001" case from returning"00001"; prevent "000" from returning "000" 
			a.negative = false ;  
			a.front = null ; 
			a.numDigits = 0 ;
			System.out.println( a.numDigits); 
			if ( a.negative == false ) {
				System.out.println( "false ");
			} else {
				System.out.println( "true ");
			}
			return a ; 
		}
		
		//int zero = 0 ; 
		for ( int i = sub1.length()-1; i >= 0 ; i -- ) {
			if ( !(Character.isDigit(sub1.charAt (i))) ) {
				throw new IllegalArgumentException ();
			} else {
				int n1 = Character.getNumericValue( sub1.charAt (i) ); 
				//if ( n1 == 0 ) { zero ++ ; }
				if ( a.front == null ) {
					a.front = new DigitNode ( n1, null );
					ptr = a.front;
					a.numDigits ++;
				} else {
					ptr.next = new DigitNode ( n1, null ); 
					ptr = ptr.next;
					a.numDigits ++;
				}
			}
		}
/*		if ( zero == a.numDigits ) { // prevent "+/-0000" case from returning 0000
			a.negative = false ;  
			a.front = new DigitNode ( 0 , null ) ; 
			a.numDigits = 0 ;   
		} */
//		else { // prevent "+0001" but failed 
//			ptr = a.front;
//			while ( ptr.digit == 0 ) {
//				a.front = a.front.next;
//				ptr = a.front ; 
//			}
//		}
		if ( a.front == null ) { throw new IllegalArgumentException ( "Empty integer");}  
//		System.out.println( "numDigits: " + a.numDigits);
//		if (a.negative) {
//			System.out.println( "negative: true" );
//		} else {
//			System.out.println( "negative: false" );
//		}
		System.out.println( a.numDigits); 
		if ( a.negative == false ) {
			System.out.println( "false ");
		} else {
			System.out.println( "true ");
		}
		return a ; 
		
		
//		/* IMPLEMENT THIS METHOD */
//		
//		// following line is a placeholder for compilation
//		return null;
	}
	
	/**
	 * Adds the first and second big integers, and returns the result in a NEW BigInteger object. 
	 * DOES NOT MODIFY the input big integers.
	 * 
	 * NOTE that either or both of the input big integers could be negative.
	 * (Which means this method can effectively subtract as well.)
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return Result big integer
	 */
	public static BigInteger add(BigInteger first, BigInteger second) {
		// @ author Songyuan Zhang
		
		DigitNode ptr1 = null;
		DigitNode ptr2 = null;
		ptr1 = first.front;
		ptr2 = second.front;
		DigitNode ptr3 = null ; 
		BigInteger answer = new BigInteger () ; 
		ptr3 = answer.front; 
		String sub1 = new String () ; 
		
		int addition = 0 ; 
		int reduction = 0 ; 
		int sum = 0 ; 
		int number = 0 ; 
		int a = first.numDigits ; int b = second.numDigits ; 
		double a1 = 0 ; double b1 = 0 ; 
		int count1 = 0 ; int count2 = 0 ; 
		int ptr2digit = 0 ; 
//		String a1 = new String (); 
//		String b1 = new String ();
//		int inta1 = 0 ; 
//		int intb1 = 0 ;
		for ( ptr1 = first.front; ptr1 != null ; ptr1 = ptr1.next ) {
//			a1 = Integer.toString ( ptr1.digit ) + a1; // cannot handle large number 
			a1 = a1 + ptr1.digit * Math.pow(10, count1) ;
			count1++; 
		}
//		inta1 = Integer.parseInt(a1) ;     
//		System.out.println("a1 is :" + a1);


		for ( ptr2 = second.front; ptr2 != null ; ptr2 = ptr2.next ) {
//			b1 = Integer.toString ( ptr2.digit ) + b1; // cannot handle large number 
			b1 = b1 + ptr2.digit * Math.pow(10, count2) ;
			count2 ++;
		}
//		intb1 = Integer.parseInt(b1) ; 
//		System.out.println("b1 is :" + b1);
		
		if ( a > b ) {
			ptr1 = first.front ; 
			ptr2 = second.front ; //System.out.println( ptr2.digit);
		} else if ( a == b ) {
				if ( a1 > b1 ) {
					ptr1 = first.front ; ptr2 = second.front ; 
				}
				else if ( b1 > a1 ) {
					ptr1 = second.front; ptr2 = first.front ;
				} else {
					ptr1 = first.front; 
					ptr2 = second.front;
				}
		} else {
			ptr1 = second.front;
			ptr2 = first.front; 
		}
//		System.out.println( "add bigger: " + ptr1.digit ); 
//		System.out.println( "add smaller: " + ptr2.digit );
		
		if ( ( (first.negative) && (second.negative) ) || 
			 ( (!(first.negative)) && (!(second.negative)) ) ) { 
			ptr3 = answer.front; 
			while ( ptr1 != null || ptr2 != null ) {
//				System.out.println( "ptr1 data: " + ptr1.digit);
//				System.out.println( "ptr2 data: " + ptr2.digit);
				if ( ptr2 != null ) { 
					sum = ptr1.digit + ptr2.digit; 
				} else {
					sum = ptr1.digit + 0 ; 
				}
				if ( sum >= 10 ) {
					number = sum - 10 + addition ; 
//					System.out.println("number1: " + number);
					addition = 1;
				} else {
					number = sum + addition; 
					if ( number >= 10 ) {
						number = number - 10; //+ addition ; 
//						System.out.println( "number2: " + number);
						addition = 1 ;
					} else {
						addition = 0 ; 
					}
				}

//				if ( answer.front == null ) {
//					answer.front = new DigitNode ( number, null ) ; 
//					ptr3 = answer.front ; 
//					answer.numDigits ++ ; 
//					sub1 = (char) number + sub1; 
//				} else {
//					ptr3.next = new DigitNode ( number, null ) ; 
//					ptr3 = ptr3.next; 
//					answer.numDigits ++ ; 
//					sub1 = (char) number + sub1; 
//				}
				sub1 = Integer.toString(number) + sub1; 

					if ( ptr1 != null ) { ptr1 = ptr1.next ; }
					if ( ptr2 != null ) { ptr2 = ptr2.next ; }
				
			}			
		} else {
			while ( ptr1 != null || ptr2 != null ) {
				if ( ptr2 != null ) { 
					ptr2digit = ptr2.digit; 
				} else {
					ptr2digit = 0 ; 
				}
				ptr1.digit = ptr1.digit - reduction ; 
				if ( (ptr1.digit < ptr2digit )      //
					|| (ptr1.digit < 0) ) {
					number = 10 - ptr2digit + ptr1.digit;
					reduction = 1 ;
				} else {
					number = ptr1.digit - ptr2digit;
					reduction = 0 ; 
				}
//				System.out.println("reduction number: " + number);
				sub1 = Integer.toString(number) + sub1; 

//				if ( answer.front == null ) {
//					answer.front = new DigitNode ( number , null ) ; 
//					ptr3 = answer.front; 
//					answer.numDigits ++ ;
////					sub1 = (char) number  + sub1; 
//
//				} else {
//					ptr3.next = new DigitNode ( number, null ) ; 
//					ptr3 = ptr3.next ;
//					answer.numDigits ++ ; 
////					sub1 = (char) number + sub1; 
//					
//				}
				
				if ( ptr1 != null ) { 
					ptr1 = ptr1.next; 
				} else {
					break;
				}
				
				if ( ptr2 != null ) {
					ptr2 = ptr2.next ;
				}
			}
		}	
		if ( addition != 0  ) {
			//ptr3.next = new DigitNode ( 1 , null ) ;
			sub1 = Integer.toString(addition) + sub1; 
		}
		
//		System.out.println( "sub1 : " + sub1 );   // to prevent 620-600 from printiing 020 FAILED 
		while ( ( sub1.length() != 0) && (Character.toString ( sub1.charAt(0) ).equals ( "0" )) ) {
			sub1 = sub1.substring(1) ;
		}
		
//		System.out.println( "sub1 w no 0 : " + sub1 );
		for ( int i = sub1.length()-1; i >= 0 ; i -- ) {
		    number = Character.getNumericValue( sub1.charAt(i) );
			if ( answer.front == null ) {
				answer.front = new DigitNode ( number , null ) ; 
				ptr3 = answer.front; 
				answer.numDigits ++ ;

			} else {
				ptr3.next = new DigitNode ( number , null ) ; 
				ptr3 = ptr3.next ;
				answer.numDigits ++ ; 
			}
		}
		
		if (( (first.negative) && (second.negative) ) ||
			( (first.negative) && (!(second.negative)) && ( a1 > b1 ) ) ||
			( (!first.negative) && ((second.negative)) && ( b1 > a1 ) )   
		   ) {
			answer.negative = true ;
//			System.out.println( "add negativity = true" );
		} else {
			answer.negative = false ;
//			System.out.println( "add negativity = false" );
		}
//		System.out.println( "add numDigits: " + answer.numDigits);
		System.out.println( answer.numDigits); 
		if ( answer.negative == false ) {
			System.out.println( "false ");
		} else {
			System.out.println( "true ");
		}
		return answer;
		
		/* IMPLEMENT THIS METHOD */
		
		// following line is a placeholder for compilation
		//return null;
	}
//private static void traverse ( BigInteger first ) {
//		if (first.front == null) {
//			System.out.println("Empty list");
//			return;
//		}
//		System.out.print(first.front.digit);  // first item
//		DigitNode ptr=first.front.next;    // prepare to loop starting with second item
//		while (ptr != null) {
//			System.out.print(" --> " + ptr.digit);
//			ptr = ptr.next;
//		}
//		System.out.println();
//	}
	
	/**
	 * Returns the BigInteger obtained by multiplying the first big integer
	 * with the second big integer
	 * 
	 * This method DOES NOT MODIFY either of the input big integers
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return A new BigInteger which is the product of the first and second big integers
	 */
	public static BigInteger multiply(BigInteger first, BigInteger second) {
		// @ author Songyuan Zhang

		BigInteger answer = new BigInteger () ; 
		BigInteger mult = new BigInteger () ; 
		if ( (first.front == null) || (second.front == null) ) {
			answer.front = null ;
			answer.negative = false; 
			answer.numDigits = 0 ;
			System.out.println( answer.numDigits); 
			if ( answer.negative == false ) {
				System.out.println( "false ");
			} else {
				System.out.println( "true ");
			}
			return answer; 
		}
		
		int temp = first.numDigits ;
		int j = 0 ; 
		DigitNode ptr1 = null ;
		DigitNode ptr2 = null ; 
		DigitNode ptra = null ; 
		DigitNode ptrm1 = null ; 	
				
//		int temp = 0 ;
		int a = first.numDigits ; int b = second.numDigits ; 
		double a1 = 0 ; double b1 = 0 ; 
		int count1 = 0 ; int count2 = 0 ; 
		int i = 0 ; 
		boolean firstbigger = false; 
		for ( ptr1 = first.front; ptr1 != null ; ptr1 = ptr1.next ) {
			a1 = a1 + ptr1.digit * Math.pow(10, count1) ;
			count1++; 
		}
//		System.out.println("a1 is :" + a1);


		for ( ptr2 = second.front; ptr2 != null ; ptr2 = ptr2.next ) {
			b1 = b1 + ptr2.digit * Math.pow(10, count2) ;
			count2 ++;
		}

//		System.out.println("b1 is :" + b1);
		
		if ( a > b ) {
			ptr1 = first.front ; 
			ptr2 = second.front ; 
			i = second.numDigits - 1; 
			j = second.numDigits;
			firstbigger = true; 
		}
		else if ( a == b ) {
				if ( a1 > b1 ) {
					ptr1 = first.front ; ptr2 = second.front ; 
					firstbigger = true; 
					i = second.numDigits -1 ;
					j = second.numDigits;
				}else if ( b1 > a1 ) {
					ptr1 = second.front; ptr2 = first.front ;
					firstbigger = false; 
					i = first.numDigits - 1;
					j = first.numDigits;
				}else {
					ptr1 = first.front; 
					ptr2 = second.front;
					firstbigger = true; 
					i = second.numDigits - 1;
					j = second.numDigits;
				}
		} else {
			ptr1 = second.front;
			ptr2 = first.front; 
			firstbigger = false; 
			i = first.numDigits -1;
			j = first.numDigits;
		}
//								System.out.println( "number of j: " + j);
//								System.out.println( "number of i: " + i );
//		System.out.println("ptr1's front: " + ptr1.digit);
//		System.out.println("ptr2's front: " + ptr2.digit);

//		ptr1 = first.front; 
//		ptr2 = second.front;
		
		int product = 0 ; 
//		int i = second.numDigits - 1; 
		int number = 0; 
		int addition  = 0 ; 
		int zeroptr1 = 0 ; 
		int zeroptr2 = 0 ; 
		DigitNode ptr3 = null ;
		while ( (ptr1.digit == 0) && (ptr1.next != null) ) {
			ptr1 = ptr1.next;  zeroptr1 ++ ; // i --; j --;
		}
		while ( (ptr2.digit == 0) && (ptr2.next != null)) { 
			ptr2 = ptr2.next; zeroptr2 ++ ; i --; j --; 
		}
		ptr3 = ptr1;
		
//									System.out.println("zeroptr1: "+ zeroptr1 +"at: " + ptr1.digit );
//									System.out.println("zeroptr2: "+ zeroptr2 + "at: " + ptr2.digit);
		while (ptr2!= null ) {
			if ( answer.front == null ) {
				while ( ptr1 != null ) {
					product = ptr1.digit * ptr2.digit;
					
					number = product % 10 + addition; 
					if (number >=10 ) {
						addition = number /10 + product /10 ; 
						number = number %10 ; 
					}else {
						addition = product / 10 ; 
					}
//									System.out.println("addition: "+ addition);
					if ( answer.front == null ) {
						answer.front = new DigitNode ( number, null ) ; 
//						System.out.println ("answer.front : " + answer.front.digit );
						ptra = answer.front; 
						answer.numDigits ++ ; 
					} else {
						ptra.next = new DigitNode ( number, null ) ; 
						ptra = ptra.next; 
						answer.numDigits ++ ; 
//						System.out.print (" ptra: " + ptra.digit + " " );
//						System.out.println();

					}
//									System.out.println( "number: "+ number );
//									System.out.println();
					ptr1= ptr1.next; 
				}
				if ( addition > 0 ) {
					ptra.next = new DigitNode ( addition , null ) ; 
					ptra = ptra.next;
				}
				addition = 0 ; 
			}else {
//									System.out.println( "value of i: " + i );
				if ( i != 0 ) {
					temp = i ; 
					while ( temp != j ) {
						if ( mult.front == null ) {
							mult.front = new DigitNode ( 0 , null ) ;
							ptrm1 = mult.front; 
							mult.numDigits ++ ;
//												System.out.println("mult.front: " + mult.front.digit );
						} else {
							ptrm1.next = new DigitNode ( 0 , null ) ;
							ptrm1 = ptrm1.next ;
							mult.numDigits ++ ;
//												System.out.println("ptrm1: " + ptrm1.digit );

						}
						temp ++ ; 
					}
				} 
				i -- ;
				while ( ptr1 != null ) {
					product = ptr1.digit * ptr2.digit;
										
					number = product % 10 + addition; 
					if (number >=10 ) {
						addition = number /10 + product /10 ; 
						number = number %10 ; 
					}else {
						addition = product / 10 ; 
					}
					ptrm1.next = new DigitNode ( number, null ) ; 
					mult.numDigits ++ ;
					ptrm1 = ptrm1.next; 
//										System.out.println("ptrm1 nonzero: " + ptrm1.digit );
					ptr1= ptr1.next; 
				}
				if ( addition > 0 ) {
					ptrm1.next = new DigitNode ( addition, null ) ; 
					ptrm1 = ptrm1.next; 
				}			
				addition = 0 ;
			}
//										System.out.print ("traverse answerLL: " );
//										traverse (answer);
//										System.out.print ("traverse multLL: " );
//										traverse (mult);
			answer = add ( answer, mult ) ; 
//			if(firstbigger) {
//				ptr1 = first.front;
//			} else {
//				ptr1 = second.front; 
//			}
			ptr1 = ptr3 ; 
			ptr2 = ptr2.next; 
			mult.front = null ; 
			mult.numDigits = 0 ;
		}
//		DigitNode testzero = null ;
//		int testzero1 = 0 ; 
//		for ( testzero = answer.front; testzero != null ; testzero = testzero.next ) { //test for zero
//			if (testzero.digit == 0 ) {
//				testzero1 ++; 
//			}
//		}
//		if ( testzero1 == answer.numDigits ) {
//			answer.front = new DigitNode ( 0 , null ) ; 
//			answer.negative = false; 
//			answer.numDigits = 0; 
//			return answer; 
//		}
//									System.out.println("zeroptr1: "+ zeroptr1 );
//									System.out.println("zeroptr2: "+ zeroptr2 );
		while ( zeroptr1 != 0 ) {
			answer.front = new DigitNode ( 0 , answer.front ) ;
			answer.numDigits ++ ;
			zeroptr1 -- ; 
		}
		while ( zeroptr2 != 0 ) {
			answer.front = new DigitNode ( 0 , answer.front ) ; 
			answer.numDigits ++ ;
			zeroptr2 -- ; 
		}
//										System.out.println("zeroptr1: "+ zeroptr1);
//										System.out.println("zeroptr2: "+ zeroptr2);
		if ( (first.negative) && (second.negative) || !(first.negative) && !(second.negative) ) {
			answer.negative = false; 
		} else {
			answer.negative = true; 
		}
		
//		if (answer.negative) {
//										System.out.println( "answer's negative: true" );
//		} else {
//										System.out.println( "answer's negative: false" );
//		}
//										System.out.println("answer's numDigits: " + answer.numDigits );
		
		System.out.println( answer.numDigits); 
		if ( answer.negative == false ) {
			System.out.println( "false ");
		} else {
			System.out.println( "true ");
		}
	return answer; 
	//	/* IMPLEMENT THIS METHOD */
	//	
	//	// following line is a placeholder - compiler needs a return
	//			// modify it according to need
	////	return null;
	//}
}


	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (front == null) {
			return "0";
		}
		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
				retval = curr.digit + retval;
		}
		
		if (negative) {
			retval = '-' + retval;
		}
		return retval;
	}
}
