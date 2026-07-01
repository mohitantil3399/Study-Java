# pyrefly: ignore [missing-import]
import matplotlib.pyplot as plt 
import numpy as np

print("Generating Matplotlib examples...")

# Sample Data to plot
x = np.linspace(0, 10, 100)
y1 = np.sin(x)
y2 = np.cos(x)

# --------------------------------------------------
# 1. Basic Line Plot with Customizations
# --------------------------------------------------
plt.figure(figsize=(8, 5)) # Create a figure with a specific size (width, height)
plt.plot(x, y1, label='sin(x)', color='blue', linestyle='-', linewidth=2)
plt.plot(x, y2, label='cos(x)', color='red', linestyle='--', linewidth=2)

# Adding details to the plot
plt.title("Basic Line Plot: Sine and Cosine Waves", fontsize=14)
plt.xlabel("X-axis (Time/Angle)", fontsize=12)
plt.ylabel("Y-axis (Amplitude)", fontsize=12)
plt.legend() # Displays the legend based on the 'label' argument in plt.plot
plt.grid(True, linestyle=':', alpha=0.7) # Adds a subtle grid


# Note: plt.show() is normally used to display the plot in a window. 
# Here we use plt.savefig() to save it as an image file so the script doesn't pause.
plt.savefig('basic_line_plot.png')
plt.show()
plt.close() # Close the figure to free up memory
print("Saved: basic_line_plot.png")


# --------------------------------------------------
# 2. Scatter Plot
# --------------------------------------------------
# Useful for viewing relationships between two variables
x_scatter = np.random.rand(50)
y_scatter = np.random.rand(50)
colors = np.random.rand(50)
sizes = 1000 * np.random.rand(50) # Random bubble sizes

plt.figure(figsize=(8, 5))
plt.scatter(x_scatter, y_scatter, c=colors, s=sizes, alpha=0.5, cmap='viridis')
plt.title("Scatter Plot with varying color and size")
plt.colorbar(label='Color intensity')
plt.savefig('scatter_plot.png')
plt.close()
print("Saved: scatter_plot.png")


# --------------------------------------------------
# 3. Bar Chart
# --------------------------------------------------
# Great for categorical data
categories = ['Apples', 'Bananas', 'Cherries', 'Dates']
values = [25, 40, 15, 30]

plt.figure(figsize=(8, 5))
plt.bar(categories, values, color=['red', 'yellow', 'purple', 'brown'])
plt.title("Fruit Sales - Bar Chart")
plt.xlabel("Fruit Types")
plt.ylabel("Quantity Sold")
plt.savefig('bar_chart.png')
plt.close()
print("Saved: bar_chart.png")


# --------------------------------------------------
# 4. Histogram
# --------------------------------------------------
# Used to visualize the distribution of a single variable
data = np.random.randn(1000) # 1000 Normally distributed data points

plt.figure(figsize=(8, 5))
plt.hist(data, bins=30, color='skyblue', edgecolor='black')
plt.title("Histogram of Normally Distributed Data")
plt.xlabel("Value")
plt.ylabel("Frequency")
plt.savefig('histogram.png')
plt.close()
print("Saved: histogram.png")


# --------------------------------------------------
# 5. Subplots (Multiple plots in one figure)
# --------------------------------------------------
# Creating a 2x2 grid of plots
fig, axs = plt.subplots(2, 2, figsize=(10, 8)) 

# Top-Left [row 0, col 0]
axs[0, 0].plot(x, y1, 'tab:blue')
axs[0, 0].set_title('Sine Wave')

# Top-Right [row 0, col 1]
axs[0, 1].plot(x, y2, 'tab:orange')
axs[0, 1].set_title('Cosine Wave')

# Bottom-Left [row 1, col 0]
axs[1, 0].bar(categories, values, color='tab:green')
axs[1, 0].set_title('Bar Chart')

# Bottom-Right [row 1, col 1]
axs[1, 1].scatter(x_scatter, y_scatter, color='tab:red', alpha=0.6)
axs[1, 1].set_title('Scatter Plot')

plt.tight_layout() # Automatically adjusts spacing between subplots to prevent overlapping
plt.savefig('subplots.png')
plt.close()
print("Saved: subplots.png")

print("\nAll plots have been generated and saved as PNG files in the current directory.")
print("If you want to view them interactively instead, replace `plt.savefig('filename.png')` with `plt.show()`!")
