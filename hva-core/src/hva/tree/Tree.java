package hva.tree;

import java.io.Serializable;

import java.lang.reflect.Constructor;

import java.lang.Math;

/**
 * Represents a tree in the hotel application
 */
public class Tree implements Serializable {
    // Tree attributes
    private String id;
    private String name;
    private int age;
    private int cleaningDifficulty; 
    private String type;
    private int nSeasons;
    private String cicle;
    private int seasonEffort;


    /**
     * Constructor to initialize the tree attributes
     *
     * @param id                the unique identifier for the tree
     * @param name              the name of the tree
     * @param age               the age of the tree
     * @param cleaningDifficulty the difficulty level of cleaning the tree
     * 
     */
    public Tree(String id, String name, int age, int cleaningDifficulty, String type, String cicle) { 
        this.id = id;
        this.name = name;
        this.age = age;
        this.cleaningDifficulty = cleaningDifficulty;  
        this.type = type;
        this.nSeasons = 0;
        this.cicle = cicle;
        this.seasonEffort = 0;
    }

    /**
     * Getters to return the tree's data
     *
     * @return the identifier, name, age and the difficulty level of cleaning the tree
     */
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getCleaningDifficulty() {
        return cleaningDifficulty;
    }

    public String getType(){
        return type;
    }
    public String getCicle(){
        return cicle;
    }

    public void setCicle(String s){
        cicle = s;
    }
    public void setSeasoneffort(int s ){
        seasonEffort = s;
    }

    /**
     * Checks if the tree is deciduous
     *
     * @return true if the tree is deciduous, false otherwise
     */
    public boolean isDeciduous() {
        return type.equals("deciduous");
    }



    /**
     * Calculates the cleaning effort based on the current season and tree age.
     *
     * @param seasonalEffort the effort based on the current season
     * @return the total cleaning effort
     */
    public double calculateCleaningEffort() {
        return cleaningDifficulty * seasonEffort * Math.log(age + 1); // Effort based on the tree's age
    }
    
    
    public void incrementsforSeason() { //Increments the age of the tree
        nSeasons++;
        updateage();
    }
    public void updateage(){
        if (nSeasons==4){
            age +=1;
            nSeasons = 0;
        }

    }

    /**
     * Setters to modify the tree's data
     *
     * @param id                 the new id for the tree
     * @param name               the new name for the tree
     * @param age                the new age for the tree
     * @param cleaningDifficulty the new difficulty level for cleaning the tree
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets a new difficulty level for cleaning the tree
     *
     * @param cleaningDifficulty the new difficulty level for cleaning the tree
     */
    public void setCleaningDifficulty(int cleaningDifficulty) {
        this.cleaningDifficulty = cleaningDifficulty; 
    }
}
