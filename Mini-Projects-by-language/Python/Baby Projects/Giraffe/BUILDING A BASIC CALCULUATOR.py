# BUILDING A BASIC CALCULATOR

# by default python converts inputs from a user into a string
# int: converts strings into numbers (stands for integer)
# float: basically a number that has decimals
num1 = input("Enter a number: ")
num2 = input("Enter a number: ")
result = float(num1) + float(num2)
print(num1 + " + " + num2 + " = " + str(result))
