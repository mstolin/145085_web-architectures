#!/bin/sh
echo "Content-type: text/plain; charset=iso-8859-1"
echo

REVERSE_JAVA_CLASS_PATH=/Users/marcel/workspace/web-architectures/assignment_1/StringReverser/out/production/StringReverser
REVERSE_JAVA_CLASS=it.unitn.disi.webarch.assignment1.StringReverser

echo $REQUEST_METHOD
echo
echo $QUERY_STRING
echo
echo $(whoami) # <---- WEGEN DEBUG
echo

REVERSED_STRING=$(java -cp $REVERSE_JAVA_CLASS_PATH $REVERSE_JAVA_CLASS $QUERY_STRING)

echo $REVERSED_STRING


