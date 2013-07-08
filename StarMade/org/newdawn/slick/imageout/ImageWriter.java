package org.newdawn.slick.imageout;

import java.io.IOException;
import java.io.OutputStream;
import org.newdawn.slick.Image;

public abstract interface ImageWriter
{
  public abstract void saveImage(Image paramImage, String paramString, OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException;
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.imageout.ImageWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */