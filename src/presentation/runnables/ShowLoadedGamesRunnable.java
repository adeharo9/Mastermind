package presentation.runnables;

import presentation.controllers.PresentationController;

import java.util.Set;

public class ShowLoadedGamesRunnable implements Runnable
{
    private PresentationController presentationController;
    private Set<String> savedGames;

    public ShowLoadedGamesRunnable(final PresentationController presentationController, final Set<String> savedGames)
    {
        this.presentationController = presentationController;
        this.savedGames = savedGames;
    }

    public void run()
    {
        presentationController.showLoadedGames(savedGames);
    }
}
