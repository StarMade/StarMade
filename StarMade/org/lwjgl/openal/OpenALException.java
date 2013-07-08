/*  1:   */package org.lwjgl.openal;
/*  2:   */
/* 19:   */public class OpenALException
/* 20:   */  extends RuntimeException
/* 21:   */{
/* 22:   */  private static final long serialVersionUID = 1L;
/* 23:   */  
/* 39:   */  public OpenALException() {}
/* 40:   */  
/* 56:   */  public OpenALException(int error_code)
/* 57:   */  {
/* 58:58 */    super("OpenAL error: " + AL10.alGetString(error_code) + " (" + error_code + ")");
/* 59:   */  }
/* 60:   */  
/* 64:   */  public OpenALException(String message)
/* 65:   */  {
/* 66:66 */    super(message);
/* 67:   */  }
/* 68:   */  
/* 73:   */  public OpenALException(String message, Throwable cause)
/* 74:   */  {
/* 75:75 */    super(message, cause);
/* 76:   */  }
/* 77:   */  
/* 81:   */  public OpenALException(Throwable cause)
/* 82:   */  {
/* 83:83 */    super(cause);
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.OpenALException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */