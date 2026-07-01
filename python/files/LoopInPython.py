'''
there are three type of loops in python as well 
for loop,while loop, do-while loop
'''
for i in range(1,101,3):
    print(i)# 6 is excluded whereas 1 is included because , its open interval
# range function in for loop : range(start index , end index , step size )
# step size : like we had i++ ,i+2 ,in java , its same , next number will be +x, here in +3 

# using while loop : 
i = 1
j = 7
print("The table of ",j,"is: ")
while(i<=10):
     print( j*i)
     
     i = i+1

# this is how we iterate in list and tuple 
list1 =[123,1232,131,23,12334,34,34,534,5,43,5435,"Hello"]
for i in list1:
     print(i)

tuple1 = (23,13,2,2,423,4,23,4,234,23,4,"Hello")
print("tuple is immutable :")
for j in tuple1:
     print(j)     

name = "Mohit"
print("Iteration in strings :")
for i in name:
     print(i)     
print("for loop with else : ")
# for loop can also be used with else statement as well , which executes after loop exhausts 
for i in range(8):
     print(i)
else:
     print(9)     

# break : used to exist amid running in the loop 
# continue : skip one iteration , ek loop beech mein chhod do 
#pass statement : its a null statement and does nothing  

# Example of continue statement
print("Using continue to skip even numbers:")
for i in range(1, 10):
    if i % 2 == 0:
        continue
    print(i)

# Example of pass statement
print("Using pass in loop:")
for i in range(5):
    if i == 2:
        pass  # does nothing, just a placeholder
    print("Value:", i)