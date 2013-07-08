/*  1:   */package org.lwjgl.input;
/*  2:   */
/*  3:   */import java.lang.reflect.Field;
/*  4:   */import java.lang.reflect.Method;
/*  5:   */import java.security.AccessController;
/*  6:   */import java.security.PrivilegedActionException;
/*  7:   */import java.security.PrivilegedExceptionAction;
/*  8:   */import org.lwjgl.opengl.Display;
/*  9:   */import org.lwjgl.opengl.InputImplementation;
/* 10:   */
/* 44:   */final class OpenGLPackageAccess
/* 45:   */{
/* 46:   */  static final Object global_lock;
/* 47:   */  
/* 48:   */  static
/* 49:   */  {
/* 50:   */    try
/* 51:   */    {
/* 52:52 */      global_lock = AccessController.doPrivileged(new PrivilegedExceptionAction() {
/* 53:   */        public Object run() throws Exception {
/* 54:54 */          Field lock_field = Class.forName("org.lwjgl.opengl.GlobalLock").getDeclaredField("lock");
/* 55:55 */          lock_field.setAccessible(true);
/* 56:56 */          return lock_field.get(null);
/* 57:   */        }
/* 58:   */      });
/* 59:   */    } catch (PrivilegedActionException e) {
/* 60:60 */      throw new Error(e);
/* 61:   */    }
/* 62:   */  }
/* 63:   */  
/* 65:   */  static InputImplementation createImplementation()
/* 66:   */  {
/* 67:   */    try
/* 68:   */    {
/* 69:69 */      (InputImplementation)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/* 70:   */        public InputImplementation run() throws Exception {
/* 71:71 */          Method getImplementation_method = Display.class.getDeclaredMethod("getImplementation", new Class[0]);
/* 72:72 */          getImplementation_method.setAccessible(true);
/* 73:73 */          return (InputImplementation)getImplementation_method.invoke(null, new Object[0]);
/* 74:   */        }
/* 75:   */      });
/* 76:   */    } catch (PrivilegedActionException e) {
/* 77:77 */      throw new Error(e);
/* 78:   */    }
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.OpenGLPackageAccess
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */