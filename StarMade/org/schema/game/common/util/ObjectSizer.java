package org.schema.game.common.util;

import java.io.PrintStream;
import org.schema.game.common.data.element.Element;

public final class ObjectSizer
{
  private static int jdField_field_2024_of_type_Int = 100;
  private static long jdField_field_2024_of_type_Long = 100L;
  
  private static void a()
  {
    try
    {
      System.gc();
      Thread.currentThread();
      Thread.sleep(jdField_field_2024_of_type_Long);
      System.runFinalization();
      Thread.currentThread();
      Thread.sleep(jdField_field_2024_of_type_Long);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
  
  private static long a1()
  {
    a();
    a();
    long l1 = Runtime.getRuntime().totalMemory();
    a();
    a();
    long l2 = Runtime.getRuntime().freeMemory();
    return l1 - l2;
  }
  
  private static long a2(Class paramClass)
  {
    long l1 = 0L;
    try
    {
      paramClass.getConstructor(new Class[0]);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      System.err.println(paramClass + " does not have a no-argument constructor.");
      return 0L;
    }
    Object[] arrayOfObject = new Object[jdField_field_2024_of_type_Int];
    try
    {
      long l2 = a1();
      for (int i = 0; i < arrayOfObject.length; i++) {
        arrayOfObject[i] = paramClass.newInstance();
      }
      l1 = Math.round((float)(a1() - l2) / 100.0F);
    }
    catch (Exception localException)
    {
      System.err.println("Cannot create object using " + paramClass);
    }
    return l1;
  }
  
  public static void main(String... paramVarArgs)
  {
    long l = a2(paramVarArgs = Element.class);
    System.out.println("Approximate size of " + paramVarArgs + " objects: " + l);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.util.ObjectSizer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */