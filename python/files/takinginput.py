a = input("Enter number 1 : ")
b = input("Enter number 2 : ")
print("The entered number 1 : ", a)
print("The entered number 2 : ", b)
print("The sum is : ", a+b,":This output is beacuse the entered numbers are taken as strings not integers " )
# to take input as integers :
c = int(input("Enter number 1 : "))
d = int(input("Enter number 2 : "))
print("the sum is : ",c+d)

# checking the type taken using input 
g = (input("Enter something : "))
print(type(g))

# to finding a sqaure of the number 
num = int(input("Enter the number : "))
print ( "The square number is : ",num**2)
print ( "The number to power 10  is : ",num**10)

note = '''Python is an interpreted language that is why instead of taking all the input once 
it took input for first declarations , print the output and then the next declrtions , and so on 
unlike java , kotlin , c or c++ then is no need of central main function , because it is 
not a compiled language .'''
print(note)