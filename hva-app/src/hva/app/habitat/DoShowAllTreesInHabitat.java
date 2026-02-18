package hva.app.habitat;

import hva.Hotel;
import hva.render.RenderTrees;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;
//FIXME import other classes if needed

class DoShowAllTreesInHabitat extends Command<Hotel> {

    DoShowAllTreesInHabitat(Hotel receiver) {
        super(Label.SHOW_TREES_IN_HABITAT, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        String habitatId = Form.requestString(Prompt.habitatKey());
        

        // Creates an instance of RenderTrees with the habitat ID
        RenderTrees renderer = new RenderTrees(habitatId);

        try {
            _receiver.doesHabitatExist(habitatId);
            // Applies the visitor pattern to the hotel to process the tree data
            _receiver.accept(renderer);

            // Displays the generated output if there is data
            if (renderer.getOutput().length() != 0) {
                _display.popup(renderer.getOutput());
            }

        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());
          
            // Handles the unknown habitat key exception
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handles other errors
            
        }
    }

}
