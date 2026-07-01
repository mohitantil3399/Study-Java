# create a class for storing information of few programmers working at microsoft
class programmers:
    company = "Microsoft" # class attribute
    def __init__(self,Name,employeeid,number):# constructor in python 
          self.Name = Name              # declaring attributes (properties)
          self.employeeid = employeeid  # instance attribute
          self.number = number 

p1 = programmers("Harry","Harry1234",239932832)
print(p1.company,p1.employeeid,p1.Name,p1.number)

