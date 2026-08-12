import java.util.ArrayList;
public class Graph2 {
    static class Edges{
        int source;
        int destination;
    
    Edges(int src, int dest){
        this.source = src;
        this.destination = dest;
        }
    }
    public static void createGraph(ArrayList<Edges>graph[]){
        for (int i = 0;i<graph.length;i++){//as graph is an array 
            graph[i]=new ArrayList<Edges>();
        }
        graph[0].add(new Edges(0, 1));//This created a pair of source and destination (one edge) of a graph 
        graph[0].add(new Edges(0, 2));
        graph[1].add(new Edges(1, 4));
        graph[1].add(new Edges(1, 2));
        graph[2].add(new Edges(2, 3));
        graph[3].add(new Edges(3, 4));
   

}
    public static void main(String[] args) {
        int v = 4;
        @SuppressWarnings("unchecked")
        ArrayList<Edges>[] graph = (ArrayList<Edges>[]) new ArrayList[v];
        createGraph(graph);
        //printing for the 2 index
        for (int i =0;i<graph[2].size();i++){
            Edges edge = graph[2].get(i);
            System.out.print(edge.destination+" ");
        }
    }
}
