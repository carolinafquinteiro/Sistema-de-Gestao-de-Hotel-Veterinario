package hva.app.animal;

import hva.Hotel;
import hva.app.exceptions.DuplicateAnimalKeyException;
import hva.exceptions.DuplicateAnimalException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.exceptions.UnknownAnimalException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;
//FIXME import other classes if needed

class DoTransferToHabitat extends Command<Hotel> {

    DoTransferToHabitat(Hotel hotel) {
        super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
        //FIXME add command fields if needed
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String animalId = Form.requestString(Prompt.animalKey());
            String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
            _receiver.transfertoHabitat(animalId, habitatId);

        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(e.getKey());

        
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());

    }



          

        //FIXME implement command
    }

}
