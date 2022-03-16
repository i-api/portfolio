#include <stdio.h> //  C library to perform Input/Output operations (header) //3 file handles for the command prompt: stdin, stdout, stderr
#include <stdlib.h> // C Standard General Utilities Library (header)
#include <string.h> // C Strings (header)
#include <ctype.h> // isalpha, isdigit, isalnum, isupper, islower, ispunct, isspace, isblank, iscntrl, isxdigit
#include <assert.h> // If the argument is true, nothing happens. Otherwise, the code aborts and a core dump file is generated
#include <math.h>   //   C numerics library (header)
#include <stdbool.h> // C Boolean type (header)
#include <time.h>

/* GDB  gcc -g helloWorld.c | gdb ./a.out | break functionNameOrLineNum | run
	display a
	display *a
    info display
	print a
	s // (step)
	n //(next)
Step - Next Line, 
Next - Skips functions // when your reach printf, do Next, not step
	display a
	bt full
-----------------

Valgrind: valgrind --leak-check=full --show-leak-kinds=all ./a.out

To use: as when using gdb to debug, compile program with -g.
Run program using valgrind:
valgrind --leak-check=full --show-leak-kinds=all \
./myFile <arg1> <arg2> ...
See also: http://valgrind.org/docs/manual/QuickStart.html

valgrind --leak-check=full --show-leak-kinds=all ./a.out


-----------------
gccc helloWorld.c -o helloWorld | ./helloWorld
optional: -o filename
// alias gccc='gcc -std=c99 -pedantic -Wall -Wextra'
gcc -std=c99 -Wall -Wextra -pedantic fileName.c -o fileName

*/

void printCmdLineArgs(int argc, char **argv);
void structDemo(void);
void scanDemo(void);
int* descending(int a, int b); //descending(3, 7); // int* a = descending(10, -20);
void shifty (char *a, int shift); /*char s[] = "Good luck! 2u"; shifty(s, 16); printf("%s\n", s);*/
void displayStaticInt();
void bracketsWithinFunc(); // brackets create their own scope
void finalPdemo();
void simpleLinkedList();
void testLinkedList();

typedef struct node_ { // Linked List.
    char data;
    struct node_* next;
} Node;

Node * create_node(char); // put char into a newly created node
void print_list(const Node *); // output the list iteratively
void print_rec(const Node *); // output the list recursively
void reverse_print(const Node *);
int length(const Node *); //WORKS
long length_rec(const Node *head); //untested

void add_after(Node *list_ptr, char val); //WORKS
void add_front(Node ** list_ptr, char val); //WORKS
void insert_before(Node **list_ptr, char sval, char dval); //WORKS
void clear_list(Node **list_ptr); // call: clear_list(&head);
void add_tail(Node **list_ptr, char val); // insert char at end of list
Node *find(Node *node, char value);
Node *find_prev_node(Node *node, char value);
int remove_char(Node **list_ptr, char val);
int replace(Node *head, char old, char noo);
Node* copy_list(Node* list);

int delete_front(Node **head);
int delete_after(Node **head, char val);
int delete_at(Node **head, int position);





// testLinkedList();
// run | clean 
int main (int argc, char **argv) {
  printCmdLineArgs(argc, argv);

  testLinkedList();





return 1;
}





void testLinkedList() {

    // create nodes
    Node * n1 = create_node('A');
    Node * n2 = create_node('C');
    Node * n3 = create_node('E');
    Node * n4 = create_node('G');

    //link nodes
    n1->next = n2;
    n2->next = n3;
    n3->next = n4;

    print_list(n1);    // prints A C E G
    printf("\n"); 

    //TESTS:
    assert(length(n1) == 4);
    
    add_after (n1, 'B');
    add_after (n2, 'D');
    add_after (n3, 'F');
    assert(length(n1) == 7); // there must be 7 nodes now
    print_list(n1); // should now print A B C D E F G
    printf("\n");

    reverse_print(n1); // should now print G F E D C B A
    printf("\n");

    
    //Tim Added
    clear_list(&n1); // works
    add_front(&n1, '@'); add_front(&n1, 'M'); add_front(&n1, 'I'); add_front(&n1, 'T');
    print_list(n1);
    printf("\n");

    add_tail(&n1, '#'); add_tail(&n1, 'd'); add_tail(&n1, 'o'); add_tail(&n1, 'N'); add_tail(&n1, 'e');
    print_list(n1); // should now print T I M @ # d o n e
    printf("\n");

    printf("'%c'\n", (find(n1, '#')->data)); // will segfault if not found
    remove_char(&n1, '#'); //uses find method //must free memory
    replace(n1, 'N', 'n');
    print_list(n1); // should now print: T I M @ d o n e
    printf("\n");

    Node * myCopy1 = copy_list(n1);
    delete_front(&myCopy1); // I M @ d o n e
    delete_at(&myCopy1, 0); // M @ d o n e
    delete_after(&myCopy1, '@'); // M @ o n e

    print_list(myCopy1); // should now print: M @ o n e 
    printf("\n");

    //free the memory | valgrind --leak-check=full --show-leak-kinds=all ./a.out
    Node * prev;
    while (n1) {
        prev = n1;
        n1 = n1->next;
        free(prev);
    }
    while (myCopy1) {
        prev = myCopy1;
        myCopy1 = myCopy1->next;
        free(prev);
    }


}

