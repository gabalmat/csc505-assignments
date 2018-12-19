package org.csc505.homework4;

public class Dijkstra {
	
	// n x 2 matrix holding shortest paths and usp values
	static int ans[][];
	
//	public static void main(String[] args) {
//		int n = 5;
//		int e = 6;
//		//int mat[][] = {{1, 3, 2}, {1, 5, 3}, {2, 5, 3}, {4, 1, 1}, {4, 2, 1}};
//		int mat[][] = {{1, 2, 9}, {1, 3, 6}, {1, 4, 5}, {1, 5, 3}, {3, 2, 2,}, {3, 4, 4}};
//		int s = 1;
//		
//		Dijkstra_alg(n, e, mat, s);
//		
//		System.out.println("Hello");
//	}
	
    public static int [][] Dijkstra_alg( int n, int e, int mat[][], int s)
    {
    	 //Write your code here to calculate shortest paths and usp values from source to all vertices
		 //This method will have four inputs (Please see testcase file)
		 //This method should return a two dimensional array as output (Please see testcase file) 
    	
    	// Build adjacency matrix
    	Integer[][] adjMat = genAdjMatrix(n, mat);
    	
    	// Array holding vertices G.V
    	Vertex[] G = new Vertex[n+1];
    	
    	// Initialize ans as the n x 2 matix which gets returned
    	ans = new int [n][2];
    	// Set initial usp values for all to vertices to 1
    	for (int i=0; i<n; i++) {
    		ans[i][1] = 1;
    	}
    	
    	// Initialize the list of vertices G.V and the 'priority queue' Q
    	Vertex[] Q = new Vertex[n+1];
    	for (int i=1; i<=n; i++) {
    		if (i == s) {
    			G[i] = new Vertex(i, 0);
    			Q[i] = new Vertex(i, 0);
    		} else {
    			G[i] = new Vertex(i, Integer.MAX_VALUE);
    			Q[i] = new Vertex(i, Integer.MAX_VALUE);
    		}
    	}
    	
    	for (int i=1; i<=n; i++) {
    		// Extraxt-Min
    		Vertex u = null;
    		int min = Integer.MAX_VALUE;
    		for (int j=1; j<=n; j++) {
    			if (Q[j] != null && Q[j].getD() < min) {
    				min = Q[j].getD();
    				u = Q[j];
    				
    			}
    		}
    		
    		// Reset u.d to infinity so it doesn't get picked up on our next search for the min value
    		//Q[u.getI()].setD(Integer.MAX_VALUE);
    		Q[u.getI()] = null;
    		
    		// call RELAX for each neighbor of u
    		for (int j=1; j<=n; j++) {
    			int uIdx = u.getI();
    			int vIdx = j;
    			
    			if (adjMat[uIdx][vIdx] != null && Q[vIdx] != null) {
    				int newWeight = relax(G[uIdx], G[vIdx], adjMat[uIdx][vIdx]);
    				Q[vIdx].setD(newWeight);
    				ans[G[vIdx].getI() - 1][0] = newWeight;
    			}
    		}
    	}
    	
    	return ans;
    }
    
    private static int relax(Vertex u, Vertex v, int weight) {
    	if (v.getD() > u.getD() + weight) {
			v.setD(u.getD() + weight);
			v.setPi(u);
		}
    	else if (v.getD() == u.getD() + weight) {
    		ans[v.getI() - 1][1] = 0;
    	}
    	
		return v.getD();
	}
    
    // Builds adjacency matrix holding weights for edges (u,v) in M[u][v]
    private static Integer[][] genAdjMatrix(int n, int[][] mat) {
    	Integer [][] adjMat = new Integer[n+1][n+1];
    	
    	for (int i=0; i<mat.length; i++) {
    		adjMat[mat[i][0]][mat[i][1]] = mat[i][2];
    		adjMat[mat[i][1]][mat[i][0]] = mat[i][2];
    	}
    	
    	return adjMat;
    }
    
    private static class Vertex {
    	
    	// number
    	private int id;
    	
    	// shortest path value
    	private int d;
    	
    	// predecessor
    	private Vertex pi;
    	
    	public Vertex(int v, int initD) {
    		this.setI(v);
    		this.setD(initD);
    		this.setPi(null);
    	}
    	
    	public int getI() {
			return id;
		}

		public void setI(int i) {
			this.id = i;
		}

		public int getD() {
			return d;
		}

		public void setD(int d) {
			this.d = d;
		}

		public Vertex getPi() {
			return pi;
		}

		public void setPi(Vertex pi) {
			this.pi = pi;
		}
    	
    }
   
}
