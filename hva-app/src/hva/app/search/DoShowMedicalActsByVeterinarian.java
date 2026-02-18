package hva.app.search;

import hva.Hotel;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.exceptions.UnknownVetException;
import hva.render.RenderVetVaccination;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

    DoShowMedicalActsByVeterinarian(Hotel receiver) {
        super(Label.MEDICAL_ACTS_BY_VET, receiver);
	//FIXME add command fields if needed
    }

    @Override
    
    protected void execute() throws CommandException {
        String vetId = Form.requestString(hva.app.employee.Prompt.employeeKey());
        

        // Creates an instance of RenderVetVaccination with the veterinarian's ID
        RenderVetVaccination renderer = new RenderVetVaccination(vetId);

        try {
            _receiver.doesVetExist(vetId);
            // Applies the visitor pattern to the hotel to calculate the satisfaction of the animal
            _receiver.accept(renderer);

            // Displays the output generated if there is data
            if (renderer.getOutput().length() != 0) {
                _display.popup(renderer.getOutput());
            }

        } catch (UnknownVetException e) {
            throw new UnknownVeterinarianKeyException(e.getKey());
          
            // Handles the unknown veterinarian key exception
            
        } 
    }

}
