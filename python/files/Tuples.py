"""
=============================================================================
                    PYTHON TUPLES — COMPLETE STUDY GUIDE
=============================================================================
A Tuple is an ordered, immutable (unchangeable) collection of elements.
Tuples are written with round brackets: ( )

Key Characteristics:
1. Ordered: Elements have a defined order that will not change.
2. Immutable: Cannot add, remove, or modify items after creation.
3. Allows Duplicates: Can contain identical values.
4. Heterogeneous: Can hold elements of different data types.
5. Faster & Memory-Efficient: Compared to lists, tuples take less memory
   and are faster to iterate over.
6. Hashable (Dictionary Keys): Unlike lists, tuples can be used as dictionary
   keys and set elements (if all tuple items are also hashable).
=============================================================================
"""

from collections import namedtuple

# ─────────────────────────────────────────────────────────────────────────────
# 1. CREATING TUPLES
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 60)
print("1. CREATING TUPLES")
print("=" * 60)

# Empty tuple
empty_tuple = ()
print(f"Empty tuple: {empty_tuple}, type: {type(empty_tuple)}")

# CRITICAL: Single element tuple requires a trailing comma!
# Without a comma, Python treats it as a standard parenthesized expression.
single_elem = (42,)       # Tuple
not_a_tuple = (42)        # Integer!
print(f"single_elem = (42,) -> type: {type(single_elem)}")
print(f"not_a_tuple = (42)  -> type: {type(not_a_tuple)}")

# Tuple with mixed data types
mixed_tuple = ("Python", 3.12, True, [1, 2, 3], {"key": "value"})
print(f"Mixed Tuple: {mixed_tuple}")

# Tuple constructor tuple()
from_list = tuple(["apple", "banana", "cherry"])
from_string = tuple("HELLO")
print(f"From list: {from_list}")
print(f"From string: {from_string}")

# Tuple without parentheses (Tuple Packing)
packed_tuple = 10, 20, 30, "hello"
print(f"Packed tuple: {packed_tuple}, type: {type(packed_tuple)}\n")


# ─────────────────────────────────────────────────────────────────────────────
# 2. INDEXING & SLICING
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 60)
print("2. INDEXING & SLICING")
print("=" * 60)

fruits = ("apple", "banana", "cherry", "mango", "orange", "kiwi", "melon")

# Positive indexing: 0 to len-1
print(f"First fruit (fruits[0]): {fruits[0]}")
print(f"Third fruit (fruits[2]): {fruits[2]}")

# Negative indexing: -1 (last) to -len (first)
print(f"Last fruit (fruits[-1]): {fruits[-1]}")
print(f"Second last (fruits[-2]): {fruits[-2]}")

# Slicing: [start : stop : step] (stop index is exclusive)
print(f"fruits[1:4]   -> {fruits[1:4]}")     # Index 1, 2, 3
print(f"fruits[:3]    -> {fruits[:3]}")      # Start to index 2
print(f"fruits[3:]    -> {fruits[3:]}")      # Index 3 to end
print(f"fruits[::2]   -> {fruits[::2]}")     # Every 2nd element
print(f"fruits[::-1]  -> {fruits[::-1]}")    # Reversed tuple\n")


# ─────────────────────────────────────────────────────────────────────────────
# 3. TUPLE IMMUTABILITY & WORKAROUNDS
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 60)
print("3. TUPLE IMMUTABILITY")
print("=" * 60)

numbers = (1, 2, 3, 4, 5)

# Attempting to modify directly raises TypeError:
# numbers[0] = 99  # TypeError: 'tuple' object does not support item assignment

# Workaround to modify: Convert to list, modify, then convert back
temp_list = list(numbers)
temp_list.append(6)
temp_list[0] = 100
modified_tuple = tuple(temp_list)
print(f"Original numbers: {numbers}")
print(f"Modified via list conversion: {modified_tuple}")

# Caveat: Tuples can contain mutable objects (like lists), and those objects can mutate!
mutable_inside = (10, [1, 2, 3], "static")
print(f"Before mutating inner list: {mutable_inside}")
mutable_inside[1].append(4)  # Modifying list inside tuple is valid
print(f"After mutating inner list:  {mutable_inside}\n")


