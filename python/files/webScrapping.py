from bs4 import BeautifulSoup
import requests
# saving data to a csv file 
import csv
base_url = "https://books.toscrape.com"
response = requests.get(base_url)
print(response.status_code)# checked status code

#print(response.text) # prints raw response
#using beautiful soup 
soup = BeautifulSoup(response.text,"html.parser")
books = soup.find_all("article",class_ = "product_pod")

for book in books:
    title = book.h3.a["title"]
    price = book.find("p",class_ = "price_color").text
   # print(f"{title} : {price}")

with open("webScrapping.csv","w",newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["title","price"])
    
    for book in books:
        title = book.h3.a["title"]
        price = book.find("p",class_ = "price_color").text
        writer.writerow([title,price])

    
