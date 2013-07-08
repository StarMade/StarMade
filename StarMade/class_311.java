import java.util.HashSet;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.game.common.data.world.Segment;

public final class class_311
  implements class_888
{
  public class_311(SegmentDrawer paramSegmentDrawer) {}
  
  public final boolean handle(Segment paramSegment)
  {
    if (paramSegment != null)
    {
      synchronized (SegmentDrawer.a65(this.field_105))
      {
        SegmentDrawer.a65(this.field_105).add((class_657)paramSegment);
      }
      ((class_657)paramSegment).e(true);
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_311
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */