import java.io.File;
import java.io.FilenameFilter;
import org.schema.game.common.controller.SegmentController;

public final class class_1096
  implements FilenameFilter
{
  public class_1096(SegmentController paramSegmentController) {}
  
  public final boolean accept(File paramFile, String paramString)
  {
    return paramString.startsWith(this.field_1369.getUniqueIdentifier() + ".");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1096
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */