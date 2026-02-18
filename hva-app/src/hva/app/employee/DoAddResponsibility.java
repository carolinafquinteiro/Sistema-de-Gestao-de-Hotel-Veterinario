package hva.app.employee;
import pt.tecnico.uilib.forms.Form;

import hva.Hotel;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.app.exceptions.NoResponsibilityException;

import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoAddResponsibility extends Command<Hotel> {

    DoAddResponsibility(Hotel receiver) {
        super(Label.ADD_RESPONSABILITY, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
         try {
            // Requests the identifier of the new employee
            String employeeId = Form.requestString(Prompt.employeeKey());
            
            // Requests the responsibility of the employee
            String employeeResponsibility = Form.requestString(Prompt.responsibilityKey());
            // Registers the new employee with the given responsibility
            _receiver.giveResponsibility(employeeId, employeeResponsibility);

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
