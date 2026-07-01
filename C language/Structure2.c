//syntax :
// struct name{
//  data type_ members
// data type _ members
//  }
#include<stdio.h>
struct employees{
    int emplId;
    char nameOfEmpl[20];
    float salary;
};
int main(){
 struct employees empl1;
 printf("Enter the emplyee id : ");
 scanf("%d",&empl1.emplId);

 printf("Enter the employee name : ");
 scanf("%s", empl1.nameOfEmpl);

  printf("Enter the employee salary : ");
  scanf("%f",&empl1.salary);

 printf("The employee details are :\n");
  printf("The employee id is : %d\n",empl1.emplId);
   printf("The employee name is : %s\n",empl1.nameOfEmpl);
    printf("The employee salary is : %.5f\n",empl1.salary);
    return 0;
}
