package hva.app.habitat;
import pt.tecnico.uilib.forms.Form;

import hva.Hotel;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import hva.exceptions.UnknownSpecieException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoChangeHabitatInfluence extends Command<Hotel> {

    DoChangeHabitatInfluence(Hotel receiver) {
        super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        try{
            String habitatId = Form.requestString(Prompt.habitatKey());
    
            String specieId = Form.requestString(hva.app.animal.Prompt.speciesKey());
            
            String influence;
            do {
                influence = Form.requestString(Prompt.habitatInfluence());  // Converts to uppercase
            } while (!influence.equals("POS") && !influence.equals("NEU") 
            && !influence.equals("NEG"));

            _receiver.changeHabitatInfluence(habitatId, specieId, influence);

        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());

        } catch (UnknownSpecieException e) {
            throw new UnknownSpeciesKeyException(e.getKey());

        }catch (Exception e) {
            e.printStackTrace();
            // Handles other errors
        }
        //FIXME implement command
    }

}
