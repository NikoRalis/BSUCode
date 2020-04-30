Project 003

Author: Nik Morales
Class: CS253 Section 2

Overview

    This program is an updated version of my version of bash 'smash' with a history function.

Compiling and Using

    To compile and run this program enter 
        > make run

    This should give you the '$ ' symbol, allowing you to use the program like a limited bash.

    or compile using:
        >make

    and to run:
        >./smash

Testing

To test this code I ran it through gdb to try and see were my leak was occuring, I wasn't able
to get my leak fixed but my history works as long as it doesn't fill up, I aslo am getting:
    "Makefile:12: recipe for target 'run' failed
     make: *** [run] Segmentation fault  "
I double and triple checked the clear_history function and couldn't find where my logic was failing.
However when running it the second way I only got a;
    "Segmentation fault"
Not good either way, I know.

Sources used

For this Project the main source I use was the TA Joe.