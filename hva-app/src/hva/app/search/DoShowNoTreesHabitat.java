package hva.app.search;
import hva.Hotel;
import hva.render.RenderHabitatsWithoutTrees;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

public class DoShowNoTreesHabitat extends Command<Hotel>{
    DoShowNoTreesHabitat(Hotel receiver) {
        super(Label.NO_TREES, receiver);
    }

    @Override
    protected final void execute() {
        RenderHabitatsWithoutTrees renderer = new RenderHabitatsWithoutTrees(); // Creates a new renderer
        _receiver.accept(renderer); // Accepts the visitor
        
        if (renderer.getOutput().length() != 0) {
            _display.popup(renderer.getOutput()); // Displays the generated output
        }
    }
}


