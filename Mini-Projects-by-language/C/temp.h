#ifndef LIST_H
#define LIST_H




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
void add_front(Node ** list_ptr, char val); //call: add_front(&list_ptr, 'a');
void insert_before(Node **list_ptr, char sval, char dval); //WORKS
void clear_list(Node **list_ptr); // call: clear_list(&head);
void add_tail(Node **list_ptr, char val); // insert char at end of list
Node *find(Node *node, char value);
Node *find_prev_node(Node *node, char value);
int remove_char(Node **list_ptr, char val);
int replace(Node *head, char old, char new);
Node* copy_list(Node* list);

int delete_front(Node **head);
int delete_after(Node **head, char val);
int delete_at(Node **head, int position);




#endif
/*
That prevents double declaration of any identifiers such as types, enums and static variables.
This prevent from the multiple inclusion of same header file multiple time.
*/