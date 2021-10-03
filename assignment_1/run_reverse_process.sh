#!/bin/sh

send_response () {
  echo "Content-type: text/plain; charset=iso-8859-1"
  echo "Content-Length: ${#1}"
  echo
  echo $1
}


# Check if the request method is GET
if [ $REQUEST_METHOD == "GET" ]; then
  # Check if query is given
  if [ ! $QUERY_STRING ]; then
    send_response "No query given"
  else
    # Parse query
    saveIFS=$IFS
    IFS='=&'
    params=($QUERY_STRING)
    IFS=$saveIFS
    STRING_TO_REVERSE=${params[1]}

    # Check if the given parameter is valid
    if [ ! $STRING_TO_REVERSE ]; then
      send_response "No valid string to reverse is given"
    else
      REVERSE_JAVA_ARTIFACT="/Users/marcel/workspace/web-architectures/assignment_1/MiniHTTPD/jars/StringReverser.jar"
      REVERSED_STRING=$(java -jar $REVERSE_JAVA_ARTIFACT $STRING_TO_REVERSE)

      send_response $REVERSED_STRING
    fi
  fi
else
  send_response "Only GET requests are allowed"
fi
