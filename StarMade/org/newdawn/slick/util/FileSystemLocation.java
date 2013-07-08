package org.newdawn.slick.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class FileSystemLocation
  implements ResourceLocation
{
  private File root;
  
  public FileSystemLocation(File root)
  {
    this.root = root;
  }
  
  public URL getResource(String ref)
  {
    try
    {
      File file = new File(this.root, ref);
      if (!file.exists()) {
        file = new File(ref);
      }
      if (!file.exists()) {
        return null;
      }
      return file.toURI().toURL();
    }
    catch (IOException file) {}
    return null;
  }
  
  public InputStream getResourceAsStream(String ref)
  {
    try
    {
      File file = new File(this.root, ref);
      if (!file.exists()) {
        file = new File(ref);
      }
      return new FileInputStream(file);
    }
    catch (IOException file) {}
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.FileSystemLocation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */