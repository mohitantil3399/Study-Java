file = open("IMG_20250820_190946.jpg","rb")
data = file.read()
print(data)
file.close()# this will print the data in binary format not the visual format

# THis is html rendering , creates a file to open and preview , not useful 
import base64

# Read the image in binary mode
with open("IMG_20250820_190946.jpg", "rb") as file:
    image_data = file.read()

# Convert to base64
base64_data = base64.b64encode(image_data).decode('utf-8')

# Create HTML string
html = f"""
<!DOCTYPE html>
<html>
<head><title>Image Preview</title></head>
<body>
    <h2>Embedded Image</h2>
    <img src="data:image/jpeg;base64,{base64_data}" alt="Embedded Image" />
</body>
</html>
"""

# Save to an HTML file
with open("image_preview.html", "w") as html_file:
    html_file.write(html)

print("HTML file created: image_preview.html")