package hva.employee;

import java.util.TreeMap;
import hva.habitat.Habitat;

/**
 * Represents a caretaker responsible for cleaning specific habitats.
 */
public class Trt extends Employee {
    private TreeMap<String, Habitat> habitats;
    private int nresponsabilities; // A set of habitat IDs that this keeper can clean.

    /**
     * Calls the constructor of the Employee class to initialize the keeper's attributes.
     *
     * @param id   the unique identifier for the veterinarian
     * @param name the name of the veterinarian
     */
    public Trt(String id, String name) {
        super(id, name, "TRT"); 
        this.habitats = new TreeMap<>();
        this.nresponsabilities = 0; // Initializes the habitatIds set
    }
    public TreeMap<String, Habitat> getHabitats(){
        return habitats;
    }

    /**
     * Checks if the caretaker has any current responsibilities.
     * 
     * @return true if the caretaker is responsible for at least one habitat, false otherwise.
     */
    public boolean hasResponsibilities(){
        if (nresponsabilities>0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Retrieves a comma-separated string of habitat IDs for which the caretaker is responsible.
     * 
     * @return a string of habitat IDs, separated by commas, or an empty string if there are no responsibilities.
     */
    public String getResponsibilities() {
        StringBuilder responsibilities = new StringBuilder();
        for (Habitat h : habitats.values()) {
            if (responsibilities.length() > 0) {
                responsibilities.append(","); // Add a comma before the next ID if not the first
            }
            responsibilities.append(h.getId()); // Append the species ID
        }
        return responsibilities.toString(); // Return the constructed string
    }

    /**
     * Checks if the caretaker is responsible for a specific habitat.
     * 
     * @param responsibilityId the ID of the habitat to check
     * @return true if the caretaker is responsible for the habitat with the given ID, false otherwise.
     */
    public boolean hasResponsibility(String responsibilityId) {
        return habitats.containsKey(responsibilityId); // Verifica se o tratador já limpa o habitat
    }

    /**
     * Adds a new habitat responsibility for the caretaker.
     *
     * @param habitatId the ID of the habitat to add as a responsibility
     * @param h         the Habitat object to assign to the caretaker
     */
    public void addResponsibility(String habitatId, Habitat h) {
        habitats.put(habitatId, h );
        this.nresponsabilities++; 
    }

    /**
     * Removes a habitat responsibility from the caretaker.
     *
     * @param habitatId the ID of the habitat to remove from the responsibilities
     */
    public void removeResponsibility(String habitatId ) {
        habitats.remove(habitatId);
        this.nresponsabilities--; 
    }
}