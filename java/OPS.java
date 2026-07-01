class Pen{//declaration of class
    //properties of the class :
    String color ;
    String type ;

    public void printProperties(){
        System.out.println(this.color);
        //this. is a keyword ,to tell what want to access the color String of class Pen 
        System.out.println(this.type);
    }
    // using polymorphism : 
    // creating different method with same name to use the declared method again in the code ;
    // but either different type of input variable or different type of modifier 
    void printProperties(String type){
        this.type = "type";
        System.out.println(type);
    }
}
public  class OPS{
    public static void main(String[] args) {
      //creating an object
      Pen pen1 = new Pen();//pen() is a constructor ,with no predefind paramaters in it 
      // below is calling a polymorphic methos :
      pen1.printProperties(pen1.type = "Gell");
    }
}
