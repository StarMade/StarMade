import java.io.PrintStream;
import org.schema.schine.graphicsengine.core.GLException;

final class class_335
  extends class_72
{
  public final void a(class_73 paramclass_73)
  {
    System.err.println("Init Shop FrameBuffer");
    try
    {
      if (class_949.field_1206.b1()) {
        (class_333.field_134 = new class_925(512, 512)).e();
      }
      return;
    }
    catch (GLException paramclass_73)
    {
      class_333.field_134 = null;
      paramclass_73.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_335
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */