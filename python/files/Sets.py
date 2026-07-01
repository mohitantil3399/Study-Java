# Sets is a collection of different objects, no item is repeated

# Creating an empty set
set1 = set()

# Adding elements to the set
set1.add("Hello")
set1.add("World")
set1.add("Hello")  # Duplicate, will be ignored
print("Set1 contents:", set1, "\n")

# Creating a set with mixed data types and duplicates
set2 = {1, 2, 3, 3, 4, 5, 6, 7, 8, 3, 6, 543, 43, "Harry", "Hello"}
print("Set2 contents:", set2, "\n")

# Properties of sets:
# - Only unique elements are stored
# - Order is not guaranteed
# - No indexing (cannot access elements by position)
# - Length can be obtained using len()

# Removing an element from the set
set2.remove("Harry")
print("Set2 after removing 'Harry':", set2, "\n")

# Union of sets: combines all unique elements from both
print("The union set:", set1.union(set2), "\n")

# Intersection of sets: common elements between both
print("The intersection set:", set1.intersection(set2), "\n")

# Length of the set
print("The length of set2:", len(set2), "\n")

# -------------------------------
# Additional Set Methods
# -------------------------------

# .discard() – removes an element if present, no error if absent
set2.discard("Unknown")  # No error even if "Unknown" is not in set
print("After discarding 'Unknown':", set2, "\n")

# .copy() – creates a shallow copy of the set
set3 = set2.copy()
print("Copied set (set3):", set3, "\n")

# .difference() – elements in set2 but not in set1
print("Difference (set2 - set1):", set2.difference(set1), "\n")

# .symmetric_difference() – elements in either set but not both
print("Symmetric difference:", set2.symmetric_difference(set1), "\n")

# .isdisjoint() – checks if two sets have no elements in common
print("Are set1 and set2 disjoint?", set1.isdisjoint(set2), "\n")

# .issubset() – checks if set1 is a subset of set2
print("Is set1 a subset of set2?", set1.issubset(set2), "\n")

# .issuperset() – checks if set2 is a superset of set1
print("Is set2 a superset of set1?", set2.issuperset(set1), "\n")

# .clear() – removes all elements from the set
# Uncomment to use:
# set2.clear()
# print("Set2 after clearing:", set2)