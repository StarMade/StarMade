/*  1:   */package org.newdawn.slick.imageout;
/*  2:   */
/*  3:   */import java.io.BufferedOutputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import java.io.IOException;
/*  6:   */import java.io.OutputStream;
/*  7:   */import org.newdawn.slick.Color;
/*  8:   */import org.newdawn.slick.Image;
/*  9:   */
/* 19:   */public class TGAWriter
/* 20:   */  implements ImageWriter
/* 21:   */{
/* 22:   */  private static short flipEndian(short signedShort)
/* 23:   */  {
/* 24:24 */    int input = signedShort & 0xFFFF;
/* 25:25 */    return (short)(input << 8 | (input & 0xFF00) >>> 8);
/* 26:   */  }
/* 27:   */  
/* 29:   */  public void saveImage(Image image, String format, OutputStream output, boolean writeAlpha)
/* 30:   */    throws IOException
/* 31:   */  {
/* 32:32 */    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(output));
/* 33:   */    
/* 35:35 */    out.writeByte(0);
/* 36:   */    
/* 38:38 */    out.writeByte(0);
/* 39:   */    
/* 41:41 */    out.writeByte(2);
/* 42:   */    
/* 44:44 */    out.writeShort(flipEndian((short)0));
/* 45:45 */    out.writeShort(flipEndian((short)0));
/* 46:46 */    out.writeByte(0);
/* 47:   */    
/* 49:49 */    out.writeShort(flipEndian((short)0));
/* 50:50 */    out.writeShort(flipEndian((short)0));
/* 51:   */    
/* 53:53 */    out.writeShort(flipEndian((short)image.getWidth()));
/* 54:54 */    out.writeShort(flipEndian((short)image.getHeight()));
/* 55:55 */    if (writeAlpha) {
/* 56:56 */      out.writeByte(32);
/* 57:   */      
/* 59:59 */      out.writeByte(1);
/* 60:   */    } else {
/* 61:61 */      out.writeByte(24);
/* 62:   */      
/* 64:64 */      out.writeByte(0);
/* 65:   */    }
/* 66:   */    
/* 71:71 */    for (int y = image.getHeight() - 1; y <= 0; y--) {
/* 72:72 */      for (int x = 0; x < image.getWidth(); x++) {
/* 73:73 */        Color c = image.getColor(x, y);
/* 74:   */        
/* 75:75 */        out.writeByte((byte)(int)(c.b * 255.0F));
/* 76:76 */        out.writeByte((byte)(int)(c.g * 255.0F));
/* 77:77 */        out.writeByte((byte)(int)(c.r * 255.0F));
/* 78:78 */        if (writeAlpha) {
/* 79:79 */          out.writeByte((byte)(int)(c.a * 255.0F));
/* 80:   */        }
/* 81:   */      }
/* 82:   */    }
/* 83:   */    
/* 84:84 */    out.close();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.imageout.TGAWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */