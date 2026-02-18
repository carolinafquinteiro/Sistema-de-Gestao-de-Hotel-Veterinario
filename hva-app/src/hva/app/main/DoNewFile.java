package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoNewFile extends Command<HotelManager> {
    DoNewFile(HotelManager receiver) {
        super(Label.NEW_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {

        if (_receiver.isModified() && Form.confirm(Prompt.saveBeforeExit())) {
            // Calls the save command, if necessary
            DoSaveFile saveCommand = new DoSaveFile(_receiver);
            saveCommand.execute(); // Ensures this does not throw unhandled exceptions
        }
        _receiver.createNewApplication(); // Method to reset the state
    
    }
}
