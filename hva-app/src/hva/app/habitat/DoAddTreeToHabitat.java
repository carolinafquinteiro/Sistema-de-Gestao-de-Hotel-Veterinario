package hva.app.habitat;

import pt.tecnico.uilib.forms.Form;
import hva.Hotel;
import hva.app.exceptions.DuplicateTreeKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.DuplicateTreeException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoAddTreeToHabitat extends Command<Hotel> {

    DoAddTreeToHabitat(Hotel receiver) {
        super(Label.ADD_TREE_TO_HABITAT, receiver);
        //FIXME add command fields if needed
    }

   @Override
    protected void execute() throws CommandException {
        //FIXME implement command
         try {
            // Requests the habitat identifier
            String habitatId = Form.requestString(Prompt.habitatKey());
            String treeId = Form.requestString(Prompt.treeKey());
            String treeName = Form.requestString(Prompt.treeName());
            String treeAge = Form.requestString(Prompt.treeAge());
            int age = Integer.parseInt(treeAge);
            String dificultytree = Form.requestString(Prompt.treeDifficulty());
            int dificulty = Integer.parseInt(dificultytree);
            String treeType;
            do {
                treeType = Form.requestString(Prompt.treeType());  // Converts to uppercase
            } while (!treeType.equals("PERENE") && !treeType.equals("CADUCA"));

            _receiver.addTree(habitatId, treeId, treeName, age , dificulty, treeType);
            _display.popup(_receiver.printTree(treeId));

            
            
        } catch (DuplicateTreeException e) {
            throw new DuplicateTreeKeyException(e.getKey());
          
            // Handles duplicate tree ID exception
            
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());

        } catch (Exception e) {
            e.printStackTrace();
            // Handles other errors
            
        }
    }
}
