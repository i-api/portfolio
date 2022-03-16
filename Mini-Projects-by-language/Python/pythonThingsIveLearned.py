from array import *
#from re import S


def main():

    # forLoops()
    # ifStatements()
    # variables()
    # pythonOperators()

    # theCollectionDataTypes() #Tuples, Lists, Sets, Dictionaries
    # dictionaries()
    #lists()
    #arrayToList()
    #stringToList()
    
    #TooSum()
    lambdaDemo()

def forLoops():
    for x in range(10):  # range(exclusive)
        print(x),  # comma prints without a newline
    print("\n"),
    for x in range(0, 11):  # range(inclusive, exclusive)
        print(x),
    print("\n"),
    for x in range(2, 9, 3):  # range(inclusive, exclusive, step increment);
        print(x),
        if x == 8:
            print("\n")


def ifStatements():
    five = 5
    ten = 10
    if five > ten:
        print("5 is greater than 10")
    elif five == ten:
        print("5 and 10 are equal")
    else:
        print("5 is less than 10")


myGlobalVar = "this is a global variable"

def variables():

    global myGlobalVar1
    myGlobalVar1 = "global keyword used. now belongs to global scope"

    x = 10  # int
    y = 10.5  # float
    z = True  # bool
    a = "Hey!"  # str

    print(str(x) + "   " +str(type(x)))
    print(str(y) + " " +str(type(y)))
    print(str(z) + " " +str(type(z)))
    print(str(a) + " " +str(type(a)))
    
    """
    Text Type: 	    str
    Numeric Types: 	int, float, complex
    Sequence Types: list, tuple, range
    Mapping Type: 	dict
    Set Types: 	    set, frozenset
    Boolean Type: 	bool
    Binary Types: 	bytes, bytearray, memoryview

    """


def pythonOperators():

    # Logical Operators:  and, or, not
    print(True and True)    # True
    print(1 == 1 or 1 == 100)   # True
    print(not(1 == 100))      # True

    # Identity Operators, used to compare objects
    obj263 = ["apple", "banana", "cherry"] # lists are ordered and changeable
    t95sup = ["apple", "banana", "cherry"]

    print(obj263 is t95sup)  # False, because it points to diff memory address
    print(obj263 == t95sup)  # True, because values are the same

    """
    Operator |  Example  |  Simplified  

        = 	    x = 5 	    x = 5 	
        += 	    x += 3 	    x = x + 3 	
        -= 	    x -= 3 	    x = x - 3 	
        *= 	    x *= 3 	    x = x * 3 	
        /= 	    x /= 3 	    x = x / 3 	
        %= 	    x %= 3 	    x = x % 3 	
        //= 	x //= 3 	x = x // 3 	
        **= 	x **= 3 	x = x ** 3 	
        &= 	    x &= 3 	    x = x & 3 	
        |= 	    x |= 3 	    x = x | 3 	
        ^= 	    x ^= 3 	    x = x ^ 3 	
        >>= 	x >>= 3 	x = x >> 3 	
        <<= 	x <<= 3 	x = x << 3
        
        
        + 	    x + y 	    addition
        - 	    x - y 	    subtraction
        * 	    x * y 	    multiplication
        / 	    x / y 	    division
        % 	    x % y 	    modulo
        ** 	    x ** y 	    exponentiation
        // 	    x // y 	    floor division
    """




def theCollectionDataTypes():

    myList = ["red", "good", "redemption"]; myList[1] = "dead"
    myTuple = (44, 44.0, "cherry", myList, True)
    mySet = {"apple", "banana", "cherry"}
    myDict = {"Key": [1964, "myValue2"], 12: False, True: "Love"}

    print(str(type(myList)) + "  " + str(myList))
    print(str(type(myTuple)) + " " + str(myTuple))
    print(str(type(mySet)) + "   " + str(mySet))
    print(str(type(myDict)) + "  " + str(myDict))

    """
    These are ALL collections:
        List:       ordered      changeable.    Allows duplicate members.
        Tuple:      ordered      unchangeable.  Allows duplicate members. 
        Set:        unordered    unchangeable*  No duplicate members. But you can remove items and add new items. Unindexed.
        Dictionary: ordered**    changeable.    No duplicate members.
    """




