
package hva.employee;

import java.util.TreeMap;
import hva.species.Species;

/**
 * Represents a veterinarian who can treat specific species.
 */
public class Vet extends Employee {
    private TreeMap<String, Species> species; 
    private int nresponsabilities;// TreeMap to store species that the veterinarian can treat

    /**
     * Calls the constructor of the Employee class to initialize the veterinarian's attributes.
     *
     * @param id   the unique identifier for the veterinarian
     * @param name the name of the veterinarian
     */
    public Vet(String id, String name) {
        super(id, name, "VET"); 
        this.species = new TreeMap<>();
        this.nresponsabilities = 0;  // Initializes the species TreeMap
    }

    public TreeMap<String, Species> getSpecies() {
        return species;
    }

    /**
     * Returns the species that the veterinarian can treat
     *
     * @return a TreeMap of species where the key is the species ID and the value is the Species object
     */
    public String getSpeciesIds() {
        // Join the species IDs (keys) with commas
        return String.join(", ", species.keySet());
    }

    /**
     * Checks if the veterinarian has any current responsibilities
     *
     * @return true if the veterinarian is responsible for at least one species, false otherwise
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
        for (Species spec : species.values()) {
            if (responsibilities.length() > 0) {
                responsibilities.append(","); // Add a comma before the next ID if not the first
            }
            responsibilities.append(spec.getId()); // Append the species ID
        }
        return responsibilities.toString(); // Return the constructed string
    }

    /**
     * Checks if the veterinarian is responsible for a specific species
     * 
     * @param speciesId the ID of the species to check
     * @return true if the veterinarian is responsible for the species with the given ID, false otherwise.
     */
    public boolean hasResponsibility(String speciesId) {
        return species.containsKey(speciesId); // Checks if the veterinarian treats the species
    }

    /**
     * Adds a new species responsibility for the veterinarian
     *
     * @param speciesId   the ID of the species to add as a responsibility.
     * @param speciesObj  the Species object to assign to the veterinarian.
     */
    public void addResponsibility(String speciesId, Species speciesObj) {
        species.put(speciesId, speciesObj);
        this.nresponsabilities++; // Adds the responsibility to treat a species
    }

    /**
     * Removes a species responsibility from the veterinarian.
     *
     * @param speciesId the ID of the species to remove from the responsibilities.
     */
    public void removeResponsibility(String speciesId) {
        species.remove(speciesId); 
        this.nresponsabilities--;// Removes the responsibility to treat a species
    }
    
}
