# string is data type also called array of characters , we define a string within double qoutes 
# ex : "Hello","name","Hi"

string ="String"
 
print(type(string)) 
# taking a substring(small portion)     
print()

#nagative indexin
negativesub = string[-4:-1]
print(negativesub)

# to find length of the string
s = " fnsaksnfioaslfmsakfopaskdfopjOJfsmdlfmasdmfasdgfasdofoasdgmcxklbvcxnb nsiodfjioajfiojasiofjaklsmdgvklasgvjaiogjvioasjvi"
print(len(s))

 #Check starting and ending points 
name = "Adatiya"
print(name.endswith('y'))
print(name.startswith('A'))
a = "aditya","Aditi","ramesh","kajol"
sub = a[0:3]
print(sub)
x = "Abhimanyu"        
print(len(x))
z = "gopal rajnigandha"
print('gopal rajnigandha'.upper())
c = "LAPIZLAZULI"
print("LAPIZLAZULI".lower())
print("raji chaorasiya".capitalize())
print("nana patekar".title())
print("    rajo     ".strip())
print("    gangu bai".lstrip())
print("  dinchak pooja     ".rstrip())
print("haku na j matata f".replace("j","f"))