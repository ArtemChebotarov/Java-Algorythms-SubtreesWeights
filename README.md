# Finding Subtrees Weights

Programming tool:

The task is performed in Java JDK 8.

Description and solution of the problem:

The file with input data contains graph data, which we will continue to check. The first line contains the number of vertices,
starting from 3 lines we have vertices (parents) connected with their children. At the first-line reading level, 
we create a Main class object with the number of vertices indicated in the argument. The Main class, in turn, is an abstract 
representation of a graph, so we will continue working with this class object. Starting from the third line, we save all the 
numbers read into the list, and then add it to the indexedChildren list, which contains all the connections of parents and 
children. We also write these numbers to the neighborhood table of all neighboring units using the addNeigbourhood method.
We use a given list to determine the presence of cycles in the graph. At the reading phase, we immediately determine the top 
vertex to determine the weight of the subtrees (we need to know from which vertex we need to start the countdown). This is done
as follows: we add all "parents" to the top variable and subtract all children. In the end we will get the index of the upper 
vertex. Then we check if the graph is a tree. A graph is a tree if two conditions are met: the number of vertex connections is 
equal to n-1 in relation to the number of vertices n; the graph has no vertices connected cyclically with each other. The check
will be invoked using the isTree method and will be performed as follows: first, a visited [] array of the boolean type is
created, in which all visited vertices will be marked (from the beginning it is filled with false meanings); therefore the
isCyclic method is called, which in turn checks, using the dfs algorithm, a graph for the presence of cyclic connections,
taking in the arguments the table of visited vertices, the index of the next vertex and the parent's index, marking the next
vertex as visited, calling recursively for all "children "The next vertex is the isCyclic method and checking if there is
another" child "" parent "of the unit visited at a given moment (if it is not - returns true and we can come to the conclusion
that we encountered a cycle); finally if isCyclic returned false - we check if there are unvisited vertices in the visited []
array (if it is not - false is returned and it is assumed that the graph is not a tree). If the graph is a tree - the weight
of all subtrees is checked using the getSubGraphWeight method, which works as follows: for the first time the method run is
given, as a parameter, the upper vertex index; each top vertex child is visited in turn, and the index is added to the sum
variable; the getSubGraphWeight method is recursively invoked with the next vertex index given as the parameter; after
"returning" to the top vertex, the weight of the visited vertex is checked and compared with the minimum weight minSum and
maximum weight maxSum; if the comparison matches - the maximum weight value is replaced, minimum weight or both. At the end,
the program outputs the results in the console in the form: if the graph is a tree:
IS weight_maximum weight_minimum, 
if the graph is not a tree:
NO
NOTE: the algorithm works correctly only when marking vertices with natural numbers from 1 in ascending order

Analysis:

Pessimistic complexity of the algorithm - n:
isTree () - O (n) because inside the method we use the recursive isCyclic () method, besides, we fill the visited [] array with false values and finally check the presence of true inside vivsited [], which gives the O (n) complexity;
isCyclic () - O (n) because inside the method we use only the dfs algorithm, whose complexity is O (n);
getSubGraphWeight () - O (n) because inside the method we use the similarity of the dfs (O (n)) algorithm with which we visit
each of the children of the main vertex;
constructor Main () - O (n) because inside the method we create a list of neighbors for each vertex, nothing more
inside the main () method, the biggest part of the work is reading data from files, the remaining part turns on the process of
checking the graph and displays the received data on the screen we find that the pessimistic complexity of algorithms is n.
Memory complexity - at most n^2:
in my case, we have 2 variants of data structures - a list of letters and an array of letters, i.e. in both cases, with the 
worst data, the complexity will be 𝑛2, (e.g. Linked List (S (n)) * array (S (n)) = S (n^2))

