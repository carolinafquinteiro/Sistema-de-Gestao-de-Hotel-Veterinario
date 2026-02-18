package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.DuplicateHabitatException;
import hva.app.exceptions.DuplicateHabitatKeyException;
//FIXME import other classes if needed

class DoRegisterHabitat extends Command<Hotel> {

    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
         try {
            // Request the identifier of the new habitat
            String habitatId = Form.requestString(Prompt.habitatKey());
            
            // Request the name of the habitat
            String habitatName = Form.requestString(Prompt.habitatName());

            // Request the area of the habitat
            String habitatArea = Form.requestString(Prompt.habitatArea());
            int area = Integer.parseInt(habitatArea);
           
            // Register the new habitat in the hotel
            _receiver.registerHabitat(habitatId, habitatName, area);
            
            
        } catch (DuplicateHabitatException e) {
            throw new DuplicateHabitatKeyException(e.getKey());
          
            // Handles the duplicate key exception
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handles other errors
            
        }
    }

}
