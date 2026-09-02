import java.util.Scanner;
public class recursion9 {
    public static int count = 0;
    public static void Subsequences(String str, int idx , String NewString){
        if( idx == str.length()){
            System.out.println(NewString);
            count ++;//incremental counting of all the subsequences printed by the function
            return;
        }//declaratin of current character
        char currChar = str.charAt(idx);
        // to be chosen in newstring:
        Subsequences(str, idx+1, NewString+currChar);
        // not to be chosen in the newstring:
        Subsequences(str, idx+1, NewString);

    } public static void main(String[] args) {
        System.out.print("Enter your String : ");
        Scanner ds = new Scanner(System.in);
        String str = ds.nextLine();
        System.out.println("The all possible subsequences are: ");
        Subsequences(str, 0, "");
        System.out.println("The total number of subsequences is : "+count);
        ds.close();
    }
}
