// butterfly pattern
public class nestedloop12 {
    public static void main(String[] args) {
        int n = 20;
        for (int i = 1;i<=n;i++){
            for(int j = 1;j<=i;j++){
             System.out.print("X"+" ");
            }for(int k = n-i;k>=1;k--){
                System.out.print(" ");
            }for (int k = n-i;k>=1;k--){
                System.out.print("   ");
            }
            for ( int l = 1;l<=i;l++ ){
                System.out.print("X"+" ");
            }
            System.out.println();
        } int a= n;
        for (int i = 1;i<=a;i++){
            for(int j = a;j>=i;j--){
             System.out.print("X"+" ");
            }for(int k = a-2;k>=a-i;k--){ 
                System.out.print("  ");
            }for (int k = a-2;k>=a-i;k--){
               System.out.print("  ");
            }
            
            for ( int l = a;l>=i;l-- ){
                System.out.print("X"+" ");
            }
            System.out.println();
        }
    }
}
