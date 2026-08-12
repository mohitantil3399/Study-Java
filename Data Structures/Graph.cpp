# include<iostream>
#include<vector>
#include <list>
using namespace std;

//graph class 
class Graph{
    //we need number of vertices 
    int v ;
    //we need a dynamic array list for each vertex to store its neighbours (adjacency list)
    list<int>*li;

    //constructor of the class , initialised with the vertex
public:
    Graph(int v){
        this->v=v;//initialised vertex
        //initialise list 
        li = new list<int>[v];//v is size of the list (total number of edges)
    }
    //function to add edges adjacent to the given vertex
    void addEdge(int u , int v){
        //suppose u and v are the connected vertices : u------v , then adjacency list of u will have : v and vice versa
        li[u].push_back(v);
        li[v].push_back(u);
    }
    void printlist(){
        //we loop over the vertices to print the adjacent vertices to which the edges are connected 
        for(int i =0;i<v;i++){
            //which vertex adjacent vertices are we printing
            cout << i << " : ";
            for(int neighbours:li[i]){
                cout <<neighbours<<"  ";
            }
            cout << endl;
        }
    }
};
int main(){
//let's create the adjacency list to represent the grpah 
    //make an object of class Graph
    Graph graph(5);// 5 = total vertices i am defining
    graph.addEdge(0,1);
    graph.addEdge(1,2);
    graph.addEdge(1,3);
    graph.addEdge(2,4);
    graph.addEdge(2,3);
//printing the output 
    graph.printlist();
    return 0;
}

