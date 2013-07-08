/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/* 52:   */public final class Util
/* 53:   */{
/* 54:   */  public static void checkGLError()
/* 55:   */    throws OpenGLException
/* 56:   */  {
/* 57:57 */    int err = GL11.glGetError();
/* 58:58 */    if (err != 0) {
/* 59:59 */      throw new OpenGLException(err);
/* 60:   */    }
/* 61:   */  }
/* 62:   */  
/* 65:   */  public static String translateGLErrorString(int error_code)
/* 66:   */  {
/* 67:67 */    switch (error_code) {
/* 68:   */    case 0: 
/* 69:69 */      return "No error";
/* 70:   */    case 1280: 
/* 71:71 */      return "Invalid enum";
/* 72:   */    case 1281: 
/* 73:73 */      return "Invalid value";
/* 74:   */    case 1282: 
/* 75:75 */      return "Invalid operation";
/* 76:   */    case 1283: 
/* 77:77 */      return "Stack overflow";
/* 78:   */    case 1284: 
/* 79:79 */      return "Stack underflow";
/* 80:   */    case 1285: 
/* 81:81 */      return "Out of memory";
/* 82:   */    case 32817: 
/* 83:83 */      return "Table too large";
/* 84:   */    case 1286: 
/* 85:85 */      return "Invalid framebuffer operation";
/* 86:   */    }
/* 87:87 */    return null;
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */