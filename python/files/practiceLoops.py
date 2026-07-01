l = [ "Hemant","Kabir","Kashish","Karan","Harry","Sunny"]
for name in l :
    if(name.startswith('K')):
        print(f"Hello {name}")


# write a program to  find if prime 
num = int(input("Enter the number : "))
for i in range(2,num):
    if(num%i == 0):
        print("The number is not prime")
        break
else:
    print("The number is prime")       