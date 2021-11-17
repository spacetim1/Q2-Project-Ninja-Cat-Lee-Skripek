

public class ArrayIntoCoding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	System.out.println("intro to arrays");
	
	int x = 1;
	String str = "hello";
	double y = 3.14;
	
	//arrays - a collection of similar elements
	//type[] arrayName = new type[size];
	//create an array of 10 integers 
	
	int[] scores = new int[10];
	//primitive types are initialized
	//ints are initialized to 0
	//doubles -> 0.0
	//booleans -> false
	
	System.out.println(scores.length);
	
	//reading from a 1d array
	//call the array by its name
	//specify its location!
	System.out.println(scores[0]); //first element
	
	//print the last element of the array
	//do not hard code a number
	int lastIndex = scores.length - 1;
	System.out.println(scores[lastIndex]);
	
	//write 7 to index 0
	scores[0] = 7; //assign element 0 to 7
	scores[1] = 8;
	scores[2] = 10;
	

	//two different ways to create arrays
	
	double[] myDoubles = { 86.5, 99.9 };

	double[] mydoubles2 = new double [2];
	mydoubles2[0] = 86.5;
	mydoubles2[1] = 99.9;

	
	//1d array of 31 integers
	int[] integer = new int [31];
	
	//write a loop that counts from 0 to 30 inclusive
	//traversal - visiting elements of an array
	for( int index = 0; index < 31; index++) {
		integer[index] = (int)(Math.random()*2343);
	}
	
	//write the loop to print the elements in their own line
	for( int index = 0; index < integer.length; index++) {
		System.out.println(integer[index]);
		
	}
	
	
	
	
	
	
	
	}

}
