package org.newdawn.slick.util;

import java.io.InputStream;
import java.net.URL;

public class ClasspathLocation
  implements ResourceLocation
{
  public URL getResource(String ref)
  {
    String cpRef = ref.replace('\\', '/');
    return ResourceLoader.class.getClassLoader().getResource(cpRef);
  }
  
  public InputStream getResourceAsStream(String ref)
  {
    String cpRef = ref.replace('\\', '/');
    return ResourceLoader.class.getClassLoader().getResourceAsStream(cpRef);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.ClasspathLocation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */