#!/bin/bash

cd ../../../old/
make jar
cat ./test/testgames/input/PlayCPUvsCPUMediumGameTG.tg | java -jar ./Mastermind.jar
cd test/testgames/scripts
