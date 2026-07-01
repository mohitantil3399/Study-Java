public class jagged_array {
    public static void main(String[] args) {
        // number of rows is fixed but columns can varry 
        int nums[][]= new int[4][];

        // defining columns for each row separately : 
        nums[0] = new int[5];
        nums[1] = new int[8];
        nums[2]= new int[16];
        nums[3] = new int[9];
        //crreating a random array 

        for(int i = 0; i < nums.length;i++){
            for(int j = 0 ; j <nums[i].length;j++){
                nums[i][j] = (int)(Math.random()*100);
            }
        }
        // lets print the array 
        for(int n[]: nums){                         
            for(int m:n){
                System.out.print(m +" ");
            }
            System.out.println();
        }
        String hello = new String("Hello");
         String result = hello.concat(" World");
        System.out.println(result);
    }
}
