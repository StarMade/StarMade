/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.LWJGLException;
/*  4:   */import org.lwjgl.Sys;
/*  5:   */
/* 46:   */final class WindowsRegistry
/* 47:   */{
/* 48:   */  static final int HKEY_CLASSES_ROOT = 1;
/* 49:   */  static final int HKEY_CURRENT_USER = 2;
/* 50:   */  static final int HKEY_LOCAL_MACHINE = 3;
/* 51:   */  static final int HKEY_USERS = 4;
/* 52:   */  
/* 53:   */  static String queryRegistrationKey(int root_key, String subkey, String value)
/* 54:   */    throws LWJGLException
/* 55:   */  {
/* 56:56 */    switch (root_key) {
/* 57:   */    case 1: 
/* 58:   */    case 2: 
/* 59:   */    case 3: 
/* 60:   */    case 4: 
/* 61:61 */      break;
/* 62:   */    default: 
/* 63:63 */      throw new IllegalArgumentException("Invalid enum: " + root_key);
/* 64:   */    }
/* 65:65 */    return nQueryRegistrationKey(root_key, subkey, value);
/* 66:   */  }
/* 67:   */  
/* 68:   */  private static native String nQueryRegistrationKey(int paramInt, String paramString1, String paramString2)
/* 69:   */    throws LWJGLException;
/* 70:   */  
/* 71:   */  static {}
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsRegistry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */