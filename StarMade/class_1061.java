import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;

final class class_1061
  implements Runnable
{
  class_1061(String paramString) {}
  
  public final void run()
  {
    Object localObject1 = new class_1017(this);
    for (Object localObject2 : new File(class_1041.field_149).listFiles((FilenameFilter)localObject1))
    {
      System.err.println("[SERVER][SEGMENTCONTROLLER] PERMANENTLY DELETING ENTITY DATA: " + localObject2.getName());
      localObject2.delete();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1061
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */