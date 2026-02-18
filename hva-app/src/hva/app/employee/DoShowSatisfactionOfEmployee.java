package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.exceptions.UnknownEmployeeException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowSatisfactionOfEmployee extends Command<Hotel> {

    DoShowSatisfactionOfEmployee(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
        //FIXME add command fields if needed
    }

    @Override
   
    protected final void execute() throws CommandException {
        
        try {
            String employeeId = Form.requestString(Prompt.employeeKey());
            _receiver.doesEmployeeExist(employeeId);
            _display.popup(_receiver.calculateemployeesatisfaction(employeeId));

        } catch (UnknownEmployeeException e) {
            // If the employee is not found, an error message is displayed
            throw new UnknownEmployeeKeyException(e.getKey());
        }
     
    }
}
