package org.newdawn.slick.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ResourceLoader
{
  private static ArrayList locations = new ArrayList();
  
  public static void addResourceLocation(ResourceLocation location)
  {
    locations.add(location);
  }
  
  public static void removeResourceLocation(ResourceLocation location)
  {
    locations.remove(location);
  }
  
  public static void removeAllResourceLocations()
  {
    locations.clear();
  }
  
  public static InputStream getResourceAsStream(String ref)
  {
    InputStream local_in = null;
    for (int local_i = 0; local_i < locations.size(); local_i++)
    {
      ResourceLocation location = (ResourceLocation)locations.get(local_i);
      local_in = location.getResourceAsStream(ref);
      if (local_in != null) {
        break;
      }
    }
    if (local_in == null) {
      throw new RuntimeException("Resource not found: " + ref);
    }
    return new BufferedInputStream(local_in);
  }
  
  public static boolean resourceExists(String ref)
  {
    URL url = null;
    for (int local_i = 0; local_i < locations.size(); local_i++)
    {
      ResourceLocation location = (ResourceLocation)locations.get(local_i);
      url = location.getResource(ref);
      if (url != null) {
        return true;
      }
    }
    return false;
  }
  
  public static URL getResource(String ref)
  {
    URL url = null;
    for (int local_i = 0; local_i < locations.size(); local_i++)
    {
      ResourceLocation location = (ResourceLocation)locations.get(local_i);
      url = location.getResource(ref);
      if (url != null) {
        break;
      }
    }
    if (url == null) {
      throw new RuntimeException("Resource not found: " + ref);
    }
    return url;
  }
  
  static
  {
    locations.add(new ClasspathLocation());
    locations.add(new FileSystemLocation(new File(".")));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.ResourceLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */