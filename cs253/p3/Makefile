#makeFile

CC = gcc
CFLAGS = -Wall -g -I.

all:smash

smash: history.o history.h smash.o
	$(CC) $(CFLAGS) $^ -o $@

run: smash
	./$<


clean:
	rm -f *.o smash
