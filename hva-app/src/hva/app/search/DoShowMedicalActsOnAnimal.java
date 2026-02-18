package hva.app.search;

import hva.Hotel;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.exceptions.UnknownAnimalException;
import hva.render.RenderAnimalVaccination;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowMedicalActsOnAnimal extends Command<Hotel> {

    DoShowMedicalActsOnAnimal(Hotel receiver) {
        super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
	//FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        String animalId = Form.requestString(hva.app.animal.Prompt.animalKey());
        

        // Creates an instance of RenderAnimalVaccination with the animal's ID
        RenderAnimalVaccination renderer = new RenderAnimalVaccination(animalId);

        try {
            _receiver.doesAnimalExist(animalId);
            // Applies the visitor pattern to the hotel to calculate the satisfaction of the animal
            _receiver.accept(renderer);

            // Displays the output generated if there is data
            if (renderer.getOutput().length() != 0) {
                _display.popup(renderer.getOutput());
            }

        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(e.getKey());
          
            // Handles the unknown animal key exception
            
        } 
    }
}
