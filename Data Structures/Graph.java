import java.util.ArrayList;
import java.util.List;
public class Graph {
    int v;
    List<List<Integer>>list;
    //constructor
    Graph(int v){
        this.v = v;
        list = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {//as arraylist is default initialised with null , here we make it empty 
        list.add(new ArrayList<>());
    }
    }
    //function to make the adcency list of the graph vertices 
    void addEdge(int u, int v){
        list.get(u).add(v);
        list.get(v).add(u);
    }
    void printlist(){
        //we loop over the vertices to print the adjacent vertices to which the edges are connected 
        for(int i =0;i<v;i++){
            //which vertex adjacent vertices are we printing
            System.out.print(i+" : ");
            for(int neighbours:list.get(i)){
                System.out.print(neighbours+"  ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(2,3);
        //printing the output 
        graph.printlist();
    }
}
