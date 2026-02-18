package hva.seasons;

import hva.tree.Tree;

import java.io.Serializable;

public abstract class SeasonState implements Serializable{

    private int _deciduous, _evergreen;
    private String _dcicle, _ecicle;
    private Season _season;

    /**
     * Constructor to initialize the parameters of the SeasonState
     *
     * @param season     the season associated with this state
     * @param deciduous  the deciduous tree cycle type
     * @param evergreen   the evergreen tree cycle type
     * @param d          the string representation of the deciduous cycle
     * @param e          the string representation of the evergreen cycle
     */
    public SeasonState(Season season, int deciduous, int evergreen, String d, String e) {
        this._season = season;
        this._deciduous = deciduous;
        this._evergreen = evergreen;
        this._dcicle = d;
        this._ecicle = e;
    }

    /**
     * Retrieves the associated season for this state
     *
     * @return the Season object associated with this state
     */
    public Season getSeason() {
        return _season;
    }

    /**
     * Retrieves the deciduous tree cycle type
     *
     * @return an integer representing the type of deciduous tree cycle
     */
    public int getSeasonEffortDeciduous() {
        return _deciduous;
    }

    /**
     * Retrieves the evergreen tree cycle type
     *
     * @return an integer representing the type of evergreen tree cycle
     */    public int getSeasonEffortEvergreen() {
        return _evergreen;
    }

    /**
     * Retrieves the string representation of the deciduous tree cycle stage
     *
     * @return a string indicating the current deciduous tree cycle stage
     */
    public String getdeciduousCicle(){
        return _dcicle;
    }

    /**
     * Retrieves the string representation of the evergreen tree cycle stage.
     *
     * @return a string indicating the current evergreen tree cycle stage
     */
    public String getevergreenCicle(){
        return _ecicle;
    }

    public abstract void nextSeason(); // Advances to the next season

    // Abstract methods implemented in the subclasess
 
    public abstract int getState();


}
