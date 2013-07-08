/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */
/*  60:    */public class MipMap
/*  61:    */  extends Util
/*  62:    */{
/*  63:    */  public static int gluBuild2DMipmaps(int target, int components, int width, int height, int format, int type, ByteBuffer data)
/*  64:    */  {
/*  65: 65 */    if ((width < 1) || (height < 1)) { return 100901;
/*  66:    */    }
/*  67: 67 */    int bpp = bytesPerPixel(format, type);
/*  68: 68 */    if (bpp == 0) {
/*  69: 69 */      return 100900;
/*  70:    */    }
/*  71: 71 */    int maxSize = glGetIntegerv(3379);
/*  72:    */    
/*  73: 73 */    int w = nearestPower(width);
/*  74: 74 */    if (w > maxSize) {
/*  75: 75 */      w = maxSize;
/*  76:    */    }
/*  77: 77 */    int h = nearestPower(height);
/*  78: 78 */    if (h > maxSize) {
/*  79: 79 */      h = maxSize;
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    PixelStoreState pss = new PixelStoreState();
/*  83:    */    
/*  85: 85 */    GL11.glPixelStorei(3330, 0);
/*  86: 86 */    GL11.glPixelStorei(3333, 1);
/*  87: 87 */    GL11.glPixelStorei(3331, 0);
/*  88: 88 */    GL11.glPixelStorei(3332, 0);
/*  89:    */    
/*  91: 91 */    int retVal = 0;
/*  92: 92 */    boolean done = false;
/*  93:    */    ByteBuffer image;
/*  94: 94 */    if ((w != width) || (h != height))
/*  95:    */    {
/*  96: 96 */      ByteBuffer image = BufferUtils.createByteBuffer((w + 4) * h * bpp);
/*  97: 97 */      int error = gluScaleImage(format, width, height, type, data, w, h, type, image);
/*  98: 98 */      if (error != 0) {
/*  99: 99 */        retVal = error;
/* 100:100 */        done = true;
/* 101:    */      }
/* 102:    */      
/* 104:104 */      GL11.glPixelStorei(3314, 0);
/* 105:105 */      GL11.glPixelStorei(3317, 1);
/* 106:106 */      GL11.glPixelStorei(3315, 0);
/* 107:107 */      GL11.glPixelStorei(3316, 0);
/* 108:    */    } else {
/* 109:109 */      image = data;
/* 110:    */    }
/* 111:    */    
/* 112:112 */    ByteBuffer bufferA = null;
/* 113:113 */    ByteBuffer bufferB = null;
/* 114:    */    
/* 115:115 */    int level = 0;
/* 116:116 */    while (!done) {
/* 117:117 */      if (image != data)
/* 118:    */      {
/* 119:119 */        GL11.glPixelStorei(3314, 0);
/* 120:120 */        GL11.glPixelStorei(3317, 1);
/* 121:121 */        GL11.glPixelStorei(3315, 0);
/* 122:122 */        GL11.glPixelStorei(3316, 0);
/* 123:    */      }
/* 124:    */      
/* 125:125 */      GL11.glTexImage2D(target, level, components, w, h, 0, format, type, image);
/* 126:    */      
/* 127:127 */      if ((w == 1) && (h == 1)) {
/* 128:    */        break;
/* 129:    */      }
/* 130:130 */      int newW = w < 2 ? 1 : w >> 1;
/* 131:131 */      int newH = h < 2 ? 1 : h >> 1;
/* 132:    */      
/* 133:    */      ByteBuffer newImage;
/* 134:    */      ByteBuffer newImage;
/* 135:135 */      if (bufferA == null) {
/* 136:136 */        newImage = bufferA = BufferUtils.createByteBuffer((newW + 4) * newH * bpp); } else { ByteBuffer newImage;
/* 137:137 */        if (bufferB == null) {
/* 138:138 */          newImage = bufferB = BufferUtils.createByteBuffer((newW + 4) * newH * bpp);
/* 139:    */        } else
/* 140:140 */          newImage = bufferB;
/* 141:    */      }
/* 142:142 */      int error = gluScaleImage(format, w, h, type, image, newW, newH, type, newImage);
/* 143:143 */      if (error != 0) {
/* 144:144 */        retVal = error;
/* 145:145 */        done = true;
/* 146:    */      }
/* 147:    */      
/* 148:148 */      image = newImage;
/* 149:149 */      if (bufferB != null) {
/* 150:150 */        bufferB = bufferA;
/* 151:    */      }
/* 152:152 */      w = newW;
/* 153:153 */      h = newH;
/* 154:154 */      level++;
/* 155:    */    }
/* 156:    */    
/* 158:158 */    pss.save();
/* 159:    */    
/* 160:160 */    return retVal;
/* 161:    */  }
/* 162:    */  
/* 178:    */  public static int gluScaleImage(int format, int widthIn, int heightIn, int typein, ByteBuffer dataIn, int widthOut, int heightOut, int typeOut, ByteBuffer dataOut)
/* 179:    */  {
/* 180:180 */    int components = compPerPix(format);
/* 181:181 */    if (components == -1) {
/* 182:182 */      return 100900;
/* 183:    */    }
/* 184:    */    
/* 191:191 */    float[] tempIn = new float[widthIn * heightIn * components];
/* 192:192 */    float[] tempOut = new float[widthOut * heightOut * components];
/* 193:    */    
/* 194:    */    int sizein;
/* 195:195 */    switch (typein) {
/* 196:    */    case 5121: 
/* 197:197 */      sizein = 1;
/* 198:198 */      break;
/* 199:    */    case 5126: 
/* 200:200 */      sizein = 4;
/* 201:201 */      break;
/* 202:    */    default: 
/* 203:203 */      return 1280;
/* 204:    */    }
/* 205:    */    
/* 206:    */    int sizeout;
/* 207:207 */    switch (typeOut) {
/* 208:    */    case 5121: 
/* 209:209 */      sizeout = 1;
/* 210:210 */      break;
/* 211:    */    case 5126: 
/* 212:212 */      sizeout = 4;
/* 213:213 */      break;
/* 214:    */    default: 
/* 215:215 */      return 1280;
/* 216:    */    }
/* 217:    */    
/* 218:    */    
/* 219:219 */    PixelStoreState pss = new PixelStoreState();
/* 220:    */    int rowlen;
/* 221:    */    int rowlen;
/* 222:222 */    if (pss.unpackRowLength > 0) {
/* 223:223 */      rowlen = pss.unpackRowLength;
/* 224:    */    } else
/* 225:225 */      rowlen = widthIn;
/* 226:    */    int rowstride;
/* 227:227 */    int rowstride; if (sizein >= pss.unpackAlignment) {
/* 228:228 */      rowstride = components * rowlen;
/* 229:    */    } else
/* 230:230 */      rowstride = pss.unpackAlignment / sizein * ceil(components * rowlen * sizein, pss.unpackAlignment);
/* 231:    */    int k;
/* 232:232 */    int i; switch (typein) {
/* 233:    */    case 5121: 
/* 234:234 */      k = 0;
/* 235:235 */      dataIn.rewind();
/* 236:236 */      for (i = 0; i < heightIn;) {
/* 237:237 */        int ubptr = i * rowstride + pss.unpackSkipRows * rowstride + pss.unpackSkipPixels * components;
/* 238:238 */        for (j = 0; j < widthIn * components; j++) {
/* 239:239 */          tempIn[(k++)] = (dataIn.get(ubptr++) & 0xFF);
/* 240:    */        }
/* 241:236 */        i++;continue;
/* 242:    */        
/* 249:244 */        k = 0;
/* 250:245 */        dataIn.rewind();
/* 251:246 */        for (i = 0; i < heightIn;)
/* 252:    */        {
/* 253:248 */          int fptr = 4 * (i * rowstride + pss.unpackSkipRows * rowstride + pss.unpackSkipPixels * components);
/* 254:249 */          for (j = 0; j < widthIn * components; j++)
/* 255:    */          {
/* 256:251 */            tempIn[(k++)] = dataIn.getFloat(fptr);
/* 257:252 */            fptr += 4;
/* 258:    */          }
/* 259:246 */          i++;continue;
/* 260:    */          
/* 270:257 */          return 100900;
/* 271:    */        }
/* 272:    */      } }
/* 273:    */    int j;
/* 274:261 */    float sx = widthIn / widthOut;
/* 275:262 */    float sy = heightIn / heightOut;
/* 276:    */    
/* 277:264 */    float[] c = new float[components];
/* 278:    */    
/* 280:267 */    for (int iy = 0; iy < heightOut; iy++) {
/* 281:268 */      for (int ix = 0; ix < widthOut; ix++) {
/* 282:269 */        int x0 = (int)(ix * sx);
/* 283:270 */        int x1 = (int)((ix + 1) * sx);
/* 284:271 */        int y0 = (int)(iy * sy);
/* 285:272 */        int y1 = (int)((iy + 1) * sy);
/* 286:    */        
/* 287:274 */        int readPix = 0;
/* 288:    */        
/* 290:277 */        for (int ic = 0; ic < components; ic++) {
/* 291:278 */          c[ic] = 0.0F;
/* 292:    */        }
/* 293:    */        
/* 295:282 */        for (int ix0 = x0; ix0 < x1; ix0++) {
/* 296:283 */          for (int iy0 = y0; iy0 < y1; iy0++)
/* 297:    */          {
/* 298:285 */            int src = (iy0 * widthIn + ix0) * components;
/* 299:    */            
/* 300:287 */            for (int ic = 0; ic < components; ic++) {
/* 301:288 */              c[ic] += tempIn[(src + ic)];
/* 302:    */            }
/* 303:    */            
/* 304:291 */            readPix++;
/* 305:    */          }
/* 306:    */        }
/* 307:    */        
/* 309:296 */        int dst = (iy * widthOut + ix) * components;
/* 310:    */        
/* 311:298 */        if (readPix == 0)
/* 312:    */        {
/* 313:300 */          int src = (y0 * widthIn + x0) * components;
/* 314:301 */          for (int ic = 0; ic < components; ic++) {
/* 315:302 */            tempOut[(dst++)] = tempIn[(src + ic)];
/* 316:    */          }
/* 317:    */        }
/* 318:    */        else {
/* 319:306 */          for (k = 0; k < components; k++) {
/* 320:307 */            tempOut[(dst++)] = (c[k] / readPix);
/* 321:    */          }
/* 322:    */        }
/* 323:    */      }
/* 324:    */    }
/* 325:    */    
/* 328:315 */    if (pss.packRowLength > 0) {
/* 329:316 */      rowlen = pss.packRowLength;
/* 330:    */    } else {
/* 331:318 */      rowlen = widthOut;
/* 332:    */    }
/* 333:320 */    if (sizeout >= pss.packAlignment) {
/* 334:321 */      rowstride = components * rowlen;
/* 335:    */    } else {
/* 336:323 */      rowstride = pss.packAlignment / sizeout * ceil(components * rowlen * sizeout, pss.packAlignment);
/* 337:    */    }
/* 338:325 */    switch (typeOut) {
/* 339:    */    case 5121: 
/* 340:327 */      k = 0;
/* 341:328 */      for (i = 0; i < heightOut;) {
/* 342:329 */        int ubptr = i * rowstride + pss.packSkipRows * rowstride + pss.packSkipPixels * components;
/* 343:    */        
/* 344:331 */        for (j = 0; j < widthOut * components; j++) {
/* 345:332 */          dataOut.put(ubptr++, (byte)(int)tempOut[(k++)]);
/* 346:    */        }
/* 347:328 */        i++;continue;
/* 348:    */        
/* 356:337 */        k = 0;
/* 357:338 */        for (i = 0; i < heightOut;) {
/* 358:339 */          int fptr = 4 * (i * rowstride + pss.unpackSkipRows * rowstride + pss.unpackSkipPixels * components);
/* 359:    */          
/* 360:341 */          for (j = 0; j < widthOut * components; j++) {
/* 361:342 */            dataOut.putFloat(fptr, tempOut[(k++)]);
/* 362:343 */            fptr += 4;
/* 363:    */          }
/* 364:338 */          i++;continue;
/* 365:    */          
/* 374:348 */          return 100900;
/* 375:    */        }
/* 376:    */      } }
/* 377:351 */    return 0;
/* 378:    */  }
/* 379:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.MipMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */