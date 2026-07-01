# write python program to print content of a directory using os module
import os

# specify the directory you want to list names
directory_path = 'C:\Program Files'

# listing all the files in this directory 
contents = os.listdir(directory_path)

# print each file name 
i = 0
for item in contents : 
    print(i+1, item)
    i = i+1