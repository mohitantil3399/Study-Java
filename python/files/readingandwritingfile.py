# we can use python to read and write a file of any format
# for example
file = open("sample.txt", "r")  # "r" for reading (default mode)
data = file.read()
print(data)
file.close()

f = open("wrote.txt", "w")  # "w" for writing (overwrites existing content or creates new file)
str = "This is how we write something in the existing text file using python"
d = f.write(str)
print(d)  # prints the number of characters written
f.close()

# Additional file opening modes:
# "r"  : Read mode – default, file must exist
# "w"  : Write mode – creates new file or overwrites existing
# "a"  : Append mode – adds content to the end of file if it exists
# "r+" : Read and write – file must exist
# "w+" : Write and read – overwrites existing or creates new file
# "a+" : Append and read – reads and appends, creates file if not exists
# "rb" : Read binary – for non-text files like images, audio, etc.
# "wb" : Write binary – write binary data
# "ab" : Append binary – append binary data
# "rb+" / "wb+" / "ab+" : Read/write binary modes

# Example of append mode:
# this is using with statement
with open("wrote.txt", "a") as log_file:
    log_file.write("\nNew log entry\n")  # adds to the end of wrote.txt