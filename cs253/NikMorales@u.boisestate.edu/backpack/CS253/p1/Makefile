#makeFile

CC = gcc
CFLAGS = -Wall


all: hello


hello: f1.o f2.o f3.o main.o
	$(CC) $(CFLAGS) $^ -o $@


run:hello
	./$<

clean: 
	rm -f *.o hello
