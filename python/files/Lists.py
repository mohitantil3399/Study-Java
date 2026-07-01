# unlike strings lists are mutable 
list1 = ["hi","Hello",5,445,4.234,324.2342,False,True,"Bool"]
print(list1[4])
list1[4] = "My"
print(list1[4]) 
# can iterate like strings using indexing 
print(list1[3:7])

list2 = [ 1,22,33213,13121,312,323242,32,4,23,32,31,2323342]
list3 = ["Apple","Ameer","Amazing","Hi","Hello","learning"]



# append(x) - Adds an item to the end of the list
list2.append(999)
print(list2)
# list2 now ends with 999

# extend(iterable) - Adds all elements from another iterable
list2.extend([1000, 2000])
# list2 now has 1000 and 2000 at the end

# insert(i, x) - Inserts item x at position i
list2.insert(2, 555)
# 555 is inserted at index 2

# remove(x) - Removes first occurrence of x
list2.remove(32)
# Removes the first 32 from list2

# pop([i]) - Removes and returns item at index i (last item if i not given)
last_item = list2.pop()
print(last_item)
# Removes and returns the last item (2000)

# clear() - Removes all items from the list
temp_list = list3.copy()
temp_list.clear()
print(temp_list)
# temp_list is now []

# index(x) - Returns the index of first occurrence of x
idx = list3.index("Hello")
print("Index of 'Hello':", idx)  # Output: 4

# count(x) - Returns the number of times x appears
count_32 = list2.count(32)
print("Count of 32 in list2:", count_32)  # Output: 2

# sort() - Sorts the list in ascending order (modifies in place)
list2.sort()
print("Sorted list2:", list2)

# reverse() - Reverses the list in place
list3.reverse()
print("Reversed list3:", list3)

# copy() - Returns a shallow copy of the list
copy_list = list2.copy()
print("Copy of list2:", copy_list)

# len() - Returns the number of elements in the list
length = len(list3)
print("Length of list3:", length)  # Output: 6

# max() - Returns the largest item
max_val = max(list2)
print("Max value in list2:", max_val)  # Output: 2323342

# min() - Returns the smallest item
min_val = min(list2)
print("Min value in list2:", min_val)  # Output: 1

# sum() - Returns the sum of all numeric elements
total = sum(list2)
print("Sum of list2:", total)

# sorted() - Returns a new sorted list (original remains unchanged)
sorted_list3 = sorted(list3)
print("Sorted copy of list3:", sorted_list3)

# any() - Returns True if any element is truthy
has_truthy = any(list2)
print("Any truthy in list2:", has_truthy)  # Output: True

# all() - Returns True if all elements are truthy
all_truthy = all(list2)
print("All truthy in list2:", all_truthy)  # Output: True

# enumerate() - Returns index-element pairs
print("Enumerating list3:")
for idx, val in enumerate(list3):
    print(f"Index {idx} has value {val}")

# zip() - Combines two lists into pairs
print("Zipping list2 and list3:")
for num, word in zip(list2, list3):
    print(f"{num} -> {word}")

# list comprehension - Create a new list with a condition
even_numbers = [x for x in list2 if x % 2 == 0]
print("Even numbers in list2:", even_numbers)


