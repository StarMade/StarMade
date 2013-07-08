import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public abstract class class_1177
{
  public static boolean a12(int paramInt1, int paramInt2, int paramInt3, Segment paramSegment, Short paramShort)
  {
    if (paramShort.shortValue() == 0) {
      return false;
    }
    if (!paramSegment.a16().containsUnsave((byte)paramInt1, (byte)paramInt2, (byte)paramInt3))
    {
      paramSegment.a16().setInfoElementForcedAddUnsynched((byte)paramInt1, (byte)paramInt2, (byte)paramInt3, paramShort.shortValue(), false);
      return true;
    }
    return false;
  }
  
  public abstract void a3(SegmentController paramSegmentController, Segment paramSegment);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1177
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */