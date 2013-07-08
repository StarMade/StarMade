import java.io.IOException;
import java.io.PrintStream;
import org.schema.schine.graphicsengine.core.ResourceException;

final class class_313
  extends class_72
{
  public final void a(class_73 paramclass_73)
  {
    System.err.println("Loading BACK ground");
    if (!class_949.field_1246.b1()) {
      paramclass_73 = "data//sky/milkyway/Milky-Way-texture-cube";
    } else {
      paramclass_73 = "data//sky/generic/generic";
    }
    try
    {
      class_333.field_134 = class_969.a3().a4(paramclass_73, "png");
      System.err.println("Loading BACK ground DONE");
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      throw new ResourceException("data/" + paramclass_73);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_313
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */