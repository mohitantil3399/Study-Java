import random
# defining the function : 
def game():
    print("you are playing a game of random numbers ..")
    score = random.randint(1,200)
    # fetch the hiscore file
    with open("Hiscore.txt","r") as file:
        hiscore = file.read()
        if(hiscore != ""):
            hiscore = int(hiscore)
        else:
            hiscore = 0
    print(f"Your score is : {score}")        
    # updating the hiscore on the basis of score 
    if(score > hiscore ):
        with open("Hiscore.txt","w") as f:
         f.write(str(score))
         print("Its the new hiscore")
    return score 
# calling the function 
game()