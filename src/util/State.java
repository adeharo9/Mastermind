package util;

public enum State
{
    askForClue,

    checkInfo,
    checkRanking,

    endProgram,
    exitGameWithoutSaving,

    gameDifficultySelection,
    gameModeSelection,
    gamePause,
    gameSelection,

    initProgram,
    initSession,

    loadGame,
    logInUser,

    newGame,

    playSelection,
    playTurn,

    registerUser,

    saveGameAndContinue,
    saveGameAndExit;

    public static boolean isValid(State state)
    {
        return state != null;
    }
}