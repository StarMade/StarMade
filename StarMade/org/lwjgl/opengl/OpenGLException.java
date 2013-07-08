/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/* 22:   */public class OpenGLException
/* 23:   */  extends RuntimeException
/* 24:   */{
/* 25:   */  private static final long serialVersionUID = 1L;
/* 26:   */  
/* 46:   */  public OpenGLException(int gl_error_code)
/* 47:   */  {
/* 48:48 */    this(createErrorMessage(gl_error_code));
/* 49:   */  }
/* 50:   */  
/* 51:   */  private static String createErrorMessage(int gl_error_code) {
/* 52:52 */    String error_string = Util.translateGLErrorString(gl_error_code);
/* 53:53 */    return error_string + " (" + gl_error_code + ")";
/* 54:   */  }
/* 55:   */  
/* 60:   */  public OpenGLException() {}
/* 61:   */  
/* 65:   */  public OpenGLException(String message)
/* 66:   */  {
/* 67:67 */    super(message);
/* 68:   */  }
/* 69:   */  
/* 75:   */  public OpenGLException(String message, Throwable cause)
/* 76:   */  {
/* 77:77 */    super(message, cause);
/* 78:   */  }
/* 79:   */  
/* 84:   */  public OpenGLException(Throwable cause)
/* 85:   */  {
/* 86:86 */    super(cause);
/* 87:   */  }
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.OpenGLException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */