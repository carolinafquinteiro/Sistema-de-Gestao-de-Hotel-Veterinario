package hva.app.vaccine;

import hva.Hotel;
import hva.render.RenderVaccines;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowAllVaccines extends Command<Hotel> {

    DoShowAllVaccines(Hotel receiver) {
        super(Label.SHOW_ALL_VACCINES, receiver);
	//FIXME add command fields if needed
    }

    @Override
    
    protected final void execute() {
        RenderVaccines renderer = new RenderVaccines(); // Creates a new renderer
        _receiver.accept(renderer); // Accepts the visitor
        
        if (renderer.getOutput().length() != 0) {
            _display.popup(renderer.getOutput()); // Displays the generated output
        }
    }
        //FIXME implement command
    
}
