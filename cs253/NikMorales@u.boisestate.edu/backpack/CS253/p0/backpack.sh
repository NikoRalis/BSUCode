#!/bin/bash
if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save the grades in"
  exit 0;
fi

dest="$1"
exe=hello

#Make sure the program executable exists and is called hello
if [ ! -e "$exe" ];then
  echo "HW1: FAIL reason: $exe executable not found" >> $dest
  exit 1 # fail the build
fi

#Run the executable
./$exe

if [ $? -eq 0 ];then
  echo "HW1: PASS (don't forget to delete this file and the executable before submitting)" >> $dest
else
  echo "HW1: FAIL reason: program exited with non-successful return value " >> $dest
  exit 1
fi
