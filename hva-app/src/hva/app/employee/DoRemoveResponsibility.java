package hva.app.employee;
import pt.tecnico.uilib.forms.Form;

import hva.Hotel;
import hva.app.exceptions.NoResponsibilityException;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoRemoveResponsibility extends Command<Hotel> {

    DoRemoveResponsibility(Hotel receiver) {
        super(Label.REMOVE_RESPONSABILITY, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
      try {
            // Requests the identifier of the employee
            String employeeId = Form.requestString(Prompt.employeeKey());
            
            // Requests the responsibility of the employee
            String employeeResponsibility = Form.requestString(Prompt.responsibilityKey());
            // Assigns the new responsibility to the employee based on the type
            _receiver.takeResponsibility(employeeId, employeeResponsibility);

        } catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(e.getKey());

        } catch (UnknownResponsibilityException e) {
            throw new NoResponsibilityException(e.getKeyEmployee(), e.getKeyResponsibility());


        } catch (Exception e) {
            e.printStackTrace();
            // Handles other errors
            
        }
    }

}
