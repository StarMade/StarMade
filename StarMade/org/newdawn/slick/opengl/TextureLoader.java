package org.newdawn.slick.opengl;

import java.io.IOException;
import java.io.InputStream;

public class TextureLoader
{
  public static Texture getTexture(String format, InputStream local_in)
    throws IOException
  {
    return getTexture(format, local_in, false, 9729);
  }
  
  public static Texture getTexture(String format, InputStream local_in, boolean flipped)
    throws IOException
  {
    return getTexture(format, local_in, flipped, 9729);
  }
  
  public static Texture getTexture(String format, InputStream local_in, int filter)
    throws IOException
  {
    return getTexture(format, local_in, false, filter);
  }
  
  public static Texture getTexture(String format, InputStream local_in, boolean flipped, int filter)
    throws IOException
  {
    return InternalTextureLoader.get().getTexture(local_in, local_in.toString() + "." + format, flipped, filter);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.TextureLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */