package hva.render;
import java.util.Map;

import hva.Hotel;
import hva.HotelVisitor;
import hva.vaccine.VaccinationRecord;

/**
 * RenderAnimals is a class that implements the HotelVisitor interface
 * It is responsible for generating a formatted string representation 
 * of all the animals in a hotel
 */
public class RenderVaccinationRecords implements HotelVisitor {

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
        if (hotel == null || hotel.getRecords() == null) {
            return;
        }
        int total = hotel.getRecords().size(); // Gets the total number of animals in the hotel
        int currentIndex = 0; // Counter to track of the current index

        // Iterates through the hotel's animals
        for (VaccinationRecord r : hotel.getRecords()) {
            
            if (r != null) {
                // Extract the animal's data
                String idspecie = r.getSpeciesId();
                String idvet = r.getVeterinarianId();
                String idvacine = r.getVaccineId();
               
                

                // Builds the output string in the required format
                output.append("REGISTO-VACINA|").append(idvacine).append("|").append(idvet)
                      .append("|").append(idspecie);
                
                // Adds a newline if this is not the last animal
                if (currentIndex < total - 1) {
                    output.append("\n");
                }
                currentIndex++; // Increment the current index
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

