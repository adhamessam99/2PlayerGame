#!/bin/bash

# Compile the project
mvn clean compile

# Run the Main class
java -cp target/classes org.example.Main
