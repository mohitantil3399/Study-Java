choice = input("Enter choice : ")#using string as input
match(choice):
    case "white" : 
        print("White color.")
    case "black" :
        print("Black color.")
    case "blue" : 
        print("blue color.")
    case "yellow" :
        print("yellow color.")
    case _: 
        print("unkown case.")

choice = int(input("Enter choice : "))#using integer input
match(choice):
    case 1 : 
        print("White color.")
    case 2 :
        print("Black color.")
    case 3 : 
        print("blue color.")
    case 4 :
        print("yellow color.")
    case _: 
        print("unkown case.")

# ─────────────────────────────────────────────
# ADVANCED MATCH STATEMENT CONCEPTS
# ─────────────────────────────────────────────

# ── 1. GUARD CLAUSE  (if condition inside case) ──────────────────────────────
print("\n── 1. Guard Clause (if) ──")
score = int(input("Enter score (0-100): "))
match score:
    case s if s >= 90:
        print(f"Grade A  (score={s})")
    case s if s >= 75:
        print(f"Grade B  (score={s})")
    case s if s >= 60:
        print(f"Grade C  (score={s})")
    case s if s >= 40:
        print(f"Grade D  (score={s})")
    case _:
        print("Grade F – Failed")

# ── 2. MULTI-CHECKING  (OR patterns  |) ──────────────────────────────────────
print("\n── 2. Multi-Checking (| OR pattern) ──")
day = input("Enter day name: ").lower()
match day:
    case "saturday" | "sunday":
        print(f"{day.title()} is a Weekend 🎉")
    case "monday" | "tuesday" | "wednesday" | "thursday" | "friday":
        print(f"{day.title()} is a Weekday 💼")
    case _:
        print("Unknown day!")

# ── 3. TUPLE PATTERNS ────────────────────────────────────────────────────────
print("\n── 3. Tuple Patterns ──")
point = (int(input("x: ")), int(input("y: ")))
match point:
    case (0, 0):
        print("Origin point (0, 0)")
    case (x, 0):
        print(f"On X-axis at x={x}")
    case (0, y):
        print(f"On Y-axis at y={y}")
    case (x, y) if x == y:
        print(f"On diagonal  x=y={x}")
    case (x, y):
        print(f"General point at ({x}, {y})")

# ── 4. LIST / SEQUENCE PATTERNS ──────────────────────────────────────────────
print("\n── 4. List / Sequence Patterns ──")
commands = input("Enter words separated by spaces: ").split()
match commands:
    case []:
        print("Empty input!")
    case [single]:
        print(f"Single word: '{single}'")
    case ["go", direction]:
        print(f"Moving {direction}")
    case ["go", direction, steps]:
        print(f"Moving {direction} for {steps} steps")
    case ["quit"] | ["exit"] | ["bye"]:
        print("Goodbye! 👋")
    case [first, *rest]:
        print(f"First word: '{first}', remaining: {rest}")

# ── 5. NESTED TUPLE + GUARD ───────────────────────────────────────────────────
print("\n── 5. Nested Tuple + Guard ──")
rgb = (
    int(input("Red   (0-255): ")),
    int(input("Green (0-255): ")),
    int(input("Blue  (0-255): ")),
)
match rgb:
    case (255, 0, 0):
        print("Pure Red 🔴")
    case (0, 255, 0):
        print("Pure Green 🟢")
    case (0, 0, 255):
        print("Pure Blue 🔵")
    case (0, 0, 0):
        print("Black ⬛")
    case (255, 255, 255):
        print("White ⬜")
    case (r, g, b) if r == g == b:
        print(f"Shade of Gray – {r}")
    case (r, g, b):
        print(f"Custom color RGB({r}, {g}, {b})")

# ── 6. DICTIONARY / MAPPING PATTERNS ─────────────────────────────────────────
print("\n── 6. Mapping (dict) Patterns ──")
response = {"status": 200, "data": "OK"}
match response:
    case {"status": 200, "data": data}:
        print(f"Success ✅  data = {data}")
    case {"status": 404}:
        print("Not Found ❌")
    case {"status": 500, **rest}:
        print(f"Server Error 💥  extra info: {rest}")
    case _:
        print("Unknown response")

# ── 7. CAPTURING SUB-PATTERN WITH  AS ────────────────────────────────────────
print("\n── 7. Capture with 'as' ──")
coord = (int(input("a: ")), int(input("b: ")))
match coord:
    case (0, 0) as origin:
        print(f"Matched origin, full value: {origin}")
    case (x, 0) as pt:
        print(f"On X-axis, full tuple captured: {pt}, x={x}")
    case (x, y) as pt:
        print(f"General point captured: {pt}")
