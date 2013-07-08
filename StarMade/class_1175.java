import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_1175
  extends class_1177
{
  public class_1175()
  {
    new class_48();
  }
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    paramSegment = paramSegmentController;
    if ((paramSegmentController = paramSegment).field_34.a3(0, 0, 0)) {
      for (int i = 0; i < 16; i = (byte)(i + 1)) {
        for (int j = 0; j < 16; j = (byte)(j + 1)) {
          a12(j, 0, i, paramSegmentController, Short.valueOf((short)76));
        }
      }
    }
    paramSegment.getSegmentBuffer().b6(paramSegmentController);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1175
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */