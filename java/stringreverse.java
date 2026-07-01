import java.util.Scanner;
public class stringreverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your String: ");
        String str = sc.nextLine();
       StringBuilder sb = new StringBuilder(str); 
       System.out.println("The given string is : ");
       System.out.println(sb); 
       for ( int i = 0 ; i <=sb.length()/2;i++){
       int front = i;
       int back = sb.length()-i-1;
       char frontChar = sb.charAt(front);
       char backChar  = sb.charAt(back);
       sb.setCharAt(front, backChar);
       sb.setCharAt(back, frontChar);
       }
       System.out.println("The reverse string is : "+sb);
    sc.close();
    }
}
