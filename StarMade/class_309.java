import com.bulletphysics.linearmath.Transform;
import java.util.Comparator;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;

public final class class_309
  implements Comparator
{
  private Vector3f field_659 = new Vector3f();
  private Vector3f field_660 = new Vector3f();
  
  private synchronized int a(class_884 paramclass_8841, class_884 paramclass_8842)
  {
    if ((paramclass_8841 == paramclass_8842) || (paramclass_8841.equals(paramclass_8842))) {
      return 0;
    }
    return (int)(a1(paramclass_8841) - a1(paramclass_8842));
  }
  
  private float a1(class_884 paramclass_884)
  {
    Transform localTransform = paramclass_884.a12().getWorldTransformClient();
    this.field_660.set((paramclass_884.a11().field_475 + 4 << 4) + localTransform.origin.field_615 - 8.0F, (paramclass_884.a11().field_476 + 4 << 4) + localTransform.origin.field_616 - 8.0F, (paramclass_884.a11().field_477 + 4 << 4) + localTransform.origin.field_617 - 8.0F);
    this.field_660.sub(this.field_659);
    return this.field_660.lengthSquared();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_309
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */