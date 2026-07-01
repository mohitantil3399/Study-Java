import java.util.Scanner;
public class recursion6{
    public static int  first = -1;
        public static int last = -1;
    public static void printIndex(int indx , char element,String str){
        if (indx == str.length()){
            System.out.println("first index : "+first);
            System.out.println("Last index : "+last);
            return;
        }
        if ( element == str.charAt(indx)){
            if (first == -1){
                first = indx;
            } 
           last = indx;
        } printIndex(indx+1, element, str);
    }
    public static void main(String[] args) {
        Scanner ms = new Scanner(System .in);
        System.out.print("Enter your string: ");
        String str = ms.next();
       System.out.println(str.length());
        printIndex(0, 'a', str);
        ms.close();
    }
}