# ─────────────────────────────────────────────────────────────────────────────
# 4. TUPLE OPERATIONS & METHODS
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 60)
print("4. TUPLE OPERATIONS & METHODS")
print("=" * 60)

t1 = (1, 2, 3)
t2 = (4, 5, 6)

# Concatenation (+)
combined = t1 + t2
print(f"t1 + t2 = {combined}")

# Repetition (*)
repeated = t1 * 3
print(f"t1 * 3 = {repeated}")

# Membership test (in / not in)
print(f"Is 2 in t1? {2 in t1}")
print(f"Is 10 in t1? {10 in t1}")

# Built-in functions on tuples
sample = (15, 3, 89, 42, 3, 15, 3)
print(f"\nSample: {sample}")
print(f"len(sample): {len(sample)}")
print(f"max(sample): {max(sample)}")
print(f"min(sample): {min(sample)}")
print(f"sum(sample): {sum(sample)}")

# Only 2 Tuple Methods exist: .count() and .index()
# 1. count(value): returns occurrences of value
print(f"sample.count(3): {sample.count(3)} times")

# 2. index(value): returns index of first occurrence (raises ValueError if not found)
print(f"sample.index(42): Index {sample.index(42)}")
print(f"sample.index(3):  First occurrence at Index {sample.index(3)}\n")


# ─────────────────────────────────────────────────────────────────────────────
# 5. TUPLE UNPACKING (DESTRUCTURING)
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 60)
print("5. TUPLE UNPACKING")
print("=" * 60)

# Basic unpacking
coords = (10, 20, 30)
x, y, z = coords
print(f"x = {x}, y = {y}, z = {z}")

# Extended unpacking with * (asterisk / star operator)
record = ("Alice", 21, "CSE", "Python", "Java", "C++")
name, age, branch, *skills = record
print(f"Name: {name}, Age: {age}, Branch: {branch}")
print(f"Skills (captured as list with *skills): {skills}")

# Ignoring values with _ (underscore)
person = ("Bob", 25, "Male", "Engineer")
p_name, _, _, p_job = person
print(f"Extracted: Name = {p_name}, Job = {p_job}")

# Swapping variables without temporary variable (Uses tuple packing/unpacking)
a, b = 5, 10
print(f"Before swap: a = {a}, b = {b}")
a, b = b, a  # (10, 5) packed and unpacked into a, b
print(f"After swap:  a = {a}, b = {b}\n")


# ─────────────────────────────────────────────────────────────────────────────
# 6. NAMEDTUPLE (collections.namedtuple)
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 60)
print("6. NAMED TUPLES (collections.namedtuple)")
print("=" * 60)
# Namedtuples assign meaning to each position, making code self-documenting
# and allowing access via dot notation as well as index.

Point = namedtuple('Point', ['x', 'y'])
pt = Point(10, 20)
print(f"Point: {pt}")
print(f"Access by attribute: pt.x = {pt.x}, pt.y = {pt.y}")
print(f"Access by index:     pt[0] = {pt[0]}, pt[1] = {pt[1]}")

Student = namedtuple('Student', 'name roll_no marks')
s1 = Student(name="Rohit", roll_no=101, marks=95.5)
print(f"\nStudent record: {s1}")
print(f"Student Name: {s1.name}, Marks: {s1.marks}\n")


# ─────────────────────────────────────────────────────────────────────────────
# 7. SUMMARY: TUPLE VS LIST COMPARISON
# ─────────────────────────────────────────────────────────────────────────────
print("=" * 60)
print("7. TUPLE VS LIST CHEATSHEET")
print("=" * 60)
print("""
| Feature          | List                         | Tuple                       |
|------------------|------------------------------|-----------------------------|
| Syntax           | [1, 2, 3]                    | (1, 2, 3)                   |
| Mutability       | Mutable (Can add/remove/edit)| Immutable (Cannot change)   |
| Size / Memory    | Larger (Extra allocation)    | Smaller (Exact size)        |
| Performance     | Slower iteration             | Faster iteration            |
| Dictionary Key   | No (Unhashable)              | Yes (Hashable if items are) |
| Primary Use Case | Dynamic collections          | Fixed data records          |
""")
