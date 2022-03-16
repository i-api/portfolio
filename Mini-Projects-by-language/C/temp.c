#include <stdio.h> //  C library to perform Input/Output operations (header) //3 file handles for the command prompt: stdin, stdout, stderr
#include <stdlib.h> // C Standard General Utilities Library (header)
#include <string.h> // C Strings (header)
#include <ctype.h> // isalpha, isdigit, isalnum, isupper, islower, ispunct, isspace, isblank, iscntrl, isxdigit
#include <assert.h> // If the argument is true, nothing happens. Otherwise, the code aborts and a core dump file is generated
#include <math.h>   //   C numerics library (header)
#include <stdbool.h> // C Boolean type (header)
#include <time.h>

#include "temp.h"
void printCmdLineArgs(int argc, char **argv);


typedef struct {
int * ages; // array to store age numbers as int values
float * weights; // array to store weights in lb as float values
} AgeWeight;
void print(const AgeWeight data, int size) { for (int i = 0; i < size; i++)
printf("%d %.2f\n", data.ages[i], data.weights[i]);
}



// make | make clean
int main (int argc, char **argv) {
    printCmdLineArgs(argc, argv);
    //testLinkedList();



    int cap;
    AgeWeight data; // a struct to store pairs of age and weight data
    int size = 0; // used to keep track of the number of data pairs
    printf("enter ages and weights, ^d to end\n");
    data.ages = malloc(cap * sizeof(int));
    data.weights = malloc(cap * sizeof(float));  
    int a = -1;
    float w = -1.0;
    while (scanf (" %d%f", &a, &w) == 2) {
        if (size == cap) {
        cap += 2; 
        data.ages = realloc(data.ages, cap * sizeof(int));
        data.weights = realloc(data.weights, cap * sizeof(float));
        }
        data.ages[size] = a;
        data.weights[size] = w;
        size++;
    }
    print(data, size);
    free(data.ages);
    free(data.weights);


    
    return 0; // so make doesn't say "program exited with code 1"
}














void printCmdLineArgs(int argc, char **argv) {
    printf("-----int argc = %d\n", argc);
    printf("-----char **argv = ");
    for (int i = 0; i < argc; i++) {
        printf("%s ", argv[i]);
    }
    printf("\n\n");
}








