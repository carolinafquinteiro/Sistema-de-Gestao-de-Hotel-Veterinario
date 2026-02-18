package hva.render;
import java.util.Map;

import hva.Hotel;
import hva.HotelVisitor;
import hva.habitat.Habitat;
import hva.tree.Tree;


/**
 * RenderHabitats is a class that implements the HotelVisitor interface
 * It is responsible for generating a formatted string representation 
 * of all habitats in a hotel
 */
public class RenderHabitats implements HotelVisitor {

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

        int totalHabitats = hotel.getHabitats().size();  // Gets the total number of habitats in the hotel.
        int currentIndex = 0; // Counter to track the current habitat index

        // Iterate through the hotel's habitats
        for (Map.Entry<String, Habitat> entry : hotel.getHabitats().entrySet()) {
            Habitat habitat = entry.getValue();
            // Extracts the habitat's data
            String habitatId = habitat.getId();
            String habitatName = habitat.getName();
            int area = habitat.getArea();
            int n_trees = habitat.numberofTrees();

            // Builds the output string in the required format
            output.append("HABITAT|").append(habitatId).append("|")
                  .append(habitatName).append("|").append(area).append("|").append(n_trees);
            
            if (n_trees > 0) {
                output.append("\n");
                int totaltrees = habitat.getTrees().size(); // Gets the total number of animals in the hotel
                int tIndex = 0;
                for (Tree t : habitat.getTrees().values()){
                    String treeId = t.getId();
                    String treeName = t.getName();
                    String treeAge = String.valueOf(t.getAge());
                    String treeType = t.getType();
                    String treeDificulty = String.valueOf(t.getCleaningDifficulty());
                    String treeCicle = t.getCicle();
                    output.append("ÁRVORE|").append(treeId).append("|").append(treeName)
                            .append("|").append(treeAge).append("|").append(treeDificulty).
                            append("|").append(treeType).append("|").append(treeCicle);
                if (tIndex < totaltrees - 1) {
                    output.append("\n");
                }
                tIndex++;
                }
            }
            // Adds a newline if this is not the last habitat
            if (currentIndex < totalHabitats - 1) {
                output.append("\n");
            }

            currentIndex++;  // Increments the current index
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

