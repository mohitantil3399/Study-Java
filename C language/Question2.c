// Write a program to find area and perimeter of a circle and a triangle.
#include<stdio.h>
#include<math.h>
const float  pi= 3.14;
void areaOfCircle(float r ){
    if(r==0){
        printf("The are of circle is : 0 sq units\n");
    }
    if(r< 0){
        printf("Enter positive radius \n ");
        
    }else{
    float area = pi*r*r;
    printf("The area of the circle is :%.6f sq units\n",area);
    }
}
void perimeterOfCircle(float r){
    if(r==0){
        printf("The perimeter of the circle is 0 units\n");
    }
    if(r<0){
        printf("Enter positive radius \n");
        
    }else{
    float perimeter = 2*pi*r;
    printf("The perimeter of the circle is : %.6f units\n",perimeter); 
    }
}
float perimeterOfTriangle(float a,float b, float c){
    if(a==0&&b==0&&c==0){
     printf("The triangle does not exist");
     return 0;
    }
    else{
    const float p = a+b+c;
    return p;
    }
    
}
void areaOfTriangle(float a, float b , float c){
    if(a==0&&b==0&&c==0){
    printf("The triangle does not exist");
    }
    float sp = perimeterOfTriangle(a,b,c)/2;
    float area = sqrt(sp*(sp-a)*(sp-b)*(sp-c));
    printf("The area of Triangle is : %.6f sq units", area);
}
int main (){
printf("_ _ _ _The circle calculations are :_ _ _ _  \n\n");
printf("Enter the radius of circle : ");
float r;
scanf("%f",&r);
printf("\n");
perimeterOfCircle(r);
areaOfCircle(r);
printf("\n");
float a,b,c;
printf("_ _ _ _ _The triangle calculations :_ _ _ _ _\n");
printf("Enter length of side 1 : ");
scanf("%f",&a);
printf("\nEnter length of side 2: ");
scanf("%f",&b);
printf("\nEnter length of side 3: ");
scanf("%f",&c);
printf("The triangle perimeter is : %.6f units\n",perimeterOfTriangle(a,b,c));
areaOfTriangle(a,b,c);
return 0;
}