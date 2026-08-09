import java.util.Scanner;
public class Buffer {
    void main(){
        //This all the input statements are stored into buffer
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number one: ");
        int num1 = sc.nextInt();
        System.out.println("Enter number2 :");
        int num2 = sc.nextInt();
        System.out.println("Enter the string: ");
        String str = sc.nextLine();
        System.out.println("Enter number3 :");
        int num3 = sc.nextInt();
        System.out.println(num1 +"\n"+ num2+"\n"+ str+"\n"+num3);
        sc.close();

        // StringBuilder sb = new StringBuilder();
        // for (char i = 0;i<1001;i++){
        //     sb.append(i).append(", ");
        // }
        //     System.out.println(sb);
    }
}
