import java.util.Comparator;
import javax.vecmath.Vector3f;

public final class class_357
  implements Comparator
{
  private final Vector3f field_707;
  
  public class_357()
  {
    new Vector3f();
    this.field_707 = new Vector3f();
  }
  
  public final synchronized int a(class_657 paramclass_6571, class_657 paramclass_6572)
  {
    if ((paramclass_6571 == paramclass_6572) || (paramclass_6571.equals(paramclass_6572))) {
      return 0;
    }
    return Float.compare(paramclass_6571.field_34, paramclass_6572.field_34);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_357
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */