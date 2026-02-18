package hva.animal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an animal in the hotel application
 */
public class Animal implements Serializable {
    // Animal attributes
    private String id;
    private String name;
    private String speciesId;
    private String habitatId;
    private List<String> healthcare;
    private boolean vacinated;

    /**
     * Constructor to initialize the attributes of the Animal
     *
     * @param id        the identifier of the animal
     * @param name      the name of the animal
     * @param speciesId the ID of the species the animal belongs to
     * @param habitatId the ID of the habitat where the animal lives
     * 
     */
    public Animal(String id, String name, String speciesId, String habitatId) {
        this.id = id;
        this.name = name;
        this.speciesId = speciesId;
        this.habitatId = habitatId;
        this.healthcare = new ArrayList<>();
    }

    /**
     * Getters to return the animal's data
     *
     * @return the identifier, name, species ID, and habitat ID of the animal
     */

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciesId() {
        return speciesId;
    }

    public String getHabitatId() {
        return habitatId;
    }

    /**
     * Setters for modifying the attributes of the animal
     *
     * @param id        the new identifier of the animal
     * @param name      the new name of the animal
     * @param speciesId the new species ID of the animal
     * @param habitatId the new habitat ID of the animal
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciesId(String speciesId) {
        this.speciesId = speciesId;
    }

    public void setHabitatId(String habitatId) {
        this.habitatId = habitatId;
    }

    /**
     * Adds a new healthcare record to the animal's healthcare history
     * and marks the animal as vaccinated if a healthcare entry is added
     *
     * @param health the new healthcare record to add
     */
    public void addHealthRegist(String health){
        this.healthcare.add(health);
        this.vacinated = true;
    }

    /**
     * Joins the elements of the healthcare list into a single string separated by commas
     * If the animal has been vaccinated, the healthcare records are returned; 
     * otherwise, "VOID" is returned indicating no healthcare records.
     *
     * @return a string of healthcare records or "VOID" if no records exist
     */
    public String getHealthcare() {
        // Join the elements of the healthcare list into a single string separated by commas
        if (vacinated){
            return String.join(",", healthcare);
        }
        else{return "VOID";}
    }

}
