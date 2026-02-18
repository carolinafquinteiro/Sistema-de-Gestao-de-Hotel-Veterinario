
package hva.app.search;
import hva.Hotel;
import hva.render.RenderAnimalsWithoutVaccines;
import hva.render.RenderHabitatsWithoutTrees;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

public class DoShowAnimalswithoutVaccines extends Command<Hotel>{
    DoShowAnimalswithoutVaccines(Hotel receiver) {
        super(Label.NO_VACCINATION, receiver);
    }

    @Override
    protected final void execute() {
        RenderAnimalsWithoutVaccines renderer = new RenderAnimalsWithoutVaccines(); // Creates a new renderer
        _receiver.accept(renderer); // Accepts the visitor
        
        if (renderer.getOutput().length() != 0) {
            _display.popup(renderer.getOutput()); // Displays the generated output
        }
    }
}