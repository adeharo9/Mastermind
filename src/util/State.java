package util;

public enum State
{
    askForClue,

    checkInfo,
    checkRanking,
    continueGame,

    endProgram,
    exitGameWithoutSaving,

    gameDifficultySelection,
    gameModeSelection,
    gamePause,
    gameInProgressSelection,

    initProgram,
    initSession,

    loadGame,
    logInUser,

    newGame,

    playSelection,
    playTurn,

    registerUser,
    registerUserInput,

    saveGameAndContinue,
    saveGameAndExit;

    public static boolean isValid(State state)
    {
        return state != null;
    }
}