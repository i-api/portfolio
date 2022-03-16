// alias g+++='g++ -g -std=c++11 -pedantic -Wall -Wextra'
#include <iostream>
#include <vector> // resizeable 
#include <string>
using namespace std; // Be Careful. DON'T use this in Header files.
// using std::cout; using std::endl; // now I don't need to use std::cout or std::endl
/*  namespaces: C++ items with same name safely placed in distinct "namespaces". in C we have Problem, haha fuck C.
    - in generall, classes/objects are defined within global mamespaces
    - classes/objects provided by c++ are provided in namespace 
        std:: 
*/
///@brief 
#ifdef fuckPrototypes ///@brief 
int main(int argc, char** argv) {
    printCmdLineArgs(argc, argv);
    //dataTypes();
    //pointers();
    //stringMethods();
    //userInput();
    //arrays();
    //arrays2d();
    //appendCharArrayUsingVectors();
    //vectorMini();
    vectors();
    //runAClassThatIsBelowMainViaThisFunction();
    //ParentOne::coolStatic();
    //ifdefDemo();
    //ParentOne object2; object2.normalMethod(); cout << "--------\n";
    //ChildOne myobject1("Betty", "10,000"); myobject1.normalMethod();

    return 0;
}

#else //Allows you to run functions and classes below main


void printCmdLineArgs(int argc, char* argv[]) {
    printf("----------int argc = %d\n", argc);
    printf("----------char* argv[] = ");
    for (int i = 0; i < argc; i++) {
        printf("%s ", argv[i]);
    }
    cout << endl; //endl creates newline, and flushes output buffer,
    cout << "\n"; // does not flush output buffer
}

void dataTypes() {

    char myChar = 'A';
    string myString = "Hello World!";
    int myInt = 777;
    double myDouble = 222.223;
    bool myBool = true;

    cout << myString << " " << myChar << " " << myInt << " " << myDouble << " " << myBool <<"\n";   
}

#pragma GCC diagnostic push // to ignore warnings
#pragma GCC diagnostic ignored "-Wunused-value" // another neat one: "-Wunused-parameter"
void pointers() {
    int age = 55; int* pointerToAge = &age;
    string name = "mike"; string* pointerToName = &name;
    cout << *pointerToName + " " << pointerToName << "\n"; // cout << str + str
    cout << *pointerToAge << " " << pointerToAge << endl; // cout << int << str
    
    int i = 43;
    int* p = &i;
    &i; // address of i
    p; // pointer to i;
    *p; // value stored at address p
    *p+i == 86; //true.
}
#pragma GCC diagnostic pop // to ignore warnings



void stringMethods() { //Strings in C++ are objects, and can be as long as needed
    string myString = "Hello World!";
    cout << "'Hello' found at index: " << myString.find("Hello", 0) << endl; // (value, index to start searching at)
    cout << myString.substr(0, 10) << endl; // (start index, length)
    cout << myString.length() << endl;  // 12
    cout << myString.capacity() << endl;  //15? why
    cout << myString.c_str() << endl; // c_str gets pointer
}

void userInput() {
    string name;
    string middleName; // char middleName[80]; //fuck chars - old shit code from C
    string lifeGoals;

    cout << "What's your name? ";
    cin >> name; // gets first string, ignores whitespace. However when \n is pressed, this character is still waiting in the input buffer, so we must clean it
    cin.ignore(); // clean previous \n character from cin >> name
    cout << "Hey " << name << "!\n";

    cout << "What's your middle name? ";
    getline(cin, middleName); // read entire line, and eats up newline character at the end
    cout << "Hey " + name + " " + middleName + "!\n";
    
    cout << "What are your life goals? ";
    getline(cin, lifeGoals); // fuck cin.getLine(); fuck C strings aka char arrays
    cout << "Those are some pretty cool goals " << name << "! | " << name << "'s Goal(s): " << lifeGoals << endl;

}



#pragma GCC diagnostic push // to ignore warnings
#pragma GCC diagnostic ignored "-Wsign-compare" // another neat one: "-Wunused-parameter"
void arrays() {
    int array[] = {8, 8, 8, 8, 8};
    for (auto value : array) {
        cout << value << " ";
    }
    cout << "\n--------\n";
    for (int i = 0; i < sizeof(array)/sizeof(array[i]); i++) { // *(&array + 1) - array //bullshit. use vectors. Have: .size()
        cout << array[i] << " ";
    }
    
    string myarray2[3] = {"grade ", "Succeed"}; // inits other element to whitespace
    cout << "\n" << myarray2[0] << *(myarray2 + 1) << endl;
}
#pragma GCC diagnostic pop // to ignore warnings


void arrays2d() {
    //  array2d[row][col]== elements per row
    int array2d[][7] = { {7, 5, 7, 7,}, 
                         {7, 7, 9, 7, 7, 7} };

    for (auto &row : array2d) { // *(&array2d + 1) - array2d // sizeof(array2d)/sizeof(array2d[i])
        for (auto column : row ) { // *(&array2d[i] + 1) - array2d[i] // sizeof(array2d[i])/sizeof(array2d[i][j])
            cout << column << " ";
        }
        cout << "\n";
    }
    cout << "----------\n";
    for (int i = 0; i < *(&array2d + 1) - array2d; i++) { // sizeof(array2d)/sizeof(array2d[i])
        for (int j = 0; j < *(&array2d[i] + 1) - array2d[i]; j++) { // sizeof(array2d[i])/sizeof(array2d[i][j])
            cout << array2d[i][j] << " ";
        }
        cout << "\n";
    }
}


void appendCharArrayUsingVectors() {
    std::vector<char> arr {'a', 'b', 'c'};
    std::string str{arr.cbegin(), arr.cend()};
    std::cout << str << endl;
}


