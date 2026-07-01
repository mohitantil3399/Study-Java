
             public class Life{
                 Boolean happy;
                 Boolean sad;
                 String moto;
              Life(String moto){             
                 this.happy = null;
                 this.sad   =  null;
                 this.moto = moto;
               }
               Boolean ishappy(){             
                 return true;
               }
               Boolean isSad(){             
                 return  true;
               }
             
              public static void main(String []args){
               Life moto = new Life("To Live");
               if(moto.isSad()){
                moto.ishappy();
              }
               System.out.println("Is happy : "+ moto.ishappy());
              }
              }