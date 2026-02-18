package hva.exceptions;
import java.io.Serial;

public class UnknownEmployeeException extends Exception {
  
    /** Class serial number. */
    @Serial
    private static final long serialVersionUID = 202407081733L;
  
    private final String _key;
  
    
    public UnknownEmployeeException(String key) {
      this._key = key;
    }
  
    /** @return the duplicate key */
    public String getKey() {
      return _key;
    }
  }
  