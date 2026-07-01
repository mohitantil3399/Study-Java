import pyttsx3

# Initialize the text-to-speech engine
speak = pyttsx3.init()

# List available voices
voices = speak.getProperty('voices')
for index, voice in enumerate(voices):
    print(f"Voice {index}: {voice.name} - {voice.id}")

# Set a custom voice by index (e.g., 1 for female voice, if available)
speak.setProperty('voice', voices[1].id)  # Change index as needed

# Optional: Adjust rate and volume
speak.setProperty('rate', 150)    # Speed of speech
speak.setProperty('volume', 5.0)  # Volume (0.0 to 1.0)

# Speak the poem
speak.say('''Twinkle, twinkle, little star,  
How I wonder what you are!  
Up above the world so high,  
Like a diamond in the sky.

When the blazing sun is gone,  
When he nothing shines upon,  
Then you show your little light,  
Twinkle, twinkle, all the night.

Then the traveler in the dark  
Thanks you for your tiny spark;  
He could not see which way to go,  
If you did not twinkle so.

In the dark blue sky you keep,  
And often through my curtains peep,  
For you never shut your eye  
Till the sun is in the sky.

As your bright and tiny spark  
Lights the traveler in the dark,  
Though I know not what you are,  
Twinkle, twinkle, little star.''')

speak.runAndWait()