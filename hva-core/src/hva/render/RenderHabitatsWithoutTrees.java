
package hva.render;
import java.util.Map;

import hva.Hotel;
import hva.HotelVisitor;
import hva.habitat.Habitat;

/**
 * RenderHabitats is a class that implements the HotelVisitor interface
 * It is responsible for generating a formatted string representation 
 * of all habitats in a hotel
 */
public class RenderHabitatsWithoutTrees implements HotelVisitor {

    // StringBuilder to store the generated output as a string
    private StringBuilder output = new StringBuilder();

    /**
     * Visits a specific hotel and gathers information about its habitats
     *
     * @param hotel the Hotel instance to be visited
     */
    @Override
    public void visit(Hotel hotel) {
        // If the habitat list is null, do nothing
        if (hotel.getHabitats().isEmpty()) {
            return;
        }

        int totalHabitats = 0;  // Gets the total number of habitats in the hotel.
        int currentIndex = 0; // Counter to track the current habitat index
        for (Map.Entry<String, Habitat> entry : hotel.getHabitats().entrySet()) {
            Habitat habitat = entry.getValue();
            if (habitat.numberofTrees()==0){totalHabitats++;}
        }
        // Iterate through the hotel's habitats
        for (Map.Entry<String, Habitat> entry : hotel.getHabitats().entrySet()) {
            Habitat habitat = entry.getValue();
            int n_trees = habitat.numberofTrees();
            if(n_trees==0){
            // Extracts the habitat's data
            String habitatId = habitat.getId();
            String habitatName = habitat.getName();
            int area = habitat.getArea();
           

            // Builds the output string in the required format
            output.append("HABITAT|").append(habitatId).append("|")
                  .append(habitatName).append("|").append(area).append("|").append(n_trees);
            
            
            // Adds a newline if this is not the last habitat
            if (currentIndex < totalHabitats - 1) {
                output.append("\n");
            }

            currentIndex++;  // Increments the current index
            }
        }
    }
    
    /**
     * Returns the generated output as a string
     *
     * @return the formatted string representation of the habitats
     */
    public String getOutput() {
        return output.toString();
    }
}
