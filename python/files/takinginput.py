"""
=============================================================================
             USER INPUT & TYPE CONVERSION IN PYTHON — NOTES & GUIDE
=============================================================================
In Python, input from the user is captured using the built-in `input()` function.

Key Rules:
1. input() ALWAYS returns a string (str), regardless of what the user types.
2. To perform mathematical operations, you MUST explicitly typecast (convert)
   the string to an `int` or `float`.
3. Multiple inputs on a single line can be captured using `.split()` and `map()`.
=============================================================================
"""

print("=" * 60)
print("USER INPUT & TYPE CONVERSIONS")
print("=" * 60)

# 1. Basic String Input
name = input("Enter your name: ") if False else "Mohit"
print(f"Hello, {name}! type(name): {type(name)}")

# 2. Integer & Float Typecasting
# Uncomment below lines to test interactively:
# a = int(input("Enter integer 1: "))
# b = int(input("Enter integer 2: "))
# print(f"Sum: {a + b}")
# price = float(input("Enter price: "))

# 3. Multiple Inputs on a Single Line (Space-separated)
# Example input: "10 20 30"
sample_line = "10 20 30"
x, y, z = map(int, sample_line.split())
print(f"Parsed multiple numbers: x={x}, y={y}, z={z}, sum={x + y + z}")

# 4. Reading a list of numbers from input
numbers_list = list(map(int, sample_line.split()))
print(f"List of numbers: {numbers_list}")

# 5. Concept Note
note = """
[CONCEPT NOTE]
Python is an interpreted, dynamically-typed language.
Execution flows line-by-line from top to bottom.
Unlike Java, C, or C++, Python does not require a 'public static void main'
class boilerplate to execute code.
"""
print(note)