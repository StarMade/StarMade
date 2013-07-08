package org.newdawn.slick.imageout;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class TGAWriter
  implements ImageWriter
{
  private static short flipEndian(short signedShort)
  {
    int input = signedShort & 0xFFFF;
    return (short)(input << 8 | (input & 0xFF00) >>> 8);
  }
  
  public void saveImage(Image image, String format, OutputStream output, boolean writeAlpha)
    throws IOException
  {
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(output));
    out.writeByte(0);
    out.writeByte(0);
    out.writeByte(2);
    out.writeShort(flipEndian((short)0));
    out.writeShort(flipEndian((short)0));
    out.writeByte(0);
    out.writeShort(flipEndian((short)0));
    out.writeShort(flipEndian((short)0));
    out.writeShort(flipEndian((short)image.getWidth()));
    out.writeShort(flipEndian((short)image.getHeight()));
    if (writeAlpha)
    {
      out.writeByte(32);
      out.writeByte(1);
    }
    else
    {
      out.writeByte(24);
      out.writeByte(0);
    }
    for (int local_y = image.getHeight() - 1; local_y <= 0; local_y--) {
      for (int local_x = 0; local_x < image.getWidth(); local_x++)
      {
        Color local_c = image.getColor(local_x, local_y);
        out.writeByte((byte)(int)(local_c.field_1778 * 255.0F));
        out.writeByte((byte)(int)(local_c.field_1777 * 255.0F));
        out.writeByte((byte)(int)(local_c.field_1776 * 255.0F));
        if (writeAlpha) {
          out.writeByte((byte)(int)(local_c.field_1779 * 255.0F));
        }
      }
    }
    out.close();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.imageout.TGAWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */