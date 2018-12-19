package org.csc505.homework3;

public class h3p2_recursive_gabalmat {
	
	private static int numRecursiveCalls = -1;
	private static int numMultsHW = 0;
	
	public static void main (String[] args) {
		
	// Process input array and create integer array of dimensions
		String inputArray = args[0];
		String[] numbers = inputArray.split(",");
		int[] dimensions = new int[numbers.length];
		
		for (int i=0; i<numbers.length; i++) {
			int num = Integer.parseInt(numbers[i]);
			dimensions[i] = num;
		}
		
		int i = 1;
		int j = dimensions.length - 1;
		
		int total = recursiveMatrixChain(i, j, dimensions);
		
		System.out.println("Minimum number of scalar mults (recursive): " + total);
		System.out.println("Num Recursive Calls: " + numRecursiveCalls);
		System.out.println("Num Multiplications: " + numMultsHW);
	}
	
	public static int recursiveMatrixChain (int i, int j, int[] p) {
		numRecursiveCalls++;
		
		if (i == j) {
			return 0;
		}
		
		int numMults = Integer.MAX_VALUE;
		for (int k=i; k<=j-1; k++) {
			int q = recursiveMatrixChain(i, k, p) + recursiveMatrixChain(k+1, j, p) + 
					(p[i-1] * p[k] * p[j]);
			numMultsHW = numMultsHW + 2;
			if (q < numMults) {
				numMults = q;
			}
		}
		
		return numMults;
	}
}