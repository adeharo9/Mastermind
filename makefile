JFLAGS = -cp ./src -d ./bin -g
JC = ../jdk1.8.0_151/bin/javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	./src/domain/classes/Action.java \
	./src/domain/classes/Board.java \
	./src/domain/classes/Code.java \
	./src/domain/classes/CodeBreak.java \
	./src/domain/classes/CodeCorrect.java \
	./src/domain/classes/CodeMake.java \
	./src/domain/classes/CPU.java \
	./src/domain/classes/Game.java \
	./src/domain/classes/Human.java \
	./src/domain/classes/Player.java \
	./src/domain/classes/Ranking.java \
	./src/domain/classes/Turn.java \
	./src/domain/controllers/BoardController.java \
	./src/domain/controllers/DomainController.java \
	./src/domain/controllers/GameController.java \
	./src/domain/controllers/PlayerController.java \
	./src/enums/Color.java \
	./src/enums/Difficulty.java \
	./src/enums/Mode.java \
	./src/enums/Role.java \
	./src/enums/State.java \
	./src/exceptions/AbstractException.java \
	./src/exceptions/GameNotStartedException.java \
	./src/exceptions/IllegalActionException.java \
	./src/exceptions/IntegrityCorruptionException.java \
	./src/exceptions/ReservedKeywordException.java \
	./src/exceptions/WrongPasswordException.java \
	./src/persistence/AbstractPersistence.java \
	./src/persistence/GamePersistence.java \
	./src/persistence/PlayerPersistence.java \
	./src/persistence/RankingPersistence.java \
	./src/presentation/controllers/PresentationController.java \
	./src/testing/drivers/DriverBoard.java \
	./src/testing/drivers/DriverCode.java \
	./src/testing/drivers/DriverColor.java \
	./src/testing/drivers/DriverCPU.java \
	./src/testing/drivers/DriverCPUController.java \
	./src/testing/drivers/DriverGame.java \
	./src/testing/drivers/DriverGameController.java \
	./src/testing/drivers/DriverGamePersistence.java \
	./src/testing/drivers/DriverPlayerPersistence.java \
	./src/testing/drivers/DriverPresentationBoard.java \
	./src/testing/drivers/DriverRanking.java \
	./src/testing/drivers/DriverRankingPersistence.java \
	./src/testing/drivers/DriverTurn.java \
	./src/testing/AbstractTesting.java \
	./src/util/Constants.java \
	./src/util/DeepCopyable.java \
	./src/util/Input.java \
	./src/util/ioUtils.java \
	./src/util/Pair.java \
	./src/util/Rule.java \
	./src/util/Translate.java \
	./src/util/Utils.java \
	./src/Mastermind.java

default: mkdir classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM)r ./bin
	mkdir -p ./bin

jar:
	jar cvfe Mastermind.jar Mastermind $(CLASSES:.java=.class)

mkdir:
	mkdir -p ./bin

run:
	java -cp ./bin Mastermind
