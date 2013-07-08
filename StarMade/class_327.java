import java.io.IOException;
import java.io.PrintStream;
import org.schema.schine.graphicsengine.core.ResourceException;

final class class_327
  extends class_72
{
  public final void a(class_73 paramclass_73)
  {
    try
    {
      System.err.println("Loading marble seamless");
      class_333.field_135 = class_969.a3().a6("data/./textures/marble-seamless-texture.png", true);
      System.err.println("Loading marble seamless DONE");
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      throw new ResourceException("data/./textures/marble-seamless-texture.png");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_327
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */