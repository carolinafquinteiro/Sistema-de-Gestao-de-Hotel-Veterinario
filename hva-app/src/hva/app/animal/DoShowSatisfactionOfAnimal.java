package hva.app.animal;

import hva.Hotel;
import hva.app.exceptions.UnknownAnimalKeyException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.exceptions.UnknownAnimalException;

class DoShowSatisfactionOfAnimal extends Command<Hotel> {

    DoShowSatisfactionOfAnimal(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        
        try {
            String animalId = Form.requestString(Prompt.animalKey());

            _receiver.doesAnimalExist(animalId);
            _display.popup(_receiver.calculateAnimalSatisfaction(animalId));

        } catch (UnknownAnimalException e) {
            // If the animal is not found, an error message is displayed
            throw new UnknownAnimalKeyException(e.getKey());
        }
    }
}

