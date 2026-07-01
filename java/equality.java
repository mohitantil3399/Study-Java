import java.util.Scanner;

public class equality {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        //int z =sc.nextInt();
       // int b = sc.nextInt();
         ////if ( z==b ){
          //  System.out.println("equal");
         //}
       
         // else if (z>b){
           //     System.out.println("z is greater than b");
           // } else {
           //     System.out.println("b is greater than z");
           // }
            int number = sc.nextInt();

           switch(number){
         case 1 : System.out.println("january");break;// switch statement h 
         case 2 : System.out.println("february");break;
         case 3 : System.out.println("march");break;
        case 4 : System.out.println("april");break;
         case 5 : System.out.println("may");break;
  case 6 : System.out.println("june");break;
     case 7 : System.out.println("july");break;
         case 8 : System.out.println("august");break;
        case 9 : System.out.println("september");break;
         case 10 : System.out.println("october");break;
         case 11: System.out.println("november");break;
         case 12 : System.out.println("december");break;

         }
         sc.close();
    }
}

       