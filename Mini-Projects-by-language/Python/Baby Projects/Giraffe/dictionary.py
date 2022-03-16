# dictionaries:
# english dictionary: word followed by definition
# python dictionary: key followed by value
# key value pairs

monthconversions = {
    "jan": "january",
    "feb": "february",
    "mar": "march",
    "key": "value",
}

print(monthconversions["jan"])
print(monthconversions.get("mar"))
print(monthconversions.get("luv", "not a valid key"))
