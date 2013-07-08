import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import java.util.Iterator;
import javax.vecmath.Vector3f;

public final class class_471
  extends class_16
{
  private class_1050 field_4;
  
  public class_471(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void b2(boolean paramBoolean)
  {
    class_1046.field_1306 = !paramBoolean;
    if (paramBoolean)
    {
      a6().b4(false);
      int i = 0;
      synchronized (a6().b())
      {
        Iterator localIterator = a6().b().iterator();
        while (localIterator.hasNext()) {
          if (((class_12)localIterator.next() instanceof D)) {
            i = 1;
          }
        }
      }
      if (i == 0)
      {
        ??? = new class_1(a6());
        a6().b().add(???);
      }
    }
    super.b2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    super.a15(paramclass_941);
    class_1046.field_1306 = false;
    if (this.field_4 == null)
    {
      class_471 localclass_471 = this;
      (localObject = new Transform()).setIdentity();
      Object localObject = new class_960(new class_473((Transform)localObject));
      localclass_471.a6().a23();
      Vector3f localVector3f;
      (localVector3f = new Vector3f(class_307.a25().a83())).negate();
      localVector3f.normalize();
      localclass_471.field_4 = new class_1050((class_960)localObject, new Vector3f(125.0F, 70.0F, 223.0F), localVector3f);
    }
    if (class_969.a1() != this.field_4)
    {
      class_969.a9(this.field_4);
      this.field_4.a12(paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_471
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */