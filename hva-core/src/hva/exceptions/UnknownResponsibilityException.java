package hva.exceptions;
import java.io.Serial;


public class UnknownResponsibilityException extends Exception {
  
    /** Class serial number. */
    @Serial
    private static final long serialVersionUID = 202407081733L;
  
    private final String _keyEmployee;
    private final String _keyResponsibility;
  
   
    public UnknownResponsibilityException(String keyEmployee, String keyResponsibility) {
      this._keyEmployee = keyEmployee;
      this._keyResponsibility = keyResponsibility;

    }
  
    /** @return the duplicate key */
    public String getKeyEmployee() {
      return _keyEmployee;
    }

    public String getKeyResponsibility() {
      return _keyResponsibility;
    }
  }