import java.io.File;
import org.schema.game.common.data.world.Segment;

final class class_722
  implements class_888
{
  class_722(class_720 paramclass_720) {}
  
  public final boolean handle(Segment paramSegment)
  {
    if ((paramSegment = new File(class_720.a12(this.field_105, paramSegment.field_34.field_475, paramSegment.field_34.field_476, paramSegment.field_34.field_477, class_720.a11(this.field_105)))).exists()) {
      paramSegment.delete();
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_722
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */