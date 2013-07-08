import java.io.File;
import java.io.FilenameFilter;
import org.schema.game.common.controller.SegmentController;

public final class class_896
  implements FilenameFilter
{
  public class_896(SegmentController paramSegmentController) {}
  
  public final boolean accept(File paramFile, String paramString)
  {
    return paramString.startsWith(this.field_1123.getUniqueIdentifier());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_896
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */