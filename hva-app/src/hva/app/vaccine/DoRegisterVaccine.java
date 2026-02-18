package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.DuplicateVaccineKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.UnknownSpecieException;

//FIXME import other classes if needed

class DoRegisterVaccine extends Command<Hotel> {
    private final Hotel receiver;

    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);
        this.receiver = receiver;
	//FIXME add command fields if needed
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            // Requests the vaccine identifier
            String vaccineId = Form.requestString(Prompt.vaccineKey());

            // Requests the name of the vaccine
            String vaccineName = Form.requestString(Prompt.vaccineName());

            // Requests the list of species IDs, separated by commas
            String speciesKeysInput = Form.requestString(Prompt.listOfSpeciesKeys());
            speciesKeysInput = speciesKeysInput.trim();

            // Splits the IDs and removes whitespace from each ID   
            String[] speciesIds = speciesKeysInput.split("\\s*,\\s*");
            for (int i = 0; i < speciesIds.length; i++) {
                speciesIds[i] = speciesIds[i].trim(); // Removes whitespace from each ID
            }

            // Registers the new vaccine in the hotel
            receiver.registerVaccine(vaccineId, vaccineName, speciesIds);

        

        } catch (DuplicateVaccineException e) {
            throw new DuplicateVaccineKeyException(e.getKey());
            // Handles the duplicate key exception
            
        } catch (UnknownSpecieException e) {
            throw new UnknownSpeciesKeyException(e.getKey());
            // Handles the unknown species key exception
            
        } catch (Exception e) {
            // Handles other errors
            
        }
    }

}
