def add(): # function definition :
    a = int(input("Enter your number 1 : "))
    b = int(input("Enter your number 2 : "))
    c = int(input("Enter your number 3 : "))
    d = int(input("Enter your number 4 : "))
    f = int(input("Enter your number 5 : "))
    g = int(input("Enter your number 6 : "))
    l = [a,b,c,d,f,g]
    print(f"The sum is : {sum(l)}")
# function call :
add()    

def factorial(n):
    if (n == 1 or n == 0):
        return 1
    fact = n * factorial(n-1)
    return fact

n = int(input("Enter the number : "))
print("The factorial is : ", factorial(n))

def ackermann(m,n):
    if (m == 0):
        return n + 1
    elif (m > 0 and n == 0) :
        return ackermann(m - 1, 1)
    else :
        return ackermann(m - 1, ackermann(m, n - 1))

m = int(input("Enter m: "))
n = int(input("Enter n : "))
print(ackermann(m,n))    

def pattern(r):
    if(r==0):
        return 
    print("*"*r)
    pattern(r-1)

r = int(input("Enter the number of rows : "))    
pattern(r)