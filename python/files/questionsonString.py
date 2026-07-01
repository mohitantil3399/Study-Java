# say good afternoon to your user with his name 
name = input("Enter your name : ")
print(f"Good Afternoon {name}!!")

# complete the sequence with name and date 
'''Dear|<name>|
   Congratulations
   You are selected !!  
   Date of reporting is : |<Date>|'''


date = input ("Enter the date : ")
print(f'''Dear {name}
       Congratulations 
      You are selected !!
     Date of reporting  is : {date}''')

# write a program to detect double space in given string
say = " Hello buddies  i am writing  to detect double  space "
space = say.find(("  "))
print("The first double space index is : " ,space)# returns the index of substring in a given string ,if not then returns -1

# replace double spaces from above with single space 
print("The original string : ",say)
print(say.replace("  "," ").replace("  "," ").replace("  "," "))
