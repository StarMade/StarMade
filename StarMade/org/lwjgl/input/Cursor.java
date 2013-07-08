/*   1:    */package org.lwjgl.input;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */import org.lwjgl.LWJGLUtil;
/*   7:    */import org.lwjgl.NondirectBufferWrapper;
/*   8:    */import org.lwjgl.Sys;
/*   9:    */import org.lwjgl.opengl.InputImplementation;
/*  10:    */
/*  74:    */public class Cursor
/*  75:    */{
/*  76:    */  public static final int CURSOR_ONE_BIT_TRANSPARENCY = 1;
/*  77:    */  public static final int CURSOR_8_BIT_ALPHA = 2;
/*  78:    */  public static final int CURSOR_ANIMATION = 4;
/*  79:    */  private final CursorElement[] cursors;
/*  80:    */  private int index;
/*  81:    */  private boolean destroyed;
/*  82:    */  
/*  83:    */  public Cursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
/*  84:    */    throws LWJGLException
/*  85:    */  {
/*  86: 86 */    synchronized (OpenGLPackageAccess.global_lock) {
/*  87: 87 */      if ((getCapabilities() & 0x1) == 0)
/*  88: 88 */        throw new LWJGLException("Native cursors not supported");
/*  89: 89 */      images = NondirectBufferWrapper.wrapBuffer(images, width * height * numImages);
/*  90: 90 */      if (delays != null)
/*  91: 91 */        delays = NondirectBufferWrapper.wrapBuffer(delays, numImages);
/*  92: 92 */      if (!Mouse.isCreated())
/*  93: 93 */        throw new IllegalStateException("Mouse must be created before creating cursor objects");
/*  94: 94 */      if (width * height * numImages > images.remaining())
/*  95: 95 */        throw new IllegalArgumentException("width*height*numImages > images.remaining()");
/*  96: 96 */      if ((xHotspot >= width) || (xHotspot < 0))
/*  97: 97 */        throw new IllegalArgumentException("xHotspot > width || xHotspot < 0");
/*  98: 98 */      if ((yHotspot >= height) || (yHotspot < 0)) {
/*  99: 99 */        throw new IllegalArgumentException("yHotspot > height || yHotspot < 0");
/* 100:    */      }
/* 101:101 */      Sys.initialize();
/* 102:    */      
/* 104:104 */      yHotspot = height - 1 - yHotspot;
/* 105:    */      
/* 107:107 */      this.cursors = createCursors(width, height, xHotspot, yHotspot, numImages, images, delays);
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 117:    */  public static int getMinCursorSize()
/* 118:    */  {
/* 119:119 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 120:120 */      if (!Mouse.isCreated())
/* 121:121 */        throw new IllegalStateException("Mouse must be created.");
/* 122:122 */      return Mouse.getImplementation().getMinCursorSize();
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 132:    */  public static int getMaxCursorSize()
/* 133:    */  {
/* 134:134 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 135:135 */      if (!Mouse.isCreated())
/* 136:136 */        throw new IllegalStateException("Mouse must be created.");
/* 137:137 */      return Mouse.getImplementation().getMaxCursorSize();
/* 138:    */    }
/* 139:    */  }
/* 140:    */  
/* 148:    */  public static int getCapabilities()
/* 149:    */  {
/* 150:150 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 151:151 */      if (Mouse.getImplementation() != null) {
/* 152:152 */        return Mouse.getImplementation().getNativeCursorCapabilities();
/* 153:    */      }
/* 154:154 */      return OpenGLPackageAccess.createImplementation().getNativeCursorCapabilities();
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 160:    */  private static CursorElement[] createCursors(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
/* 161:    */    throws LWJGLException
/* 162:    */  {
/* 163:163 */    IntBuffer images_copy = BufferUtils.createIntBuffer(images.remaining());
/* 164:164 */    flipImages(width, height, numImages, images, images_copy);
/* 165:    */    
/* 169:    */    CursorElement[] cursors;
/* 170:    */    
/* 174:174 */    switch (LWJGLUtil.getPlatform())
/* 175:    */    {
/* 177:    */    case 2: 
/* 178:178 */      convertARGBtoABGR(images_copy);
/* 179:    */      
/* 181:181 */      cursors = new CursorElement[numImages];
/* 182:182 */      for (int i = 0; i < numImages; i++) {
/* 183:183 */        Object handle = Mouse.getImplementation().createCursor(width, height, xHotspot, yHotspot, 1, images_copy, null);
/* 184:184 */        long delay = delays != null ? delays.get(i) : 0L;
/* 185:185 */        long timeout = System.currentTimeMillis();
/* 186:186 */        cursors[i] = new CursorElement(handle, delay, timeout);
/* 187:    */        
/* 189:189 */        images_copy.position(width * height * (i + 1));
/* 190:    */      }
/* 191:191 */      break;
/* 192:    */    
/* 193:    */    case 3: 
/* 194:194 */      cursors = new CursorElement[numImages];
/* 195:195 */      for (int i = 0; i < numImages; i++)
/* 196:    */      {
/* 198:198 */        int size = width * height;
/* 199:199 */        for (int j = 0; j < size; j++) {
/* 200:200 */          int index = j + i * size;
/* 201:201 */          int alpha = images_copy.get(index) >> 24 & 0xFF;
/* 202:202 */          if (alpha != 255) {
/* 203:203 */            images_copy.put(index, 0);
/* 204:    */          }
/* 205:    */        }
/* 206:    */        
/* 207:207 */        Object handle = Mouse.getImplementation().createCursor(width, height, xHotspot, yHotspot, 1, images_copy, null);
/* 208:208 */        long delay = delays != null ? delays.get(i) : 0L;
/* 209:209 */        long timeout = System.currentTimeMillis();
/* 210:210 */        cursors[i] = new CursorElement(handle, delay, timeout);
/* 211:    */        
/* 213:213 */        images_copy.position(width * height * (i + 1));
/* 214:    */      }
/* 215:215 */      break;
/* 216:    */    
/* 217:    */    case 1: 
/* 218:218 */      Object handle = Mouse.getImplementation().createCursor(width, height, xHotspot, yHotspot, numImages, images_copy, delays);
/* 219:219 */      CursorElement cursor_element = new CursorElement(handle, -1L, -1L);
/* 220:220 */      cursors = new CursorElement[] { cursor_element };
/* 221:221 */      break;
/* 222:    */    default: 
/* 223:223 */      throw new RuntimeException("Unknown OS"); }
/* 224:    */    
/* 225:225 */    return cursors;
/* 226:    */  }
/* 227:    */  
/* 232:    */  private static void convertARGBtoABGR(IntBuffer imageBuffer)
/* 233:    */  {
/* 234:234 */    for (int i = 0; i < imageBuffer.limit(); i++) {
/* 235:235 */      int argbColor = imageBuffer.get(i);
/* 236:    */      
/* 237:237 */      byte alpha = (byte)(argbColor >>> 24);
/* 238:238 */      byte blue = (byte)(argbColor >>> 16);
/* 239:239 */      byte green = (byte)(argbColor >>> 8);
/* 240:240 */      byte red = (byte)argbColor;
/* 241:    */      
/* 242:242 */      int abgrColor = ((alpha & 0xFF) << 24) + ((red & 0xFF) << 16) + ((green & 0xFF) << 8) + (blue & 0xFF);
/* 243:    */      
/* 244:244 */      imageBuffer.put(i, abgrColor);
/* 245:    */    }
/* 246:    */  }
/* 247:    */  
/* 256:    */  private static void flipImages(int width, int height, int numImages, IntBuffer images, IntBuffer images_copy)
/* 257:    */  {
/* 258:258 */    for (int i = 0; i < numImages; i++) {
/* 259:259 */      int start_index = i * width * height;
/* 260:260 */      flipImage(width, height, start_index, images, images_copy);
/* 261:    */    }
/* 262:    */  }
/* 263:    */  
/* 270:    */  private static void flipImage(int width, int height, int start_index, IntBuffer images, IntBuffer images_copy)
/* 271:    */  {
/* 272:272 */    for (int y = 0; y < height >> 1; y++) {
/* 273:273 */      int index_y_1 = y * width + start_index;
/* 274:274 */      int index_y_2 = (height - y - 1) * width + start_index;
/* 275:275 */      for (int x = 0; x < width; x++) {
/* 276:276 */        int index1 = index_y_1 + x;
/* 277:277 */        int index2 = index_y_2 + x;
/* 278:278 */        int temp_pixel = images.get(index1 + images.position());
/* 279:279 */        images_copy.put(index1, images.get(index2 + images.position()));
/* 280:280 */        images_copy.put(index2, temp_pixel);
/* 281:    */      }
/* 282:    */    }
/* 283:    */  }
/* 284:    */  
/* 287:    */  Object getHandle()
/* 288:    */  {
/* 289:289 */    checkValid();
/* 290:290 */    return this.cursors[this.index].cursorHandle;
/* 291:    */  }
/* 292:    */  
/* 293:    */  private void checkValid() {
/* 294:294 */    if (this.destroyed) {
/* 295:295 */      throw new IllegalStateException("The cursor is destroyed");
/* 296:    */    }
/* 297:    */  }
/* 298:    */  
/* 302:    */  public void destroy()
/* 303:    */  {
/* 304:304 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 305:305 */      if (this.destroyed)
/* 306:306 */        return;
/* 307:307 */      if (Mouse.getNativeCursor() == this) {
/* 308:    */        try {
/* 309:309 */          Mouse.setNativeCursor(null);
/* 310:    */        }
/* 311:    */        catch (LWJGLException e) {}
/* 312:    */      }
/* 313:    */      
/* 314:314 */      for (CursorElement cursor : this.cursors) {
/* 315:315 */        Mouse.getImplementation().destroyCursor(cursor.cursorHandle);
/* 316:    */      }
/* 317:317 */      this.destroyed = true;
/* 318:    */    }
/* 319:    */  }
/* 320:    */  
/* 323:    */  protected void setTimeout()
/* 324:    */  {
/* 325:325 */    checkValid();
/* 326:326 */    this.cursors[this.index].timeout = (System.currentTimeMillis() + this.cursors[this.index].delay);
/* 327:    */  }
/* 328:    */  
/* 332:    */  protected boolean hasTimedOut()
/* 333:    */  {
/* 334:334 */    checkValid();
/* 335:335 */    return (this.cursors.length > 1) && (this.cursors[this.index].timeout < System.currentTimeMillis());
/* 336:    */  }
/* 337:    */  
/* 340:    */  protected void nextCursor()
/* 341:    */  {
/* 342:342 */    checkValid();
/* 343:343 */    this.index = (++this.index % this.cursors.length);
/* 344:    */  }
/* 345:    */  
/* 348:    */  private static class CursorElement
/* 349:    */  {
/* 350:    */    final Object cursorHandle;
/* 351:    */    
/* 353:    */    final long delay;
/* 354:    */    
/* 355:    */    long timeout;
/* 356:    */    
/* 358:    */    CursorElement(Object cursorHandle, long delay, long timeout)
/* 359:    */    {
/* 360:360 */      this.cursorHandle = cursorHandle;
/* 361:361 */      this.delay = delay;
/* 362:362 */      this.timeout = timeout;
/* 363:    */    }
/* 364:    */  }
/* 365:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.Cursor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */