#Project 004

Author: Nik Morales
Class: CS253 Section 2

##Overview

Project 4 is an updated smash program, smash now can fork and exe functions out side of the main program.
P4 also will randomly kill some childrem processes 1/4 of the times.

##Compiling and Using

cd into the directory p4:

>cd p4

run make clean to remove any old .o files and executables:

>make clean

Then run make run or make and ./smash to start the program:

>make
>./smash

or-

>make run

Smash will now be running from here you can run built in commands:

$ cd /directory
$ pwd
$ history
$ exit (will exit program don't run until done testing)

Smash can also run commands not hard coded in these will print out the pid and randomly kill it:

$ ls
$ ls -a
$ cat smash.c 

##Testing

To test this Project I used a lot of printf statements to figure out where execactly the program was trying to exe a fork. I also used
printf statements to check my random int to make sure it was changing and to check the pid as I was terminating.

##Sources used

Besides in class examples and instructor/TA help the only outside source I used was stackoverflow.com to get a better idea how to create a random
int, link at:
http://stackoverflow.com/questions/822323/how-to-generate-a-random-number-in-c