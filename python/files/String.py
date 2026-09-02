name = "Mohit"
subname = name[0:3]# 0 is starting index and 3 is ending index where 3 is excluded , creates a substring
print("The main string is : ",name)
print("The substring is : ", subname)
# negative slicing in python :
print("The substring is : ",name[-4:-2])
# negative index : -1 is for length -1 and the 1st character is the least number (Here -5)
print("The Length of string is : ",len(name))


# slicing techniques in python 
say = "0123456789"
print("index slicing is : ",say[0:6:2])# output is 024 , because [0:6] gives 012345 , 
                 #from this every 2 element is printed after the beigning index element
print()

# to check if a string ends with a given element 
# return true oe false only 
print("Is it ending with h : ",name.endswith("h"))             
print("Is it ending with it : ",name.endswith("it"))             
# starts with 
print("Is it starting with M : ",name.startswith("M"))



# len() - Returns the length of the string
print(len("hello"))  # Output: 5

# lower() - Converts all characters to lowercase
print("HELLO".lower())  # Output: hello

# upper() - Converts all characters to uppercase
print("hello".upper())  # Output: HELLO

# capitalize() - Capitalizes the first character
print("python".capitalize())  # Output: Python

# title() - Capitalizes the first letter of each word
print("hello world".title())  # Output: Hello World

# strip() - Removes leading and trailing whitespace
print("  hello  ".strip())  # Output: hello

# lstrip() - Removes leading whitespace
print("  hello".lstrip())  # Output: hello

# rstrip() - Removes trailing whitespace
print("hello  ".rstrip())  # Output: hello

# replace(old, new) - Replaces all occurrences of old with new
print("banana".replace("a", "o"))  # Output: bonono

# split(delimiter) - Splits string into list using delimiter
print("a,b,c".split(","))  # Output: ['a', 'b', 'c']

# join(list) - Joins list into string using delimiter
print("-".join(["a", "b", "c"]))  # Output: a-b-c

# find(substring) - Returns index of first occurrence, -1 if not found
print("hello".find("e"))  # Output: 1

# index(substring) - Like find(), but raises error if not found
print("hello".index("l"))  # Output: 2

# count(substring) - Counts occurrences of substring
print("banana".count("a"))  # Output: 3

# startswith(prefix) - Checks if string starts with prefix
print("hello".startswith("he"))  # Output: True

# endswith(suffix) - Checks if string ends with suffix
print("hello".endswith("lo"))  # Output: True

# isalpha() - Checks if all characters are alphabetic
print("abc".isalpha())  # Output: True

# isdigit() - Checks if all characters are digits
print("123".isdigit())  # Output: True

# isnumeric() - Checks if string is numeric (includes Unicode numbers)
print("Ⅻ".isnumeric())  # Output: True

# isalnum() - Checks if all characters are alphanumeric
print("abc123".isalnum())  # Output: True

# isspace() - Checks if all characters are whitespace
print("   ".isspace())  # Output: True

# swapcase() - Swaps case of each character
print("Hello".swapcase())  # Output: hELLO

# zfill(width) - Pads string on the left with zeros
print("42".zfill(5))  # Output: 00042