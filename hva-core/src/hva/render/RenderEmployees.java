package hva.render;
import java.util.Map;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;


import hva.Hotel;
import hva.HotelVisitor;
import hva.employee.Employee;
import hva.employee.Vet;
import hva.species.Species;
import hva.employee.Trt;

/**
 * RenderEmployees is a class that implements the HotelVisitor interface
 * It is responsible for generating a formatted string representation 
 * of all employees in a hotel
 */
public class RenderEmployees implements HotelVisitor {

    // StringBuilder to store the generated output as a string
    private StringBuilder output = new StringBuilder();

    /**
     * Visits a specific hotel and gathers information about its employees
     *
     * @param hotel the Hotel instance to be visited
     */
    @Override
    public void visit(Hotel hotel) {
        // If the employee list is null, do nothing
        if (hotel.getEmployees().isEmpty()) {
            return;
        }
        int totalEmployees = hotel.getEmployees().size(); // Gets the total number of employees in the hotel
        int currentIndex = 0; // Counter to track of the current employee index

        // Iterates through the hotel's animals
        for (Map.Entry<String, Employee> entry : hotel.getEmployees().entrySet()) {
            Employee employee = entry.getValue();
            // Extracts the employee's data
            String id = employee.getId();
            String name = employee.getName();
            String type = employee.getType();

            // Builds the output string in the required format
            output.append(String.format("%s|%s|%s", type, id, name));

            if (type.equals("VET")) {
                Vet vet = hotel.getVeterinarians().get(id);  // Obtém o Vet pelo id
                if (vet != null) {
                    if (vet.hasResponsibilities()){
                        

                    
                    String speciesIds = vet.getResponsibilities();

                    
                    output.append("|" + speciesIds);  // Adiciona a lista invertida de espécies
                    }
                }
                
            }

            if (type.equals("TRT")) {
                Trt keeper = hotel.getKeepers().get(id);  // Obtém o Keeper pelo id
                if (keeper != null) {  // Verifica se o cuidador existe
                   if (keeper.hasResponsibilities()){
                    String habitatsids = keeper.getResponsibilities();

                    
                    output.append("|" + habitatsids);  // Adiciona a lista invertida de espécies
                    }

                }



            }
            


            // Adds a newline if this is not the last employee
            if (currentIndex < totalEmployees - 1) {
                output.append("\n");
            }
            currentIndex++;  // Increments the current index.
        }
    }

    /**
     * Returns the generated output as a string.
     *
     * @return the formatted string representation of the employees
     */
    public String getOutput() {
        return output.toString();
    }
}