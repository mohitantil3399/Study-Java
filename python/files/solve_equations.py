# System of equations:
# a1*x + b1*y = c1
# a2*x + b2*y = c2

# Input coefficients
a1 = float(input("Enter the value of a1: "))
b1 = float(input("Enter the value of b1: "))
c1 = float(input("Enter the value of c1: "))

a2 = float(input("Enter the value of a2: "))
b2 = float(input("Enter the value of b2: "))
c2 = float(input("Enter the value of c2: "))

# Augmented matrix
matrix = [
    [a1, b1, c1],
    [a2, b2, c2]
]

# Step 1: Make the pivot of row 1 equal to 1
pivot = matrix[0][0]
for j in range(3):
    matrix[0][j] /= pivot

# Step 2: Eliminate the first column of row 2
factor = matrix[1][0]
for j in range(3):
    matrix[1][j] -= factor * matrix[0][j]

# Step 3: Make the pivot of row 2 equal to 1
pivot = matrix[1][1]
for j in range(3):
    matrix[1][j] /= pivot

# Step 4: Eliminate the second column of row 1
factor = matrix[0][1]
for j in range(3):
    matrix[0][j] -= factor * matrix[1][j]

# Final solution
x = matrix[0][2]
y = matrix[1][2]

print(f"Solution: x = {x}, y = {y}")
