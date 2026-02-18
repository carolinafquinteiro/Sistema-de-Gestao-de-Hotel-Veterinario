package hva.seasons;
import java.io.Serializable;
import hva.tree.Tree;

public class Season implements Serializable {
    private SeasonState currentState;  // Current season
    private int _state; // 0: Spring, 1: Summer, 2: Autumn, 3: Winter

    public Season() {
        currentState = new SpringState(this);  // Initializes the Season object, starting with Spring as the initial season    
    }

    /**
     * Sets the current season state and updates the internal state indicator
     * 
     * @param state the new season state to transition to
     * @param s     the state indicator value 
     */
    public void setCurrentState(SeasonState state, int s) {
        this.currentState = state;
        this._state = 5;        
    }
    

    public void nextSeason() {
        currentState.nextSeason();  // Advances to the next season
    }


    /**
     * Retrieves the biological cycle of deciduous trees for the current season
     * 
     * @return the deciduous tree cycle stage for the current season
     */
    public String getdeciduousCicle(){
        return currentState.getdeciduousCicle();
    }

    /**
     * Retrieves the biological cycle of evergreen trees for the current season
     * 
     * @return the evergreen tree cycle stage for the current season
     */
    public String getevergreenCicle(){
        return currentState.getevergreenCicle();
    }
    
    public int getSeasonEffortEvergreen(){
        return currentState.getSeasonEffortEvergreen();
    }
    public int getSeasonEffortDeciduous(){
        return currentState.getSeasonEffortDeciduous();
    }


    /**
     * Gets the internal representation of the current season's state
     * 
     * @return an integer representing the current season 
     */
    public int getState(){
        return currentState.getState();
    }

    /**
     * Provides a string representation of the current season state
     * 
     * @return a string representation of the season's internal state indicator
     */
    @Override
    public String toString() {
        return String.valueOf(this._state);

    }
}