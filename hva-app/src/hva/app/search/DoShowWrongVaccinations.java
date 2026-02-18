package hva.app.search;

import hva.Hotel;
import hva.render.RenderDamageVaccine;
import hva.render.RenderVaccines;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowWrongVaccinations extends Command<Hotel> {

    DoShowWrongVaccinations(Hotel receiver) {
        super(Label.WRONG_VACCINATIONS, receiver);
	//FIXME add command fields if needed
    }

    @Override
    protected final void execute() {
        RenderDamageVaccine renderer = new RenderDamageVaccine(); // Creates a new renderer
        _receiver.accept(renderer); // Accepts the visitor
        
        if (renderer.getOutput().length() != 0) {
            _display.popup(renderer.getOutput()); // Displays the generated output
        }
    }  

}
