/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.nio.ByteBuffer;
/*   6:    */import org.newdawn.slick.util.GlUtil;
/*   7:    */
/*  22:    */public class PNGImageData
/*  23:    */  implements LoadableImageData
/*  24:    */{
/*  25:    */  private int width;
/*  26:    */  private int height;
/*  27:    */  private int texHeight;
/*  28:    */  private int texWidth;
/*  29:    */  private PNGDecoder decoder;
/*  30:    */  private int bitDepth;
/*  31:    */  private ByteBuffer scratch;
/*  32:    */  
/*  33:    */  public int getDepth()
/*  34:    */  {
/*  35: 35 */    return this.bitDepth;
/*  36:    */  }
/*  37:    */  
/*  40:    */  public ByteBuffer getImageBufferData()
/*  41:    */  {
/*  42: 42 */    return this.scratch;
/*  43:    */  }
/*  44:    */  
/*  47:    */  public int getTexHeight()
/*  48:    */  {
/*  49: 49 */    return this.texHeight;
/*  50:    */  }
/*  51:    */  
/*  54:    */  public int getTexWidth()
/*  55:    */  {
/*  56: 56 */    return this.texWidth;
/*  57:    */  }
/*  58:    */  
/*  60:    */  public ByteBuffer loadImage(InputStream fis)
/*  61:    */    throws IOException
/*  62:    */  {
/*  63: 63 */    return loadImage(fis, false, null);
/*  64:    */  }
/*  65:    */  
/*  67:    */  public ByteBuffer loadImage(InputStream fis, boolean flipped, int[] transparent)
/*  68:    */    throws IOException
/*  69:    */  {
/*  70: 70 */    return loadImage(fis, flipped, false, transparent);
/*  71:    */  }
/*  72:    */  
/*  74:    */  public ByteBuffer loadImage(InputStream fis, boolean flipped, boolean forceAlpha, int[] transparent)
/*  75:    */    throws IOException
/*  76:    */  {
/*  77: 77 */    if (transparent != null) {
/*  78: 78 */      forceAlpha = true;
/*  79: 79 */      throw new IOException("Transparent color not support in custom PNG Decoder");
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    PNGDecoder decoder = new PNGDecoder(fis);
/*  83:    */    
/*  84: 84 */    if (!decoder.isRGB()) {
/*  85: 85 */      throw new IOException("Only RGB formatted images are supported by the PNGLoader");
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    this.width = decoder.getWidth();
/*  89: 89 */    this.height = decoder.getHeight();
/*  90: 90 */    this.texWidth = get2Fold(this.width);
/*  91: 91 */    this.texHeight = get2Fold(this.height);
/*  92:    */    
/*  93: 93 */    int perPixel = decoder.hasAlpha() ? 4 : 3;
/*  94: 94 */    this.bitDepth = (decoder.hasAlpha() ? 32 : 24);
/*  95:    */    
/*  97: 97 */    this.scratch = GlUtil.getDynamicByteBuffer(this.texWidth * this.texHeight * perPixel);
/*  98: 98 */    decoder.decode(this.scratch, this.texWidth * perPixel, perPixel == 4 ? PNGDecoder.RGBA : PNGDecoder.RGB);
/*  99:    */    
/* 100:100 */    if (this.height < this.texHeight - 1) {
/* 101:101 */      int topOffset = (this.texHeight - 1) * (this.texWidth * perPixel);
/* 102:102 */      int bottomOffset = (this.height - 1) * (this.texWidth * perPixel);
/* 103:103 */      for (int x = 0; x < this.texWidth; x++) {
/* 104:104 */        for (int i = 0; i < perPixel; i++) {
/* 105:105 */          this.scratch.put(topOffset + x + i, this.scratch.get(x + i));
/* 106:106 */          this.scratch.put(bottomOffset + this.texWidth * perPixel + x + i, this.scratch.get(bottomOffset + x + i));
/* 107:    */        }
/* 108:    */      }
/* 109:    */    }
/* 110:110 */    if (this.width < this.texWidth - 1) {
/* 111:111 */      for (int y = 0; y < this.texHeight; y++) {
/* 112:112 */        for (int i = 0; i < perPixel; i++) {
/* 113:113 */          this.scratch.put((y + 1) * (this.texWidth * perPixel) - perPixel + i, this.scratch.get(y * (this.texWidth * perPixel) + i));
/* 114:114 */          this.scratch.put(y * (this.texWidth * perPixel) + this.width * perPixel + i, this.scratch.get(y * (this.texWidth * perPixel) + (this.width - 1) * perPixel + i));
/* 115:    */        }
/* 116:    */      }
/* 117:    */    }
/* 118:    */    
/* 119:119 */    if ((!decoder.hasAlpha()) && (forceAlpha)) {
/* 120:120 */      ByteBuffer temp = GlUtil.getDynamicByteBuffer(this.texWidth * this.texHeight * 4);
/* 121:121 */      for (int x = 0; x < this.texWidth; x++) {
/* 122:122 */        for (int y = 0; y < this.texHeight; y++) {
/* 123:123 */          int srcOffset = y * 3 + x * this.texHeight * 3;
/* 124:124 */          int dstOffset = y * 4 + x * this.texHeight * 4;
/* 125:    */          
/* 126:126 */          temp.put(dstOffset, this.scratch.get(srcOffset));
/* 127:127 */          temp.put(dstOffset + 1, this.scratch.get(srcOffset + 1));
/* 128:128 */          temp.put(dstOffset + 2, this.scratch.get(srcOffset + 2));
/* 129:129 */          if ((x < getHeight()) && (y < getWidth())) {
/* 130:130 */            temp.put(dstOffset + 3, (byte)-1);
/* 131:    */          } else {
/* 132:132 */            temp.put(dstOffset + 3, (byte)0);
/* 133:    */          }
/* 134:    */        }
/* 135:    */      }
/* 136:    */      
/* 137:137 */      this.bitDepth = 32;
/* 138:138 */      this.scratch = temp;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    if (transparent != null) {
/* 142:142 */      for (int i = 0; i < this.texWidth * this.texHeight * 4; i += 4) {
/* 143:143 */        boolean match = true;
/* 144:144 */        for (int c = 0; c < 3; c++) {
/* 145:145 */          if (toInt(this.scratch.get(i + c)) != transparent[c]) {
/* 146:146 */            match = false;
/* 147:    */          }
/* 148:    */        }
/* 149:    */        
/* 150:150 */        if (match) {
/* 151:151 */          this.scratch.put(i + 3, (byte)0);
/* 152:    */        }
/* 153:    */      }
/* 154:    */    }
/* 155:    */    
/* 156:156 */    this.scratch.position(0);
/* 157:    */    
/* 158:158 */    return this.scratch;
/* 159:    */  }
/* 160:    */  
/* 166:    */  private int toInt(byte b)
/* 167:    */  {
/* 168:168 */    if (b < 0) {
/* 169:169 */      return 256 + b;
/* 170:    */    }
/* 171:    */    
/* 172:172 */    return b;
/* 173:    */  }
/* 174:    */  
/* 180:    */  private int get2Fold(int fold)
/* 181:    */  {
/* 182:182 */    int ret = 2;
/* 183:183 */    while (ret < fold) {
/* 184:184 */      ret *= 2;
/* 185:    */    }
/* 186:186 */    return ret;
/* 187:    */  }
/* 188:    */  
/* 191:    */  public void configureEdging(boolean edging) {}
/* 192:    */  
/* 194:    */  public int getWidth()
/* 195:    */  {
/* 196:196 */    return this.width;
/* 197:    */  }
/* 198:    */  
/* 199:    */  public int getHeight() {
/* 200:200 */    return this.height;
/* 201:    */  }
/* 202:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.PNGImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */