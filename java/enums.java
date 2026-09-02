 public class enums {
    //enum used to define usable set of constants 
    //it automatically defines public static final fields that can be used throughout the file 
    
    public static void main(String[] args) {
        // i can access the enum fields here 
        /*
         *They are already public static final type of fields , that are extension of enum class of java 
         */
        System.out.println(days.FRIDAY);
        System.out.println(days.FRIDAY);
        System.out.println(days.FRIDAY);
        System.out.println(days.FRIDAY);
        System.out.println(days.FRIDAY.ordinal());//for constant value just like the index of an array 
        System.out.println(days.THURSDAY.ordinal());
        System.out.println(days.SATURDAY.ordinal());

        days monday = days.MONDAY;//creating an object of enum to perform actions
        System.out.println(monday);
        String day_name = monday.name().replace('O', 'a');//using .name()
        System.out.println(day_name);
        //can get index of the object as well using .ordinal()
        days day = days.SUNDAY;
        int idx = day.ordinal();
        System.out.println(idx);
//using valueof()
        days newDay = days.valueOf("SATURDAY");
        System.out.println(newDay);

        days day2 = days.THURSDAY;
        System.out.println(day2.getLower());
        
        days day3 = days.FRIDAY;
        System.out.println(day3.getLower());

        System.out.println(day2.getIdx());
        System.out.println(day3.getIdx());
    }
}

//Defining an enum 
enum days{
    MONDAY("monday",10),
    TUESDAY ("tuesday",20), 
    WEDNESDAY ("wednesday",30), 
    THURSDAY ("thursday",40), 
    FRIDAY("friday",50), 
    SATURDAY("saturday",60), 
    SUNDAY("sunday",70);


    //I can declare custom methods and fields here 
    private String lower_value;
    private int customIdx;
    //make custom constructors here 
    private days(String lower_value,int idx){
        this.lower_value = lower_value;
        this.customIdx=idx;
    }
    //writing getter for this private string field lower_value
    public String getLower(){
        return lower_value;
    }
    //getter for private custo index field 
    public int getIdx(){
        return customIdx;
    }
}
