"""
=============================================================================
     OBJECT-ORIENTED PROGRAMMING (OOP) IN PYTHON — COMPLETE MASTERCLASS
=============================================================================
Object-Oriented Programming (OOP) is a programming paradigm based on
'objects' that contain data (attributes) and code (methods).

Core Pillars of OOP:
1. Encapsulation : Bundling data and methods; restricting direct access.
2. Inheritance   : Deriving new classes from existing classes for reusability.
3. Polymorphism   : Ability to use a single unified interface for different types.
4. Abstraction   : Hiding complex implementation details; exposing only essentials.
=============================================================================
"""

from abc import ABC, abstractmethod

# =============================================================================
# 1. CLASSES, OBJECTS, & ATTRIBUTES
# =============================================================================
print("=" * 70)
print("1. CLASSES, OBJECTS, & ATTRIBUTES")
print("=" * 70)

class Student:
    # Class Attribute (Shared across all instances of Student)
    university_name = "Tech University"
    total_students = 0

    def __init__(self, name: str, roll_no: int, marks: float):
        # Instance Attributes (Unique to each instance)
        self.name = name
        self.roll_no = roll_no
        self.marks = marks
        Student.total_students += 1

    # Instance Method: receives 'self' (the specific instance)
    def display_details(self):
        print(f"Student: {self.name} | Roll No: {self.roll_no} | Marks: {self.marks} | Uni: {self.university_name}")

    # Class Method: receives 'cls' (the class itself); great for factory constructors
    @classmethod
    def from_string(cls, data_str: str):
        """Alternative constructor from string 'Name-RollNo-Marks'"""
        name, roll, marks = data_str.split("-")
        return cls(name, int(roll), float(marks))

    # Static Method: independent utility function (no self or cls)
    @staticmethod
    def is_passing_grade(marks: float) -> bool:
        return marks >= 40.0

s1 = Student("Aarav", 101, 88.5)
s2 = Student.from_string("Diya-102-92.0")

s1.display_details()
s2.display_details()
print(f"Total enrolled students: {Student.total_students}")
print(f"Is 35 passing? {Student.is_passing_grade(35)}")
print(f"Is 88 passing? {Student.is_passing_grade(88)}\n")


# =============================================================================
# 2. ENCAPSULATION & @property (GETTERS & SETTERS)
# =============================================================================
print("=" * 70)
print("2. ENCAPSULATION (Public, Protected, Private & Property Decorators)")
print("=" * 70)

class BankAccount:
    """
    Access Modifiers by convention:
    - Public: var_name (accessible everywhere)
    - Protected: _var_name (intended for internal/subclass use)
    - Private: __var_name (Name mangling applied: _ClassName__var_name)
    """
    def __init__(self, account_holder: str, initial_balance: float):
        self.account_holder = account_holder      # Public
        self._account_type = "Savings"             # Protected
        self.__balance = initial_balance           # Private

    # Getter using @property
    @property
    def balance(self) -> float:
        """Read-only access to private balance attribute."""
        return self.__balance

    # Setter using @balance.setter with data validation
    @balance.setter
    def balance(self, value: float):
        if value < 0:
            raise ValueError("Balance cannot be set to a negative value!")
        self.__balance = value

    def deposit(self, amount: float):
        if amount > 0:
            self.__balance += amount
            print(f"Deposited ${amount:.2f}. New Balance: ${self.__balance:.2f}")
        else:
            print("Deposit amount must be positive!")

    def withdraw(self, amount: float):
        if 0 < amount <= self.__balance:
            self.__balance -= amount
            print(f"Withdrew ${amount:.2f}. Remaining Balance: ${self.__balance:.2f}")
        else:
            print("Insufficient funds or invalid withdrawal amount!")

account = BankAccount("Pooja", 1000.0)
print(f"Account Holder: {account.account_holder}")
print(f"Current Balance (via @property getter): ${account.balance:.2f}")
account.deposit(500)
account.withdraw(200)

# Name mangling demo for private variable __balance:
# print(account.__balance) -> Raises AttributeError
print(f"Accessing via mangled name (_BankAccount__balance): ${account._BankAccount__balance:.2f}\n")


# =============================================================================
# 3. INHERITANCE (Single, Multilevel, Multiple & super())
# =============================================================================
print("=" * 70)
print("3. INHERITANCE (Single, Multilevel, Multiple & super())")
print("=" * 70)

# Base Class
class Employee:
    def __init__(self, name: str, emp_id: str, salary: float):
        self.name = name
        self.emp_id = emp_id
        self.salary = salary

    def get_info(self):
        return f"Employee: {self.name} (ID: {self.emp_id}) | Base Salary: ${self.salary}"

# Single Inheritance
class Developer(Employee):
    def __init__(self, name: str, emp_id: str, salary: float, programming_language: str):
        # super() invokes parent class constructor
        super().__init__(name, emp_id, salary)
        self.programming_language = programming_language

    # Method Overriding
    def get_info(self):
        parent_info = super().get_info()
        return f"{parent_info} | Tech Stack: {self.programming_language}"

# Multiple Inheritance
class TeamLead:
    def __init__(self, team_size: int):
        self.team_size = team_size

    def lead_team(self):
        return f"Leading a team of {self.team_size} engineers."

