
package hva.exceptions;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to add an animal with a duplicate key.
 */
public class InappropriateVaccineException extends Exception {
  
  /** Class serial number. */
  @Serial
  private static final long serialVersionUID = 202407081733L;

  private final String _idvacine;
  
  private final String _idanimal ;

  /** 
   * Constructs a DuplicateAnimalException with the specified key.
   * @param key the duplicate key that caused the exception
   */
  public InappropriateVaccineException(String idvacine, String idanimal) {
    this._idvacine = idvacine;
    this._idanimal = idanimal;

  }

  /** @return the duplicate key */
  public String getvacine() {
    return _idvacine;
  
  }
  public String getanimal() {
    return _idanimal;
  
  }
}