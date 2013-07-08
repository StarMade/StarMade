import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_730
  extends class_908
{
  private class_1152 field_195;
  
  public class_730(SegmentController paramSegmentController)
  {
    super(paramSegmentController);
    paramSegmentController.getSeed();
    this.field_195 = new class_1152();
  }
  
  public final void a(Segment paramSegment)
  {
    this.field_195.a3(paramSegment.a15(), paramSegment);
  }
  
  public final boolean a1()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_730
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */