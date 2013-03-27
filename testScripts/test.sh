#!/bin/bash

for i in {1..10};
do
    java -jar ../dist/UnoClone.jar 2>testResult.txt;
done