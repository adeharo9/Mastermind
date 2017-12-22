package presentation.runnables;

import presentation.controllers.PresentationController;

import java.util.List;

public class ShowLoadedGamesRunnable implements Runnable
{
    private PresentationController presentationController;
    private List<String> savedGames;

    public ShowLoadedGamesRunnable(final PresentationController presentationController, final List<String> savedGames)
    {
        this.presentationController = presentationController;
        this.savedGames = savedGames;
    }

    public void run()
    {
        presentationController.showLoadedGames(savedGames);
    }
}
