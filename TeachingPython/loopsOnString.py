#how do we loop on a string
string = "we are learning python"
length = len(string)
iterator = 0
while iterator<length:
    print(iterator)
    iterator=iterator+1

string = "We are learning python"
for s in string:
    print(s)