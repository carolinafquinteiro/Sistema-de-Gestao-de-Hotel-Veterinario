package hva.vaccine;

import java.io.Serializable;
import java.util.TreeMap;

import hva.species.Species;

/**
 * Represents a vaccine in the hotel application
 */
public class Vaccine implements Serializable{
    // Vaccine attributes
    private String id;
    private String name;
    private TreeMap<String, Species> species;
    private int timesAdministered; // Counts the times the vaccine was administered

    /**
     * Constructor to initialize the vaccine attributes
     *
     * @param id         the unique identifier for the vaccine
     * @param name       the name of the vaccine
     * @param speciesIds the array of species IDs associated with the vaccine
     */
    public Vaccine(String id, String name, TreeMap<String, Species> species) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.timesAdministered = 0; // Initializes counter equal to 0
    }

    /**
     * Getters to return the vaccine's data
     *
     * @return the identifier, name, speciesID and the times the vaccine was administrated
     */
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TreeMap<String, Species> getspecies () {
        return species;
    }

    /**
     * Constructs a string of species IDs associated with the vaccine.
     *
     * @return a comma-separated string of species IDs
     */
    public String getIds() {
        StringBuilder ids = new StringBuilder();
        for (Species spec : species.values()) {
            if (ids.length() > 0) {
                ids.append(","); // Add a comma before the next ID if not the first
            }
            ids.append(spec.getId()); // Append the species ID
        }
        return ids.toString(); // Return the constructed string
    }

    public int getTimesAdministered() {
        return timesAdministered; // Obtains the number of applications
    }

    /**
     * Increments the application counter for the vaccine.
     */
    public void incrementTimesAdministered() {
        this.timesAdministered++;  
    }

    /**
     * Calculates the number of different characters between two strings.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the count of different characters between the two strings
     */
    public int calculatediferentcarachters(String s1, String s2){   
        int c = 0; 
        for (int i = 0; i < s1.length(); i++) {
            char letter = s1.charAt(i);

                // Check if the character is present in the second string and hasn't been counted yet
                if (s2.indexOf(letter) != -1) {
                    c++;
                    
                    // Remove the matched character from the second string to avoid duplicate counting
                    s2 = s2.replaceFirst(String.valueOf(letter), "");
                }
            }
    return c;
    }
        
    /**
     * Calculates the damage value based on the species' name and its relation to the vaccine's species
     *
     * @param specie the Species object for which to calculate the damage
     * @return the calculated damage value, or -1 if the species is already associated with the vaccine
     */
    public int calculatedamage(Species specie){
        String id = specie.getId();
        if (species.containsKey(id.toUpperCase())){
            return -1;
        }
        int specie_length = 0;
        String max = "";

        for (Species s : species.values()){
            if (s.getName().length() > specie_length){
                specie_length = s.getName().length();
                max = s.getName();
            }
        }
        if (id.length()>specie_length){
            specie_length = id.length();
        }
        int equals = calculatediferentcarachters(max, specie.getName());
       return specie_length-equals;
    }
}

