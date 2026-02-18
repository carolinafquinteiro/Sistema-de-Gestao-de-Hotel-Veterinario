package hva.app.animal;

import hva.Hotel;  // Importa a classe Hotel
import hva.render.RenderAnimals;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoShowAllAnimals extends Command<Hotel> {

    DoShowAllAnimals(Hotel receiver) {
        super(Label.SHOW_ALL_ANIMALS, receiver);  // Uses the appropriate label
    }

    @Override
    protected final void execute() {
        RenderAnimals renderer = new RenderAnimals(); // Creates a new renderer
        _receiver.accept(renderer); // Accepts the visitor
        
        if (renderer.getOutput().length() != 0) {
            _display.popup(renderer.getOutput()); // Displays the generated output
        }
    }
}


