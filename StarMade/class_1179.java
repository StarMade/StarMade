import java.io.IOException;
import java.util.ArrayList;
import org.schema.game.common.controller.SegmentOutOfBoundsException;
import org.schema.game.server.controller.GameServerController;

public class class_1179
  extends Thread
{
  private class_1041 jdField_field_1387_of_type_Class_1041;
  
  public class_1179(class_1041 paramclass_1041)
  {
    this.jdField_field_1387_of_type_Class_1041 = paramclass_1041;
  }
  
  private class_1202 a()
  {
    synchronized (this.jdField_field_1387_of_type_Class_1041.e2())
    {
      while (this.jdField_field_1387_of_type_Class_1041.e2().isEmpty()) {
        this.jdField_field_1387_of_type_Class_1041.e2().wait();
      }
      return (class_1202)this.jdField_field_1387_of_type_Class_1041.e2().remove(0);
    }
  }
  
  public void run()
  {
    try
    {
      try
      {
        for (;;)
        {
          class_1202 localclass_1202;
          if ((localclass_1202 = a()).a3())
          {
            this.jdField_field_1387_of_type_Class_1041.a59();
            GameServerController.b7(localclass_1202);
          }
          else
          {
            this.jdField_field_1387_of_type_Class_1041.a59();
            GameServerController.a42(localclass_1202);
          }
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          localIOException;
        }
      }
      catch (SegmentOutOfBoundsException localSegmentOutOfBoundsException)
      {
        localSegmentOutOfBoundsException;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
      if (!jdField_field_1387_of_type_Boolean) {
        throw new AssertionError("Thread stopped");
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1179
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */