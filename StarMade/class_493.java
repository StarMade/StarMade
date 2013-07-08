import java.io.File;
import javax.swing.filechooser.FileFilter;

final class class_493
  extends FileFilter
{
  public final boolean accept(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return true;
    }
    return paramFile.getName().endsWith(".sment");
  }
  
  public final String getDescription()
  {
    return "StarMade Entitiy (.sment)";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_493
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */