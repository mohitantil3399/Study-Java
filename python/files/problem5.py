#create a tuple 
a= (1,23,324,432,"hello","hi","mytuple")
print(len(a))

my_tuple = ("Hello","using","unpacking")
a, b, c, = my_tuple
print(a,b,c)

# sum 4 numbers input in a list
first = int(input("Enter the number: "))
second = int(input("Enter the number: "))
third = int(input("Enter the number: "))
fourth = int(input("Enter the number: "))
list = [first,second,third,fourth]
print(f"the sum is : {sum(list)}")

# count number of zeros 
a = (12,0,23,00,0,0,0,0,0,0,0,0,0,000,00000,0000,2342,423,423,423,3,3423,50,0,0,0,00,0,0,0,0)
print(a.count(0))