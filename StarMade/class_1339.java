import java.io.File;
import javax.swing.filechooser.FileFilter;

final class class_1339
  extends FileFilter
{
  public final boolean accept(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return true;
    }
    return paramFile.getName().endsWith(".png");
  }
  
  public final String getDescription()
  {
    return "PNG";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1339
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */