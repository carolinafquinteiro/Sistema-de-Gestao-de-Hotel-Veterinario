package hva.exceptions;
import java.io.Serial;

public class UnknownHabitatException extends Exception {
  
    /** Class serial number. */
    @Serial
    private static final long serialVersionUID = 202407081733L;
  
    private final String _key;
  
   
    public UnknownHabitatException(String key) {
      this._key = key;
    }
  
    /** @return the duplicate key */
    public String getKey() {
      return _key;
    }
  }