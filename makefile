BIN_PATH = ./bin
SRC_PATH = ./src

SRC_EXT = .java
BIN_EXT = .class

SRC_FILES_AUX = $(CLASSES:[FILEPATH]%=$(SRC_PATH)%)
SRC_FILES = $(SRC_FILES_AUX:.[EXT]=$(SRC_EXT))

JC = javac
JFLAGS = -cp $(SRC_PATH) -d $(BIN_PATH) -g

JAR = jar
JARFLAGS = cvfe
MAIN_CLASS = Mastermind
JAR_NAME = Mastermind.jar

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	[FILEPATH]/domain/classes/Action.[EXT] \
	[FILEPATH]/domain/classes/Board.[EXT] \
	[FILEPATH]/domain/classes/Code.[EXT] \
	[FILEPATH]/domain/classes/CodeBreak.[EXT] \
	[FILEPATH]/domain/classes/CodeCorrect.[EXT] \
	[FILEPATH]/domain/classes/CodeMake.[EXT] \
	[FILEPATH]/domain/classes/CPU.[EXT] \
	[FILEPATH]/domain/classes/Game.[EXT] \
	[FILEPATH]/domain/classes/Human.[EXT] \
	[FILEPATH]/domain/classes/Player.[EXT] \
	[FILEPATH]/domain/classes/Ranking.[EXT] \
	[FILEPATH]/domain/classes/Turn.[EXT] \
	[FILEPATH]/domain/controllers/BoardController.[EXT] \
	[FILEPATH]/domain/controllers/DomainController.[EXT] \
	[FILEPATH]/domain/controllers/GameController.[EXT] \
	[FILEPATH]/domain/controllers/PlayerController.[EXT] \
	[FILEPATH]/enums/Color.[EXT] \
	[FILEPATH]/enums/Difficulty.[EXT] \
	[FILEPATH]/enums/Mode.[EXT] \
	[FILEPATH]/enums/Role.[EXT] \
	[FILEPATH]/enums/State.[EXT] \
	[FILEPATH]/exceptions/AbstractException.[EXT] \
	[FILEPATH]/exceptions/GameNotStartedException.[EXT] \
	[FILEPATH]/exceptions/IllegalActionException.[EXT] \
	[FILEPATH]/exceptions/IntegrityCorruptionException.[EXT] \
	[FILEPATH]/exceptions/ReservedKeywordException.[EXT] \
	[FILEPATH]/exceptions/WrongPasswordException.[EXT] \
	[FILEPATH]/persistence/AbstractPersistence.[EXT] \
	[FILEPATH]/persistence/GamePersistence.[EXT] \
	[FILEPATH]/persistence/PlayerPersistence.[EXT] \
	[FILEPATH]/persistence/RankingPersistence.[EXT] \
	[FILEPATH]/presentation/controllers/PresentationController.[EXT] \
	[FILEPATH]/testing/drivers/DriverBoard.[EXT] \
	[FILEPATH]/testing/drivers/DriverCode.[EXT] \
	[FILEPATH]/testing/drivers/DriverColor.[EXT] \
	[FILEPATH]/testing/drivers/DriverCPU.[EXT] \
	[FILEPATH]/testing/drivers/DriverCPUController.[EXT] \
	[FILEPATH]/testing/drivers/DriverGame.[EXT] \
	[FILEPATH]/testing/drivers/DriverGameController.[EXT] \
	[FILEPATH]/testing/drivers/DriverGamePersistence.[EXT] \
	[FILEPATH]/testing/drivers/DriverPlayerPersistence.[EXT] \
	[FILEPATH]/testing/drivers/DriverPresentationBoard.[EXT] \
	[FILEPATH]/testing/drivers/DriverRanking.[EXT] \
	[FILEPATH]/testing/drivers/DriverRankingPersistence.[EXT] \
	[FILEPATH]/testing/drivers/DriverTurn.[EXT] \
	[FILEPATH]/testing/AbstractTesting.[EXT] \
	[FILEPATH]/util/Constants.[EXT] \
	[FILEPATH]/util/DeepCopyable.[EXT] \
	[FILEPATH]/util/Input.[EXT] \
	[FILEPATH]/util/ioUtils.[EXT] \
	[FILEPATH]/util/Pair.[EXT] \
	[FILEPATH]/util/Rule.[EXT] \
	[FILEPATH]/util/Translate.[EXT] \
	[FILEPATH]/util/Utils.[EXT] \
	[FILEPATH]/Mastermind.[EXT]

default: mkdir classes

classes: $(SRC_FILES:.java=.class)

clean:
	$(RM)r $(BIN_PATH)
	$(RM) Mastermind.jar
	mkdir -p $(BIN_PATH)

jar:
	$(JAR) $(JARFLAGS) $(JAR_NAME) $(MAIN_CLASS) -C $(BIN_PATH) .

mkdir:
	mkdir -p $(BIN_PATH)

run:
	java -cp $(BIN_PATH) $(MAIN_CLASS)
