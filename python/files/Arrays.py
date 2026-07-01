arr = [[0 for _ in range(3)] for _ in range(3)]
for i in range(0,3):
    for j in range(0,3):
        arr[i][j] = int(input("Enter the values of array: "))
print ("The entered matrix is : ")        
for i in range(0,3):
    for j in range(0,3):
        print(arr[i][j], end=" ")
    print()
