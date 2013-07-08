/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.awt.Color;
/*   4:    */import java.awt.Graphics2D;
/*   5:    */import java.awt.color.ColorSpace;
/*   6:    */import java.awt.image.BufferedImage;
/*   7:    */import java.awt.image.ColorModel;
/*   8:    */import java.awt.image.ComponentColorModel;
/*   9:    */import java.awt.image.DataBufferByte;
/*  10:    */import java.awt.image.Raster;
/*  11:    */import java.awt.image.WritableRaster;
/*  12:    */import java.io.IOException;
/*  13:    */import java.io.InputStream;
/*  14:    */import java.nio.ByteBuffer;
/*  15:    */import java.nio.ByteOrder;
/*  16:    */import java.util.Hashtable;
/*  17:    */import javax.imageio.ImageIO;
/*  18:    */
/*  27:    */public class ImageIOImageData
/*  28:    */  implements LoadableImageData
/*  29:    */{
/*  30: 30 */  private static final ColorModel glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
/*  31:    */  
/*  39: 39 */  private static final ColorModel glColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
/*  40:    */  
/*  43:    */  private int depth;
/*  44:    */  
/*  46:    */  private int height;
/*  47:    */  
/*  49:    */  private int width;
/*  50:    */  
/*  52:    */  private int texWidth;
/*  53:    */  
/*  55:    */  private int texHeight;
/*  56:    */  
/*  58: 58 */  private boolean edging = true;
/*  59:    */  
/*  62:    */  public int getDepth()
/*  63:    */  {
/*  64: 64 */    return this.depth;
/*  65:    */  }
/*  66:    */  
/*  69:    */  public int getHeight()
/*  70:    */  {
/*  71: 71 */    return this.height;
/*  72:    */  }
/*  73:    */  
/*  76:    */  public int getTexHeight()
/*  77:    */  {
/*  78: 78 */    return this.texHeight;
/*  79:    */  }
/*  80:    */  
/*  83:    */  public int getTexWidth()
/*  84:    */  {
/*  85: 85 */    return this.texWidth;
/*  86:    */  }
/*  87:    */  
/*  90:    */  public int getWidth()
/*  91:    */  {
/*  92: 92 */    return this.width;
/*  93:    */  }
/*  94:    */  
/*  96:    */  public ByteBuffer loadImage(InputStream fis)
/*  97:    */    throws IOException
/*  98:    */  {
/*  99: 99 */    return loadImage(fis, true, null);
/* 100:    */  }
/* 101:    */  
/* 103:    */  public ByteBuffer loadImage(InputStream fis, boolean flipped, int[] transparent)
/* 104:    */    throws IOException
/* 105:    */  {
/* 106:106 */    return loadImage(fis, flipped, false, transparent);
/* 107:    */  }
/* 108:    */  
/* 110:    */  public ByteBuffer loadImage(InputStream fis, boolean flipped, boolean forceAlpha, int[] transparent)
/* 111:    */    throws IOException
/* 112:    */  {
/* 113:113 */    if (transparent != null) {
/* 114:114 */      forceAlpha = true;
/* 115:    */    }
/* 116:    */    
/* 117:117 */    BufferedImage bufferedImage = ImageIO.read(fis);
/* 118:118 */    return imageToByteBuffer(bufferedImage, flipped, forceAlpha, transparent);
/* 119:    */  }
/* 120:    */  
/* 121:    */  public ByteBuffer imageToByteBuffer(BufferedImage image, boolean flipped, boolean forceAlpha, int[] transparent) {
/* 122:122 */    ByteBuffer imageBuffer = null;
/* 123:    */    
/* 126:126 */    int texWidth = 2;
/* 127:127 */    int texHeight = 2;
/* 128:    */    
/* 132:132 */    while (texWidth < image.getWidth()) {
/* 133:133 */      texWidth *= 2;
/* 134:    */    }
/* 135:135 */    while (texHeight < image.getHeight()) {
/* 136:136 */      texHeight *= 2;
/* 137:    */    }
/* 138:    */    
/* 139:139 */    this.width = image.getWidth();
/* 140:140 */    this.height = image.getHeight();
/* 141:141 */    this.texHeight = texHeight;
/* 142:142 */    this.texWidth = texWidth;
/* 143:    */    
/* 146:146 */    boolean useAlpha = (image.getColorModel().hasAlpha()) || (forceAlpha);
/* 147:    */    BufferedImage texImage;
/* 148:148 */    BufferedImage texImage; if (useAlpha) {
/* 149:149 */      this.depth = 32;
/* 150:150 */      WritableRaster raster = Raster.createInterleavedRaster(0, texWidth, texHeight, 4, null);
/* 151:151 */      texImage = new BufferedImage(glAlphaColorModel, raster, false, new Hashtable());
/* 152:    */    } else {
/* 153:153 */      this.depth = 24;
/* 154:154 */      WritableRaster raster = Raster.createInterleavedRaster(0, texWidth, texHeight, 3, null);
/* 155:155 */      texImage = new BufferedImage(glColorModel, raster, false, new Hashtable());
/* 156:    */    }
/* 157:    */    
/* 159:159 */    Graphics2D g = (Graphics2D)texImage.getGraphics();
/* 160:    */    
/* 162:162 */    if (useAlpha) {
/* 163:163 */      g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.0F));
/* 164:164 */      g.fillRect(0, 0, texWidth, texHeight);
/* 165:    */    }
/* 166:    */    
/* 167:167 */    if (flipped) {
/* 168:168 */      g.scale(1.0D, -1.0D);
/* 169:169 */      g.drawImage(image, 0, -this.height, null);
/* 170:    */    } else {
/* 171:171 */      g.drawImage(image, 0, 0, null);
/* 172:    */    }
/* 173:    */    
/* 174:174 */    if (this.edging) {
/* 175:175 */      if (this.height < texHeight - 1) {
/* 176:176 */        copyArea(texImage, 0, 0, this.width, 1, 0, texHeight - 1);
/* 177:177 */        copyArea(texImage, 0, this.height - 1, this.width, 1, 0, 1);
/* 178:    */      }
/* 179:179 */      if (this.width < texWidth - 1) {
/* 180:180 */        copyArea(texImage, 0, 0, 1, this.height, texWidth - 1, 0);
/* 181:181 */        copyArea(texImage, this.width - 1, 0, 1, this.height, 1, 0);
/* 182:    */      }
/* 183:    */    }
/* 184:    */    
/* 187:187 */    byte[] data = ((DataBufferByte)texImage.getRaster().getDataBuffer()).getData();
/* 188:    */    
/* 189:189 */    if (transparent != null) {
/* 190:190 */      for (int i = 0; i < data.length; i += 4) {
/* 191:191 */        boolean match = true;
/* 192:192 */        for (int c = 0; c < 3; c++) {
/* 193:193 */          int value = data[(i + c)] < 0 ? 256 + data[(i + c)] : data[(i + c)];
/* 194:194 */          if (value != transparent[c]) {
/* 195:195 */            match = false;
/* 196:    */          }
/* 197:    */        }
/* 198:    */        
/* 199:199 */        if (match) {
/* 200:200 */          data[(i + 3)] = 0;
/* 201:    */        }
/* 202:    */      }
/* 203:    */    }
/* 204:    */    
/* 205:205 */    imageBuffer = ByteBuffer.allocateDirect(data.length);
/* 206:206 */    imageBuffer.order(ByteOrder.nativeOrder());
/* 207:207 */    imageBuffer.put(data, 0, data.length);
/* 208:208 */    imageBuffer.flip();
/* 209:209 */    g.dispose();
/* 210:    */    
/* 211:211 */    return imageBuffer;
/* 212:    */  }
/* 213:    */  
/* 216:    */  public ByteBuffer getImageBufferData()
/* 217:    */  {
/* 218:218 */    throw new RuntimeException("ImageIOImageData doesn't store it's image.");
/* 219:    */  }
/* 220:    */  
/* 231:    */  private void copyArea(BufferedImage image, int x, int y, int width, int height, int dx, int dy)
/* 232:    */  {
/* 233:233 */    Graphics2D g = (Graphics2D)image.getGraphics();
/* 234:    */    
/* 235:235 */    g.drawImage(image.getSubimage(x, y, width, height), x + dx, y + dy, null);
/* 236:    */  }
/* 237:    */  
/* 240:    */  public void configureEdging(boolean edging)
/* 241:    */  {
/* 242:242 */    this.edging = edging;
/* 243:    */  }
/* 244:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.ImageIOImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */