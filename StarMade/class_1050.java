import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_1050
  extends Camera
{
  private Vector3f field_89;
  private Vector3f field_90 = new Vector3f();
  
  public class_1050(class_960 paramclass_960, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    super(paramclass_960);
    this.field_89 = paramVector3f1;
    this.field_90 = new Vector3f(paramVector3f2);
    getWorldTransform().setIdentity();
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (paramclass_941.a() <= 0.0F) {
      return;
    }
    Vector3f localVector3f1;
    Vector3f localVector3f2;
    Vector3f localVector3f3;
    if (this.field_89 != null)
    {
      localVector3f1 = new Vector3f();
      (localVector3f2 = new Vector3f()).sub(this.field_89, getWorldTransform().origin);
      (localVector3f3 = new Vector3f(localVector3f2)).normalize();
      localVector3f3.sub(this.field_90);
      if (localVector3f3.length() > 0.1F)
      {
        GlUtil.a21(new Vector3f(this.field_90), new Vector3f(localVector3f2), localVector3f1);
        localVector3f1.normalize();
        localVector3f1.scale((float)(paramclass_941.a() * 0.35D));
        this.field_90.add(localVector3f1);
        this.field_90.normalize();
      }
    }
    (localVector3f1 = new Vector3f(this.field_90)).scale(paramclass_941.a() * 55.0F);
    ((class_960)a184()).a142().getWorldTransform().origin.add(localVector3f1);
    (localVector3f2 = GlUtil.f(new Vector3f(), getWorldTransform())).field_615 += 0.05F;
    localVector3f2.field_616 += 0.05F;
    localVector3f2.normalize();
    (localVector3f3 = new Vector3f()).cross(this.field_90, localVector3f2);
    localVector3f3.normalize();
    localVector3f2.cross(localVector3f3, this.field_90);
    localVector3f2.normalize();
    GlUtil.a30(this.field_90, getWorldTransform());
    GlUtil.d2(localVector3f2, getWorldTransform());
    GlUtil.c3(localVector3f3, getWorldTransform());
    b32(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1050
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */