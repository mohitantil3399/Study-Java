public class nestedloop9 {//diagonal rohmbus
    public static void main(String[] args) {
        int n = 17;
        for(int i = 1; i<=n;i++){
            for(int j = 1;j<=n-i;j++){
             System.out.print(" ");
            } for(int j =1;j<=n;j++){
              System.out.print("X"+" ");
            }for(int k = 1;k<=n;k++){
                System.out.print(" ");
            }System.out.println();
        }
    }
}
