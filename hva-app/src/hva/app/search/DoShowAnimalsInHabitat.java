package hva.app.search;

import hva.Hotel;
import hva.render.RenderAnimalsinHabitat;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;

//FIXME import other classes if needed

class DoShowAnimalsInHabitat extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_IN_HABITAT, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
        

        // Creates an instance of RenderAnimalsinHabitat with the habitat ID
        RenderAnimalsinHabitat renderer = new RenderAnimalsinHabitat(habitatId);

        try {
            _receiver.doesHabitatExist(habitatId);
            // Applies the visitor pattern to the hotel to calculate animal satisfaction
            _receiver.accept(renderer);

            // Displays the generated output if there is data
            if (renderer.getOutput().length() != 0) {
                _display.popup(renderer.getOutput());
            }

        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());
          
            // Handles the exception for unknown habitat key
            
        } 
    }

}