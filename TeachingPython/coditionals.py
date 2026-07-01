#coditionals : Execute a block of code when a codition is met.
'''num = int(input("Enter a number : "))
if num % 2 ==0 :
    print("This is an even number")
else:
    print("This is an odd number")
print("This block continues.")'''


'''
signal = input("Enter signal : ")
if signal.lower() =="green" or signal.lower()=="yellow":
    if signal.lower() == "green":
        print("You can go")
    else:
        print("Wait for grreen signal.")
else:
    print("Stop.")'''

signal = input("Enter signal : ")
if signal.lower() == "green":
    print("You can go.")
elif signal.lower() == "yellow":
    print("Wait for green signal.")
else:
    print("Stop")