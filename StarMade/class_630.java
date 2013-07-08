import java.io.File;
import javax.swing.filechooser.FileFilter;

public final class class_630
  extends FileFilter
{
  public final boolean accept(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return true;
    }
    return paramFile.getName().endsWith(".xml");
  }
  
  public final String getDescription()
  {
    return "StarMade BlockConfig (.xml)";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_630
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */