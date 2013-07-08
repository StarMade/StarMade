package org.lwjgl;

public class LWJGLException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  
  public LWJGLException() {}
  
  public LWJGLException(String msg)
  {
    super(msg);
  }
  
  public LWJGLException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public LWJGLException(Throwable cause)
  {
    super(cause);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.LWJGLException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */