def generatetable(i):
    table = ""
    
    for j in range(1,11):
            table += f"{i}*{j}={i*j}\n"
    table += "\n"        
    with open("table.txt","a") as file:
              file.write(table)
for i in range(2,101):
      generatetable(i)