import java.util.*;
public class recursion16 {
    static int count = 0;
    public static void printSubset(ArrayList<Integer>subset){
        for ( int i = 0;i<subset.size();i++){
     System.out.print(subset.get(i)+" ");
    }System.out.println();
    count ++;
}

    public static void findSubsets(int n , ArrayList<Integer> subset ){
        if ( n == 0){
            printSubset(subset);
            return;
        }// if element is included , its added in the the subset 
        subset.add(n);
        findSubsets(n-1, subset);
        // if element is excluded , it has to be removed from last index ,if its added again in the subset
        subset.remove(subset.size()-1);
        findSubsets(n-1, subset);
    }
    public static void main(String[] args) {
        ArrayList<Integer> subset = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements in the powerset : ");
        int n = sc.nextInt();
        System.out.println("The subsets are : ");
        findSubsets(n, subset);
        System.out.println("The total number of subsets are :"+count);
        sc.close();
    }
}
