with open("random.txt","r") as file:
    lines = file.readlines()

lineNumber = 1
for line in lines :
    if("python"in line):
        print(f"The line {lineNumber} contains the word 'python' .")
        # if i write line , it gives me the exact full line in the output 
        # therefore a new variable lineNumber tracks interger value of the line 
        break    # once found break 
    lineNumber += 1
else:
    print("The word 'python' is not present in the file.")