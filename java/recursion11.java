public class recursion11 {
    public static String keypad[] = { ".","ABC","DEF","GHI","JKL","MNO","PQRS","TUVW","XYZ"};
    public static void keypadCombo(String str , int idx , String combinations){
   
    if (idx == str.length()){
    System.out.println( combinations );
    
    return;
   }
    char currChar = str.charAt(idx);
    String mapping = keypad[currChar - '0'];
    for (int i = 0 ; i <mapping.length();i++){
    keypadCombo(str, idx+1, combinations+mapping.charAt(i));
   
    }
   }
    public static void main(String[] args) {
        String str = "678";
        System.out.println("The total combinations are : ");
        keypadCombo(str, 0, "");
    }
}
