package hva;


import java.util.ArrayList;
import java.util.List;

import java.io.Serial;
import java.io.Serializable;
import hva.exceptions.ImportFileException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import hva.exceptions.UnrecognizedEntryException;
import hva.habitat.Habitat;
import hva.seasons.Season;
import hva.species.Species;
import hva.vaccine.Vaccine;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import hva.vaccine.VaccinationRecord;
import hva.animal.Animal;
import hva.tree.Tree;
import hva.employee.Employee;
import hva.employee.Trt;
import hva.employee.Vet;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateHabitatException;
import hva.exceptions.DuplicateTreeException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.UnknownSpecieException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.UnknownResponsibilityException;
import hva.exceptions.UnknownVetException;
import hva.exceptions.VetNotAuthorizedException;
import hva.exceptions.InappropriateVaccineException;
import hva.seasons.Season;


public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    // Maps to store various hotel entities
    private TreeMap<String, Animal> animals;  // Animals
    private HashMap<String, Species> speciesMap;  // Species
    private TreeMap<String, Vaccine> vaccines;  // Vaccines
    private TreeMap<String, Habitat> habitats;  // Habitats
    private TreeMap<String, Vet> veterinarians; // Veterinarians (VET)
    private TreeMap<String, Trt> trt;  // Keepers (TRT)
    private TreeMap<String, Employee> employees; // Employees
    private TreeMap<String, Tree> trees; 
    private TreeMap<String, Tree> notinicializedtrees; 
    private boolean modified; 
    private List<VaccinationRecord> records;
    Season seasonContext;
    int numberofanimals;




    public Hotel() {
        // Constructor to initialize all maps and set the modified state to false 
        this.animals = new TreeMap<>(); 
        this.speciesMap = new HashMap<>();  
        this.vaccines = new TreeMap<>(); 
        this.habitats = new TreeMap<>();
        this.veterinarians = new TreeMap<>();
        this.trt = new TreeMap<>();
        this.employees = new TreeMap<>();
        this.trees = new TreeMap<>();
        this.notinicializedtrees = new TreeMap<>();
        this.modified = false; 
        this.records = new ArrayList<>();
        this.seasonContext = new Season();
        this.numberofanimals = 0;
        

    }
    
     // Allows a visitor (HotelVisitor) to process the Hotel
    public void accept(HotelVisitor visitor) {
        visitor.visit(this);
    }

    // Getters
    public Map<String, Animal> getAnimals() {
        return animals;
    }
    public Map<String, Vaccine> getVaccines() {
        return vaccines;
    }
    
    public Map<String, Habitat> getHabitats() {
        return habitats;
    }
    
    public Map<String, Employee> getEmployees() {
        return employees;
    }
    public List<VaccinationRecord> getRecords(){
        return records;
    }

    public Map<String, Vet> getVeterinarians() {
        return veterinarians;
    }
    public Map<String, Trt> getKeepers() {
        return trt;
    }
    
    /**
     * Sets whether there have been modifications to the hotel data.
     * 
     * @param _boolean a boolean indicating if the hotel data has been modified (true) or not (false).
     */
    public void setModified(boolean _boolean) {
        this.modified = _boolean;
    }

    /**
     * Checks if the hotel data has been modified.
     * 
     * @return a boolean value, true if the hotel data has been modified, false otherwise.
     */
    public boolean getModified() {
        return modified;
    }

    public void newseason(){
        seasonContext.nextSeason();
        modified = true;
        
    }
    public String whatseason(){
        int s = seasonContext.getState();
        return String.valueOf(s);
        
    }
    

    /**
     * Checks if a species with the given ID exists in the Species map.
     * 
     * @param speciesId the ID of the species to check.
     * @return true if the species exists in the map or if the ID is an empty string, false otherwise.
     */
    public boolean speciesExists(String speciesId) {
        return speciesMap.containsKey(speciesId.toUpperCase()) || speciesId.equals("");
    }

    /**
     * Checks if all species IDs in the provided array are valid.
     * 
     * @param speciesIds an array of species IDs to validate.
     * @return true if all species IDs exist in the Species map, false otherwise.
     */
    public boolean areSpeciesIdsValid(String[] speciesIds) {
        for (String speciesId : speciesIds) {
            if (!speciesExists(speciesId)) { // Checks if species exists
                return false; // Returns false if any ID is invalid
            }
        }
        return true; // Returns true if all IDs are valid
    }

    /**
     * Registers a new species in the Species map.
     * 
     * @param speciesId the ID of the species to register.
     * @param speciesName the name of the species to register.
     */
    public void registerSpecies(String speciesId, String speciesName) {
        speciesMap.put(speciesId.toUpperCase(), new Species(speciesId, speciesName));
    }
    public int getnanimals(){
        return numberofanimals;
    }


    /**
     * Registers a new animal and throws an exception if the animal already exists.
     *
     * @param id the ID of the animal
     * @param name the name of the animal
     * @param speciesId the species ID of the animal
     * @param habitatId the habitat ID of the animal
     * @throws DuplicateAnimalException if the animal ID already exists
     */
    public void registerAnimal(String id, String name, String speciesId, String habitatId) throws DuplicateAnimalException, UnknownHabitatException {
        String idAnimal = id.toUpperCase();
        if (animals.containsKey(idAnimal)||animals.containsKey(id)) {  // Verifies if animal ID already exists
            throw new DuplicateAnimalException(id);  // Throws exception if the animal already exists
        }
        doesHabitatExist(habitatId);
        Animal a =  new Animal(id, name, speciesId, habitatId);
        animals.put(idAnimal, a);
        Habitat h = habitats.get(habitatId.toUpperCase());
        h.addAnimal(a);  // Registers a new Animal in the map
        modified = true;
        numberofanimals++;  // Marks the state as modified

    }
    
    /**
     * Checks if a vaccine with the given ID exists in the Vaccines map.
     * 
     * @param vaccineId the ID of the vaccine to check.
     * @return true if the vaccine exists in the map, false otherwise.
     */
    public boolean vaccineExists(String vaccineId) {
        return vaccines.containsKey(vaccineId.toUpperCase());
    }

    /**
     * Checks if a animal with the given ID exists in the Animals map.
     * 
     * @param animalId the ID of the vaccine to check.
     * @return true if the vaccine exists in the map, false otherwise.
     */
    public boolean animalExists(String animalId) {
        return animals.containsKey(animalId.toUpperCase());
    }


    /**
     * Registers a new vaccine and throws exceptions if there is a duplication or unknown species
     *
     * @param id the ID of the vaccine
     * @param name the name of the vaccine
     * @param speciesIds the IDs of species associated with the vaccine
     * @throws DuplicateVaccineException if the vaccine ID already exists
     * @throws UnknownSpecieException if an unknown species ID is encountered
     */
    public void registerVaccine(String id, String name, String[] speciesIds) throws DuplicateVaccineException, UnknownSpecieException {
        String idVaccine = id.toUpperCase();
        if (vaccineExists(idVaccine)||vaccineExists(id)) {  // Verifies if vaccine ID already exists
            throw new DuplicateVaccineException(id);  // Throws exception if the vaccine already exists
        }

        TreeMap<String , Species> species_vaccine = new TreeMap<>(); 

    
        // Checks if all species ID are valid
        for (String speciesId : speciesIds) {
            if (!speciesExists(speciesId) )  {
                throw new UnknownSpecieException(speciesId);  // Throws exception for unknown species
            }
            Species sp = speciesMap.get(speciesId.toUpperCase());
            species_vaccine.put(speciesId.toUpperCase(),sp);
            sp.addVaccine(idVaccine);
        }
        vaccines.put(idVaccine, new Vaccine(id, name, species_vaccine));  // Registers a new vaccine in the map
        modified = true; 
         // Marks the state as modified
    }


    
    /**
     * Checks if a habitat with the given ID exists in the Habitat map.
     * 
     * @param habitatId the ID of the habitat to check.
     * @return true if the habitat exists in the map, false otherwise.
     */
    public boolean habitatExists(String habitatId) {
        return habitats.containsKey(habitatId.toUpperCase());
    }
    
    

    /**
     * Checks if an employee with the given ID exists in the Employee map.
     * 
     * @param employeeId the ID of the employee to check.
     * @return true if the employee exists in the map, false otherwise.
     */
    public boolean employeeExists(String employeeId) {
        if (employeeId == null){
            return false;
        }
        else {
            return employees.containsKey(employeeId.toUpperCase());
        }

    }

    /**
     * Checks if a veterinarian with the given ID exists in the Veterinarian map.
     *
     * @param vetId the ID of the veterinarian to check.
     * @return true if the veterinarian exists in the map, false otherwise.
     */
    public boolean vetExists(String vetId) {
        return veterinarians.containsKey(vetId.toUpperCase());
    }

    /**
     * Checks if a treatment record with the given ID exists in the Treatment map.
     *
     * @param Id the ID of the treatment record to check.
     * @return true if the treatment record exists in the map, false otherwise.
    */
    public boolean trtExists(String Id) {
        return trt.containsKey(Id.toUpperCase());
    }

    /**
     * Checks if a tree with the given ID exists in the Tree map.
     *
     * @param treeId the ID of the tree to check.
     * @return true if the tree exists in the map, false otherwise.
     */
    public boolean treeExists(String treeId) {
        return trees.containsKey(treeId.toUpperCase());
    }

    /**
     * Checks if the given ID belongs to a veterinarian.
     *
     * @param id the ID to check.
     * @return true if the ID belongs to a veterinarian, false otherwise.
     */
    public boolean isvet(String id){
        return veterinarians.containsKey(id.toUpperCase());
    }


    /**
     * Registers a new habitat and throws an exception if the habitat already exists.
     *
     * @param id the ID of the habitat
     * @param name the name of the habitat
     * @param area the area of the habitat
     * @throws DuplicateHabitatException if the habitat ID already exists
     */

    public void registerHabitat(String id, String name, int area) throws DuplicateHabitatException{
        String idHabitat = id.toUpperCase();
        if (habitatExists(idHabitat)||habitatExists(id)) {  // Verifies if habitat ID already exists
            throw new DuplicateHabitatException(id); 
        }
        habitats.put(idHabitat, new Habitat(id, name, area));  // Registers a new habitat in the map
        modified = true;  // Marks the state as modified
    }
    
    /**
     * Registers a new employee (veterinarian or treater) and throws an exception if they already exist.
     *
     * @param id the ID of the employee
     * @param name the name of the employee
     * @param type the type of the employee (e.g., VET or TRT)
     * @throws DuplicateEmployeeException if the employee ID already exists
     */
    public void registerEmployee(String id, String name, String type) throws DuplicateEmployeeException {
        String idEmployee = id.toUpperCase();
        if (employeeExists(idEmployee)||employeeExists(id)) {  /// Verifies if habitat ID already exists
            throw new DuplicateEmployeeException(id); 
        }
        // Adds a new employee to the apropriate map
        Employee employee = new Employee(id, name, type);
        employees.put(idEmployee, employee); // Registers a new employee in the map
    
        // Adds to the specific map for veterinarians or keepers
        if (type.equals("VET")) {
            veterinarians.put(idEmployee, new Vet(id, name));
        } else if (type.equals("TRT")) {
            trt.put(idEmployee, new Trt(id, name));
        }
        modified = true; // Marks the state as modified
    }
    

    /**
     * Import data from a text file to create domain entities (animals, habitats, vaccines)
     *
     * @param filename the name of the text file to import
     * @throws ImportFileException if an I/O error occurs or the data format is invalid
     */
    public void importFile(String filename) throws ImportFileException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Reads the file line by line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");  // Splits the line into fields 
                try {
                    registerEntry(fields); // Registers the entry based on the fields
                } catch ( DuplicateAnimalException | DuplicateHabitatException 
                        | DuplicateVaccineException | UnknownSpecieException | 
                        DuplicateEmployeeException|UnknownEmployeeException | 
                        UnknownResponsibilityException | UnknownHabitatException |DuplicateTreeException  e) {
                    e.printStackTrace();  // If an exception is thrown, print the stack trace
                }
            }
        } catch (IOException | UnrecognizedEntryException e) {
            throw new ImportFileException(filename, e); // Throws I/O exception
        }
    }

    /**
     * Register a new entity (animal, habitat, vaccine) based on the parsed data fields.
     *
     * @param fields the data fields to process
     * @throws UnknownDataException if the entry type is unknown
     * @throws DuplicateAnimalException if the animal ID already exists
     * @throws DuplicateHabitatException if the habitat ID already exists
     * @throws DuplicateVaccineException if the vaccine ID already exists
     * @throws UnknownSpecieException if an unknown species ID is encountered
     */
    public void registerEntry(String... fields) throws UnrecognizedEntryException,  DuplicateAnimalException,
            DuplicateHabitatException, DuplicateVaccineException, UnknownSpecieException, DuplicateEmployeeException, 
            UnknownEmployeeException, UnknownResponsibilityException, UnknownHabitatException, DuplicateTreeException{
        switch (fields[0]) {
            case "ESPÉCIE":  // Registers a specie
                registerSpecies(fields[1], fields[2]);
                break;

            case "ANIMAL":  // Registers an animal
                registerAnimal(fields[1], fields[2], fields[3], fields[4]);
                break;

           

            case "VACINA":  // Registers a vaccine
                String vaccineId = fields[1];
                String vaccineName = fields[2];
                String[] speciesIds;

                // Verifies the field number for Vaccine
                if (fields.length < 4) {
                    // If only 3 fields, registers the vaccine with an empty list
                    speciesIds = new String[0]; //Empty list of species IDs
                } else {
                    // If 4 or more fields, split the IDs
                    String speciesKeysInput = fields[3].trim(); 

                    speciesIds = speciesKeysInput.split("\\s*,\\s*");  // Splits the IDs
                    for (int i = 0; i < speciesIds.length; i++) {
                        speciesIds[i] = speciesIds[i].trim(); // Removes whitespaces from each ID
                    }
                }
                registerVaccine(vaccineId, vaccineName, speciesIds);
                break;

            case "VETERINÁRIO":  // Registers a veterinarian
                String vetId = fields[1];
                String vetName = fields[2];
                String[] speciesId;
                if (fields.length < 4) {
                    // If there are fewer than 4 fields, register the veterinarian with an empty list of species         
                    speciesId = new String[0];
                } else {
                    // If there are 4 or more fields, the species IDs are in field [3]
                    String speciesKeysInput = fields[3].trim();  // Species IDs in field 3
                    
                    speciesId = speciesKeysInput.split("\\s*,\\s*");  // Splits the IDs by commas
                    for (int i = 0; i < speciesId.length; i++) {
                        speciesId[i] = speciesId[i].trim();   // Removes whitespaces from each ID
                    }
                }
            
                // Registers the veterinarian
                registerEmployee(vetId, vetName, "VET");
                
                // For each species ID, calls the responsibility function
                for (String id : speciesId) {
                    giveResponsibility(vetId, id);  // Function that associates the veterinarian with the species
                }
                break;

            case "TRATADOR": // Registers a keeper
                String trtId = fields[1];
                String trtName = fields[2];
                registerEmployee(trtId, trtName, "TRT");
                String[] specieId;
                if (fields.length < 4) {
                    // If there are fewer than 4 fields, register the caretaker with an empty list of species
                    specieId = new String[0];  
                } else {
                    // If there are 4 or more fields, the species IDs are in field [3]
                    String speciesKeysInput = fields[3].trim();  // Species IDs in field 3
                    
                    specieId = speciesKeysInput.split("\\s*,\\s*");  // Splits the IDs by commas
                    for (int i = 0; i < specieId.length; i++) {
                        specieId[i] = specieId[i].trim();  // Removes whitespaces from each ID
                    }
                }

                // For each species ID, calls the responsibility function
                for (String id : specieId) {
                    giveResponsibility(trtId, id);  // Function that associates the keeper with the species
                }

                break;
            case "ÁRVORE":
                
                String id = fields[1];
                String name = fields[2];
                int age = Integer.parseInt(fields[3]);
                int cleaningDifficulty = Integer.parseInt(fields[4]);
                String type = fields[5];
                String cicle = "";
                if (type.equals("PERENE")){
                    cicle = seasonContext.getevergreenCicle();  // Gets the evergreen cycle
        
                }
                if (type.equals("CADUCA")){
                    cicle = seasonContext.getdeciduousCicle();  // Gets the deciduous cycle
        
                }
                notinicializedtrees.put(id.toUpperCase(),new Tree(id, name, age, cleaningDifficulty, type, cicle));
                break;

            case "HABITAT":
                String idhabitat = fields[1];
                String namehabitat = fields[2];
                int area = Integer.parseInt(fields[3]);
                registerHabitat(idhabitat, namehabitat, area);
                String[] treeId;

                if (fields.length < 5) {
                    // If there are fewer than 4 fields, register the habitat with an empty list of trees
                    treeId = new String[0];  
                } else {
                    // If there are 4 or more fields, the tree IDs are in field [4]
                    String treesKeysInput = fields[4].trim();  // Tree IDs in field 4
                    
                    treeId = treesKeysInput.split("\\s*,\\s*");  // Splits the IDs by commas
                    for (int i = 0; i < treeId.length; i++) {
                        treeId[i] = treeId[i].trim();  // Removes whitespaces from each ID
                    }
                }

                // For each tree ID, adds the tree to the habitat
                for (String idtree : treeId) {
                    Tree t = notinicializedtrees.get(idtree.toUpperCase());
                    addTree(idhabitat,t.getId(), t.getName(), t.getAge(), t.getCleaningDifficulty(), t.getType());
                }
                break;
            
            default:
            throw new UnrecognizedEntryException(fields[0]);  //Throws exception for unknown entry
        
        }
    
    }


    /**
     * Transfers an animal to a new habitat
     * 
     * @param id        the ID of the animal to transfer
     * @param idHabitat the ID of the habitat to transfer the animal to
     * @throws UnknownHabitatException if the habitat ID does not exist
     * @throws UnknownAnimalException  if the animal ID does not exist
     */
    public void transfertoHabitat(String id, String idHabitat) throws UnknownHabitatException, UnknownAnimalException{
        String idAnimal = id.toUpperCase();
        Animal animal = animals.get(idAnimal);
        Habitat habitat = habitats.get(idHabitat.toUpperCase());
        doesAnimalExist(id);
        doesHabitatExist(idHabitat);
        // Removes animal from the old habitat
        String oldHabitatId = animal.getHabitatId(); 
        if (oldHabitatId != null && !oldHabitatId.equals(idHabitat)) {
            Habitat oldHabitat = habitats.get(oldHabitatId);
            oldHabitat.removeAnimal(animal.getId());
        }
        // Updates the animal´s habitat
        animal.setHabitatId(idHabitat);
        habitat.addAnimal(animal);
        modified = true;
    }

    /**
     * Assigns a responsibility to an employee (veterinarian or keeper)
     * 
     * @param id           the ID of the employee
     * @param responsibility the responsibility to assign (species ID for veterinarians or habitat ID for keepers)
     * @throws UnknownEmployeeException if the employee ID does not exist
     * @throws UnknownResponsibilityException if the species or habitat does not exist for the responsibility
     */
    public void giveResponsibility(String id, String responsibility) throws  UnknownEmployeeException, UnknownResponsibilityException {
        Employee employee = employees.get(id.toUpperCase());
        
        if(!employeeExists(id)){
            throw new UnknownEmployeeException(id);
        }
        
    
        String type = employee.getType();
        
       
        if (type.equals("VET")){
            if (!speciesExists(responsibility)){
                throw new UnknownResponsibilityException(id, responsibility);
            
            }
            Vet veterinarian = veterinarians.get(id.toUpperCase());
            if (!veterinarian.hasResponsibility(responsibility)){
                veterinarian.addResponsibility(responsibility, speciesMap.get(responsibility));    
            }
             

        }

        else{ 
            if (!habitatExists(responsibility)){
                throw new UnknownResponsibilityException(id, responsibility);
            }

            Trt keeper = trt.get(id.toUpperCase());
            if (!keeper.hasResponsibility(responsibility)){
                keeper.addResponsibility(responsibility, habitats.get(responsibility));   
            }

        }

    }

    /**
     * Removes a responsibility from an employee (veterinarian or keeper)
     * 
     * @param id            the ID of the employee
     * @param responsibility the responsibility to remove (species ID for veterinarians or habitat ID for keepers)
     * @throws UnknownEmployeeException if the employee ID does not exist
     * @throws UnknownResponsibilityException if the species or habitat does not exist for the responsibility, or if the employee does not have the responsibility
     */
    public void takeResponsibility(String id, String responsibility) throws  UnknownEmployeeException, UnknownResponsibilityException {
        if(!employeeExists(id)){
            throw new UnknownEmployeeException(id);
        }
        Employee employee = employees.get(id);
        if (employee.getType().equals("VET")){
            if (!speciesExists(responsibility)){
                throw new UnknownResponsibilityException(id , responsibility);
        
            }
            Vet veterinarian = veterinarians.get(id);
            if (!veterinarian.hasResponsibility(responsibility)){
                throw new UnknownResponsibilityException(id , responsibility);    
            }
            veterinarian.removeResponsibility(responsibility);

        }
        if (employee.getType().equals("TRT")){
            if (!habitatExists(responsibility)){
                throw new UnknownResponsibilityException(id , responsibility);
            }

            Trt keeper = trt.get(id);
            if (!keeper.hasResponsibility(responsibility)){
                throw new UnknownResponsibilityException(id, responsibility);
            }
            keeper.removeResponsibility(responsibility);     

        }
    }

    /**
     * Adds a new tree to a habitat
     *
     * @param idHabitat   the ID of the habitat where the tree will be added
     * @param idTree      the ID of the tree to be added
     * @param name        the name of the tree
     * @param age         the age of the tree
     * @param dificulty   the cleaning difficulty level of the tree
     * @param type        the type of tree 
     * @throws UnknownHabitatException  if the habitat ID does not exist
     * @throws DuplicateTreeException   if a tree with the same ID already exists
     */
    public void addTree(String idHabitat, String idTree, String name, int age, int dificulty, String type) throws UnknownHabitatException, DuplicateTreeException{
        doesHabitatExist(idHabitat);
        Habitat habitat = habitats.get(idHabitat.toUpperCase());
        if (treeExists(idTree)){
            throw new DuplicateTreeException(idTree);
        }
        String cicle = "";
        if (type.equals("PERENE")){
            cicle = seasonContext.getevergreenCicle();

        }
        if (type.equals("CADUCA")){
            cicle = seasonContext.getdeciduousCicle();

        }
        Tree tree = new Tree(idTree, name, age, dificulty, type, cicle);
        if(notinicializedtrees.containsKey(idTree.toUpperCase())){
             notinicializedtrees.remove(idTree.toUpperCase());
        }
        if (type.equals("PERENE")){
            tree.setSeasoneffort(seasonContext.getSeasonEffortEvergreen());
        }
        else {
            tree.setSeasoneffort(seasonContext.getSeasonEffortDeciduous());
        }
        trees.put(idTree.toUpperCase(), tree);
        habitat.addTree(tree);
        
        
    }

    /**
     * Returns a formatted string containing details of the specified tree
     *
     * @param id the ID of the tree
     * @return a string with tree details formatted as "ÁRVORE|ID|Name|Age|CleaningDifficulty|Type|Cicle"
     */
    public String printTree(String id){

        Tree t = trees.get(id.toUpperCase());

        String s = "ÁRVORE|"+ t.getId()+ "|" + t.getName() + "|" + String.valueOf(t.getAge()) +"|" 
        + String.valueOf(t.getCleaningDifficulty()) + "|" + t.getType() + "|" + t.getCicle();
        return s.toString();

    }

    /**
     * Changes the area of a specified habitat
     *
     * @param idHabitat the ID of the habitat
     * @param area      the new area of the habitat
     * @throws UnknownHabitatException if the habitat ID does not exist
     */
    public void changeHabitatArea(String idHabitat, int area)throws UnknownHabitatException{
        doesHabitatExist(idHabitat);
        Habitat habitat = habitats.get(idHabitat.toUpperCase());
        habitat.setHabitatArea(area);

    }

    /**
     * Changes the area of a specified habitat
     *
     * @param idHabitat the ID of the habitat
     * @param area      the new area of the habitat
     * @throws UnknownHabitatException if the habitat ID does not exist
     */
    public void changeHabitatInfluence(String idHabitat, String idSpecie, String influence)throws UnknownHabitatException, 
    UnknownSpecieException{
        doesHabitatExist(idHabitat);
        if (!speciesExists(idSpecie)){
            throw new UnknownSpecieException(idSpecie);
        }
        Species s = speciesMap.get(idSpecie.toUpperCase());
        s.setInfluence(influence, idHabitat);
        Habitat habitat = habitats.get(idHabitat.toUpperCase());
        habitat.addSpecie(idSpecie.toUpperCase(), s);
    }

    /**
     * Calculates the satisfaction of an animal based on habitat conditions
     *
     * @param idanimal the ID of the animal
     * @return the satisfaction score of the animal
     * @throws UnknownAnimalException if the animal ID does not exist
     */

    public int calculateAnimalSatisfaction (String idanimal){

        Animal animal = animals.get(idanimal.toUpperCase());
        String specieid = animal.getSpeciesId();
        Species s = speciesMap.get(specieid.toUpperCase());
        Habitat habitat = habitats.get(animal.getHabitatId().toUpperCase());
        int same = habitat.equalanimals(specieid);
        int diferent = habitat.diferentanimal(specieid);
        int totalpopulation = habitat.totalpopulation(); 
        int adequacy = habitat.getadequacy(s);
        int area = habitat.getArea();
        if (totalpopulation == 0){return 0;}
        int satisfaction = 20 + 3*same - 2*diferent + (area/totalpopulation) + adequacy - 3;
        return satisfaction;
        
    }


    /**
     * Calculates the satisfaction level of an employee (either veterinarian or keeper)
     *
     * @param idEmployee the ID of the employee
     * @return the satisfaction score of the employee as a string
     * @throws UnknownEmployeeException if the employee ID does not exist
     */

    public int calculateemployeesatisfaction(String idEmployee){

        int satisfaction = 0;
        if (vetExists(idEmployee)){
            satisfaction = calculateVetSatisfaction(idEmployee);
        }
        else{
            satisfaction = calculatekeepersatisfaction(idEmployee);
        }

        return satisfaction;
    }

    /**
     * Calculates the number of animals of the same species
     *
     * @param s the species to count
     * @return the number of animals of the same species
     */
    public int samespecieanimals(Species s){
        int nanimals = 0;
        for( Animal a : animals.values() ){
            if(a.getSpeciesId().equals(s.getId())){
                nanimals++;    
            }
        }
        return nanimals;

    }

    /**
     * Calculates the number of veterinarians responsible for a species.
     *
     * @param s the species to check
     * @return the number of veterinarians responsible for the species
     */
    public int numberofvets(Species s){
        int specieVet = 0;
        for (Vet othervet : veterinarians.values()) {
            if (othervet.hasResponsibility(s.getId())){
                specieVet++;
            }
        }
        return specieVet;
    }

    /**
     * Calculates the number of keepers responsible for a habitat
     *
     * @param h the habitat to check
     * @return the number of keepers responsible for the habitat
     */
    public int numberofkeepers(Habitat h){
        int keepers = 0;
        for (Trt other : trt.values()) {
            if (other.hasResponsibility(h.getId())){
                keepers++;
            }
        }
        return keepers;
    }

    /**
     * Calculates the workload for a veterinarian based on species they are responsible for.
     *
     * @param idvet the ID of the veterinarian
     * @return the total workload of the veterinarian
     */
    public int calculatework(String idvet){
       int work = 0;
        Vet vet = veterinarians.get(idvet);
        TreeMap<String, Species> vetspecies = vet.getSpecies();
        for (Species s : vetspecies.values()){
            int vets = numberofvets(s);
            int nanimals = samespecieanimals(s);
            work += nanimals/vets;
        
        }
        return work;
    }

    /**
     * Calculates the satisfaction of a veterinarian based on workload
     *
     * @param idvet the ID of the veterinarian
     * @return the satisfaction score of the veterinarian
     */
    public int calculateVetSatisfaction(String idvet){
        int satisfaction = 20-calculatework(idvet);
        return satisfaction;
    }

    /**
     * Calculates the satisfaction of a keeper based on workload in habitats
     *
     * @param idtrt the ID of the keeper
     * @return the satisfaction score of the keeper
     */
    public int calculatekeepersatisfaction(String idtrt){
        int work = 0;
        Trt keeper = trt.get(idtrt);
        TreeMap<String, Habitat> habitats = keeper.getHabitats();
        for (Habitat h : habitats.values()){

            int workhabitat = h.workonhabitat();
            int keepers = numberofkeepers(h);
            work += workhabitat/keepers ;
        
        }
        return 300 - work;
    }

    /**
     * Administers a vaccine to an animal and records the vaccination
     * 
     * @param idVaccine  the ID of the vaccine being administered
     * @param idanimal   the ID of the animal receiving the vaccine
     * @param idVet      the ID of the veterinarian administering the vaccine
     * @throws InappropriateVaccineException if the vaccine has an inappropriate effect on the animal
     */
    public void vaccineAnimal(String idVaccine, String idanimal, String idVet) throws InappropriateVaccineException {
        Animal animal = animals.get(idanimal.toUpperCase());
        String idSpecie = animal.getSpeciesId();
        Vaccine vaccine = vaccines.get(idVaccine.toUpperCase());
        vaccine.incrementTimesAdministered();
        Species specie = speciesMap.get(idSpecie.toUpperCase());
        String vaccineDamage = "";
        int damage = vaccine.calculatedamage(specie);
        if (damage == -1){
            vaccineDamage = "NORMAL";
        }
        if (damage == 0){
            vaccineDamage = "CONFUSÃO";
        }
        if (damage>=1 && 4>=damage){
            vaccineDamage = "ACIDENTE";
        }
        if(damage>=5){
            vaccineDamage = "ERRO";
        }


        records.add(new VaccinationRecord(idVaccine, idVet, idanimal,idSpecie, vaccineDamage));
        animal.addHealthRegist(vaccineDamage);
        modified = true;
        if (!vaccineDamage.equals("NORMAL")){
            throw new InappropriateVaccineException(idVaccine, idanimal);

        }
        
    } 

    /**
     * Checks if a veterinarian is authorized to vaccinate a given animal
     * 
     * @param idVet     the ID of the veterinarian
     * @param idanimal  the ID of the animal
     * @param idvaccine the ID of the vaccine
     * @throws UnknownAnimalException     if the animal ID does not exist
     * @throws UnknownVetException        if the veterinarian ID does not exist
     * @throws UnknownVaccineException    if the vaccine ID does not exist
     * @throws VetNotAuthorizedException  if the vet is not authorized to vaccinate the animal's species
     */
    public void isVetauthorized(String idVet, String idanimal, String idvaccine ) throws UnknownAnimalException, UnknownVetException, UnknownVaccineException, VetNotAuthorizedException{
        
        if (!vaccineExists(idvaccine)){
            throw new UnknownVaccineException(idvaccine);
        }
        doesAnimalExist(idanimal);
        doesVetExist(idVet);
        Vet v = veterinarians.get(idVet.toUpperCase());
        Animal a = animals.get(idanimal.toUpperCase());
        if (!v.hasResponsibility(a.getSpeciesId().toUpperCase())){
            throw new VetNotAuthorizedException(idVet, a.getSpeciesId() );

        }
 
    }

    /**
    * Checks if a habitat exists
    * 
    * @param idHabitat the ID of the habitat to check
    * @throws UnknownHabitatException if the habitat does not exist
    */
    public void doesHabitatExist(String idHabitat) throws UnknownHabitatException{
        if(!habitatExists(idHabitat)){
            throw new UnknownHabitatException(idHabitat);
        }
    }

    /**
     * Checks if a tree is evergreen
     * 
     * @param t the tree to check
     * @return true if the tree is evergreen, false otherwise
     */
    public boolean istreeevergreen(Tree t){
        return (t.getType().equals("PERENE"));
    }

    /**
     * Updates the seasonal cycles of all trees in all habitats
     * 
     * Iterates through all habitats and trees, adjusting their cycle based on their type 
     */
    public void updateTreesInAllHabitats() {
            for (Tree tree : trees.values()) {
                tree.incrementsforSeason(); 
                if (istreeevergreen(tree)){
                    tree.setCicle(seasonContext.getevergreenCicle());
                    tree.setSeasoneffort(seasonContext.getSeasonEffortEvergreen());
                }
                else {
                    tree.setCicle(seasonContext.getdeciduousCicle());
                    tree.setSeasoneffort(seasonContext.getSeasonEffortDeciduous());

                }               
            
        }

    }



    
    public void doesAnimalExist(String idAnimal) throws UnknownAnimalException{
        if(!animals.containsKey(idAnimal.toUpperCase())){
            throw new UnknownAnimalException(idAnimal);
        }
    }

    public void doesEmployeeExist(String idEmployee) throws UnknownEmployeeException{
        if(!employeeExists(idEmployee)){
            throw new UnknownEmployeeException(idEmployee);
        }
    }
    
    public void doesVetExist(String idvet) throws UnknownVetException{
        if(!vetExists(idvet)){
            throw new UnknownVetException(idvet);
        }
    }

    public int calculateTotalSatisfaction() {
        int total = 0;
        for (Animal a : animals.values()){
             total += calculateAnimalSatisfaction(a.getId());
            
        }
        for (Employee e : employees.values()){
            total += calculateemployeesatisfaction(e.getId());
           
       }
       return total;

    }
    

}
