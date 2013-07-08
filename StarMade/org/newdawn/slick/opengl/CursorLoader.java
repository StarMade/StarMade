/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.nio.ByteOrder;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import org.lwjgl.LWJGLException;
/*   8:    */import org.lwjgl.input.Cursor;
/*   9:    */import org.newdawn.slick.util.Log;
/*  10:    */import org.newdawn.slick.util.ResourceLoader;
/*  11:    */
/*  20:    */public class CursorLoader
/*  21:    */{
/*  22: 22 */  private static CursorLoader single = new CursorLoader();
/*  23:    */  
/*  28:    */  public static CursorLoader get()
/*  29:    */  {
/*  30: 30 */    return single;
/*  31:    */  }
/*  32:    */  
/*  47:    */  public Cursor getCursor(String ref, int x, int y)
/*  48:    */    throws IOException, LWJGLException
/*  49:    */  {
/*  50: 50 */    LoadableImageData imageData = null;
/*  51:    */    
/*  52: 52 */    imageData = ImageDataFactory.getImageDataFor(ref);
/*  53: 53 */    imageData.configureEdging(false);
/*  54:    */    
/*  55: 55 */    ByteBuffer buf = imageData.loadImage(ResourceLoader.getResourceAsStream(ref), true, true, null);
/*  56: 56 */    for (int i = 0; i < buf.limit(); i += 4) {
/*  57: 57 */      byte red = buf.get(i);
/*  58: 58 */      byte green = buf.get(i + 1);
/*  59: 59 */      byte blue = buf.get(i + 2);
/*  60: 60 */      byte alpha = buf.get(i + 3);
/*  61:    */      
/*  62: 62 */      buf.put(i + 2, red);
/*  63: 63 */      buf.put(i + 1, green);
/*  64: 64 */      buf.put(i, blue);
/*  65: 65 */      buf.put(i + 3, alpha);
/*  66:    */    }
/*  67:    */    try
/*  68:    */    {
/*  69: 69 */      int yspot = imageData.getHeight() - y - 1;
/*  70: 70 */      if (yspot < 0) {
/*  71: 71 */        yspot = 0;
/*  72:    */      }
/*  73:    */      
/*  74: 74 */      return new Cursor(imageData.getTexWidth(), imageData.getTexHeight(), x, yspot, 1, buf.asIntBuffer(), null);
/*  75:    */    } catch (Throwable e) {
/*  76: 76 */      Log.info("Chances are you cursor is too small for this platform");
/*  77: 77 */      throw new LWJGLException(e);
/*  78:    */    }
/*  79:    */  }
/*  80:    */  
/*  92:    */  public Cursor getCursor(ByteBuffer buf, int x, int y, int width, int height)
/*  93:    */    throws IOException, LWJGLException
/*  94:    */  {
/*  95: 95 */    for (int i = 0; i < buf.limit(); i += 4) {
/*  96: 96 */      byte red = buf.get(i);
/*  97: 97 */      byte green = buf.get(i + 1);
/*  98: 98 */      byte blue = buf.get(i + 2);
/*  99: 99 */      byte alpha = buf.get(i + 3);
/* 100:    */      
/* 101:101 */      buf.put(i + 2, red);
/* 102:102 */      buf.put(i + 1, green);
/* 103:103 */      buf.put(i, blue);
/* 104:104 */      buf.put(i + 3, alpha);
/* 105:    */    }
/* 106:    */    try
/* 107:    */    {
/* 108:108 */      int yspot = height - y - 1;
/* 109:109 */      if (yspot < 0) {
/* 110:110 */        yspot = 0;
/* 111:    */      }
/* 112:112 */      return new Cursor(width, height, x, yspot, 1, buf.asIntBuffer(), null);
/* 113:    */    } catch (Throwable e) {
/* 114:114 */      Log.info("Chances are you cursor is too small for this platform");
/* 115:115 */      throw new LWJGLException(e);
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 127:    */  public Cursor getCursor(ImageData imageData, int x, int y)
/* 128:    */    throws IOException, LWJGLException
/* 129:    */  {
/* 130:130 */    ByteBuffer buf = imageData.getImageBufferData();
/* 131:131 */    for (int i = 0; i < buf.limit(); i += 4) {
/* 132:132 */      byte red = buf.get(i);
/* 133:133 */      byte green = buf.get(i + 1);
/* 134:134 */      byte blue = buf.get(i + 2);
/* 135:135 */      byte alpha = buf.get(i + 3);
/* 136:    */      
/* 137:137 */      buf.put(i + 2, red);
/* 138:138 */      buf.put(i + 1, green);
/* 139:139 */      buf.put(i, blue);
/* 140:140 */      buf.put(i + 3, alpha);
/* 141:    */    }
/* 142:    */    try
/* 143:    */    {
/* 144:144 */      int yspot = imageData.getHeight() - y - 1;
/* 145:145 */      if (yspot < 0) {
/* 146:146 */        yspot = 0;
/* 147:    */      }
/* 148:148 */      return new Cursor(imageData.getTexWidth(), imageData.getTexHeight(), x, yspot, 1, buf.asIntBuffer(), null);
/* 149:    */    } catch (Throwable e) {
/* 150:150 */      Log.info("Chances are you cursor is too small for this platform");
/* 151:151 */      throw new LWJGLException(e);
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 169:    */  public Cursor getAnimatedCursor(String ref, int x, int y, int width, int height, int[] cursorDelays)
/* 170:    */    throws IOException, LWJGLException
/* 171:    */  {
/* 172:172 */    IntBuffer cursorDelaysBuffer = ByteBuffer.allocateDirect(cursorDelays.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
/* 173:173 */    for (int i = 0; i < cursorDelays.length; i++) {
/* 174:174 */      cursorDelaysBuffer.put(cursorDelays[i]);
/* 175:    */    }
/* 176:176 */    cursorDelaysBuffer.flip();
/* 177:    */    
/* 178:178 */    LoadableImageData imageData = new TGAImageData();
/* 179:179 */    ByteBuffer buf = imageData.loadImage(ResourceLoader.getResourceAsStream(ref), false, null);
/* 180:    */    
/* 181:181 */    return new Cursor(width, height, x, y, cursorDelays.length, buf.asIntBuffer(), cursorDelaysBuffer);
/* 182:    */  }
/* 183:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.CursorLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */