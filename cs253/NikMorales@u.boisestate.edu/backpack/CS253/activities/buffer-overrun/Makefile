#makeFile

CC = gcc
CFLAGS = -Wall -g -std=c99


all: strings


strings: main.o
	$(CC) $(CFLAGS) $^ -o $@


run:strings
	./$<

clean: 
	rm -f *.o strings