void vectorMini() {
    vector<char> array{'a', 'b', 'c'};
    for (auto i : array) { // autothe type of the variable that is begin declared will automatically be deduced from its initializer and for functions if their return type is auto then that will be evaluated by return type expression at runtime.
        cout << i << ' '; // will print: "a b c"
    } 
}


#pragma GCC diagnostic push // to ignore warnings
#pragma GCC diagnostic ignored "-Wunused-value" // another neat one: "-Wunused-parameter"
void vectors() { // https://www.cplusplus.com/reference/vector/vector/
    vector<string> myVector;
    myVector.push_back("Abe");
    myVector.push_back("Barb");
    myVector.push_back("Conner");
    myVector.insert(myVector.begin() + 3, "Drake");  // shifts displaced element to the right.
    
    //myVector.resize(10, "default"); // (numElements, defaultVal)
    myVector.size(); // 4
    myVector.capacity(); // 4
    myVector.max_size(); // 288230376151711743

    *(myVector.data()+1);  //Barb //returns pointer (index 0)
    *(myVector.begin()+1); //Barb //returns iterator (index 0), basically same thing as .data()
    //myVector.clear(); // erases entire
    //myVector.erase(myVector.begin()); // erases Abe, elements right of removed value shifted left
    
    int i = 0;
    for (auto val : myVector) {
        cout << i << "." << val + " \n"; i++;
    }
    
    while (!myVector.empty()) {
        cout << myVector.back() + ", "; //only accesses
        myVector.pop_back(); // pops back. void return 
        if (myVector.empty() == (1 == true)) {cout << "\n";}
    }
    
}
#pragma GCC diagnostic pop // to ignore warnings








/** @brief My first fuckin class.
 * @param name
 * @param salary
 * @overload
 * @see ur girlfriend
 */
class ParentOne {
    private: // only code inside the ParentOne class, can access these fields
        string salary;
    public:
        string name;

        ///@overload constructor, go fuck urself👍, /// for documentation
        ParentOne() { // constructor
            this->name = "defaultName🍻";
            this->salary = "defaultSalary🤦‍♂️";
        }


        void normalMethod() {
        cout << "Cool stuff yo! Normal method! " << this->name  << " | "<< salary << "\n";
        coolStatic();
        myVirtualMethod();
        }

        ///@param none here
        static void coolStatic() {
        cout << "Calling a static method! \n";
        }

        virtual void myVirtualMethod() { // virtual methods are like abstract methods, but more flexible, don't have to be defined
            cout << "parent virtual method, doesnt have to be defined\n" ;
            /* A virtual function, is basically saying look, here's the functionality that may or 
                may not be good enough for the child class. So if it is good enough, use this 
                method, if not, then override me, and provide your own functionality.
                
                An abstract function cannot have functionality. You're basically saying, any 
                child class MUST give their own version of this method, however it's too general 
                to even try to implement in the parent class.

            */
        }
};

class ChildOne : public ParentOne { // inheritance. ChildOne is child.  
    public:
        string salary; //made public! was not able to access before because was private in parent 
        // string name already public in Parent. constructor is called by child
        ChildOne(string name, string salary) : ParentOne(){ // : ParentOne(name, salary)  { // call superclass constructor. param names must be the same for both constructors
            this->salary = salary;
            this->name = name;
        }

        ///@override
        void normalMethod() {
        cout << "ParentOne == Angry about overriden method."
        << " She wallops " << this->name << "🍻 with " << this->salary << "😩 spankings. \n";
        coolStatic();
        myVirtualMethod();
        parentDiscipline(this->name);
        }
        
        ///@override
        virtual void myVirtualMethod() {
            cout << "virtual methods are flexible\n" ;
        }

        static void parentDiscipline(string name) {
            cout << "New Child only method -"<< name << ", the child, gets spanked by Parent Ouch!\n";
        }

};


void runAClassThatIsBelowMainViaThisFunction() {
    ParentOne::coolStatic(); cout << "\n";

    ParentOne myObject; // create object instance
    myObject.normalMethod();
    myObject.coolStatic(); // objects can call static methods?!
    cout << myObject.name <<"\n"; 

    ParentOne* pointerToObject = new ParentOne(); // create pointer to new object instance
    pointerToObject->normalMethod();
    pointerToObject->coolStatic();
    cout <<pointerToObject->name <<"\n"; 
}




#define timsFirstifdef // if this is taken out, ifdefDemo will not run
#ifdef timsFirstifdef
/* This should remain commented. Code below should enable when #define timsFirstifdef */
void ifdefDemo() {
    cout << "ifdefDemo works! \n";

    /* #ifdef https://www.cprogramming.com/reference/preprocessor/ifdef.html
    #ifdef <token>
    // code
    #else
    // code to include if the token is not defined
    #endif
     ifdef checks whether the given token has been #defined earlier in the file or in 
     an included file. If so, it includes everything between it and the closing #else or, 
     if no #else is present, the closing #endif. #ifdef can be used with built-in token 
     identifiers set by the compiler to indicate that additional functionality is available.
     For instance, the __cplusplus macro is defined in C++ but not C; you can use this fact 
     to mix C and C++ code using an #ifdef statement: 
     */

}
#endif













#define fuckPrototypes
#include __FILE__
#endif

/*  #ifdef - run functions and classes below main! fuck that function prototype bullshit hahah
    #ifdef please_see_definitions_below_main // goes just above main
    #else // goes just below main

    #define please_see_definitions_below_main // goes at bottom of CPP file 
    #include __FILE__ // goes at bottom of CPP file 
    #endif // goes at bottom of CPP file 
*/