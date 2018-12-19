# NCSU CSC 505 (Design and Analysis of Algorithms)

Programming assignments

Homework 3 - Solve the matrix chain multiplication problem using:
- [dynamic programming](src/org/csc505/homework3/h3p2_dp_gabalmat.java)
- [memoization](src/org/csc505/homework3/h3p2_memoized_gabalmat.java)
- [recursion](src/org/csc505/homework3/h3p2_recursive_gabalmat.java)

Homework 4: Implement modification to Dijkstra's algorithm that runs asymptotically
as fast as the original algorithm, and assigns a binary value *usp[u]* to every vertex *u* 
in *G*, so that *usp[u]=1* if and only if there is a unique shortest path from *s* to *u*. 
By definition *usp[s]=1*. [Dijkstra.java](src/org/csc505/homework4/Dijkstra.java) has a method: *Dijkstra_alg*. The method has 
an input parameter list (n, e, mat, s), where 
- n = number of vertices of G, 
- e = number of undirected edges of G,
- mat = an e x 3 matrix that defines the edges of G, 
- s = the source vertex of Dijkstra’s algorithm. 
Assume the vertices of G are numbered 1…n. In mat each row consists of three integers <u, v, weight>. 
Here, u and v define the edge (u,v), and weight is the corresponding edge weight. The method returns
an n x 2 matrix, where the ith row contains the path length and the usp value for the 
shortest path between source and vertex i for i in {1, …n}.