def dictionaries():
    myDict2 = {3: "elderberry", 47: "banana", 4: "acai", 5: "elderberry"}
    print(myDict2)

    len(myDict2)      # 4
    myDict2.keys()    # [1, 2, 3, 4, 5]
    myDict2[5]        # elderberry
    myDict2.get(5)    # elderberry
    get_dict_key_from_value("elderberry", myDict2) # returns [3, 5]
    #myList = list(myDict2.values()) - Jalen - Jalen March 12, leetcode practice
    #print(myList.index("banana")) - Jalen March 12, leetcode practice
 
# returns list of key(s) given the value, and the dictionary
def get_dict_key_from_value(val, dictionary):
    myList = []
    for key, value in dictionary.items():
        if val == value:
            myList.append(key)
    if not myList:  # if myList is empty, alternativeley if len(mylist)==0:
        return "key doesn't exist for that value"
    else:
        print("val: " + val + " |Found at keys:" + str(myList)) #optional
        return myList


def lists():
    list = ["aed", "bloo", "Redemption"]
    print(list)
    list.index("aed")  # returns 0;
    list[0]  # returns "aed"
    len(list)  # 3

    list[0] = "Red"  # replaces "aed" at index 0 with "red"
    list.insert(1, "Dead")  # doesn't delete anything; displaces list to the right
    list.remove("bloo")  # removes the first instance of "bloo"
    list.append(2)  # adds to the end, lists can store ints and strings
    print(list)


def arrayToList():
    array1 = array('i', [111, 2444, 23, 1212, 2,
                   53, 2, 399, 10])  # declare array

    # Array <-> List
    list2 = array1.tolist()
    list2.sort()  # convert array to list then sort

    # convert sorted list back to an Array
    arr3 = array('i', list2)

    print(type(arr3)),
    print(arr3)


def stringToList():
    string1 = "Cat Jessie Joel Abe Bob Ellie TLOU2"  # The Last of US Part 2

    # String <-> List
    list1 = string1.split()
    list1.sort()  # convert String to list then sort

    # convert sorted list back to a String
    str3 = ""
    for element in list1:
        str3 += element + " "

    print(type(str3)),
    print(str3)




def TooSum():
    class TwoSum(object):
        
        def twoSum(self, nums, target):
            
            hashMap = {} # makes a dictionary
            for i in range(len(nums)):
                if nums[i] in hashMap:
                    return [hashMap[nums[i]], i]
                else:
                    hashMap[target-nums[i]] = i
            """
            :type nums: List[int]
            :type target: int
            :rtype: List[int]
            
            Time Complexity: O(N)
            Space complexity: (auxillary space) O(N)
            """
        #Test Cases
        print(twoSum(object, [2, 7, 11, 15], 9))
        print(twoSum(object, [3, 2, 4], 6))
        print(twoSum(object, [3, 3], 6))
        """ Two Sum: Sample inputs
        Input: nums = [2,7,11,15], target = 9
        Output: [0,1]

        Input: nums = [3,2,4], target = 6
        Output: [1,2]

        Input: nums = [3,3], target = 6
        Output: [0,1]
        """


def lambdaDemo():
    # functions are objects in python, so we can pass them as arguments
    def square(x): 
        return x * x
    print(square(5))

    myFunction = lambda a : a*a # lambda is a function that returns a function
    print(myFunction(5))

    myFunctionadd = lambda a, b : a+b
    print(myFunctionadd(5,10))


# main method call executes the program,but only if this file is run directly   
if __name__ == '__main__':
    main()