"""
=============================================================================
               PYTHON FUNCTIONS — COMPLETE STUDY GUIDE
=============================================================================
A Function is a reusable block of organized code designed to perform a
specific task. Functions help achieve modularity and DRY (Don't Repeat Yourself).

Key Function Topics:
1. Defining and calling functions (def, return)
2. Arguments: Positional, Keyword, Default, *args, **kwargs
3. Lambda (Anonymous) Functions
4. Built-in Higher-Order Functions (map, filter, reduce)
5. Variable Scope (LEGB Rule, global, nonlocal)
6. Recursion & Recursion Limits
7. Docstrings and Type Hints
=============================================================================
"""

import functools

# ─────────────────────────────────────────────────────────────────────────────
# 1. FUNCTION DEFINITIONS, PARAMETERS & RETURN VALUES
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 65)
print("1. BASIC FUNCTION DEFINITIONS & ARGUMENTS")
print("=" * 65)

# Docstrings & Type Hints
def calculate_area(length: float, width: float = 1.0) -> float:
    """
    Calculates the area of a rectangle.
    - length: length of rectangle
    - width: width of rectangle (default = 1.0)
    """
    return length * width

print(f"Area (10, 5): {calculate_area(10, 5)}")
print(f"Area with default width (length=7): {calculate_area(7)}")
print(f"Keyword arguments: {calculate_area(width=4, length=6)}")
print(f"Docstring: {calculate_area.__doc__.strip()}\n")


# ─────────────────────────────────────────────────────────────────────────────
# 2. VARIABLE ARGUMENTS (*args and **kwargs)
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 65)
print("2. VARIABLE ARGUMENTS (*args and **kwargs)")
print("=" * 65)

# *args collects extra positional arguments into a TUPLE
def sum_all_numbers(*args) -> float:
    """Takes any number of numeric arguments and returns their sum."""
    print(f"  args received as tuple: {args}")
    return sum(args)

print("sum_all_numbers(1, 2, 3, 4, 5):", sum_all_numbers(1, 2, 3, 4, 5))
print("sum_all_numbers(10, 20):", sum_all_numbers(10, 20))

# **kwargs collects extra keyword arguments into a DICTIONARY
def student_profile(name: str, **kwargs):
    """Builds a student profile dictionary with dynamic attributes."""
    print(f"\nStudent: {name}")
    for key, value in kwargs.items():
        print(f"  {key.title()}: {value}")

student_profile("Rohit", age=20, branch="CSE", semester=4, cgpa=9.1)


# ─────────────────────────────────────────────────────────────────────────────
# 3. LAMBDA FUNCTIONS (ANONYMOUS FUNCTIONS)
# ─────────────────────────────────────────────────────────────────────────────
print("\n" + "=" * 65)
print("3. LAMBDA FUNCTIONS (Anonymous inline functions)")
print("=" * 65)

# Syntax: lambda arguments : expression
square = lambda x: x ** 2
add_three = lambda a, b, c: a + b + c

print(f"square(6): {square(6)}")
print(f"add_three(10, 20, 30): {add_three(10, 20, 30)}")

# Sorting with custom lambda key
students = [("Aarav", 88), ("Diya", 95), ("Karan", 72), ("Pooja", 91)]
students_sorted_by_marks = sorted(students, key=lambda student: student[1], reverse=True)
print(f"Sorted by marks (descending): {students_sorted_by_marks}\n")


# ─────────────────────────────────────────────────────────────────────────────
# 4. HIGHER-ORDER FUNCTIONS: map(), filter(), reduce()
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 65)
print("4. HIGHER-ORDER FUNCTIONS (map, filter, reduce)")
print("=" * 65)

nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# map(func, iterable): Applies func to every item in iterable
doubled = list(map(lambda x: x * 2, nums))
print(f"Original nums: {nums}")
print(f"map (x * 2):   {doubled}")

# filter(predicate, iterable): Keeps only items where predicate returns True
evens = list(filter(lambda x: x % 2 == 0, nums))
print(f"filter (evens): {evens}")

# functools.reduce(func, iterable): Cumulatively reduces iterable to a single value
product = functools.reduce(lambda a, b: a * b, [1, 2, 3, 4, 5])
print(f"reduce (factorial of 5): {product}\n")


# ─────────────────────────────────────────────────────────────────────────────
# 5. VARIABLE SCOPE & LEGB RULE (global, nonlocal)
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 65)
print("5. VARIABLE SCOPE & LEGB RULE")
print("=" * 65)
# Python resolves variable names in this order:
# L = Local (Inside current function)
# E = Enclosing (Inside enclosing/outer function)
# G = Global (Module level)
# B = Built-in (Python built-in namespace: print, len, sum, etc.)

count = 100  # Global variable

def modify_global():
    global count
    count += 50
    print(f"Inside modify_global(): count = {count}")

modify_global()
print(f"In global scope: count = {count}")

def outer_func():
    message = "Hello from Outer"
    def inner_func():
        nonlocal message  # Modifies enclosing scope variable
        message = "Modified by Inner!"
        print(f"  [Inner] message: {message}")
    inner_func()
    print(f"  [Outer] message: {message}")

outer_func()
print()


# ─────────────────────────────────────────────────────────────────────────────
# 6. RECURSION EXAMPLES
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 65)
print("6. RECURSION (Factorial, Fibonacci, Ackermann)")
print("=" * 65)

# Factorial
def factorial(n: int) -> int:
    if n <= 1:
        return 1
    return n * factorial(n - 1)

# Fibonacci
def fibonacci(n: int) -> int:
    if n <= 0:
        return 0
    if n == 1:
        return 1
    return fibonacci(n - 1) + fibonacci(n - 2)

# Ackermann Function (Deep recursion test)
def ackermann(m: int, n: int) -> int:
    if m == 0:
        return n + 1
    elif m > 0 and n == 0:
        return ackermann(m - 1, 1)
    else:
        return ackermann(m - 1, ackermann(m, n - 1))

print(f"factorial(6): {factorial(6)}")
print(f"fibonacci(7): {fibonacci(7)}")
print(f"ackermann(2, 3): {ackermann(2, 3)}")