# Welcome to The Maze of Waze!
-----
### The project deals with the development of a directional weighted graph data structure.
### and using algorithms on it such as: short path calculation, short path passing through a set of vertices, Strongly Connect graph (for directed graph).
### In addition, support save the graph to a file and load from it.
       `          /*
      Data Structure                
                
      class DGraph --- >  Hashmap<node.key, nodeData class>
                
      class nodeData ---> Hashmap<node.key(dest), edgeData class>

      every node has hashmap of edges - node as source and other nodes as his destination node

      1--->2 will return the edge between them
                
      Each cell contains nodeData    Each cell contains edgeData 
                
                *****                   **********************
             1  *   *  ----------->     *   *    *   *   *   *
                *****                   **********************
             2  *   *                     1    2   3   4   5
                *****   
             3  *   *
                *****
             4  *   *
                *****

     /*`
---
<a href="https://ibb.co/3Bgwhf3"><img src="https://i.ibb.co/sy8h35f/graph.png" alt="graph" border="0"></a>
