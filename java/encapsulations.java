class Demo {
    // making private variables 
    private String name;
    private Double password ;

    // writing setter functions to assign the values from outside 
    public void setName(String name){
        this.name = name;// we use this keyword to refer to the class instance variables directly 
    }
    public void setPassword(Double password){
        this.password = password;
    }

    // writting getter functiion to access those assigned values in  the main function or other functions outside the class 
    public String getName(){
        return name;
    }
    public Double getPassword(){
        return password;
    }

}

public class encapsulations {

    public static void main(String[] args) {
        Demo obj = new Demo();
        //obj.name;  not directly accessible as its private to the class 
        obj.setName("Yash");
        obj.setPassword(121232.2332);
        System.out.println(obj.getName()+"\n\n"+obj.getPassword());
    }
}
