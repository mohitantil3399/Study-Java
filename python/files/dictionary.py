'''
It is just like mapping in Java
'''

# Creating a dictionary
marks = {
    "Harry ": 10,
    "Rohan ": 21,
    "Mohan ": 22,
    "Rohit ": 32,
    "Rohit": 21,  # Duplicate key: this will overwrite the previous "Rohit "
}

# Printing the dictionary and its type
print(marks, "The type is:", type(marks))

# Accessing value using key
print("Marks of Rohan are:\n", marks["Rohan "])

'''
Properties of dictionary:
- It is mutable
- It is indexed
- It is unordered (in versions < 3.7)
- Cannot contain duplicate keys, but may contain duplicate values
'''

# Using .items() to get key-value pairs as tuples
print("Items (key-value pairs):", marks.items(), "\n")

# Using .keys() to get all keys
print("Keys in dictionary:", marks.keys(), "\n")

# Using .values() to get all values
print("Values in dictionary:", marks.values(), "\n")

# Using .update() to update or add a key-value pair
marks.update({"Rohan": 45})
print("After updating Rohan's marks:", marks, "\n")

# Using .get() to safely access a value
print("The get function returns key's value (for Rohan):", marks.get("Rohan"), "\n")

# Difference between .get() and direct access:
# marks["Unknown"] → KeyError if key doesn't exist
# marks.get("Unknown") → returns None if key doesn't exist

# Using .pop() to remove a key and return its value
removed = marks.pop("Harry ", None)
print("Removed 'Harry ':", removed)
print("After pop:", marks, "\n")

# Using .popitem() to remove the last inserted item
last = marks.popitem()
print("Last item removed:", last)
print("After popitem:", marks, "\n")

# Using .copy() to create a shallow copy
marks_copy = marks.copy()
print("Copied dictionary:", marks_copy, "\n")

# Using .setdefault() to get value or insert default if key is missing
default_val = marks.setdefault("Aman", 50)
print("Value for 'Aman':", default_val)
print("After setdefault:", marks, "\n")

# Using .fromkeys() to create a new dictionary from a list of keys
subjects = ["Math", "Science", "English"]
default_marks = dict.fromkeys(subjects, 0)
print("New dictionary from keys:", default_marks, "\n")

# Uncomment below to clear the dictionary
# marks.clear()
# print("After clearing:", marks)