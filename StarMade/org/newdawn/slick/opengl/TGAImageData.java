/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.DataInputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.InputStream;
/*   7:    */import java.nio.ByteBuffer;
/*   8:    */import java.nio.ByteOrder;
/*   9:    */import org.newdawn.slick.util.GlUtil;
/*  10:    */
/*  37:    */public class TGAImageData
/*  38:    */  implements LoadableImageData
/*  39:    */{
/*  40:    */  private int texWidth;
/*  41:    */  private int texHeight;
/*  42:    */  private int width;
/*  43:    */  private int height;
/*  44:    */  private short pixelDepth;
/*  45:    */  
/*  46:    */  private short flipEndian(short signedShort)
/*  47:    */  {
/*  48: 48 */    int input = signedShort & 0xFFFF;
/*  49: 49 */    return (short)(input << 8 | (input & 0xFF00) >>> 8);
/*  50:    */  }
/*  51:    */  
/*  54:    */  public int getDepth()
/*  55:    */  {
/*  56: 56 */    return this.pixelDepth;
/*  57:    */  }
/*  58:    */  
/*  61:    */  public int getWidth()
/*  62:    */  {
/*  63: 63 */    return this.width;
/*  64:    */  }
/*  65:    */  
/*  68:    */  public int getHeight()
/*  69:    */  {
/*  70: 70 */    return this.height;
/*  71:    */  }
/*  72:    */  
/*  75:    */  public int getTexWidth()
/*  76:    */  {
/*  77: 77 */    return this.texWidth;
/*  78:    */  }
/*  79:    */  
/*  82:    */  public int getTexHeight()
/*  83:    */  {
/*  84: 84 */    return this.texHeight;
/*  85:    */  }
/*  86:    */  
/*  88:    */  public ByteBuffer loadImage(InputStream fis)
/*  89:    */    throws IOException
/*  90:    */  {
/*  91: 91 */    return loadImage(fis, true, null);
/*  92:    */  }
/*  93:    */  
/*  95:    */  public ByteBuffer loadImage(InputStream fis, boolean flipped, int[] transparent)
/*  96:    */    throws IOException
/*  97:    */  {
/*  98: 98 */    return loadImage(fis, flipped, false, transparent);
/*  99:    */  }
/* 100:    */  
/* 102:    */  public ByteBuffer loadImage(InputStream fis, boolean flipped, boolean forceAlpha, int[] transparent)
/* 103:    */    throws IOException
/* 104:    */  {
/* 105:105 */    if (transparent != null) {
/* 106:106 */      forceAlpha = true;
/* 107:    */    }
/* 108:108 */    byte red = 0;
/* 109:109 */    byte green = 0;
/* 110:110 */    byte blue = 0;
/* 111:111 */    byte alpha = 0;
/* 112:    */    
/* 113:113 */    BufferedInputStream bis = new BufferedInputStream(fis, 100000);
/* 114:114 */    DataInputStream dis = new DataInputStream(bis);
/* 115:    */    
/* 117:117 */    short idLength = (short)dis.read();
/* 118:118 */    short colorMapType = (short)dis.read();
/* 119:119 */    short imageType = (short)dis.read();
/* 120:120 */    short cMapStart = flipEndian(dis.readShort());
/* 121:121 */    short cMapLength = flipEndian(dis.readShort());
/* 122:122 */    short cMapDepth = (short)dis.read();
/* 123:123 */    short xOffset = flipEndian(dis.readShort());
/* 124:124 */    short yOffset = flipEndian(dis.readShort());
/* 125:    */    
/* 126:126 */    if (imageType != 2) {
/* 127:127 */      throw new IOException("Slick only supports uncompressed RGB(A) TGA images");
/* 128:    */    }
/* 129:    */    
/* 130:130 */    this.width = flipEndian(dis.readShort());
/* 131:131 */    this.height = flipEndian(dis.readShort());
/* 132:132 */    this.pixelDepth = ((short)dis.read());
/* 133:133 */    if (this.pixelDepth == 32) {
/* 134:134 */      forceAlpha = false;
/* 135:    */    }
/* 136:    */    
/* 137:137 */    this.texWidth = get2Fold(this.width);
/* 138:138 */    this.texHeight = get2Fold(this.height);
/* 139:    */    
/* 140:140 */    short imageDescriptor = (short)dis.read();
/* 141:141 */    if ((imageDescriptor & 0x20) == 0) {
/* 142:142 */      flipped = !flipped;
/* 143:    */    }
/* 144:    */    
/* 146:146 */    if (idLength > 0) {
/* 147:147 */      bis.skip(idLength);
/* 148:    */    }
/* 149:    */    
/* 150:150 */    byte[] rawData = null;
/* 151:151 */    if ((this.pixelDepth == 32) || (forceAlpha)) {
/* 152:152 */      this.pixelDepth = 32;
/* 153:153 */      rawData = new byte[this.texWidth * this.texHeight * 4];
/* 154:154 */    } else if (this.pixelDepth == 24) {
/* 155:155 */      rawData = new byte[this.texWidth * this.texHeight * 3];
/* 156:    */    } else {
/* 157:157 */      throw new RuntimeException("Only 24 and 32 bit TGAs are supported");
/* 158:    */    }
/* 159:    */    
/* 160:160 */    if (this.pixelDepth == 24) {
/* 161:161 */      if (flipped) {
/* 162:162 */        for (int i = this.height - 1; i >= 0; i--) {
/* 163:163 */          for (int j = 0; j < this.width; j++) {
/* 164:164 */            blue = dis.readByte();
/* 165:165 */            green = dis.readByte();
/* 166:166 */            red = dis.readByte();
/* 167:    */            
/* 168:168 */            int ofs = (j + i * this.texWidth) * 3;
/* 169:169 */            rawData[ofs] = red;
/* 170:170 */            rawData[(ofs + 1)] = green;
/* 171:171 */            rawData[(ofs + 2)] = blue;
/* 172:    */          }
/* 173:    */        }
/* 174:    */      } else {
/* 175:175 */        for (int i = 0; i < this.height; i++) {
/* 176:176 */          for (int j = 0; j < this.width; j++) {
/* 177:177 */            blue = dis.readByte();
/* 178:178 */            green = dis.readByte();
/* 179:179 */            red = dis.readByte();
/* 180:    */            
/* 181:181 */            int ofs = (j + i * this.texWidth) * 3;
/* 182:182 */            rawData[ofs] = red;
/* 183:183 */            rawData[(ofs + 1)] = green;
/* 184:184 */            rawData[(ofs + 2)] = blue;
/* 185:    */          }
/* 186:    */        }
/* 187:    */      }
/* 188:188 */    } else if (this.pixelDepth == 32) {
/* 189:189 */      if (flipped) {
/* 190:190 */        for (int i = this.height - 1; i >= 0; i--) {
/* 191:191 */          for (int j = 0; j < this.width; j++) {
/* 192:192 */            blue = dis.readByte();
/* 193:193 */            green = dis.readByte();
/* 194:194 */            red = dis.readByte();
/* 195:195 */            if (forceAlpha) {
/* 196:196 */              alpha = -1;
/* 197:    */            } else {
/* 198:198 */              alpha = dis.readByte();
/* 199:    */            }
/* 200:    */            
/* 201:201 */            int ofs = (j + i * this.texWidth) * 4;
/* 202:    */            
/* 203:203 */            rawData[ofs] = red;
/* 204:204 */            rawData[(ofs + 1)] = green;
/* 205:205 */            rawData[(ofs + 2)] = blue;
/* 206:206 */            rawData[(ofs + 3)] = alpha;
/* 207:    */            
/* 208:208 */            if (alpha == 0) {
/* 209:209 */              rawData[(ofs + 2)] = 0;
/* 210:210 */              rawData[(ofs + 1)] = 0;
/* 211:211 */              rawData[ofs] = 0;
/* 212:    */            }
/* 213:    */          }
/* 214:    */        }
/* 215:    */      } else {
/* 216:216 */        for (int i = 0; i < this.height; i++) {
/* 217:217 */          for (int j = 0; j < this.width; j++) {
/* 218:218 */            blue = dis.readByte();
/* 219:219 */            green = dis.readByte();
/* 220:220 */            red = dis.readByte();
/* 221:221 */            if (forceAlpha) {
/* 222:222 */              alpha = -1;
/* 223:    */            } else {
/* 224:224 */              alpha = dis.readByte();
/* 225:    */            }
/* 226:    */            
/* 227:227 */            int ofs = (j + i * this.texWidth) * 4;
/* 228:    */            
/* 229:229 */            if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
/* 230:230 */              rawData[ofs] = red;
/* 231:231 */              rawData[(ofs + 1)] = green;
/* 232:232 */              rawData[(ofs + 2)] = blue;
/* 233:233 */              rawData[(ofs + 3)] = alpha;
/* 234:    */            } else {
/* 235:235 */              rawData[ofs] = red;
/* 236:236 */              rawData[(ofs + 1)] = green;
/* 237:237 */              rawData[(ofs + 2)] = blue;
/* 238:238 */              rawData[(ofs + 3)] = alpha;
/* 239:    */            }
/* 240:    */            
/* 241:241 */            if (alpha == 0) {
/* 242:242 */              rawData[(ofs + 2)] = 0;
/* 243:243 */              rawData[(ofs + 1)] = 0;
/* 244:244 */              rawData[ofs] = 0;
/* 245:    */            }
/* 246:    */          }
/* 247:    */        }
/* 248:    */      }
/* 249:    */    }
/* 250:250 */    fis.close();
/* 251:    */    
/* 252:252 */    if (transparent != null) {
/* 253:253 */      for (int i = 0; i < rawData.length; i += 4) {
/* 254:254 */        boolean match = true;
/* 255:255 */        for (int c = 0; c < 3; c++) {
/* 256:256 */          if (rawData[(i + c)] != transparent[c]) {
/* 257:257 */            match = false;
/* 258:    */          }
/* 259:    */        }
/* 260:    */        
/* 261:261 */        if (match) {
/* 262:262 */          rawData[(i + 3)] = 0;
/* 263:    */        }
/* 264:    */      }
/* 265:    */    }
/* 266:    */    
/* 268:268 */    ByteBuffer scratch = GlUtil.getDynamicByteBuffer(rawData.length);
/* 269:269 */    scratch.put(rawData);
/* 270:    */    
/* 271:271 */    int perPixel = this.pixelDepth / 8;
/* 272:272 */    if (this.height < this.texHeight - 1) {
/* 273:273 */      int topOffset = (this.texHeight - 1) * (this.texWidth * perPixel);
/* 274:274 */      int bottomOffset = (this.height - 1) * (this.texWidth * perPixel);
/* 275:275 */      for (int x = 0; x < this.texWidth * perPixel; x++) {
/* 276:276 */        scratch.put(topOffset + x, scratch.get(x));
/* 277:277 */        scratch.put(bottomOffset + this.texWidth * perPixel + x, scratch.get(this.texWidth * perPixel + x));
/* 278:    */      }
/* 279:    */    }
/* 280:280 */    if (this.width < this.texWidth - 1) {
/* 281:281 */      for (int y = 0; y < this.texHeight; y++) {
/* 282:282 */        for (int i = 0; i < perPixel; i++) {
/* 283:283 */          scratch.put((y + 1) * (this.texWidth * perPixel) - perPixel + i, scratch.get(y * (this.texWidth * perPixel) + i));
/* 284:284 */          scratch.put(y * (this.texWidth * perPixel) + this.width * perPixel + i, scratch.get(y * (this.texWidth * perPixel) + (this.width - 1) * perPixel + i));
/* 285:    */        }
/* 286:    */      }
/* 287:    */    }
/* 288:    */    
/* 289:289 */    scratch.flip();
/* 290:    */    
/* 291:291 */    return scratch;
/* 292:    */  }
/* 293:    */  
/* 299:    */  private int get2Fold(int fold)
/* 300:    */  {
/* 301:301 */    int ret = 2;
/* 302:302 */    while (ret < fold) {
/* 303:303 */      ret *= 2;
/* 304:    */    }
/* 305:305 */    return ret;
/* 306:    */  }
/* 307:    */  
/* 310:    */  public ByteBuffer getImageBufferData()
/* 311:    */  {
/* 312:312 */    throw new RuntimeException("TGAImageData doesn't store it's image.");
/* 313:    */  }
/* 314:    */  
/* 315:    */  public void configureEdging(boolean edging) {}
/* 316:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.TGAImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */