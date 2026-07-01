import java.util.Scanner;
public class strings1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String str = sc.nextLine();
        String result = " ";
        for ( int i = 0 ; i <str.length();i++){
            if( str.charAt(i) == 'e' ){
            result+='i';
//if the input is equal to e ,the result string will add an 'i' otherwise the same chracters as entered in the string str by the user.
            }else{
                result += str.charAt(i);
            }
        }
        System.out.println(result);
    sc.close();
}
}
