import java.util.*;
public class printPErmutations {
    public static void printPermutations(String str, int idx,String permutation){
        if(idx == str.length()){
            System.out.println(permutation);
            return;
        } for ( int i = 0 ; i < str.length();i++){
            char currchar = str.charAt(i);
            String newstring = str.substring(0, i)+str.substring(i+1);
           printPermutations(newstring, idx, permutation+currchar);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String str = sc.nextLine();
        printPermutations(str, 0,"");
        sc.close();
    }
}
