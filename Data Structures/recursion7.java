import java.util.Scanner;
public class recursion7 {
    public static void printString(String str, int idx , int count , String newString){
         if(idx==str.length()){
            for(int i = 0;i<count;i++){
                newString +='x';}
                System.out.println(newString);
                return;
         }
        char charx = str.charAt(idx);
        if( charx == 'x' ){
            count ++;
            printString(str, idx+1, count, newString);

        }else{
            newString += charx;
             printString(str, idx+1, count, newString);
        }
    }
    public static void main(String[] args) {
        Scanner md = new Scanner(System.in);
        System.out.print("Enter your String : ");
        String str = md.nextLine();
        printString(str, 0, 0, "");
        md.close();
    }
}
