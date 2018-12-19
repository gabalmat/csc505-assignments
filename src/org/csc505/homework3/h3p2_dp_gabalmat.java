package org.csc505.homework3;

public class h3p2_dp_gabalmat {
	
	private static int numMultsHW = 0;
	
	// Auxiliary table storing the m[i][j] costs
	private static int[][] m;
	
	// Auxiliary table storing optimal k values
	private static int[][] s;
	
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
		s = new int[n+1][n+1];
		
		int total = matrixChainOrder(dimensions);
		
		System.out.println("Minimum number of scalar mults (DP): " + total);
		System.out.println("Num Multiplications: " + numMultsHW);
		
		//printOptimalParens(1, n);
		
	}
	
	public static int matrixChainOrder (int[] p) {
		for (int i=1; i<=n; i++) {
			m[i][i] = 0;
		}
		
		for (int l=2; l<=n; l++) {
			for (int i=1; i<=n-l+1; i++) {
				int j = i + l - 1;
				
				m[i][j] = Integer.MAX_VALUE;
				
				for (int k=i; k<j; k++) {
					int q = m[i][k] + m[k+1][j] +
							(p[i-1] * p[k] * p[j]);
					numMultsHW = numMultsHW + 2;
					if (q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		
		return m[1][n];
	}
	
//	public static void printOptimalParens(int i, int j) {
//		if (i == j) {
//			System.out.print("A_" + i + " ");
//		} else {
//			System.out.print("(");
//			printOptimalParens(i, s[i][j]);
//			printOptimalParens(s[i][j] + 1, j);
//			System.out.print(")");
//		}
//	}
}