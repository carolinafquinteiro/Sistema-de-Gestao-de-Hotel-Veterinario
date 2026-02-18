package hva.app.vaccine;

import hva.Hotel;
import hva.render.RenderVaccinationRecords;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowVaccinations extends Command<Hotel> {

    DoShowVaccinations(Hotel receiver) {
        super(Label.SHOW_VACCINATIONS, receiver);
    }

    @Override
    protected final void execute() {
        RenderVaccinationRecords renderer = new RenderVaccinationRecords(); // Creates a new renderer
        _receiver.accept(renderer); // Accepts the visitor
        
        if (renderer.getOutput().length() != 0) {
            _display.popup(renderer.getOutput()); // Displays the generated output
        }
    }
       
}

