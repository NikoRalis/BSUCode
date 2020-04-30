
Ring Buffer Library {#mainpage}
===================

#Project 005

Author: Nik Morales
Class: CS253 Section 2
##Overview

    This program is a simple ring buffer library that adds the logs to an output file when dumped.
    This buffer only allows one index to be writen and only allows 6 indexes total after this
    the index will reset to 0 overwriting previous entries. 

##Compiling and Using

    To run the test programs you will need to set the paths to find the library:

        $ export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./lib

    Then to compile make sure you are in the p5 directory and run:

        $ make

    To run the the program independently run:

        $ ./run.sh

    If you would like to run the program through backpack and the grader compile normally and run:

        $ ./backpack.sh results.txt

    This will dump whether the program passes or fails. Additionally if you want to look at the log file run:

        $ cat ring.log

##Testing

    This section should detail how you tested your code. Simply stating "I ran it a few times and it seems to work" is not sufficient. 
    Your testing needs to be detailed here or even better, this section should just point the user at the appropriate unit/system tests that you wrote.

    Intially I was having problems getting into my dump_log() and onalarm() this was an easy fix after I moved my alarm call and signal call to my init_buff
    function. Along with this to quickly debug I added print statements at the beginning of dump_log() and onalarm() verifying I was getting in there correctly.
    The only other thing I had to fix was I wasn't using a boolean flag, This was an easy fix although I did have to look up the correct header to use the var 'bool'

##Sources used

   - For this project I used the files provided and the wiki page on Circular buffer that was provided on the project page.
   - The only other outside Source I used was a stack overflow page on declaring the boolean flag becuase I couldn't remember
     the header, This is found here: http://stackoverflow.com/questions/1921539/using-boolean-values-in-c

