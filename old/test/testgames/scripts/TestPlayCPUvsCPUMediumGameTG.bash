#!/bin/bash

cd ../../../
make jar
cat ./test/testgames/input/PlayCPUvsCPUMediumGameTG.tg | java -jar ./Mastermind.jar
cd test/testgames/scripts
