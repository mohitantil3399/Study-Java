# 1. Variables & Print
# Declare two variables, `fruit` and `price`, and print them together in one sentence.
fruit = "Apple"
price = 1.99
print(f"The price of an {fruit} is ${price}.")

print("\n-------------------\n")

# 2. Comments
# Write a program that multiplies two numbers. Add a single-line comment explaining the calculation and a multiline comment describing the purpose of the program.
"""
This program demonstrates basic multiplication of two numbers.
It is part of the practice set for learning Python.
"""
a = 5
b = 10
# Multiply a and b and store the result in c
c = a * b
print(f"The result of multiplying {a} and {b} is {c}.")

print("\n-------------------\n")

# 3. Strings & Methods
# Given `text = "Python is Amazing"`, perform the following:
# - Convert the string to uppercase.
# - Replace "Amazing" with "Fun".
# - Print the length of the string.
text = "Python is Amazing"
print(text.upper())
print(text.replace("Amazing", "Fun"))
print(len(text))

print("\n-------------------\n")

# 4. type() Function
# Create three variables of different types (integer, float, string) and use `type()` to display their data types.
my_int = 42
my_float = 3.14
my_string = "Hello"
print(type(my_int))
print(type(my_float))
print(type(my_string))

print("\n-------------------\n")

# 5. Challenge
# Write a program that declares a variable `quote = "Practice makes perfect"`.
# - Print the quote.
# - Print the quote in title case.
# - Add a comment explaining what the program does.

# This program declares a string variable containing a quote and prints it in both its original form and title case.
quote = "Practice makes perfect"
print(quote)
print(quote.title())
