
package hva.render;
import java.util.Map;

import hva.Hotel;
import hva.HotelVisitor;
import hva.animal.Animal;

/**
 * RenderAnimals is a class that implements the HotelVisitor interface
 * It is responsible for generating a formatted string representation 
 * of all the animals in a hotel
 */
public class RenderAnimalsWithoutVaccines implements HotelVisitor {

    // StringBuilder to store the generated output as a string
    private StringBuilder output = new StringBuilder();

    /**
     * Visits a specific hotel and gathers information about its animals
     *
     * @param hotel the Hotel instance to be visited
     */
    @Override
    public void visit(Hotel hotel) {
        // If the hotel or its animal list is null, do nothing
        if (hotel == null || hotel.getAnimals() == null) {
            return;
        }
        int totalAnimals = 0; // Gets the total number of animals in the hotel
        int currentIndex = 0; // Counter to track of the current index
        
        for (Map.Entry<String, Animal> entry : hotel.getAnimals().entrySet()) {
            Animal animal = entry.getValue();
            String healthcare = animal.getHealthcare();
            if(healthcare.equals("VOID")){totalAnimals++;}
        }

        // Iterates through the hotel's animals
        for (Map.Entry<String, Animal> entry : hotel.getAnimals().entrySet()) {
            Animal animal = entry.getValue();
            if (animal != null) {
                String healthcare = animal.getHealthcare();
                if (healthcare.equals("VOID")){
                // Extract the animal's data
                String idAnimal = animal.getId();
                String nameAnimal = animal.getName();
                String idEspecie = animal.getSpeciesId();
                String idHabitat = animal.getHabitatId();
                
                

                // Builds the output string in the required format
                output.append("ANIMAL|").append(idAnimal).append("|").append(nameAnimal)
                      .append("|").append(idEspecie).append("|").append(healthcare).append("|").append(idHabitat);
                
                // Adds a newline if this is not the last animal
                if (currentIndex < totalAnimals - 1) {
                    output.append("\n");
                }
            }
                
            }
        }
    }

    /**
     * Returns the generated output as a string
     *
     * @return the formatted string representation of the animals
     */
    public String getOutput() {
        return output.toString();
    }
}