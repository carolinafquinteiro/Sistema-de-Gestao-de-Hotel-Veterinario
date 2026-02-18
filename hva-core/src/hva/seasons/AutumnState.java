package hva.seasons;

import hva.tree.Tree;

public class AutumnState extends SeasonState {

    // Constructs an AutumnState object with the specified season
    public AutumnState(Season season) {
        super(season, 5, 1, "LARGARFOLHAS", "COMFOLHAS"); // 5 for deciduous, 1 for evergreen
    }

 
    public void nextSeason() {
        getSeason().setCurrentState(new WinterState(getSeason()), 3); // Advances to Winter
    }

    /**
     * Returns the state code for Autumn.
     * 
     * @return an integer representing the state of Autumn, here it returns 2.
     */
    public int getState(){
        return 2;
    }
}
