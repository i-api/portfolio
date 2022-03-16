#include <stdio.h> //  C library to perform Input/Output operations (header) //3 file handles for the command prompt: stdin, stdout, stderr
#include <stdlib.h> // C Standard General Utilities Library (header)
#include <string.h> // C Strings (header)

#include <assert.h>

void printCmdLineArgs(int argc, char **argv);



typedef struct _node {
  float data;
  struct _node * next;
} Node;

/*
Complete the resize_fill function. Its parameters are

    p: points to a dynamically allocated array of float elements
    cur_size: the size of the array that p points to
    new_size: the new "desired" size of the array
    fill: a "fill" value: if new_size > cur_size, then this value should be used to initialize all elements whose indices are greater than or equal to cur_size

The function returns a pointer to the resized array.
 Make sure that the memory used for the old array is not leaked.
*/

float *resize_fill(float *p, int cur_size, int new_size, float fill) {
  float *arr = (float *) realloc(p, new_size * sizeof(float));
  if (new_size > cur_size) {
    for (int i = cur_size; i < new_size; i++) {
      arr[i] = fill;
    }
  }
  return arr;
}


// test | clean
int main (int argc, char *argv[]) {
    printCmdLineArgs(argc, argv);




    printf("\n");
    return 0; // so make doesn't say "program exited with code 1"
}














void printCmdLineArgs(int argc, char **argv) {
    printf("-----int argc = %d\n", argc);
    printf("-----char **argv = ");
    for (int i = 0; i < argc; i++) {
        printf("%s ", argv[i]);
    }
    printf("\n");
}








