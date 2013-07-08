package org.schema.game.common.staremote;

import class_1261;
import class_1266;
import class_1278;
import class_1315;
import class_371;
import class_52;
import class_531;
import class_535;
import class_539;
import class_899;
import class_949;
import java.awt.EventQueue;
import java.io.IOException;
import org.schema.game.common.Starter;
import org.schema.game.network.StarMadeServerStats;

public class Staremote
{
  private class_371 jdField_field_2145_of_type_Class_371;
  private class_531 jdField_field_2145_of_type_Class_531;
  private class_1261 jdField_field_2145_of_type_Class_1261;
  public static class_1278 field_2145;
  
  public static void main(String[] paramArrayOfString)
  {
    class_1266.a();
    class_949.a1();
    try
    {
      Starter.d();
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    new Staremote().a();
  }
  
  public final void a()
  {
    EventQueue.invokeLater(new class_539(this));
  }
  
  public final void a1(class_371 paramclass_371)
  {
    if (jdField_field_2145_of_type_Class_1278 != null) {
      jdField_field_2145_of_type_Class_1278.b();
    }
    try
    {
      StarMadeServerStats localStarMadeServerStats = paramclass_371.a4().a17();
      this.jdField_field_2145_of_type_Class_531.a(localStarMadeServerStats);
      if (this.jdField_field_2145_of_type_Class_1261 != null)
      {
        paramclass_371 = paramclass_371.a4().a18();
        this.jdField_field_2145_of_type_Class_1261.a(paramclass_371);
        this.jdField_field_2145_of_type_Class_1261 = null;
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
  
  public final void a2(class_1315 paramclass_1315)
  {
    paramclass_1315 = new class_899(paramclass_1315.field_1493, paramclass_1315.jdField_field_1492_of_type_Int, paramclass_1315.jdField_field_1492_of_type_JavaLangString);
    new Thread(new class_535(this, paramclass_1315)).start();
  }
  
  public static String a3()
  {
    return "./.starmotecon";
  }
  
  public final void b()
  {
    Staremote localStaremote = this;
    try
    {
      localStaremote.jdField_field_2145_of_type_Class_371.disconnect();
    }
    catch (Exception localException)
    {
      localException;
    }
    System.exit(0);
  }
  
  public final void a4(class_1261 paramclass_1261)
  {
    this.jdField_field_2145_of_type_Class_1261 = paramclass_1261;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.staremote.Staremote
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */