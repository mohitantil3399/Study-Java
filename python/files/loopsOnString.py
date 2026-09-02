#how do we loop on a string
string = "we are learning python"
length = len(string)
iterator = 0
while iterator<length:
    print(iterator)
    iterator=iterator+1

string = "We are learning python are are are "
for s in string:
    print(s)


# taking a sentence as input and returning how many times a word appeared in that and also the word with its repetitions
string = input("Enter the sentence : ").lower()
word = input("Enter the word whose repetition you want to know: ")
count = 0
lits = []
newString = ""
words = []
for s in string:
    if not s == " ":
        lits.append(s)

    else:
        newString += "".join(lits)
        if newString== word:
            words.append(newString)
            count += 1
        newString =""
        lits = []
    
print(words)
print(f"The word :{word}, appeared :{count} times in the sentence.")