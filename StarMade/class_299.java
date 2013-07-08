import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_299
  extends class_960
{
  public class_299(class_1382 paramclass_1382)
  {
    super(paramclass_1382);
  }
  
  public final Vector3f a83()
  {
    Vector3f localVector3f1 = super.a83();
    Vector3f localVector3f2;
    (localVector3f2 = GlUtil.f(new Vector3f(), this.field_89)).scale(0.485F);
    localVector3f1.add(localVector3f2);
    return localVector3f1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_299
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */