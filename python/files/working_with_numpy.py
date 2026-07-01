import numpy as np

# --- Existing Code ---
#using numpy array 
arr = np.array([4,5,6,7,8,9])**4
print("1D array powered by 4:\n", arr)

array_2d = np.array([[1,2,3],
                     [4,5,6]
                     ])
print("\n2D array:\n", array_2d)

array_3d = np.array([[[1,2,3],[4,5,6],[7,8,9]],
                     [[11,12,13],[14,15,16],[17,18,19]],
                     [[21,22,23],[24,25,26],[27,28,29]]
                     ])
print("\n3D array:\n", array_3d)

print("\nPrinting 3D array elements using loops:")
#printing in a single row using loops 
for i in range(3):
    for j in range(3):
        for k in range(3):
            print(array_3d[i,j,k],end=" ")
print("\n")


# --- New Code: Important NumPy Concepts ---

print("-" * 50)
print("1. Array Creation Functions")
print("-" * 50)
zeros_arr = np.zeros((2, 3)) # 2x3 matrix of zeros
ones_arr = np.ones((3, 2))   # 3x2 matrix of ones
range_arr = np.arange(0, 10, 2) # Start 0, stop 10 (exclusive), step 2
linspace_arr = np.linspace(0, 1, 5) # 5 values evenly spaced between 0 and 1
random_arr = np.random.rand(2, 2) # 2x2 matrix of random values between 0 and 1

print("np.zeros((2, 3)):\n", zeros_arr)
print("\nnp.arange(0, 10, 2):\n", range_arr)
print("\nnp.linspace(0, 1, 5):\n", linspace_arr)


print("\n" + "-" * 50)
print("2. Array Attributes")
print("-" * 50)
print("Shape of array_3d:", array_3d.shape) # Dimensions
print("Number of dimensions:", array_3d.ndim)
print("Total elements:", array_3d.size)
print("Data type:", array_3d.dtype)


print("\n" + "-" * 50)
print("3. Reshaping and Flattening")
print("-" * 50)
a = np.arange(1, 13) # Array from 1 to 12
reshaped = a.reshape(3, 4) # Reshape to 3 rows, 4 columns
flattened = reshaped.flatten() # Back to 1D
print("Original 1D:\n", a)
print("Reshaped 3x4:\n", reshaped)
print("Flattened:\n", flattened)


print("\n" + "-" * 50)
print("4. Mathematical Operations & Universal Functions")
print("-" * 50)
x = np.array([1, 2, 3])
y = np.array([4, 5, 6])

print("Element-wise addition (x + y):", x + y)
print("Element-wise multiplication (x * y):", x * y)
print("Dot product (np.dot(x, y)):", np.dot(x, y))
print("Square root (np.sqrt(x)):", np.sqrt(x))
print("Exponential (np.exp(x)):", np.exp(x))


print("\n" + "-" * 50)
print("5. Aggregation Functions")
print("-" * 50)
data = np.array([[1, 2, 3], [4, 5, 6]])
print("Data:\n", data)
print("Sum of all elements:", np.sum(data))
print("Sum of columns (axis=0):", np.sum(data, axis=0))
print("Mean of rows (axis=1):", np.mean(data, axis=1))
print("Maximum value:", np.max(data))
print("Index of maximum value (argmax):", np.argmax(data))


print("\n" + "-" * 50)
print("6. Indexing and Slicing")
print("-" * 50)
mat = np.arange(1, 17).reshape(4, 4)
print("Matrix:\n", mat)
print("First row:", mat[0, :])
print("First column:", mat[:, 0])
print("Sub-matrix (rows 1-2, cols 1-2):\n", mat[1:3, 1:3])
print("Conditional Indexing (elements > 10):", mat[mat > 10])