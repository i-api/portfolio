# Python: Statements are executed one at a time, in order from top to bottom.

from math import *

# DRAWING A SHAPE
print("    /|")
print("   / |")
print("  /  |")
print(" /___|")

print("\n \n \n")


# VARIABLES AND DATA TYPES
character_name = "John"
character_age = 50.65
is_male = False
print("There once was a man named " + character_name + ",")
print("he was " + str(character_age) + " years old.")

character_name = "Mike"
print("He really liked the name " + character_name + ",")
print("but he didn't like being " + str(character_age))

# important SHIT (types of data):
# 1: strings ("plain text" aka sequences of text characters)
# 2: numbers (integers, real numbers, et al.)
# 3: booleans ( true or false)
# 4: collections (values made up of multiple other values)

print("\n \n \n")


# WORKING WITH STRINGS
phrase = "Giraffe\"Academy"
print(phrase + " is cool")
print(phrase.upper().isupper())
print(len(phrase))
print(phrase.lower()[0] + phrase.upper()[1] + phrase[2] + phrase.upper()[3] + phrase[4] + phrase.upper()[5] + phrase[6])
print(phrase.index("Giraffe"))
print(phrase.replace("Giraffe", "Elephant"))

print("\n \n \n")


# WORKING WITH NUMBERS
print("test")
print(3 * (4 + 5))
print(10 % 3)
# Modulus operator, or "10 mod 3";
# This takes first number, divide by second number, and give remainder

my_num = -5
print(str(my_num) + " my favorite number")
print(str(abs(my_num)) + " my favorite number")
print(pow(9, 2))
print(max(4, 6))
print(min(4, 6))
print(round(4.7))

# str: converts number into string so that it can be printed along with a string
# abs: absolute value
# pow: raises the first number to the _ power
# max: prints larger number in group
# min: prints smaller number
# round: rounds to whole number

# from math import *   this is located at top of file
# imports additional math functions

print("\n")
print(floor(3.7))
print(ceil(3.7))
print(sqrt(36))
