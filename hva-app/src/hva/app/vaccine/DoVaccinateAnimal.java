package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.UnknownVaccineKeyException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.app.exceptions.VeterinarianNotAuthorizedException;
import hva.exceptions.InappropriateVaccineException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.UnknownVetException;
import hva.exceptions.VetNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoVaccinateAnimal extends Command<Hotel> {

    DoVaccinateAnimal(Hotel receiver) {
        super(Label.VACCINATE_ANIMAL, receiver);
        //FIXME add command fields if needed
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            // Requests the vaccine ID
            String vaccineId = Form.requestString(Prompt.vaccineKey());
            String vetId = Form.requestString(Prompt.veterinarianKey());
            String animalId = Form.requestString(hva.app.animal.Prompt.animalKey());
        
            _receiver.isVetauthorized(vetId, animalId, vaccineId);
            _receiver.vaccineAnimal(vaccineId, animalId, vetId);
    
        }catch (UnknownAnimalException e){
            throw new UnknownAnimalKeyException(e.getKey());
        }catch (UnknownVetException e){
            throw new UnknownVeterinarianKeyException(e.getKey());
        }catch (VetNotAuthorizedException e){
            throw new VeterinarianNotAuthorizedException(e.getKey(), e.getids());
        }catch (UnknownVaccineException e){
            throw new UnknownVaccineKeyException(e.getKey()); 
        }catch (InappropriateVaccineException e){
            _display.popup(Message.wrongVaccine(e.getvacine(), e.getanimal()));
        
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