class EngineeringManager(Developer, TeamLead):
    """Multiple Inheritance: derives from Developer and TeamLead"""
    def __init__(self, name: str, emp_id: str, salary: float, lang: str, team_size: int):
        Developer.__init__(self, name, emp_id, salary, lang)
        TeamLead.__init__(self, team_size)

dev = Developer("Rohan", "DEV01", 85000, "Python, Java")
mgr = EngineeringManager("Neha", "MGR01", 120000, "Python, Go", team_size=12)

print(dev.get_info())
print(mgr.get_info())
print(mgr.lead_team())
print(f"Method Resolution Order (MRO) for EngineeringManager: {[c.__name__ for c in EngineeringManager.mro()]}\n")


# =============================================================================
# 4. POLYMORPHISM & DUCK TYPING
# =============================================================================
print("=" * 70)
print("4. POLYMORPHISM & DUCK TYPING")
print("=" * 70)

class PDFDocument:
    def render(self):
        return "Rendering PDF document layout..."

class WordDocument:
    def render(self):
        return "Rendering Microsoft Word DOCX layout..."

class HTMLDocument:
    def render(self):
        return "Rendering HTML5 in Browser DOM..."

# Polymorphic dispatcher: works on any object with a .render() method ("Duck Typing")
def display_document(doc):
    print(f"Document Output -> {doc.render()}")

documents = [PDFDocument(), WordDocument(), HTMLDocument()]
for doc in documents:
    display_document(doc)
print()


# =============================================================================
# 5. ABSTRACTION (abc.ABC & @abstractmethod)
# =============================================================================
print("=" * 70)
print("5. ABSTRACTION (Abstract Base Classes)")
print("=" * 70)

class Shape(ABC):
    """Abstract base class: cannot be instantiated directly."""
    @abstractmethod
    def area(self) -> float:
        """Subclasses MUST implement area()"""
        pass

    @abstractmethod
    def perimeter(self) -> float:
        """Subclasses MUST implement perimeter()"""
        pass

class Rectangle(Shape):
    def __init__(self, width: float, height: float):
        self.width = width
        self.height = height

    def area(self) -> float:
        return self.width * self.height

    def perimeter(self) -> float:
        return 2 * (self.width + self.height)

class Circle(Shape):
    def __init__(self, radius: float):
        self.radius = radius

    def area(self) -> float:
        import math
        return math.pi * (self.radius ** 2)

    def perimeter(self) -> float:
        import math
        return 2 * math.pi * self.radius

rect = Rectangle(10, 5)
circ = Circle(7)
print(f"Rectangle Area: {rect.area():.2f} | Perimeter: {rect.perimeter():.2f}")
print(f"Circle Area:    {circ.area():.2f} | Perimeter: {circ.perimeter():.2f}\n")


# =============================================================================
# 6. DUNDER (MAGIC) METHODS
# =============================================================================
print("=" * 70)
print("6. DUNDER / MAGIC METHODS (__str__, __repr__, __len__, __add__, etc.)")
print("=" * 70)

class Vector2D:
    """Represents a 2D geometric vector (x, y)."""
    def __init__(self, x: float, y: float):
        self.x = x
        self.y = y

    # __str__: User-friendly string representation for print() and str()
    def __str__(self):
        return f"Vector({self.x}, {self.y})"

    # __repr__: Unambiguous developer string representation
    def __repr__(self):
        return f"Vector2D(x={self.x}, y={self.y})"

    # __add__: Operator overloading for vector addition (+)
    def __add__(self, other):
        if isinstance(other, Vector2D):
            return Vector2D(self.x + other.x, self.y + other.y)
        return NotImplemented

    # __eq__: Equality comparison (==)
    def __eq__(self, other):
        if isinstance(other, Vector2D):
            return self.x == other.x and self.y == other.y
        return False

    # __len__: Returns length / magnitude approximation
    def __len__(self):
        import math
        return int(math.hypot(self.x, self.y))

v1 = Vector2D(3, 4)
v2 = Vector2D(1, 2)
v3 = v1 + v2  # Calls v1.__add__(v2)

print(f"v1: {v1}")
print(f"v2: {v2}")
print(f"v1 + v2: {v3}")
print(f"repr(v1): {repr(v1)}")
print(f"Magnitude len(v1): {len(v1)}")
print(f"Is v1 == v2? {v1 == v2}")
print(f"Is v1 == Vector2D(3, 4)? {v1 == Vector2D(3, 4)}\n")


# =============================================================================
# SUMMARY CHEATSHEET
# =============================================================================
print("=" * 70)
print("OOP PRINCIPLES QUICK REFERENCE")
print("=" * 70)
print("""
| Concept          | Implementation Syntax                           |
|------------------|------------------------------------------------|
| Constructor      | def __init__(self, ...):                       |
| Class Attribute  | class_var = value                              |
| Class Method     | @classmethod def func(cls, ...):               |
| Static Method    | @staticmethod def func(...):                   |
| Private Attribute| self.__private_var                             |
| Property Getter  | @property def val(self): return self.__val     |
| Property Setter  | @val.setter def val(self, x): self.__val = x   |
| Inheritance      | class Child(Parent):                           |
| Parent Super     | super().__init__(...)                          |
| Abstract Class   | from abc import ABC, abstractmethod            |
| String Dunder    | __str__(self), __repr__(self)                  |
| Math Overload    | __add__(self, other), __sub__(self, other)     |
""")