import java.io.IOException;
import java.io.PrintStream;
import org.schema.schine.graphicsengine.core.ResourceException;

final class class_315
  extends class_72
{
  public final void a(class_73 paramclass_73)
  {
    try
    {
      System.err.println("Loading effects");
      (class_333.field_135 = new class_1395[2])[0] = class_969.a3().a6("data/./effects/dudvmap.jpg", true);
      class_333.field_135[1] = class_969.a3().a6("data/./effects/noise.jpg", true);
      System.err.println("Loading effects done");
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      throw new ResourceException("data/./effects/");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_315
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */