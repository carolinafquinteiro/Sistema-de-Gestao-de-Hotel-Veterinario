package hva.seasons;

import hva.tree.Tree;


public class SummerState extends SeasonState {

    // Constructs a SummerState object with the specified season
    public SummerState(Season season) {
        super(season, 2, 1, "COMFOLHAS", "COMFOLHAS"); // 2 for deciduous, 1 for evergreen
    }

    @Override
    public void nextSeason() {
        getSeason().setCurrentState(new AutumnState(getSeason()), 2); // Advances to Autumn
    }

    

    /**
     * Retrieves the internal representation of the current season state.
     *
     * @return an integer indicating the current state, which is 1 for summer.
     */
    public int getState(){
        return 1;
    }
}