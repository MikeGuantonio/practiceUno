#!/bin/bash

#Test the return value from java -jar and if it is bad do something. Else append output. 
#Append each term run to output file for easy running. 
#Find a way to return only the exceptions as a report.

uno_Run()
{
   java -jar ../dist/UnoClone.jar > runOutput/output$1.txt 2> exception/exceptionReport$1.txt;  
}

for i in {1..10};
do
    uno_Run $i &
done