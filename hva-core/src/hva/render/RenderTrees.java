package hva.render;

import hva.Hotel;
import hva.HotelVisitor;
import hva.habitat.Habitat;
import hva.tree.Tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * RenderAnimalSatisfaction is a class that implements the HotelVisitor interface.
 * It is responsible for generating a formatted string representation 
 * of a single animal's satisfaction in a hotel.
 */
public class RenderTrees implements HotelVisitor {

    // StringBuilder to store the generated output as a string
    private StringBuilder output = new StringBuilder();
    private String targetHabitatId;

    /**
     * Constructor that accepts the target animal's ID.
     *
     * @param targetAnimalId The ID of the animal to be rendered.
     */
    public RenderTrees(String targetHabitatId) {
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
            TreeMap<String, Tree> trees = h.getTrees();
            int totaltrees = trees.size(); // Gets the total number of animals in the hotel
            int currentIndex = 0;
            for (Tree t : trees.values()){
                String treeId = t.getId();
                String treeName = t.getName();
                String treeAge = String.valueOf(t.getAge());
                String treeType = t.getType();
                String treeDificulty = String.valueOf(t.getCleaningDifficulty());
                String treeCicle = t.getCicle();
                output.append("ÁRVORE|").append(treeId).append("|").append(treeName)
                        .append("|").append(treeAge).append("|").append(treeDificulty).
                        append("|").append(treeType).append("|").append(treeCicle);
                if (currentIndex < totaltrees - 1) {
                    output.append("\n");
                }
                currentIndex++;
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


