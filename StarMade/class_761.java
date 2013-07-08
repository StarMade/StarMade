import java.io.File;
import java.io.FilenameFilter;

public final class class_761
  implements FilenameFilter
{
  public final boolean accept(File paramFile, String paramString)
  {
    return (paramString.startsWith("SECTOR")) || (paramString.startsWith("VOIDSYSTEM"));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_761
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */