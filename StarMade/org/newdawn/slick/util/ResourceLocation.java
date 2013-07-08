package org.newdawn.slick.util;

import java.io.InputStream;
import java.net.URL;

public abstract interface ResourceLocation
{
  public abstract InputStream getResourceAsStream(String paramString);
  
  public abstract URL getResource(String paramString);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.ResourceLocation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */