package hva.app.employee;


import hva.Hotel;
import hva.render.RenderEmployees;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowAllEmployees extends Command<Hotel> {

    DoShowAllEmployees(Hotel receiver) {
        super(Label.SHOW_ALL_EMPLOYEES, receiver);
    }

    @Override
     protected final void execute() {
        RenderEmployees renderer = new RenderEmployees(); // Creates a new renderer
        _receiver.accept(renderer); // Accepts the visitor
        
        if (renderer.getOutput().length() != 0) {
            _display.popup(renderer.getOutput()); // Displays the generated output
        }
        
    }

}
