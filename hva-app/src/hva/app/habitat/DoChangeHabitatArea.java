package hva.app.habitat;
import pt.tecnico.uilib.forms.Form;

import hva.Hotel;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoChangeHabitatArea extends Command<Hotel> {

    DoChangeHabitatArea(Hotel receiver) {
        super(Label.CHANGE_HABITAT_AREA, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        try{
        String habitatId = Form.requestString(Prompt.habitatKey());
    
        String habitatArea = Form.requestString(Prompt.habitatArea());
        int area = Integer.parseInt(habitatArea);
           
            // Registers the new habitat area in the hotel
         _receiver.changeHabitatArea(habitatId, area);
        
        
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());

        } catch (Exception e) {
            e.printStackTrace();
            // Handles other errors
        }
    }

}
