import com.bulletphysics.linearmath.Transform;
import java.util.Comparator;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_355
  implements Comparator
{
  private final Vector3f field_705 = new Vector3f();
  private Vector3f field_706 = new Vector3f();
  
  private synchronized int a(class_657 paramclass_6571, class_657 paramclass_6572)
  {
    if ((paramclass_6571 == paramclass_6572) || (paramclass_6571.equals(paramclass_6572))) {
      return 0;
    }
    return Float.compare(a1(paramclass_6571.field_34, paramclass_6571), a1(paramclass_6572.field_34, paramclass_6572));
  }
  
  private float a1(class_48 paramclass_48, Segment paramSegment)
  {
    this.field_706.set(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
    paramSegment.a15().getWorldTransformClient().basis.transform(this.field_706);
    this.field_706.add(paramSegment.a15().getWorldTransformClient().origin);
    this.field_706.sub(this.field_705);
    return this.field_706.lengthSquared();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_355
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */