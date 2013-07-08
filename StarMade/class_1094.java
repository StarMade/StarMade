import java.io.File;
import java.io.FileFilter;

public final class class_1094
  implements FileFilter
{
  public class_1094(class_1055 paramclass_1055) {}
  
  public final boolean accept(File paramFile)
  {
    return paramFile.getName().startsWith(this.field_1368.field_235 + ".");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1094
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */