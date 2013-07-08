import com.bulletphysics.dynamics.constraintsolver.SliderConstraint;
import java.util.LinkedList;
import org.schema.common.FastMath;

public final class class_1405
  extends class_1429
{
  private float field_276;
  private float field_277;
  private float field_278;
  
  public final class_29[] a()
  {
    null.a14(Float.valueOf(this.field_276));
    null.a14(Float.valueOf(this.field_277));
    float f;
    if (FastMath.a((f = null.getLinearPos()) - this.field_278) > 0.01D)
    {
      null.a14(Float.valueOf(f));
      this.field_278 = f;
      class_1040.a2();
    }
    return null;
  }
  
  public final void a1()
  {
    null.field_276 = Float.valueOf(null.field_19).floatValue();
    null.field_277 = Float.valueOf(null.field_19).floatValue();
    System.currentTimeMillis();
  }
  
  public static void b()
  {
    for (int i = 0; i < null.length; i++)
    {
      ((class_1042)class_1040.a56().get(i)).a2(Float.valueOf(null.field_19));
      class_1040.a56().get(i);
      class_1040.a2();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1405
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */