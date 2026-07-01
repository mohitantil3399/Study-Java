import math

num = int(input("Enter the number to be checked : "))

if num <= 1:
    print("The number is not prime.")
else:
    limit = int(math.sqrt(num))
    for i in range(2, limit + 1):
        if num % i == 0:
            print("The number is not prime.")
            break
    else:
        print("The number is prime.")