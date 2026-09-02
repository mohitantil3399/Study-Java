"""
=============================================================================
             PYTHON EXCEPTION HANDLING — COMPLETE STUDY GUIDE
=============================================================================
An Exception is an unexpected event or error that occurs during program
execution and disrupts the normal flow of instructions.

Key Components of Exception Handling:
1. try     : Contains the code that may potentially raise an exception.
2. except  : Handles the exception if one occurs inside the try block.
3. else    : Executes ONLY if NO exception was raised in the try block.
4. finally : Executes ALWAYS, whether an exception occurred or not (used for cleanup).
5. raise   : Manually throws/triggers an exception.
6. custom  : User-defined exceptions inheriting from the `Exception` class.
=============================================================================
"""

# ─────────────────────────────────────────────────────────────────────────────
# 1. BASIC TRY-EXCEPT BLOCK
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 60)
print("1. BASIC TRY-EXCEPT")
print("=" * 60)

try:
    num = int("100")
    result = 10 / 2
    print(f"Result: {result}")
except ZeroDivisionError:
    print("Error: Cannot divide by zero!")
except ValueError:
    print("Error: Invalid conversion to integer!")


# ─────────────────────────────────────────────────────────────────────────────
# 2. CATCHING SPECIFIC COMMON EXCEPTIONS
# ─────────────────────────────────────────────────────────────────────────────
print("\n" + "=" * 60)
print("2. CATCHING SPECIFIC COMMON EXCEPTIONS")
print("=" * 60)

# (A) ZeroDivisionError
try:
    x = 10 / 0
except ZeroDivisionError as e:
    print(f"Caught ZeroDivisionError: {e}")

# (B) ValueError
try:
    num = int("Python")
except ValueError as e:
    print(f"Caught ValueError: {e}")

# (C) IndexError
try:
    my_list = [10, 20, 30]
    val = my_list[10]
except IndexError as e:
    print(f"Caught IndexError: {e}")

# (D) KeyError
try:
    student = {"name": "Mohit", "branch": "CSE"}
    cgpa = student["cgpa"]
except KeyError as e:
    print(f"Caught KeyError: Missing key {e}")

# (E) TypeError
try:
    concat = "Score: " + 95
except TypeError as e:
    print(f"Caught TypeError: {e}")

# (F) FileNotFoundError
try:
    with open("non_existent_file.txt", "r") as f:
        content = f.read()
except FileNotFoundError as e:
    print(f"Caught FileNotFoundError: {e}")


# ─────────────────────────────────────────────────────────────────────────────
# 3. HANDLING MULTIPLE EXCEPTIONS IN A SINGLE BLOCK
# ─────────────────────────────────────────────────────────────────────────────
print("\n" + "=" * 60)
print("3. MULTIPLE EXCEPTIONS IN ONE EXCEPT BLOCK")
print("=" * 60)

def divide_elements(a, b):
    try:
        res = a / b
        return res
    except (ZeroDivisionError, TypeError) as err:
        print(f"Handled error: {err}")
        return None

print(f"divide_elements(10, 2):  {divide_elements(10, 2)}")
print(f"divide_elements(10, 0):  {divide_elements(10, 0)}")
print(f"divide_elements(10, 'x'): {divide_elements(10, 'x')}")


# ─────────────────────────────────────────────────────────────────────────────
# 4. THE COMPLETE TRY - EXCEPT - ELSE - FINALLY PATTERN
# ─────────────────────────────────────────────────────────────────────────────
print("\n" + "=" * 60)
print("4. COMPLETE TRY - EXCEPT - ELSE - FINALLY")
print("=" * 60)

def calculate_reciprocal(value_str):
    print(f"\nProcessing input: '{value_str}'")
    try:
        val = float(value_str)
        reciprocal = 1.0 / val
    except ValueError:
        print("  [EXCEPT] ValueError: Please provide a numeric string.")
    except ZeroDivisionError:
        print("  [EXCEPT] ZeroDivisionError: Cannot calculate reciprocal of 0.")
    else:
        # Executes ONLY if try block succeeded without any exception!
        print(f"  [ELSE] Success! Reciprocal is: {reciprocal}")
    finally:
        # Executes ALWAYS (cleanup actions, closing files, releasing locks)
        print("  [FINALLY] Operation finished (Cleanup).")

calculate_reciprocal("4")
calculate_reciprocal("0")
calculate_reciprocal("abc")


# ─────────────────────────────────────────────────────────────────────────────
# 5. RAISING EXCEPTIONS (raise keyword)
# ─────────────────────────────────────────────────────────────────────────────
print("\n" + "=" * 60)
print("5. RAISING EXCEPTIONS (raise)")
print("=" * 60)

def validate_age(age):
    if not isinstance(age, int):
        raise TypeError(f"Age must be an integer, got {type(age).__name__}")
    if age < 0:
        raise ValueError("Age cannot be negative.")
    if age > 150:
        raise ValueError("Age seems unrealistically high (>150).")
    return f"Valid age: {age}"

try:
    print(validate_age(20))
    print(validate_age(-5))
except ValueError as e:
    print(f"Validation failed: {e}")


# ─────────────────────────────────────────────────────────────────────────────
# 6. CUSTOM / USER-DEFINED EXCEPTIONS
# ─────────────────────────────────────────────────────────────────────────────
print("\n" + "=" * 60)
print("6. CUSTOM (USER-DEFINED) EXCEPTIONS")
print("=" * 60)

# Custom exceptions inherit from the built-in Exception class
class InsufficientFundsError(Exception):
    """Raised when an account withdrawal exceeds the available balance."""
    def __init__(self, balance, amount):
        super().__init__(f"Cannot withdraw ${amount}. Current balance is only ${balance}.")
        self.balance = balance
        self.amount = amount

class InvalidAccountError(Exception):
    """Raised when an account number is not recognized."""
    pass

class BankAccount:
    def __init__(self, owner, balance):
        self.owner = owner
        self.balance = balance

    def withdraw(self, amount):
        if amount <= 0:
            raise ValueError("Withdrawal amount must be positive.")
        if amount > self.balance:
            raise InsufficientFundsError(self.balance, amount)
        self.balance -= amount
        print(f"Successfully withdrew ${amount}. Remaining balance: ${self.balance}")
        return self.balance

# Testing Custom Exception
acc = BankAccount("Rohit", 500)
try:
    acc.withdraw(200)
    acc.withdraw(400)  # Exceeds remaining $300 balance
except InsufficientFundsError as err:
    print(f"Transaction Denied: {err}")
except ValueError as err:
    print(f"Input Error: {err}")


# ─────────────────────────────────────────────────────────────────────────────
# 7. BEST PRACTICES CHEATSHEET
# ─────────────────────────────────────────────────────────────────────────────
print("\n" + "=" * 60)
print("7. EXCEPTION HANDLING BEST PRACTICES")
print("=" * 60)
print("""
1. Catch Specific Exceptions:
   Avoid bare 'except:' or 'except Exception:' unless logging at top-level.
   Good: 'except ValueError:'  |  Bad: 'except:'

2. Keep Try Blocks Small:
   Only wrap the specific lines that might fail, not the whole script.

3. Use 'finally' or Context Managers ('with') for Resource Cleanup:
   Ensures files, network sockets, and database connections are closed.

4. Inherit from 'Exception', not 'BaseException':
   When creating custom exceptions, always inherit from 'Exception'.

5. Leverage 'else' for Code That Must Only Run on Success:
   Keeps the 'try' block focused and avoids masking unintended errors.
""")
