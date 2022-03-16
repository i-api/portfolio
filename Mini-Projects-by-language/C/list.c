#include "temp.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <assert.h>



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
    remove_char(&n1, '#'); //uses find method 
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

int replace(Node *head, char old, char new) {
    if (head == NULL) { return 0; }
    find(head, old)->data = new;
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





