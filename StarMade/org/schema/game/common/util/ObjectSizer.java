/*  1:   */package org.schema.game.common.util;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import org.schema.game.common.data.element.Element;
/*  5:   */
/*  9:   */public final class ObjectSizer
/* 10:   */{
/* 11:11 */  private static int jdField_a_of_type_Int = 100;
/* 12:   */  
/* 14:14 */  private static long jdField_a_of_type_Long = 100L;
/* 15:   */  
/* 16:   */  private static void a() {
/* 17:   */    try {
/* 18:18 */      System.gc();
/* 19:19 */      Thread.currentThread();
/* 20:20 */      Thread.sleep(jdField_a_of_type_Long);
/* 21:21 */      System.runFinalization();
/* 22:22 */      Thread.currentThread();
/* 23:23 */      Thread.sleep(jdField_a_of_type_Long); return;
/* 24:   */    } catch (InterruptedException localInterruptedException) {
/* 25:25 */      
/* 26:   */      
/* 27:27 */        localInterruptedException;
/* 28:   */    }
/* 29:   */  }
/* 30:   */  
/* 31:   */  private static long a() {
/* 32:30 */    a();a();
/* 33:31 */    long l1 = Runtime.getRuntime().totalMemory();
/* 34:   */    
/* 35:33 */    a();a();
/* 36:34 */    long l2 = Runtime.getRuntime().freeMemory();
/* 37:   */    
/* 38:36 */    return l1 - l2;
/* 39:   */  }
/* 40:   */  
/* 46:   */  private static long a(Class paramClass)
/* 47:   */  {
/* 48:46 */    long l1 = 0L;
/* 49:   */    
/* 51:   */    try
/* 52:   */    {
/* 53:51 */      paramClass.getConstructor(new Class[0]);
/* 54:   */    }
/* 55:   */    catch (NoSuchMethodException localNoSuchMethodException) {
/* 56:54 */      System.err.println(paramClass + " does not have a no-argument constructor.");
/* 57:55 */      return 0L;
/* 58:   */    }
/* 59:   */    
/* 62:60 */    Object[] arrayOfObject = new Object[jdField_a_of_type_Int];
/* 63:   */    
/* 66:   */    try
/* 67:   */    {
/* 68:66 */      long l2 = a();
/* 69:67 */      for (int i = 0; i < arrayOfObject.length; i++) {
/* 70:68 */        arrayOfObject[i] = paramClass.newInstance();
/* 71:   */      }
/* 72:   */      
/* 75:73 */      l1 = Math.round((float)(a() - l2) / 100.0F);
/* 76:   */    }
/* 77:   */    catch (Exception localException) {
/* 78:76 */      System.err.println("Cannot create object using " + paramClass);
/* 79:   */    }
/* 80:78 */    return l1;
/* 81:   */  }
/* 82:   */  
/* 93:   */  public static void main(String... paramVarArgs)
/* 94:   */  {
/* 95:93 */    long l = a(paramVarArgs = Element.class);
/* 96:   */    
/* 97:95 */    System.out.println("Approximate size of " + paramVarArgs + " objects: " + l);
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.util.ObjectSizer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */