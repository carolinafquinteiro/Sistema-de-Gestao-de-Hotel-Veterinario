
package hva.seasons;

import hva.tree.Tree;

public class SpringState extends SeasonState {

    // Constructs a SpringState object with the specified season
    public SpringState(Season season) {
        super(season, 1, 1, "GERARFOLHAS", "GERARFOLHAS"); // 1 for deciduous, 1 for evergreen
    }

    
    public void nextSeason() {
        getSeason().setCurrentState(new SummerState(getSeason()), 1);  // Advances to Summer
    }
        
    

    /**
     * Retrieves the internal representation of the current season state
     *
     * @return an integer indicating the current state, which is 0 for spring
     */
    public int getState(){
        return 0;
    }
}