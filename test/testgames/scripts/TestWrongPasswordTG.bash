#!/bin/bash

cd ../../../old/
make jar
cat ./test/testgames/input/WrongPasswordTG.tg | java -jar ./Mastermind.jar
cd test/testgames/scripts
