public class nestedloop10 {
    public static void main(String[] args) {
        int n = 5;
        for(int i = 1;i<=n;i++){
            for(int s = 1;s<=n-i;s++){
                System.out.print(" ");
            }for(int j=1;j<=i;j++){
                System.out.print(i+" ");
            }for(int s = n-i;s>=1;s--){
                System.out.print(" ");
            }System.out.println();
        }
    }
}
