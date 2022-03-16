num1 = float(input("Enter first number: "))
operation = input("Enter operator: ")
num2 = float(input("Enter second number: "))

if operation == "+":
    print(num1 + num2)
elif operation == "-":
    print(num1 - num2)
elif operation == "/":
    print(num1 / num2)
elif operation == "*":
    print(num1 * num2)
else:
    print("eat shit its a fookin invalid operator")
