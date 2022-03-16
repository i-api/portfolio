# List Functions

friends0 = ["Kevin", "Karen", "Jim", "Jim", "Oscar", "Toby"]
lucky_numbers = [42, 4, 8, 15, 16, 23]
friends0.extend(lucky_numbers)
print(friends0)
print("")


friends = ["Kevin", "Karen", "Jim", "Jim", "Oscar", "Toby"]
print(friends)

friends.append("Creed")
# append adds to the end
print(friends)

friends.insert(0, "Kelley Moves to Index Position 0")
print(friends)

friends.remove("Karen")
print(friends)

# friends.clear()
# removes list

# friends.pop()
# pop: removes last element (in this case Creed)
print("\n")


print(friends.index("Toby"))
# Toby is in index position 5

print(friends.count("Jim"))
# Jim appears twice in the list

friends.sort()
print(friends)
# alphabetical order

lucky_numbers.sort()
print(lucky_numbers)
# ascending order

lucky_numbers.reverse()
print(lucky_numbers)


print("")

friends2 = friends.copy()
print(friends2)
