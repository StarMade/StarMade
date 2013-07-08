/*  1:   */package org.lwjgl;
/*  2:   */
/* 20:   */public class LWJGLException
/* 21:   */  extends Exception
/* 22:   */{
/* 23:   */  private static final long serialVersionUID = 1L;
/* 24:   */  
/* 42:   */  public LWJGLException() {}
/* 43:   */  
/* 60:   */  public LWJGLException(String msg)
/* 61:   */  {
/* 62:62 */    super(msg);
/* 63:   */  }
/* 64:   */  
/* 68:   */  public LWJGLException(String message, Throwable cause)
/* 69:   */  {
/* 70:70 */    super(message, cause);
/* 71:   */  }
/* 72:   */  
/* 75:   */  public LWJGLException(Throwable cause)
/* 76:   */  {
/* 77:77 */    super(cause);
/* 78:   */  }
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.LWJGLException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */