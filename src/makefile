JFLAGS = -d ../bin
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	./domain/classes/Action.java \
	./domain/classes/Board.java \
	./domain/classes/Code.java \
	./domain/classes/CodeBreak.java \
	./domain/classes/CodeCorrect.java \
	./domain/classes/CodeMake.java \
	./domain/classes/CPU.java \
	./domain/classes/Game.java \
	./domain/classes/Human.java \
	./domain/classes/Player.java \
	./domain/classes/Ranking.java \
	./domain/classes/Turn.java \
	./domain/controllers/BoardController.java \
	./domain/controllers/CPUController.java \
	./domain/controllers/DomainController.java \
	./domain/controllers/GameController.java \
	./domain/controllers/HumanController.java \
	./domain/controllers/PlayerController.java \
	./enums/Color.java \
	./enums/Difficulty.java \
	./enums/Mode.java \
	./enums/Role.java \
	./enums/State.java \
	./exceptions/AbstractException.java \
	./exceptions/IntegrityCorruption.java \
	./exceptions/ReservedKeywordException.java \
	./exceptions/WrongPassword.java \
	./persistence/AbstractPersistence.java \
	./persistence/GamePersistence.java \
	./persistence/PlayerPersistence.java \
	./persistence/RankingPersistence.java \
	./presentation/controllers/PresentationController.java \
	./testing/drivers/CodeDriver.java \
	./testing/drivers/DriverColor.java \
	./testing/drivers/DriverCPU.java \
	./testing/drivers/DriverCPUController.java \
	./testing/drivers/DriverGamePersistence.java \
	./testing/drivers/DriverPlayerPersistence.java \
	./testing/drivers/DriverPresentationBoard.java \
	./testing/drivers/DriverRanking.java \
	./testing/drivers/DriverRankingPersistence.java \
	./testing/drivers/DriverTurn.java \
	./testing/AbstractTesting.java \
	./util/Constants.java \
	./util/DeepCopyable.java \
	./util/Input.java \
	./util/ioUtils.java \
	./util/Pair.java \
	./util/Rule.java \
	./util/Translate.java \
	./util/Utils.java \
	./Mastermind.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM)r ../bin
	mkdir ../bin

jar:
	jar cvfe Mastermind.jar Mastermind $(CLASSES:.java=.class)

run:
	java -cp ../bin Mastermind
