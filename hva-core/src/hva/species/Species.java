package hva.species;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap; // Necessary import for serialization

/**
 * Species represents an animal species in the hotel application.
 * It implements Serializable for object serialization.
 */
public class Species implements Serializable {
    private static final long serialVersionUID = 20231006L; // Recommended version ID for serialization
    // Species attributes
    private String id;
    private String name;
    private TreeMap<String, String> habitatinfluence;
    private List<String> vaccines;

    /**
     * Constructor to initialize the species attributes
     *
     * @param id   the unique identifier for the species
     * @param name the name of the species
     */
    public Species(String id, String name) {
        this.id = id;
        this.name = name;
        this.habitatinfluence = new TreeMap<>();;
        this.vaccines = new ArrayList<>();
    }

    /**
     * Getters to return the species' data
     *
     * @return the identifier and name of the species
     */
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setInfluence(String influence, String habitat){
        habitatinfluence.put(habitat, influence);
    }

    public String getInfluence(String idHabitat){
        if (!habitatinfluence.containsKey(idHabitat)){
            return "NEU";
        }
        else{return habitatinfluence.get(idHabitat);
        }
    }

    /**
     * Adds a vaccine to the species' list of vaccines
     *
     * @param v the vaccine to be added
     */
    public void addVaccine(String v){
        vaccines.add(v);
    }

    /**
     * Checks if the species has a specific vaccine.
     *
     * @param v the vaccine to check for
     * @return true if the species has the vaccine, false otherwise
     */
    public boolean hasVaccine(String v){
        return vaccines.contains(v); 
    }
}

