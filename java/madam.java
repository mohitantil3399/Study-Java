public class madam {
    public static void main(String[] args){
        Days d =Days.mon;
        System.out.println(d);
    }
}
enum Days{
    sun("sun") , mon("mon") , tue("tue") , wed("wed") , thurs("thurs") ;
    public String day;
    Days(){
    }
    Days(String day){
        this.day=day;
    }
}
