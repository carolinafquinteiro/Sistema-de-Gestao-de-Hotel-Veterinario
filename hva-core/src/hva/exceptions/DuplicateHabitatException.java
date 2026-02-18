package hva.exceptions;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to add an animal with a duplicate key.
 */
public class DuplicateHabitatException extends Exception {
  
  /** Class serial number. */
  @Serial
  private static final long serialVersionUID = 202407081733L;

  private final String _key;

  /** 
   * Constructs a DuplicateAnimalException with the specified key.
   * @param key the duplicate key that caused the exception
   */
  public DuplicateHabitatException(String key) {
    this._key = key;
  }

  /** @return the duplicate key */
  public String getKey() {
    return _key;
  }
}