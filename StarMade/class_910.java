import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_910
  extends class_908
{
  private class_1177 field_195;
  
  public class_910(class_864 paramclass_864, class_810 paramclass_810)
  {
    super(paramclass_864);
    switch (class_732.field_1007[paramclass_810.ordinal()])
    {
    case 1: 
      this.field_195 = new class_1081(paramclass_864.getSeed());
      return;
    case 2: 
      this.field_195 = new class_1095(paramclass_864.getSeed());
      return;
    case 3: 
      this.field_195 = new class_1093(paramclass_864.getSeed());
      return;
    case 4: 
      this.field_195 = new class_1085(paramclass_864.getSeed());
      return;
    }
    this.field_195 = new class_1087(paramclass_864.getSeed());
  }
  
  public final void a(Segment paramSegment)
  {
    if (paramSegment.field_34.field_476 < 0) {
      return;
    }
    if (paramSegment.a15().getMinPos().field_475 < -1)
    {
      if ((paramSegment.field_34.field_476 >= -80) && (paramSegment.field_34.field_476 <= 80) && (FastMath.l(paramSegment.field_34.field_475 * paramSegment.field_34.field_475 + paramSegment.field_34.field_477 * paramSegment.field_34.field_477) <= 256.0F)) {}
    }
    else
    {
      Vector3f localVector3f = new Vector3f(256 - paramSegment.field_34.field_475, 0.0F, 256 - paramSegment.field_34.field_477);
      if ((paramSegment.field_34.field_476 < 0) || (paramSegment.field_34.field_476 > 80) || (localVector3f.length() > 256.0F)) {
        return;
      }
    }
    this.field_195.a3(this.field_195, paramSegment);
  }
  
  public final boolean a1()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_910
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */