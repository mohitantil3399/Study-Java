public class String_Buffer {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        System.out.println(sb.capacity());// 16 char capacity 
        sb.append("Hello how are you ?");
        System.out.println(sb.capacity());//34 character capacity
        
    }
}
 