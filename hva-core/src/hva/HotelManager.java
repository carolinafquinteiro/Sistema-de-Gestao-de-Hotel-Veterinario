package hva;

import java.io.*;
import hva.exceptions.*;
import hva.Hotel;
import hva.exceptions.ImportFileException;
import hva.exceptions.MissingFileAssociationException;
import hva.exceptions.UnavailableFileException;
import hva.seasons.Season;

/**
 * Class that represents the hotel application
 * It manages the hotel data, including saving, loading, and importing data files
 */
public class HotelManager {

    /** This is the current hotel. */
    private Hotel _hotel = new Hotel();
    private String _filename;
    private Season seasonContext = new Season(); // manage seasons

    /**
     * Checks if the hotel data has been modified.
     *
     * @return true if the hotel data has been modified; false otherwise
     */
    public boolean isModified() {
        if (_hotel == null){
            return false;
        }  
        return _hotel.getModified();  
    }
    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        // Check if there is an associated filename before saving
        if (_filename == null || _filename.isBlank()) {
            throw new MissingFileAssociationException();  // Throws exception if file is missing
        }

        if (isModified()){// Ensures file streams are closed automatically
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(_hotel); 
            _hotel.setModified(false); // Serializes the Hotel object and writes it to the file
        } }

          // After saving, marks the hotel as not modified
        
     }

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @param filename the name of the file to save the hotel's state to
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        // Checks if the given filename is valid
        if (filename == null || filename.isBlank()) {
            throw new MissingFileAssociationException();
        }
        _filename = filename;
        save();  // Reuses the save() method to avoid code duplication
    }

    /**
     * Loads the serialized application's state from a specified file
     * 
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        // Automatically closes the file input stream
    try (ObjectInputStream ois = new ObjectInputStream (new BufferedInputStream (new FileInputStream(filename)))) {
        _hotel = (Hotel) ois.readObject();
        _filename = filename; // Deserializes the object and cast it to the Hotel class
        _hotel.setModified(false);  // Updates the filename
    } catch (IOException | ClassNotFoundException e) {
        throw new UnavailableFileException(filename);  // Throws exception for IO or class errors
    }
    }

    /**
     * Reads text input file and imports data into the hotel
     *
     * @param filename name of the text input file
     * @throws ImportFileException if there are issues while importing the file
     */
    public void importFile(String filename) throws ImportFileException {
        _hotel.importFile(filename);  // Calls the importFile method in the Hotel class
    }

    /**
     * Advances the season, affecting all trees in all habitats.
     * 
     * @return A string representing the current season after advancing.
     */
    public void advanceSeason() {
        _hotel.newseason();
        _hotel.updateTreesInAllHabitats(); 
        
        // Updates all trees after advancing the season
        // Returns the current season
    }
    public String seasonstr(){
        return _hotel.whatseason();
    }




    /**
     * Returns the current hotel instance.
     *
     * @return the current hotel
     */
    public Hotel getHotel(){return _hotel;} 


    /**
    *  Clears the current hotel data and resets the hotel instance
    */
    public void createNewApplication() {
        _hotel = new Hotel();
        _filename = null;

    }

    public int globalSatisfaction(){
        return _hotel.calculateTotalSatisfaction();
    }
}

    


