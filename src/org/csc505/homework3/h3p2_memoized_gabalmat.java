package org.csc505.homework3;

public class h3p2_memoized_gabalmat {
	
	private static int numRecursiveCalls = 0;
	private static int numMultsHW = 0;
	
	// Auxiliary table storing the m[i][j] costs
	private static int[][] m;
	
	// Number of matrices in the matrix chain
	private static int n;
	
	public static void main (String[] args) {
		
	// Process input array and create integer array of dimensions
		String inputArray = args[0];
		String[] numbers = inputArray.split(",");
		int[] dimensions = new int[numbers.length];
		
		for (int i=0; i<numbers.length; i++) {
			int num = Integer.parseInt(numbers[i]);
			dimensions[i] = num;
		}
		
		n = dimensions.length - 1;
		m = new int[n+1][n+1];
		
		for (int i=1; i<=n; i++) {
			for (int j=i; j<=n; j++) {
				m[i][j] = Integer.MAX_VALUE;
			}
		}
		
		int total = memoizedMatrixChain(1, n, dimensions);
		
		System.out.println("Minimum number of scalar mults (memoized): " + total);
		System.out.println("Num Recursive Calls: " + numRecursiveCalls);
		System.out.println("Num Multiplications: " + numMultsHW);
	}
	
	public static int memoizedMatrixChain(int i, int j, int[] p) {
		numRecursiveCalls++;
		
		if (m[i][j] < Integer.MAX_VALUE) {
			return m[i][j];
		}
		
		if (i == j) {
			m[i][j] = 0;
		} else {
			for (int k=i; k<=j-1; k++) {
				int q = memoizedMatrixChain(i, k, p) + memoizedMatrixChain(k+1, j, p) +
						(p[i-1] * p[k] * p[j]);
				numMultsHW = numMultsHW + 2;
				if (q < m[i][j]) {
					m[i][j] = q;
				}
			}
		}
		
		return m[i][j];
	}
}