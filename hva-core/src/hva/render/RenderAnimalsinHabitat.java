package hva.render;

import hva.Hotel;
import hva.HotelVisitor;
import hva.habitat.Habitat;
import hva.animal.Animal;

import java.util.Map;
import java.util.TreeMap;

/**
 * RenderAnimalSatisfaction is a class that implements the HotelVisitor interface.
 * It is responsible for generating a formatted string representation 
 * of a single animal's satisfaction in a hotel.
 */
public class RenderAnimalsinHabitat implements HotelVisitor {

    // StringBuilder to store the generated output as a string
    private StringBuilder output = new StringBuilder();
    private String targetHabitatId;

    /**
     * Constructor that accepts the target animal's ID.
     *
     * @param targetAnimalId The ID of the animal to be rendered.
     */
    public RenderAnimalsinHabitat( String targetHabitatId) {
        this.targetHabitatId = targetHabitatId;
    }

    /**
     * Visits a specific hotel and gathers satisfaction information about a specific animal.
     *
     * @param hotel The Hotel instance to be visited.
     */
    @Override
    public void visit(Hotel hotel) {
        // Se o hotel ou a lista de animais for nula, não faz nada
        if (hotel == null || hotel.getHabitats() == null) {

            return;
        }
    
        
        Map<String, Habitat> habitats = hotel.getHabitats();
        Habitat h = habitats.get(targetHabitatId.toUpperCase());
        if (!(h == null)){
            TreeMap<String, Animal> animals = h.getHabitatAnimals();
            int totalAnimals = animals.size(); // Gets the total number of animals in the hotel
            int currentIndex = 0; // Counter to track of the current index

        // Iterates through the hotel's animals
            for (Animal animal : animals.values()){
           
                // Extract the animal's data
                String idAnimal = animal.getId();
                String nameAnimal = animal.getName();
                String idEspecie = animal.getSpeciesId();
                String idHabitat = animal.getHabitatId();
                String healthcare = animal.getHealthcare();
                

                // Builds the output string in the required format
                output.append("ANIMAL|").append(idAnimal).append("|").append(nameAnimal)
                      .append("|").append(idEspecie).append("|").append(healthcare).append("|").append(idHabitat);
                
                // Adds a newline if this is not the last animal
                if (currentIndex < totalAnimals - 1) {
                    output.append("\n");
                }
                currentIndex++; // Increment the current index
                 }
            }
        }
    
    

    /**
     * Returns the generated output as a string.
     *
     * @return The formatted string representation of the animal's satisfaction.
     */
    public String getOutput() {
        return output.toString();
    }
}


