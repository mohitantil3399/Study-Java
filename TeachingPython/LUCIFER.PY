'''signal = input("enter signal : ")
if signal.lower() == "green" :
    print("you can go now")
elif signal.lower() == "yellow":
    print("wait for green signal")
else:
    print("stop ")'''
'''
password = int(input("enter the password"))
if password == 666:
    print("welcome sir")
else:
    print("wrong password try again")


password = input("please enter password : ")
if password.lower() == "lucifer":
    print("you are welcome sir")
elif password.lower() == "heisenberg":
    print("better luck next time")
else :
    print("no entry wrong password ")

name = input("SAY MY NAME : ")
if name.upper() == "HEISENBERG":
    print("YOU GOD DAMN RIGHT")
else:
    print("BOOM")'''
'''
choice = input("enter signal : ")
match(choice):
    case"red" :
        print("stop")
    case"yellow" :
        print("wait")
    case"green" :
        print("you can go now")
    case _:
        print("error case") '''  

'''score = int(input(": "))
match score:
    case 90|91|92|93:print("aced")
    case c if c <=30 : print("failed")'''
'''
def min(a,b,c,d):
    print(c)
min(3,4,5,6)''' 
'''
def power(num1,exp):
    return(num1**exp)
print(power(5,3))

def even(num1):
    return(num1%2==0)
print(even(4))

def odd(num2):
    return(num2%2!=0)
print(odd(77))


def max(a,d,s)->int:
    if a < d and s<d:
        return(d)
    elif d < s and a <s:
        return(s)
    else: 
        return(a)
print(max(22,5,67))

def sum (a,b,c)->int:
    return(a+b+c)
print(sum(34,6,6))

def faherenhiet(c)->float:
    return(((9/5)*c)+32)
print(faherenhiet(8))

def reverse(s)->str:
    return(s[::-1])
print(reverse("juice"))
a = "apple"
b = a[0:6:2]
print(b)

operations = '''
'''available options : 
+ -> add()
- -> sub()
* -> multi()
/ -> div()'''
'''
print(operations)
def add():
    print(a+b)

def sub(a,b):
    print(a-b)

def multi(a,b):
    print(a*b)

def div(a,b):
    print(a/b)

choice = input("Enter your operation choice : ")
match choice:
    case "+":
        a = int(input("Enter number 1 : "))
        b = int(input("Enter number 2 : "))
        add(a,b)
    case "-":
        a = int(input("Enter number 1 : "))
        b = int(input("Enter number 2 : "))
        sub(a,b)
    case "*":
        a = int(input("Enter number 1 : "))
        b = int(input("Enter number 2 : "))
        multi(a,b)
    case "/":
        a = int(input("Enter number 1 : "))
        b = int(input("Enter number 2 : "))
        div(a,b)
    case _: print("Invalid")


a = int(input("Enter number 1 : "))
b = int(input("Enter number 2 : "))
operation = input("Enter your operation : ")
result = eval(f"{a}{operation}{b}")
print(result)'''
'''for i in range(1,6):
    i = i*i
    print(i)'''
'''rows = 5
for i in range(1, rows + 1):
        print("*"*i)'''
for i in range(1,11):
    print(i*7)
    rows = 5
for i in range(1,rows+1):
    print
