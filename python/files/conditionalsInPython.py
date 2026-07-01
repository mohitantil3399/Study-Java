# syntax : if(condition ):
#          .....(indent)logic

#else if is implemented using = elif 
# else = else

'''
equal to : ==
greater or greater equal to : > or >=
smaller or smaler equal to : < or <=
 logical and : and
 logical or : or
 logical not : not 
 logica findin in any string : in(keyword)
'''

i = int(input("Enter your age : "))
if (i<18 ):
    print ("You are child and not eligible ")
elif(i == 18):
    print("You are eligible ")
else: 
    print("You are an adult and eligible ")        



    # practice set : 
    # find greatest of for numbers entered by the user 
num = int(input("Enter number 1 : "))
num2 = int(input("Enter number 2 : "))
num3 = int(input("Enter number 3 : "))
num4 = int(input("Enter number 4 : "))
largest = 0
if(num>num2 and num>num3 and num> num4):
     largest = num
elif(num2>num3 and num2> num and num2>num4):
     largest = num2
elif(num3>num4 and num3> num2 and num3> num):
     largest = num3
else: 
        largest = num4     
print("The largest number is : ",largest)


# write a code to detect spam emails on the basis of text below :
# "Make a lot of money ", "buy now ","subscribe this ","click this "

w1 = "Make a lot of money "
w2 = "buy now "
w3 = "subscribe this "
w4 = "click this "
mail = input("Enter your mail: ")
# using in keyword to check if its a spam
if((w1 in mail)or (w2 in mail )or(w3 in mail )or (w4 in mail)):
    print("This mail is a spam. ")
else:
    print("The mail is not a spam.")    


