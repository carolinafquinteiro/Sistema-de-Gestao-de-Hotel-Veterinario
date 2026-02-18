package hva.exceptions;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to add an animal with a duplicate key.
 */
public class VetNotAuthorizedException extends Exception {
  
  /** Class serial number. */
  @Serial
  private static final long serialVersionUID = 202407081733L;

  private final String _key;
  
  private final String _ids ;

  /** 
   * Constructs a DuplicateAnimalException with the specified key.
   * @param key the duplicate key that caused the exception
   */
  public VetNotAuthorizedException(String key, String ids) {
    this._key = key;
    this._ids = ids;

  }

  /** @return the duplicate key */
  public String getKey() {
    return _key;
  
  }
  public String getids() {
    return _ids;
  
  }
}