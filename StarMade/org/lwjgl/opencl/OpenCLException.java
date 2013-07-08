package org.lwjgl.opencl;

public class OpenCLException
  extends RuntimeException
{
  private static final long serialVersionUID = 1L;
  
  public OpenCLException() {}
  
  public OpenCLException(String message)
  {
    super(message);
  }
  
  public OpenCLException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public OpenCLException(Throwable cause)
  {
    super(cause);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.OpenCLException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */