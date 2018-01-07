#!/bin/bash

cd ../../../
make jar
cat ./test/testgames/input/GenerateExamplesTG.tg | java -jar ./Mastermind.jar
cd ./test/testgames/scripts
