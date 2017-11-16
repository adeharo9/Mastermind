# DIRECTORIES
BIN_DIR = ./bin
SRC_DIR = ./src
TST_DIR = testing

# EXTENSIONS
BIN_EXT = .class
SRC_EXT = .java
TST_EXT = .class

# FILES
BIN_FILES_AUX = $(CLASSES:[FILEPATH]%=$(BIN_DIR)%)
BIN_FILES = $(BIN_FILES_AUX:.[EXT]=$(BIN_EXT))

SRC_FILES_AUX = $(CLASSES:[FILEPATH]%=$(SRC_DIR)%)
SRC_FILES = $(SRC_FILES_AUX:.[EXT]=$(SRC_EXT))

TST_FILES_AUX = $(TESTS:[FILEPATH]%=$(TST_DIR)%)
TST_FILES = $(TST_FILES_AUX:.[EXT]=)

MAIN_CLASS = Mastermind
JAR_FILE = Mastermind.jar

# EXECUTABLES
JC = javac
JAR = jar

# OPTIONS
JFLAGS = -cp $(SRC_DIR) -d $(BIN_DIR) -g
JARFLAGS = cvfe

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
	[FILEPATH]/testing/stubs/Player.[EXT] \
	[FILEPATH]/util/Constants.[EXT] \
	[FILEPATH]/util/DeepCopyable.[EXT] \
	[FILEPATH]/util/Input.[EXT] \
	[FILEPATH]/util/ioUtils.[EXT] \
	[FILEPATH]/util/Pair.[EXT] \
	[FILEPATH]/util/Translate.[EXT] \
	[FILEPATH]/util/Utils.[EXT] \
	[FILEPATH]/Mastermind.[EXT]

TESTS = \
	[FILEPATH]/drivers/DriverBoard.[EXT] \
	[FILEPATH]/drivers/DriverCode.[EXT] \
	[FILEPATH]/drivers/DriverColor.[EXT] \
	[FILEPATH]/drivers/DriverCPU.[EXT] \
	[FILEPATH]/drivers/DriverCPUController.[EXT] \
	[FILEPATH]/drivers/DriverGame.[EXT] \
	[FILEPATH]/drivers/DriverGameController.[EXT] \
	[FILEPATH]/drivers/DriverGamePersistence.[EXT] \
	[FILEPATH]/drivers/DriverPlayerPersistence.[EXT] \
	[FILEPATH]/drivers/DriverPresentationBoard.[EXT] \
	[FILEPATH]/drivers/DriverRanking.[EXT] \
	[FILEPATH]/drivers/DriverRankingPersistence.[EXT] \
	[FILEPATH]/stubs/Player.[EXT]

$(BIN_FILES): | $(BIN_DIR)
	$(JC) $(JFLAGS) $(SRC_FILES)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)
	
$(JAR_FILE): $(BIN_FILES)
	$(JAR) $(JARFLAGS) $(JAR_FILE) $(MAIN_CLASS) -C $(BIN_DIR) .

all: jar

default: $(BIN_FILES)

clean:
	$(RM)r $(BIN_DIR)
	$(RM) $(JAR_FILE)

jar: $(JAR_FILE)

run: $(BIN_FILES)
	java -cp $(BIN_DIR) $(MAIN_CLASS)
	
#run-tests: $(BIN_FILES)
#	java -cp $(BIN_DIR) $(TST_FILES)
