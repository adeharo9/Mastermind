#!/bin/bash

cd ../../../
make jar
cat ./test/testgames/input/PlayerAlreadyExistsTG.tg | java -jar ./Mastermind.jar
cd test/testgames/scripts
