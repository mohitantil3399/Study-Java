
import json
import random
from pathlib import Path

class Bank:
    database = 'data.json'
    info = []
    try :
        if Path(database).exists(): 
            with open(database,'r') as f :
                info = json.loads(f.read())
                Bank.info = info
        else:
            print("No such file exists")
    except Exception as e:
        print("Error occured as: ",e)

    @staticmethod
    def update():
        with open(Bank.database,'w') as f:
             f.write(json.dumps(Bank.info))

    def createAccount(self):
        acc_no = random.randint(10000, 99999)
        while any(acc['account no'] == acc_no for acc in Bank.info):
            acc_no = random.randint(10000, 99999)

        data = {
            "Name": input("Enter your name : "),
            "age": int(input("Enter your age: ")),
            "email": input("Enter your email: "),
            "pin" : int(input("Enter a pin of 4 digits: ")),
            "account no": acc_no,
            "balance": 0
        }
        if data['age'] < 18 or len(str(data['pin']))!=4:
            print("Sorry you can't create the account.")

        else:
            print("=="*50,"\n\nAccount created successfully .","=="*50,"\n\nAccess your details : ")
            for i in data : 
                print(f"{i} : {data[i]}")
            print("=="*50,"write down your account number.")
            Bank.info.append(data)
            Bank.update()

    def _find_account(self, acc_no, pin=None):
        for acc in Bank.info:
            if acc['account no'] == acc_no:
                if pin is None or acc['pin'] == pin:
                    return acc
        return None

    def deleteAccount(self):
        try:
            acc_no = int(input("Enter your account number: "))
            pin = int(input("Enter your pin: "))
        except ValueError:
            print("Invalid input.")
            return

        acc = self._find_account(acc_no, pin)
        if acc:
            Bank.info.remove(acc)
            Bank.update()
            print("Account deleted successfully.")
        else:
            print("Invalid account number or pin.")

    def updateAccount(self):
        try:
            acc_no = int(input("Enter your account number: "))
            pin = int(input("Enter your pin: "))
        except ValueError:
            print("Invalid input.")
            return

        acc = self._find_account(acc_no, pin)
        if acc:
            print("What do you want to update? (name/email/pin)")
            choice = input().lower()
            if choice == 'name':
                acc['Name'] = input("Enter new name: ")
            elif choice == 'email':
                acc['email'] = input("Enter new email: ")
            elif choice == 'pin':
                try:
                    new_pin = int(input("Enter new 4-digit pin: "))
                    if len(str(new_pin)) == 4:
                        acc['pin'] = new_pin
                    else:
                        print("Invalid pin length.")
                        return
                except ValueError:
                    print("Invalid input.")
                    return
            else:
                print("Invalid choice.")
                return
            Bank.update()
            print("Account updated successfully.")
        else:
            print("Invalid account number or pin.")

    def deposite(self):
        try:
            acc_no = int(input("Enter your account number: "))
        except ValueError:
            print("Invalid input.")
            return

        acc = self._find_account(acc_no)
        if acc:
            try:
                amount = float(input("Enter amount to deposit: "))
            except ValueError:
                print("Invalid input.")
                return
            
            if amount > 0:
                acc['balance'] += amount
                Bank.update()
                print(f"Successfully deposited {amount}. New balance: {acc['balance']}")
            else:
                print("Invalid amount.")
        else:
            print("Invalid account number.")

    def withdraw(self):
        try:
            acc_no = int(input("Enter your account number: "))
            pin = int(input("Enter your pin: "))
        except ValueError:
            print("Invalid input.")
            return

        acc = self._find_account(acc_no, pin)
        if acc:
            try:
                amount = float(input("Enter amount to withdraw: "))
            except ValueError:
                print("Invalid input.")
                return

            if 0 < amount <= acc['balance']:
                acc['balance'] -= amount
                Bank.update()
                print(f"Successfully withdrew {amount}. New balance: {acc['balance']}")
            else:
                print("Insufficient balance or invalid amount.")
        else:
            print("Invalid account number or pin.")

    def accountDetails(self):
        try:
            acc_no = int(input("Enter your account number: "))
            pin = int(input("Enter your pin: "))
        except ValueError:
            print("Invalid input.")
            return

        acc = self._find_account(acc_no, pin)
        if acc:
            print("=="*50, "\nAccount Details:\n", "=="*50)
            for k, v in acc.items():
                print(f"{k} : {v}")
        else:
            print("Invalid account number or pin.")

user = Bank()
choices = 'create , delete , update , deposite , withdraw , details'
choice = input(f"Available choices :{choices}.\nEnter your choice: ").lower()

match choice:
    case "create" : user.createAccount()
    case "delete" : user.deleteAccount()
    case "update" : user.updateAccount()
    case "deposite" : user.deposite()
    case "withdraw" : user.withdraw()
    case "details" : user.accountDetails()
    case _: print("Unkown request , try again .")


