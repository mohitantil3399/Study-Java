from pathlib import Path
import os

def locatFile():
    path = Path("") #empty for accessing the path of current folder 
    # show the user all the existing files in the folder via creating  a list 
    items = list(path.rglob("*"))
    print("The already existing files : ")
    for i , item in enumerate(items):
        print (f" {i}->{item}")

def createfile():
    locatFile()
    try:
        name = input("Enter file name you want to create with the extension of the file: ")
        p = Path(name)
        if not p.exists(): # check if it already exists , if not exist then make the file .
            with open (p,'w') as fl:
                content = input("Add your content : ")
                fl.write(content)
                fl.close()
            print("File Created Successfully!!")
        else:print("File already exists.")
    except Exception as e :
        print("An error occured as ",e)

def updatefile():
    locatFile()
    try:
        name = input("Enter file name you want to update with the extension of the file: ")
        p = Path(name)
        if p.exists() and p.is_file: # check if it already exists , if not exist then make the file .
            choice = input("say 1 if you want to add content to existing file.\nsay 2 if you want to overwrite\n add choice : ")
            if choice == 1:
                with open (p,'a') as fl:
                    content = input("Add your content : ")
                    fl.write(content)
                    fl.close()
                print("File Updated Successfully!!")
            else:
                with open (p,'w') as fl:            
                    data = input("Add your data : ")
                    fl.write(data)
                    fl.close()
                print("File Updated Successfully!!")
         
        else:print("File does not exists.")
    except Exception as e :
        print("An error occured as ",e)

def readfile():
    locatFile()
    try:
        name = input("Enter file name you want to read with the extension of the file: ")
        p = Path(name)
        if p.exists() and p.is_file(): # check if it already exists , if not exist then make the file .
            with open (p,'r') as fl:
                print("The file content is :\n",{fl.read()})
                fl.close()
             
        else:print("File does not exist.")
    except Exception as e :
        print("An error occured as ",e)

def deletefile():
    locatFile()
    try:
        name = input("Enter file name you want to delete with the extension of the file: ")
        p = Path(name)
        if p.exists() and p.is_file(): # check if it already exists , if not exist then make the file .
            os.remove(p)
            print("File removed successfully!!")
             
        else:print("File does not exist.")
    except Exception as e :
        print("An error occured as ",e)

choices = '''Createfile, Updatefile , Readfile , Deletefile.'''

while True :
  
    user_choice = input(f"{choices}\nEnter your choice:")

    match user_choice.lower():
        case "createfile":createfile()
        case "updatefile":updatefile()
        case "readfile":readfile()
        case "deletefile":deletefile()
        case _ : print("Unkown choice.")
    print("=="*50)
    print("\nType 'continue' to continue \n 'quit' or 'exit' to leave.\n")    
    decision = input("Enter your decision : ")
    print("=="*50)
    if decision == "quit" or decision == "exit" :
        break