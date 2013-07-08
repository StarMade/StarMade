/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/* 14:   */public class OpenCLException
/* 15:   */  extends RuntimeException
/* 16:   */{
/* 17:   */  private static final long serialVersionUID = 1L;
/* 18:   */  
/* 29:   */  public OpenCLException() {}
/* 30:   */  
/* 41:   */  public OpenCLException(String message)
/* 42:   */  {
/* 43:43 */    super(message);
/* 44:   */  }
/* 45:   */  
/* 46:   */  public OpenCLException(String message, Throwable cause) {
/* 47:47 */    super(message, cause);
/* 48:   */  }
/* 49:   */  
/* 50:   */  public OpenCLException(Throwable cause) {
/* 51:51 */    super(cause);
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.OpenCLException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */