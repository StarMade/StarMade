import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.ResourceException;

final class class_323
  extends class_72
{
  public final void a(class_73 paramclass_73)
  {
    System.err.println("Init noise volume");
    GlUtil.f1();
    class_333.field_134 = new class_1391();
    try
    {
      class_29.a18(new File("data//effects/thruster/NoiseVolume.dds"), class_333.field_134);
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      throw new ResourceException("noise volume");
    }
    GlUtil.f1();
    System.err.println("Init noise volume DONE");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_323
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */