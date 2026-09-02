"""
=============================================================================
           DECORATORS & GENERATORS IN PYTHON — COMPLETE STUDY GUIDE
=============================================================================
This file covers two of Python's most powerful advanced functional features:
1. ITERATORS & GENERATORS (Memory-efficient on-demand data streaming)
2. DECORATORS (Higher-order functions to extend behavior cleanly)
=============================================================================
"""

import time
import functools

# =============================================================================
# PART 1: ITERATORS & ITERABLES
# =============================================================================
print("=" * 65)
print("PART 1: ITERATORS & ITERABLES")
print("=" * 65)

# An Iterable is an object that can be looped over (List, Tuple, String, Dict).
# An Iterator is the object that actually produces values one at a time using __next__().

my_list = ["Python", "Java", "C++"]
my_iter = iter(my_list)  # Calling iter() calls my_list.__iter__()

print(f"1st item: {next(my_iter)}")  # Output: Python
print(f"2nd item: {next(my_iter)}")  # Output: Java
print(f"3rd item: {next(my_iter)}")  # Output: C++
# next(my_iter) now would raise StopIteration

# Building a Custom Iterator Class
class CountDown:
    """Custom iterator that counts down from start to 1."""
    def __init__(self, start):
        self.current = start

    def __iter__(self):
        return self

    def __next__(self):
        if self.current <= 0:
            raise StopIteration
        val = self.current
        self.current -= 1
        return val

print("\nCustom CountDown Iterator:")
for count in CountDown(5):
    print(count, end=" ")
print("\n")


# =============================================================================
# PART 2: GENERATORS (yield keyword)
# =============================================================================
print("=" * 65)
print("PART 2: GENERATORS (yield keyword)")
print("=" * 65)

# Generators are functions that return an iterator and yield values one at a time.
# Unlike regular functions (which return and terminate), generators PAUSE execution
# and save their state after each 'yield'.

def fibonacci_gen(limit):
    """Generates Fibonacci numbers up to limit without storing in a huge list."""
    a, b = 0, 1
    count = 0
    while count < limit:
        yield a  # Pauses execution and yields 'a'
        a, b = b, a + b
        count += 1

print("Fibonacci Generator (first 8 numbers):")
for num in fibonacci_gen(8):
    print(num, end=" ")
print("\n")

# Generator Expressions (like list comprehensions, but memory-efficient with ())
# List comprehension: [x**2 for x in range(10)]  -> creates full list in RAM
# Generator expression: (x**2 for x in range(10)) -> computes values on the fly!
gen_squares = (x ** 2 for x in range(1, 6))
print(f"Generator Expression Object: {gen_squares}")
print("Values from Generator Expression:", list(gen_squares))


# =============================================================================
# PART 3: DECORATORS (@decorator_name)
# =============================================================================
print("\n" + "=" * 65)
print("PART 3: DECORATORS")
print("=" * 65)

# A Decorator is a function that takes another function as an argument,
# extends its behavior without modifying the original source code, and returns a function.

# ── 1. Understanding Closures & Functions as First-Class Citizens ─────────────
def greeting_decorator(func):
    """Simple decorator that prints before and after calling the function."""
    def wrapper():
        print("[DECORATOR] Before executing function...")
        func()
        print("[DECORATOR] After executing function...")
    return wrapper

# Using @ syntax (Syntactic Sugar for: say_hello = greeting_decorator(say_hello))
@greeting_decorator
def say_hello():
    print("  -> Hello, World!")

say_hello()


# ── 2. Decorators with Arguments (*args, **kwargs) and @functools.wraps ──────
print("\n--- Practical Decorator: @timer ---")

def timer(func):
    """Decorator to measure and display function execution time."""
    @functools.wraps(func)  # Preserves original function name and docstring
    def wrapper(*args, **kwargs):
        start_time = time.perf_counter()
        result = func(*args, **kwargs)
        end_time = time.perf_counter()
        duration = end_time - start_time
        print(f"[TIMER] '{func.__name__}' took {duration:.6f} seconds to execute.")
        return result
    return wrapper

@timer
def compute_sum_of_squares(n):
    """Calculates sum of squares from 1 to n."""
    return sum(i ** 2 for i in range(1, n + 1))

res = compute_sum_of_squares(500000)
print(f"Result: {res}")
print(f"Function Name preserved by @functools.wraps: '{compute_sum_of_squares.__name__}'")


# ── 3. Practical Decorator: @logger ───────────────────────────────────────────
print("\n--- Practical Decorator: @logger ---")

def logger(func):
    """Decorator to log function calls, arguments, and return values."""
    @functools.wraps(func)
    def wrapper(*args, **kwargs):
        args_repr = [repr(a) for a in args]
        kwargs_repr = [f"{k}={v!r}" for k, v in kwargs.items()]
        signature = ", ".join(args_repr + kwargs_repr)
        print(f"[LOG] Calling {func.__name__}({signature})")
        result = func(*args, **kwargs)
        print(f"[LOG] {func.__name__} returned: {result!r}")
        return result
    return wrapper

@logger
def multiply_numbers(a, b, multiplier=1):
    return (a * b) * multiplier

multiply_numbers(4, 5, multiplier=2)


# ── 4. Chaining Multiple Decorators ──────────────────────────────────────────
print("\n--- Chaining Multiple Decorators ---")

@timer
@logger
def greet_student(name, branch="CSE"):
    return f"Welcome {name} from {branch} department!"

print(greet_student("Pooja", branch="B.Tech CSE"))


# =============================================================================
# SUMMARY CHEATSHEET
# =============================================================================
print("\n" + "=" * 65)
print("DECORATORS & GENERATORS CHEATSHEET")
print("=" * 65)
print("""
| Concept          | Mechanism           | Key Benefit                                  |
|------------------|---------------------|----------------------------------------------|
| Iterator         | __iter__, __next__  | Standard Python traversal interface          |
| Generator        | yield keyword       | Generates data on-demand with O(1) memory    |
| Gen Expression   | (x for x in data)   | Inline lazy evaluation                       |
| Decorator        | @decorator_func     | Reusable cross-cutting logic (logging, timing)|
| functools.wraps  | @wraps(func)        | Preserves metadata (__name__, __doc__)       |
""")
