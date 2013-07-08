import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.game.common.updater.CheckSumFailedException;

final class class_1275
  implements Runnable
{
  class_1275(class_1182 paramclass_1182) {}
  
  public final void run()
  {
    try
    {
      System.err.println("VERSIONS: \n" + class_1182.a8(this.field_1461));
      localObject1 = (class_1205)class_1182.a8(this.field_1461).get(class_1182.a8(this.field_1461).size() - 1);
      this.field_1461.a6((class_1205)localObject1);
      ((class_1205)localObject1).a1();
      this.field_1461.b();
      this.field_1461.a2((class_1205)localObject1, "./StarMade/");
      class_1182.b2(this.field_1461);
      this.field_1461.notifyObservers("Update Successfull!");
      try
      {
        Thread.sleep(1000L);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
      class_1182.c1(this.field_1461);
      this.field_1461.notifyObservers("reset");
      return;
    }
    catch (IOException localIOException1)
    {
      if (((IOException)localObject1).getMessage().contains("Access is denied")) {
        try
        {
          String str = new File("./StarMade/").getAbsolutePath().replace("\\.\\", "\\").replace("\\\\", "\\").replace("/./", "/").replace("//", "/");
          throw new IOException(((IOException)localObject1).getMessage() + "\nYour operating System denies access \nto where you are trying to install StarMade (for good reasons)\n" + str + "\n\nTo solve this Problem,\nPlease Execute this JAVA file somewhere in your Own User Folder,\nOr Force the install by executing this file as administrator");
        }
        catch (IOException localIOException2)
        {
          class_29.a12(localIOException2;
        }
      }
      return;
    }
    catch (CheckSumFailedException localCheckSumFailedException)
    {
      Object localObject1;
      class_29.a12((Exception)localObject1);
      return;
    }
    finally
    {
      class_1182.a9(this.field_1461);
      class_1182.d1(this.field_1461);
      this.field_1461.notifyObservers("finished");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1275
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */