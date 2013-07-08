package org.lwjgl.openal;

public class OpenALException
  extends RuntimeException
{
  private static final long serialVersionUID = 1L;
  
  public OpenALException() {}
  
  public OpenALException(int error_code)
  {
    super("OpenAL error: " + AL10.alGetString(error_code) + " (" + error_code + ")");
  }
  
  public OpenALException(String message)
  {
    super(message);
  }
  
  public OpenALException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public OpenALException(Throwable cause)
  {
    super(cause);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.openal.OpenALException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */