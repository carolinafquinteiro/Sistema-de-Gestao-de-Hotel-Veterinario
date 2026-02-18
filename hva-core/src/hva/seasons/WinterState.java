package hva.seasons;

import hva.tree.Tree;

public class WinterState extends SeasonState {

    // Constructs a WinterState object with the specified season
    public WinterState(Season season) {
        super(season, 0, 2, "SEMFOLHAS", "LARGARFOLHAS"); // 0 for deciduous, 2 for evergreen
    }

    @Override
    public void nextSeason() {
        getSeason().setCurrentState(new SpringState(getSeason()), 0); // Advances to Spring
    }

   

    /**
     * Retrieves the internal representation of the current season state
     *
     * @return an integer indicating the current state, which is 3 for winter
     */
    public int getState(){
        return 3;
    }
}
