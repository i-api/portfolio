# LISTS

# important SHIT (types of data):
# 1: strings ("plain text" aka sequences of text characters)
# 2: numbers (integers, real numbers, et al.)
# 3: booleans ( true or false)
# 4: collections (values made up of multiple other values)

# This is an Array: cars = ["Ford", "Volvo", "BMW"]
# Arrays are used to store multiple values in one single variable
friends = ["Kevin", "Karen", "Jim", "Oscar", "Toby"]
# indexes count 0, 1, 2, 3 etc...

print(friends)

# this refers to the index of the 0 element in the list,
# in this case, kevin
print(friends[0])

print(friends[2])

print(friends[-1])

print(friends[0:3])

print("\n")

friends2 = ["Kevin", "Karen", "Jim", "Oscar", "Toby"]
friends[1] = "A Karen is a Spoiled Entitled Bitch"
print(friends[1])
