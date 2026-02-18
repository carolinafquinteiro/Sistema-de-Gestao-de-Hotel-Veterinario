package hva.app.employee;


import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.DuplicateEmployeeKeyException;
import hva.exceptions.DuplicateEmployeeException;
import hva.app.exceptions.DuplicateHabitatKeyException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.DuplicateHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoRegisterEmployee extends Command<Hotel> {

    DoRegisterEmployee(Hotel receiver) {
        super(Label.REGISTER_EMPLOYEE, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
         try {
            // Requests the identifier of the new employee
            String employeeId = Form.requestString(Prompt.employeeKey());
            
            // Requests the name of the employee
            String employeeName = Form.requestString(Prompt.employeeName());

            // Requests the type of the employee 
             String employeeType;
            do {
                employeeType = Form.requestString(Prompt.employeeType());  // Converts to uppercase
            } while (!employeeType.equals("VET") && !employeeType.equals("TRT"));

            // Registers the new employee based on the type
            _receiver.registerEmployee(employeeId, employeeName, employeeType);

        } catch (DuplicateEmployeeException e) {
            throw new DuplicateEmployeeKeyException(e.getKey());
        
        } catch (Exception e) {
            e.printStackTrace();
            // Handles other errors
            
        }
    }

}

