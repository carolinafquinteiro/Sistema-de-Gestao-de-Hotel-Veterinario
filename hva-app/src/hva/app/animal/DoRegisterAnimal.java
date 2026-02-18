package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.DuplicateAnimalKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.UnknownHabitatException;



//FIXME import other classes if needed

class DoRegisterAnimal extends Command<Hotel> {

    // Private final Hotel hotel;
    // Adding field to store the Hotel

    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        // Initializing the field
    }

    @Override
    
    protected final void execute() throws CommandException {
        try {
             // Requests the identifier for the new animal
            String animalId = Form.requestString(Prompt.animalKey());
            
            // Requests the name of the animal
            String animalName = Form.requestString(Prompt.animalName());

            // Requests the identifier of the species
            String speciesId = Form.requestString(Prompt.speciesKey());
            // If the species doesn't exist, requests the species name and registers it
            if (!_receiver.speciesExists(speciesId)){
                String speciesName = Form.requestString(Prompt.speciesName());
                _receiver.registerSpecies(speciesId, speciesName);
            }
            // Requests the identifier of the habitat
            String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());

            // Registers the new animal in the hotel
            _receiver.registerAnimal(animalId, animalName, speciesId, habitatId);
            
            
        } catch (DuplicateAnimalException e) {
            throw new DuplicateAnimalKeyException(e.getKey());
          
            // Handles the duplicate key exception
            
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());

        } catch (Exception e) {
            e.printStackTrace();
            // Handles other errors
            
        }
        
    }

}