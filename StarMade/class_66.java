import java.io.File;
import java.io.IOException;
import org.schema.schine.graphicsengine.core.ResourceException;

final class class_66
  extends class_72
{
  public final void a(class_73 paramclass_73)
  {
    try
    {
      class_73.field_135 = new class_1391();
      class_29.a18(new File("data//effects/explosionMaps/explode.dds"), class_73.field_135);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      throw new ResourceException("Explosion volume");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_66
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */