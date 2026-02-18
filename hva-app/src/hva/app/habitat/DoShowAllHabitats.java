package hva.app.habitat;


import hva.Hotel;
import hva.render.RenderHabitats;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowAllHabitats extends Command<Hotel> {

    DoShowAllHabitats(Hotel receiver) {
        super(Label.SHOW_ALL_HABITATS, receiver);
    }

    @Override
     protected final void execute() {
        RenderHabitats renderer = new RenderHabitats(); // Creates a new renderer
        _receiver.accept(renderer); // Accepts the visitor
        
        if (renderer.getOutput().length() != 0) {
            _display.popup(renderer.getOutput()); // Displays the generated output
        }
    }
}
   
