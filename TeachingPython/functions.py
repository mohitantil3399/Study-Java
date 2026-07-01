#write a function to print square of a number 
def square(num):
    return (num*num)
print(square(8))

# a function to check even or odd
def check(num):
    def even(num1):
        result = num1%2 ==0
        return result
    
    def odd(num2):
        result = num2%2!=0
        return result
    
    r = even(num)
    q = odd(num)
    if r :
        return True
    else:
        return False
    

print(check(8))

def add(a ,c,f,d,k,dj,b):
    print(b)
add(99,88,4,3,7,24,323)

