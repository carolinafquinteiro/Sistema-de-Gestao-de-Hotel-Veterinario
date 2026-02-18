package hva.render;

import java.util.Map;

import hva.Hotel;
import hva.HotelVisitor;
import hva.vaccine.Vaccine;

/**
 * RenderVaccines is a class that implements the HotelVisitor interface
 * It is responsible for generating a formatted string representation 
 * of all vaccines in a hotel
 */
public class RenderVaccines implements HotelVisitor {

    // StringBuilder to store the generated output as a string
    private StringBuilder output = new StringBuilder();

    /**
     * Visits a specific hotel and gathers information about its vaccines
     *
     * @param hotel the Hotel instance to be visited
     */
    @Override
    public void visit(Hotel hotel) {
        // If the vaccine list is null, do nothing
        if (hotel.getVaccines().isEmpty()) {
            return;
        }
        int totalVaccines = hotel.getVaccines().size();  // Gets the total number of vaccines in the hotel
        int currentIndex = 0; // Counter to track the current vaccine index

        // Iterates through the hotel's vaccines
        for (Map.Entry<String, Vaccine> entry : hotel.getVaccines().entrySet()) {
            Vaccine vaccine = entry.getValue();
            // Extract the vaccine's data 
            String id = vaccine.getId();
            String name = vaccine.getName();
            int timesAdministered = vaccine.getTimesAdministered(); // Ajuste se necessário
            String speciesIds =  vaccine.getIds();
            
            // Builds the output string in the required format
            if (speciesIds.equals("")){
                output.append(String.format("VACINA|%s|%s|%d", id, name, timesAdministered));
            }
            else{output.append(String.format("VACINA|%s|%s|%d|%s", id, name, timesAdministered, speciesIds));}

            // Add a newline if this is not the last vaccine
            if (currentIndex < totalVaccines - 1) {
                output.append("\n");
            }
            currentIndex++; // Increments the current index
        }
    }

    /**
     * Returns the generated output as a string
     *
     * @return the formatted string representation of the vaccines
     */
    public String getOutput() {
        return output.toString();
    }
}
