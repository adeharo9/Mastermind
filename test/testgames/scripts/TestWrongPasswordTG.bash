#!/bin/bash

cd ../../../
make jar
cat ./test/testgames/input/WrongPasswordTG.tg | java -jar ./Mastermind.jar
cd test/testgames/scripts
