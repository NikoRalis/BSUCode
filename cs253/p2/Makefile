#makeFile

CC = gcc
CFLAGS = -Wall -g

all:smash

smash: smash.o
	$(CC) $(CFLAGS) $^ -o $@

run: smash
	./$<

clean:
	rm -f *.o smash
