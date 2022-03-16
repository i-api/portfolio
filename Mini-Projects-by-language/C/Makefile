CC = gcc
CFLAGS = -std=c99 -pedantic -Wall -Wextra -g

# create executable,  This is for temp.c and temp.h
temp: temp.o list.o
	$(CC) temp.o list.o -o a.out
	./a.out

# create object file for testing linked list functions
temp.o: temp.c temp.h
	$(CC) $(CFLAGS) -c temp.c

# create object file for linked list functions
list.o: list.c temp.h
	$(CC) $(CFLAGS) -c list.c

# clean up
clean:
	rm -f *~ *.o main
	rm a.out