public class nestedloop4{
public static void main(String[]args){
  int n = 100;
  for (int i = 1;i<=n;i++){
    //inserting space
    for (int j = 1; j<=n-i;j++){
        System.out.print(" ");
    }
//inserting X
    for(int j=1;j<=i;j++){
    System.out.print("x");
    //starting from second line
    }System.out.println();
  }
} 
}


