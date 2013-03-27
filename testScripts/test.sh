#!/bin/bash

#Test the return value from java -jar and if it is bad do something. Else append output. 
#Append each term run to output file for easy running. 
#Find a way to return only the exceptions as a report. 
for i in {1..10};
do
    java -jar ../dist/UnoClone.jar 2>testResult.txt;
done