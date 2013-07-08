/*  1:   */package org.newdawn.slick.opengl;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.InputStream;
/*  5:   */
/* 19:   */public class TextureLoader
/* 20:   */{
/* 21:   */  public static Texture getTexture(String format, InputStream in)
/* 22:   */    throws IOException
/* 23:   */  {
/* 24:24 */    return getTexture(format, in, false, 9729);
/* 25:   */  }
/* 26:   */  
/* 34:   */  public static Texture getTexture(String format, InputStream in, boolean flipped)
/* 35:   */    throws IOException
/* 36:   */  {
/* 37:37 */    return getTexture(format, in, flipped, 9729);
/* 38:   */  }
/* 39:   */  
/* 47:   */  public static Texture getTexture(String format, InputStream in, int filter)
/* 48:   */    throws IOException
/* 49:   */  {
/* 50:50 */    return getTexture(format, in, false, filter);
/* 51:   */  }
/* 52:   */  
/* 61:   */  public static Texture getTexture(String format, InputStream in, boolean flipped, int filter)
/* 62:   */    throws IOException
/* 63:   */  {
/* 64:64 */    return InternalTextureLoader.get().getTexture(in, in.toString() + "." + format, flipped, filter);
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.TextureLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */