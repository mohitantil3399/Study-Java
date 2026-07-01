#include<stdio.h>
struct students {
    int rollNumber;
    char Name[50];
    char class[20];
};
int main(){
    struct students s1 = {24,"Hina","Twelveth"};
    printf("rollnumber = %d,Name = %s,class = %s", s1.rollNumber,s1.Name,s1.class);
    return 0;

}