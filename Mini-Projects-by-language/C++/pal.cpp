// alias g+++='g++ -g -std=c++11 -pedantic -Wall -Wextra'
#include <iostream>
#include <vector> // resizeable 
#include <string>
using namespace std; 
//MACROS are OP!





#ifdef fuckPrototypes  //Allows you to run functions and classes below main

int main(int argc, char** argv) {
    printCmdLineArgs(argc, argv);
    cout <<"Hello World!!!\n";

    

    
    
    
    
    
    
    
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











#define fuckPrototypes
#include __FILE__
#endif
