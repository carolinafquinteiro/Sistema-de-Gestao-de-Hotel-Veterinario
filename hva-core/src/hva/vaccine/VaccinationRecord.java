package hva.vaccine;
import java.io.Serializable;



public class VaccinationRecord implements Serializable{
    private String vaccineId;
    private String veterinarianId;
    private String animalId;
    private String speciesId; 
    private String damage;// Animal species
   

    /**
     * Constructs a VaccinationRecord object to initialize the vaccination details
     *
     * @param vaccineId       the unique identifier for the vaccine
     * @param veterinarianId  the unique identifier for the veterinarian administering the vaccine
     * @param animalId       the unique identifier for the animal receiving the vaccine
     * @param speciesId      the unique identifier for the species of the animal
     */
   
    public VaccinationRecord(String vaccineId, String veterinarianId, String animalId, String speciesId, String damage) {

        this.vaccineId = vaccineId;
        this.veterinarianId = veterinarianId;
        this.animalId = animalId;
        this.speciesId = speciesId;
        this.damage = damage;

        
    }

    // Getters to obtain registries information
    public String getVaccineId() {
        return vaccineId;
    }

    public String getVeterinarianId() {
        return veterinarianId;
    }

    public String getAnimalId() {
        return animalId;
    }

    public String getSpeciesId() {
        return speciesId;
    }

    public String getDamage() {
        return damage;
    }


    
}