// Linked List -----------------

Node* create_node(char data) {
    Node* newNode = (Node*) malloc(sizeof(Node));
    assert(newNode); //confirm malloc didn't fail
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

void print_list(const Node * head) {
  while (head != NULL) {
    printf("%c ", head->data);
    head = head->next;  // advance to next node
  }
}

void print_rec(const Node *head) { // print list recursively
  if (head != NULL) {
    printf("%c ", head->data);
    print_rec(head->next);
  }
}

void reverse_print(const Node * head) { // recursive
  if (head == NULL) {
    return;
  }
  reverse_print(head->next);
  printf("%c ", head->data);
}

int length(const Node * head) {
  int count = 0;
  while (head != NULL) {
    count++;
    head = head->next;
  }
  return count;
}
// count and return the number of elements in the List (recursive)
long length_rec(const Node *head) {
  if (head == NULL) { return 0; }
  return 1 + length_rec(head->next);
}

void add_after(Node *list_ptr, char val) {
    Node* newNode = create_node(val);
    newNode -> next = list_ptr -> next;
    list_ptr -> next = newNode;
}

void add_front(Node ** list_ptr, char val) { // call: add_front(&head, 'A');
    Node* newNode = create_node(val);
    newNode->next = *list_ptr;
    *list_ptr = newNode;
}

void insert_before(Node **list_ptr, char sval, char dval) {   
  if (*list_ptr == NULL) { // if empty list, do nothing
    return;
  }
  if ((*list_ptr)->data == sval) {  // special case for inserting before first node  
    Node *n = create_node(dval);
    n->next = *list_ptr;
    *list_ptr = n;
    return;
  }
  // general case: // prev lags cur, at each step see if cur contains sval,
  // if so insert a node between prev and cur
  Node *prev = *list_ptr, *cur = (*list_ptr)->next;
  while (cur != NULL) {
    if (cur->data == sval) {
      Node *n = create_node(dval);
      n->next = cur;
      prev->next = n;
      return;
    }
    prev = cur;
    cur = cur->next;
  }
    
}


// get rid of (deallocate) entire list, recursively from end to start
void clear_list(Node **list_ptr) {
  if (*list_ptr != NULL) {
    clear_list(&((*list_ptr)->next));
    free(*list_ptr);
    *list_ptr = NULL;
  }
}


void add_tail(Node **list_ptr, char val) { // insert char at end of list
  Node * newNode = create_node(val);
  if (*list_ptr == NULL) { *list_ptr = newNode; return; }
    Node *current = *list_ptr;
    while (current->next != NULL) {
      current = current->next;
    }
    current->next = newNode;
}

// find specfied val in the list, return pointer to first node in list
// that contains it, or NULL if val is not present
Node *find(Node *node, char value) {
    while (node != NULL) {
        if (node->data == value) {
        return node;
        }
        node = node->next;
    }
  return NULL;
}

Node *find_prev_node(Node *node, char value) {
    while (node->next != NULL) {
        if (node->next->data == value) {
        return node;
        }
        node = node->next;
    }
  return NULL;
}


int remove_char(Node **list_ptr, char val) { //return 1 if sucessfully removed
    if (*list_ptr == NULL) { return 0; }
    Node* prevNode = find_prev_node(*list_ptr, val);
    Node* nodeToRemove = prevNode->next;
    prevNode->next = nodeToRemove->next;
    free(nodeToRemove);

    //*find(*list_ptr, val) = *find(*list_ptr, val)->next; // don't do this
    return 1;
}

int replace(Node *head, char old, char noo) {
    if (head == NULL) { return 0; }
    find(head, old)->data = noo;
    return 1;
}


Node* copy_list(Node* list) {
    if (list == NULL) return NULL;
    Node* result = create_node(list->data);
    result->data = list->data;
    result->next = copy_list(list->next); // recursion
    return result;
}


int delete_front(Node** head) {
  if (*head == NULL) return 0;
  Node* temp = *head;
  *head = (*head)->next;
  free(temp);
  return 1;
}

int delete_after(Node** head, char val) {
  if (*head == NULL) return 0;
  if ((*head)->data == val) {
    *head = (*head)->next;
    delete_front(head);
    return 1;
  }
  Node* current = *head;
  while (current != NULL) {
    if (current->data == val) {
      Node* temp = current->next;
      current->next = current->next->next;
      free(temp);
      return 1;
    }
    current = current->next;
  }
  return 0;
}

int delete_at(Node** head, int position) { //argument is int position number
  if (*head == NULL) return 0;
  if (position == 0) {
    delete_front(head);
    return 1;
  }
  Node* current = *head;
  for (int i = 0; i < position - 1; i++) {
    current = current->next;
  }
  Node* temp = current->next;
  current->next = current->next->next;
  free(temp);
  return 1;
}

/*
Node * current = create_node(head->data); // create new Node, good practice
  current->next = head->next;
*/

// ----------------- Linked List -----------------











void simpleLinkedList(void) {
    // code creating a linked list
    Node *head = (Node*) malloc(sizeof(Node));
    head->data = 'A';
    head->next;
    head->next->data = 'B';
    head->next->next;
    head->next->next->data = 'C';
    head->next->next->next = NULL;
    
    printf("%d", length(head));
    print_list(head);
    print_list(head);
    print_list(head);
    printf("%d", length(head));

}


void displayStaticInt() {
    int b = 0;
    static int c = 0;
    if (c >=5) {
        return;
    }
    printf("%d%d  ", b++,c++);
    displayStaticInt();
}

int k = 20;
void fun(int k) { printf("%d ", k); }
void bracketsWithinFunc() {
    printf("%d ", k); // 20
    int k = 10;
    printf("%d ", k); // 10
    { // brackets create their own scope, so k = 0 -> 1
        int k = 0;
        k++;
        printf("%d ", k);
    }
    printf("%d ", k); // k = 10
    k++;
    fun(k); // 11
}



void shifty (char *a, int shift) {
   int len = strlen(a);
   for (int i = 0; i < len; i++) {
      if (isalpha(a[i])) {  // no need to modify non-alphas
         char start = 'A';  // assume we’re dealing with upper case
         if (a[i] >= 'a') { // then adjust since it’s lower case
            start = 'a'; 
            }
         a[i] = ((a[i] - start + shift) % 26) + start;
      }
} }


/*
int* descending(int a, int b) {
    int* array = malloc(sizeof(int) * a); //dynamic memory allocation

    for (int i =0; i < a; i++) {
        *(array+i) = b-i;
        printf("%d\n", *(array+i)); //for testing
    }
    return array;

}
*/

/*
void scanDemo(void) {
    int d;
    scanf("%d", &d);
    printf("%d\n", d);

    char* word = malloc(sizeof(char) * 8);
    scanf("%s", word);
    printf("%s\n", word);
}
*/


typedef struct {
    char *name;
    char *city;
} Spartan;
void structDemo() {
    Spartan spartan1 = { .name = "Leonidas", .city = "Greece"}; // or {"Leonidas", "Greece"};
    Spartan* spartan2;
    spartan2->name = "Kratos";
    (*spartan2).city = "Thebes"; //either works

    printf("%s, %s\n", spartan1.name, spartan1.city);
    printf("%s, %s\n", spartan2->name, spartan2->city);
    free(spartan2); // # of mallocs = # of frees

}



void printCmdLineArgs(int argc, char **argv) {
    printf("-----int argc = %d\n", argc);
    printf("-----char **argv = ");
    for (int i = 0; i < argc; i++) {
        printf("%s ", argv[i]);
    }
    printf("\n\n");
}

void finalPdemo() {
    int i = 43;
    int* p = &i;
    /*
    &i; // address of i
    p; // pointer to i;
    *p; // value stored at address p
    */
    printf("%d\n", *p+i);
}


/* Memory Sizes
pointers = 8 bytes
int = 4 bytes
bool = 1 byte


char =  1 byte(8 bits) character; an integer type
int = 4 bytes (32 bits), on ugrad
unsigned = same size as int, but >= 0
long = greater capacity than int
float = single-precision
double = double-precision
bool = #include <stdbool.h>


PROMOTION: smaller type is promoted to larger: char < int < unsigned < long < float < double
NARROWING: from larger to smaller types
CASTING: gives programmer control over promotion and narrowing



dont do char * str1 = str2; do strcpy(str1, str2);

*/

/*  Multiple Choice Questions:

Given the declaration: int* x;
reads in an integer and stores in x: scanf("%d", x);


Which variables are automatically initialized to 0 when declared? 
NO  A. all positions in local int array variables  
YES B. static variables  
YES C. global variables 




*/

/* Be ready to write:
Y    create_node 
Y    length
Y    print (iterative and recursive), reverse_print
Y    add_front
Y    add_after
Y    delete_front
Y    delete_after
Y    delete_at //argument is int position number
Y    clear_list
Y    copy_list


typedef struct node_ { // Linked List.
    char data;
    struct node_* next;
} Node;

Node * create_node(char); // put char into a newly created node
void print_list(const Node *); // output the list iteratively
void print_rec(const Node *); // output the list recursively
void reverse_print(const Node *);
int length(const Node *); //WORKS
long length_rec(const Node *head); //untested

void add_front(Node ** list_ptr, char val); //call: add_front(&head, 'A'); 
void add_after(Node *list_ptr, char val); //WORKS

int delete_front(Node **head);
int delete_after(Node **head, char val);
int delete_at(Node **head, int position);

void clear_list(Node **list_ptr); // call: clear_list(&head);
Node* copy_list(Node* list);


void insert_before(Node **list_ptr, char sval, char dval); //WORKS
void add_tail(Node **list_ptr, char val); // insert char at end of list

Node *find(Node *node, char value);
Node *find_prev_node(Node *node, char value);
  int remove_char(Node **list_ptr, char val);
  int replace(Node *head, char old, char noo);




*/