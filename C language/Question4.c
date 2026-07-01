//Write a program to find roots of quadratic equation using switch statements.
#include<stdio.h>
#include<math.h>
int main (){
printf("Enter the coefficients a,b,c as per the equation : ax^2+bx+c = 0  : ");
float a,b,c;
scanf("%f%f%f",&a,&b,&c);
float d = ((b*b)-4*a*c);
int  option ;
if(d>0){
     option = 1;
}else if(d<0){
    option = 2;
}else{
    option = 3;
}
switch(option){
    case 1:{ printf("The real roots are :%.4f and %.4f",(-b + sqrt(d) )/(2*a),(-b-sqrt(d)/(2*a)));
    }break;
    case 2: {float x = ((-b )/(2*a));
            float y =  (sqrt(-d)/(2*a));
         printf("The complex roots are: %.4f + %.4fi and %.4f - %.4fi",x,y,x,y);
    }
         break;
    case 3 :{ printf("The roots are same : %.4f and %.4f",-b/(2*a),-b/(2*a));
    }break; 
}
return 0;
}