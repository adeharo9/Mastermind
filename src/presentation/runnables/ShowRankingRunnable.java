package presentation.runnables;

import presentation.controllers.PresentationController;
import util.Pair;

import java.util.List;

public class ShowRankingRunnable implements Runnable
{
    private PresentationController presentationController;
    private List<Pair<String, Integer>> topTen;

    public ShowRankingRunnable(final PresentationController presentationController, final List<Pair<String, Integer>> topTen)
    {
        this.presentationController = presentationController;
        this.topTen = topTen;
    }

    public void run()
    {
        presentationController.showRanking(topTen);
    }
}
