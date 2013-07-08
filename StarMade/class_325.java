import java.io.IOException;
import java.io.PrintStream;
import org.schema.schine.graphicsengine.core.ResourceException;

final class class_325
  extends class_72
{
  public final void a(class_73 paramclass_73)
  {
    try
    {
      System.err.println("Loading cube textures");
      (class_333.field_134 = new class_1395[10])[0] = class_969.a3().a7("data/./textures/block/overlays.png");
      class_333.field_134[1] = class_969.a3().a7("data/./textures/block/t000.png");
      class_333.field_134[2] = class_969.a3().a7("data/./textures/block/t001.png");
      class_333.field_134[3] = class_969.a3().a7("data/./textures/block/t002.png");
      System.err.println("Loading cube textures DONE");
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      throw new ResourceException("data/./textures/block/");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_325
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */