# DIRECTORIES
BIN_DIR = ./bin
SRC_DIR = ./src
DOC_DIR = ./docs
TST_DIR = ./test
DRIVER_DIR = testing
JUNIT_DIR = testing

SRC_SOURCEPATH = $(SRC_DIR)
SRC_CLASSPATH = test/hamcrest-core-1.3.jar:test/junit-4.12.jar
BIN_CLASSPATH =$(BIN_DIR):$(SRC_CLASSPATH)

# EXTENSIONS
BIN_EXT = .class
SRC_EXT = .java
DRIVER_EXT =
JUNIT_EXT =
JAR_EXT = .jar
ZIP_EXT = .zip

# FILES
BIN_FILES_AUX = $(CLASSES:[FILEPATH]%=$(BIN_DIR)%)
BIN_FILES = $(BIN_FILES_AUX:.[EXT]=$(BIN_EXT))

SRC_FILES_AUX = $(CLASSES:[FILEPATH]%=$(SRC_DIR)%)
SRC_FILES = $(SRC_FILES_AUX:.[EXT]=$(SRC_EXT))

DRIVER_FILES_AUX = $(DRIVERS:[FILEPATH]%=$(DRIVER_DIR)%)
DRIVER_FILES = $(DRIVER_FILES_AUX:.[EXT]=$(DRIVER_EXT))

JUNIT_FILES_AUX = $(JUNITS:[FILEPATH]%=$(JUNIT_DIR)%)
JUNIT_FILES = $(JUNIT_FILES_AUX:.[EXT]=$(JUNIT_EXT))

MAIN_CLASS = Mastermind
JAR_FILE = Mastermind$(JAR_EXT)
ZIP_FILE = PROP_23.63$(ZIP_EXT)

# EXECUTABLES
JC = javac
JAR = jar
JV = java
ZIP = zip

# OPTIONS
JFLAGS = -sourcepath $(SRC_SOURCEPATH) -classpath $(SRC_CLASSPATH) -d $(BIN_DIR) -g
JARFLAGS = cvfe
ZOPTIONS = -r

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
	[FILEPATH]/enums/PlayingAction.[EXT] \
	[FILEPATH]/enums/PopUpWindowStyle.[EXT] \
	[FILEPATH]/enums/Role.[EXT] \
	[FILEPATH]/enums/State.[EXT] \
	[FILEPATH]/enums/StyleClass.[EXT] \
	[FILEPATH]/enums/View.[EXT] \
	[FILEPATH]/exceptions/AbstractException.[EXT] \
	[FILEPATH]/exceptions/GameNotStartedException.[EXT] \
	[FILEPATH]/exceptions/IllegalActionException.[EXT] \
	[FILEPATH]/exceptions/PasswordMismatchException.[EXT] \
	[FILEPATH]/exceptions/ReservedKeywordException.[EXT] \
	[FILEPATH]/exceptions/WrongPasswordException.[EXT] \
	[FILEPATH]/persistence/AbstractPersistence.[EXT] \
	[FILEPATH]/persistence/GamePersistence.[EXT] \
	[FILEPATH]/persistence/PlayerPersistence.[EXT] \
	[FILEPATH]/persistence/RankingPersistence.[EXT] \
	[FILEPATH]/presentation/controllers/CloseProgramWarningViewController.[EXT] \
	[FILEPATH]/presentation/controllers/EditUserViewController.[EXT] \
	[FILEPATH]/presentation/controllers/ErrorMessageWarningViewController.[EXT] \
	[FILEPATH]/presentation/controllers/ExitCurrentGameWarningViewController.[EXT] \
	[FILEPATH]/presentation/controllers/GameInProgressViewController.[EXT] \
	[FILEPATH]/presentation/controllers/GameOverViewController.[EXT] \
	[FILEPATH]/presentation/controllers/HintViewController.[EXT] \
	[FILEPATH]/presentation/controllers/InfoViewController.[EXT] \
	[FILEPATH]/presentation/controllers/InitSessionViewController.[EXT] \
	[FILEPATH]/presentation/controllers/LoadGameViewController.[EXT] \
	[FILEPATH]/presentation/controllers/LoadingViewController.[EXT] \
	[FILEPATH]/presentation/controllers/LogInViewController.[EXT] \
	[FILEPATH]/presentation/controllers/LogOutWarningViewController.[EXT] \
	[FILEPATH]/presentation/controllers/MainMenuViewController.[EXT] \
	[FILEPATH]/presentation/controllers/NewGameViewController.[EXT] \
	[FILEPATH]/presentation/controllers/NonRegisteringPresentationController.[EXT] \
	[FILEPATH]/presentation/controllers/PauseViewController.[EXT] \
	[FILEPATH]/presentation/controllers/PopUpController.[EXT] \
	[FILEPATH]/presentation/controllers/PresentationController.[EXT] \
	[FILEPATH]/presentation/controllers/RankingViewController.[EXT] \
	[FILEPATH]/presentation/controllers/RegisteringPresentationController.[EXT] \
	[FILEPATH]/presentation/controllers/RegisterViewController.[EXT] \
	[FILEPATH]/presentation/controllers/SaveGameOverwriteViewController.[EXT] \
	[FILEPATH]/presentation/controllers/SaveGameViewController.[EXT] \
	[FILEPATH]/presentation/controllers/ShowCodeViewController.[EXT] \
	[FILEPATH]/presentation/runnables/LogicThreadRunnable.[EXT] \
	[FILEPATH]/presentation/runnables/PopUpViewRunnable.[EXT] \
	[FILEPATH]/presentation/runnables/ProcessInfoRunnable.[EXT] \
	[FILEPATH]/presentation/runnables/RenderBoardRunnable.[EXT] \
	[FILEPATH]/presentation/runnables/RenderLastTurnRunnable.[EXT] \
	[FILEPATH]/presentation/runnables/ShowLoadedGamesRunnable.[EXT] \
	[FILEPATH]/presentation/runnables/UpdateViewRunnable.[EXT] \
	[FILEPATH]/testing/drivers/DriverBoard.[EXT] \
	[FILEPATH]/testing/drivers/DriverCode.[EXT] \
	[FILEPATH]/testing/drivers/DriverColor.[EXT] \
	[FILEPATH]/testing/drivers/DriverCPU.[EXT] \
	[FILEPATH]/testing/drivers/DriverCPUController.[EXT] \
	[FILEPATH]/testing/drivers/DriverGame.[EXT] \
	[FILEPATH]/testing/drivers/DriverGameController.[EXT] \
	[FILEPATH]/testing/drivers/DriverGamePersistence.[EXT] \
	[FILEPATH]/testing/drivers/DriverHuman.[EXT] \
	[FILEPATH]/testing/drivers/DriverPlayerPersistence.[EXT] \
	[FILEPATH]/testing/drivers/DriverPresentationBoard.[EXT] \
	[FILEPATH]/testing/drivers/DriverRanking.[EXT] \
	[FILEPATH]/testing/drivers/DriverRankingPersistence.[EXT] \
	[FILEPATH]/testing/drivers/DriverRenames.[EXT] \
	[FILEPATH]/testing/drivers/DriverTurn.[EXT] \
	[FILEPATH]/testing/drivers/JUnitDriverDomainController.[EXT] \
	[FILEPATH]/testing/drivers/JUnitDriverPlayerController.[EXT] \
	[FILEPATH]/testing/stubs/StubBoard.[EXT] \
	[FILEPATH]/testing/stubs/StubBoardController.[EXT] \
	[FILEPATH]/testing/stubs/StubGame.[EXT] \
	[FILEPATH]/testing/stubs/StubGameController.[EXT] \
	[FILEPATH]/testing/stubs/StubGamePersistence.[EXT] \
	[FILEPATH]/testing/stubs/StubPlayer.[EXT] \
	[FILEPATH]/testing/stubs/StubPlayerController.[EXT] \
	[FILEPATH]/testing/stubs/StubPlayerPersistence.[EXT] \
	[FILEPATH]/testing/stubs/StubPresentationController.[EXT] \
	[FILEPATH]/testing/stubs/StubTurn.[EXT] \
	[FILEPATH]/util/Constants.[EXT] \
	[FILEPATH]/util/DeepCopyable.[EXT] \
	[FILEPATH]/util/Input.[EXT] \
	[FILEPATH]/util/ioUtils.[EXT] \
	[FILEPATH]/util/Pair.[EXT] \
	[FILEPATH]/util/Translate.[EXT] \
	[FILEPATH]/util/UncheckedCast.[EXT] \
	[FILEPATH]/util/Utils.[EXT] \
	[FILEPATH]/Mastermind.[EXT]

DRIVERS = \
	[FILEPATH]/drivers/DriverBoard.[EXT] \
	[FILEPATH]/drivers/DriverCode.[EXT] \
	[FILEPATH]/drivers/DriverColor.[EXT] \
	[FILEPATH]/drivers/DriverCPU.[EXT] \
	[FILEPATH]/drivers/DriverCPUController.[EXT] \
	[FILEPATH]/drivers/DriverGame.[EXT] \
	[FILEPATH]/drivers/DriverGameController.[EXT] \
	[FILEPATH]/drivers/DriverGamePersistence.[EXT] \
	[FILEPATH]/drivers/DriverHuman.[EXT] \
	[FILEPATH]/drivers/DriverPlayerPersistence.[EXT] \
	[FILEPATH]/drivers/DriverPresentationBoard.[EXT] \
	[FILEPATH]/drivers/DriverRanking.[EXT] \
	[FILEPATH]/drivers/DriverRankingPersistence.[EXT] \
	[FILEPATH]/drivers/DriverRenames.[EXT] \
	[FILEPATH]/drivers/DriverTurn.[EXT]

JUNITS = \
	[FILEPATH]/drivers/JUnitDriverDomainController.[EXT] \
	[FILEPATH]/drivers/JUnitDriverPlayerController.[EXT]

$(BIN_FILES): | $(BIN_DIR)
	$(JC) $(JFLAGS) $(SRC_FILES)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)

$(JAR_FILE): $(BIN_FILES)
	$(JAR) $(JARFLAGS) $(JAR_FILE) $(MAIN_CLASS) -C $(BIN_DIR) .

all: zip jar

default: $(BIN_FILES)

clean:
	$(RM)r $(BIN_DIR)
	$(RM) $(JAR_FILE)
	$(RM) $(ZIP_FILE)

examples:
	$(shell cd ./test/testgames/scripts;./TestGenerateExamplesTG.bash > /dev/null 2>&1)

jar: $(JAR_FILE)

run: $(BIN_FILES)
	$(JV) -cp $(BIN_CLASSPATH) $(MAIN_CLASS)

run-drivers: $(BIN_FILES)
	$(foreach DRIVER_FILE, $(DRIVER_FILES), $(JV) -cp $(BIN_CLASSPATH) $(DRIVER_FILE);)

run-junits: $(BIN_FILES)
	$(foreach JUNIT_FILE, $(JUNIT_FILES), $(JV) -cp $(BIN_CLASSPATH) $(JUNIT_FILE);)

run-tests: run-drivers run-junits

$(ZIP):
	$(ZIP) $(ZOPTIONS) $(ZIP_FILE) $(DOC_DIR) $(SRC_DIR) $(TST_DIR) Makefile
