/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileInputStream;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.lang.ref.SoftReference;
/*   9:    */import java.nio.ByteBuffer;
/*  10:    */import java.nio.ByteOrder;
/*  11:    */import java.nio.IntBuffer;
/*  12:    */import java.util.Collection;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.Iterator;
/*  15:    */import org.lwjgl.BufferUtils;
/*  16:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  17:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  18:    */import org.newdawn.slick.util.ResourceLoader;
/*  19:    */
/*  28:    */public class InternalTextureLoader
/*  29:    */{
/*  30: 30 */  protected static SGL GL = ;
/*  31:    */  
/*  32: 32 */  private static final InternalTextureLoader loader = new InternalTextureLoader();
/*  33:    */  
/*  38:    */  public static InternalTextureLoader get()
/*  39:    */  {
/*  40: 40 */    return loader;
/*  41:    */  }
/*  42:    */  
/*  44: 44 */  private HashMap texturesLinear = new HashMap();
/*  45:    */  
/*  46: 46 */  private HashMap texturesNearest = new HashMap();
/*  47:    */  
/*  48: 48 */  private int dstPixelFormat = 6408;
/*  49:    */  
/*  54:    */  private boolean deferred;
/*  55:    */  
/*  59:    */  private boolean holdTextureData;
/*  60:    */  
/*  65:    */  public void setHoldTextureData(boolean holdTextureData)
/*  66:    */  {
/*  67: 67 */    this.holdTextureData = holdTextureData;
/*  68:    */  }
/*  69:    */  
/*  75:    */  public void setDeferredLoading(boolean deferred)
/*  76:    */  {
/*  77: 77 */    this.deferred = deferred;
/*  78:    */  }
/*  79:    */  
/*  84:    */  public boolean isDeferredLoading()
/*  85:    */  {
/*  86: 86 */    return this.deferred;
/*  87:    */  }
/*  88:    */  
/*  93:    */  public void clear(String name)
/*  94:    */  {
/*  95: 95 */    this.texturesLinear.remove(name);
/*  96: 96 */    this.texturesNearest.remove(name);
/*  97:    */  }
/*  98:    */  
/* 101:    */  public void clear()
/* 102:    */  {
/* 103:103 */    this.texturesLinear.clear();
/* 104:104 */    this.texturesNearest.clear();
/* 105:    */  }
/* 106:    */  
/* 109:    */  public void set16BitMode()
/* 110:    */  {
/* 111:111 */    this.dstPixelFormat = 32859;
/* 112:    */  }
/* 113:    */  
/* 119:    */  public static int createTextureID()
/* 120:    */  {
/* 121:121 */    IntBuffer tmp = createIntBuffer(1);
/* 122:122 */    GL.glGenTextures(tmp);
/* 123:123 */    return tmp.get(0);
/* 124:    */  }
/* 125:    */  
/* 133:    */  public Texture getTexture(File source, boolean flipped, int filter)
/* 134:    */    throws IOException
/* 135:    */  {
/* 136:136 */    String resourceName = source.getAbsolutePath();
/* 137:137 */    InputStream in = new FileInputStream(source);
/* 138:    */    
/* 139:139 */    return getTexture(in, resourceName, flipped, filter, null);
/* 140:    */  }
/* 141:    */  
/* 150:    */  public Texture getTexture(File source, boolean flipped, int filter, int[] transparent)
/* 151:    */    throws IOException
/* 152:    */  {
/* 153:153 */    String resourceName = source.getAbsolutePath();
/* 154:154 */    InputStream in = new FileInputStream(source);
/* 155:    */    
/* 156:156 */    return getTexture(in, resourceName, flipped, filter, transparent);
/* 157:    */  }
/* 158:    */  
/* 166:    */  public Texture getTexture(String resourceName, boolean flipped, int filter)
/* 167:    */    throws IOException
/* 168:    */  {
/* 169:169 */    InputStream in = ResourceLoader.getResourceAsStream(resourceName);
/* 170:    */    
/* 171:171 */    return getTexture(in, resourceName, flipped, filter, null);
/* 172:    */  }
/* 173:    */  
/* 182:    */  public Texture getTexture(String resourceName, boolean flipped, int filter, int[] transparent)
/* 183:    */    throws IOException
/* 184:    */  {
/* 185:185 */    InputStream in = ResourceLoader.getResourceAsStream(resourceName);
/* 186:    */    
/* 187:187 */    return getTexture(in, resourceName, flipped, filter, transparent);
/* 188:    */  }
/* 189:    */  
/* 197:    */  public Texture getTexture(InputStream in, String resourceName, boolean flipped, int filter)
/* 198:    */    throws IOException
/* 199:    */  {
/* 200:200 */    return getTexture(in, resourceName, flipped, filter, null);
/* 201:    */  }
/* 202:    */  
/* 212:    */  public TextureImpl getTexture(InputStream in, String resourceName, boolean flipped, int filter, int[] transparent)
/* 213:    */    throws IOException
/* 214:    */  {
/* 215:215 */    if (this.deferred) {
/* 216:216 */      return new DeferredTexture(in, resourceName, flipped, filter, transparent);
/* 217:    */    }
/* 218:    */    
/* 219:219 */    HashMap hash = this.texturesLinear;
/* 220:220 */    if (filter == 9728) {
/* 221:221 */      hash = this.texturesNearest;
/* 222:    */    }
/* 223:    */    
/* 224:224 */    String resName = resourceName;
/* 225:225 */    if (transparent != null) {
/* 226:226 */      resName = resName + ":" + transparent[0] + ":" + transparent[1] + ":" + transparent[2];
/* 227:    */    }
/* 228:228 */    resName = resName + ":" + flipped;
/* 229:    */    
/* 230:230 */    if (this.holdTextureData) {
/* 231:231 */      TextureImpl tex = (TextureImpl)hash.get(resName);
/* 232:232 */      if (tex != null) {
/* 233:233 */        return tex;
/* 234:    */      }
/* 235:    */    } else {
/* 236:236 */      SoftReference ref = (SoftReference)hash.get(resName);
/* 237:237 */      if (ref != null) {
/* 238:238 */        TextureImpl tex = (TextureImpl)ref.get();
/* 239:239 */        if (tex != null) {
/* 240:240 */          return tex;
/* 241:    */        }
/* 242:242 */        hash.remove(resName);
/* 243:    */      }
/* 244:    */    }
/* 245:    */    
/* 247:    */    try
/* 248:    */    {
/* 249:249 */      GL.glGetError();
/* 250:    */    } catch (NullPointerException e) {
/* 251:251 */      throw new RuntimeException("Image based resources must be loaded as part of init() or the game loop. They cannot be loaded before initialisation.");
/* 252:    */    }
/* 253:    */    
/* 254:254 */    TextureImpl tex = getTexture(in, resourceName, 3553, filter, filter, flipped, transparent);
/* 255:    */    
/* 259:259 */    tex.setCacheName(resName);
/* 260:260 */    if (this.holdTextureData) {
/* 261:261 */      hash.put(resName, tex);
/* 262:    */    } else {
/* 263:263 */      hash.put(resName, new SoftReference(tex));
/* 264:    */    }
/* 265:    */    
/* 266:266 */    return tex;
/* 267:    */  }
/* 268:    */  
/* 288:    */  private TextureImpl getTexture(InputStream in, String resourceName, int target, int magFilter, int minFilter, boolean flipped, int[] transparent)
/* 289:    */    throws IOException
/* 290:    */  {
/* 291:291 */    LoadableImageData imageData = ImageDataFactory.getImageDataFor(resourceName);
/* 292:292 */    ByteBuffer textureBuffer = imageData.loadImage(new BufferedInputStream(in), flipped, transparent);
/* 293:    */    
/* 294:294 */    int textureID = createTextureID();
/* 295:295 */    TextureImpl texture = new TextureImpl(resourceName, target, textureID);
/* 296:    */    
/* 297:297 */    GL.glBindTexture(target, textureID);
/* 298:    */    
/* 306:306 */    int width = imageData.getWidth();
/* 307:307 */    int height = imageData.getHeight();
/* 308:308 */    boolean hasAlpha = imageData.getDepth() == 32;
/* 309:    */    
/* 310:310 */    texture.setTextureWidth(imageData.getTexWidth());
/* 311:311 */    texture.setTextureHeight(imageData.getTexHeight());
/* 312:    */    
/* 313:313 */    int texWidth = texture.getTextureWidth();
/* 314:314 */    int texHeight = texture.getTextureHeight();
/* 315:    */    
/* 316:316 */    IntBuffer temp = BufferUtils.createIntBuffer(16);
/* 317:317 */    GL.glGetInteger(3379, temp);
/* 318:318 */    int max = temp.get(0);
/* 319:319 */    if ((texWidth > max) || (texHeight > max)) {
/* 320:320 */      throw new IOException("Attempt to allocate a texture to big for the current hardware");
/* 321:    */    }
/* 322:    */    
/* 323:323 */    int srcPixelFormat = hasAlpha ? 6408 : 6407;
/* 324:324 */    int componentCount = hasAlpha ? 4 : 3;
/* 325:    */    
/* 326:326 */    texture.setWidth(width);
/* 327:327 */    texture.setHeight(height);
/* 328:328 */    texture.setAlpha(hasAlpha);
/* 329:    */    
/* 330:330 */    if (this.holdTextureData) {
/* 331:331 */      texture.setTextureData(srcPixelFormat, componentCount, minFilter, magFilter, textureBuffer);
/* 332:    */    }
/* 333:    */    
/* 334:334 */    GL.glTexParameteri(target, 10241, minFilter);
/* 335:335 */    GL.glTexParameteri(target, 10240, magFilter);
/* 336:    */    
/* 338:338 */    GL.glTexImage2D(target, 0, this.dstPixelFormat, get2Fold(width), get2Fold(height), 0, srcPixelFormat, 5121, textureBuffer);
/* 339:    */    
/* 348:348 */    return texture;
/* 349:    */  }
/* 350:    */  
/* 357:    */  public Texture createTexture(int width, int height)
/* 358:    */    throws IOException
/* 359:    */  {
/* 360:360 */    return createTexture(width, height, 9728);
/* 361:    */  }
/* 362:    */  
/* 369:    */  public Texture createTexture(int width, int height, int filter)
/* 370:    */    throws IOException
/* 371:    */  {
/* 372:372 */    ImageData ds = new EmptyImageData(width, height);
/* 373:    */    
/* 374:374 */    return getTexture(ds, filter);
/* 375:    */  }
/* 376:    */  
/* 384:    */  public Texture getTexture(ImageData dataSource, int filter)
/* 385:    */    throws IOException
/* 386:    */  {
/* 387:387 */    int target = 3553;
/* 388:    */    
/* 390:390 */    ByteBuffer textureBuffer = dataSource.getImageBufferData();
/* 391:    */    
/* 393:393 */    int textureID = createTextureID();
/* 394:394 */    TextureImpl texture = new TextureImpl("generated:" + dataSource, target, textureID);
/* 395:    */    
/* 396:396 */    int minFilter = filter;
/* 397:397 */    int magFilter = filter;
/* 398:398 */    boolean flipped = false;
/* 399:    */    
/* 401:401 */    GL.glBindTexture(target, textureID);
/* 402:    */    
/* 410:410 */    int width = dataSource.getWidth();
/* 411:411 */    int height = dataSource.getHeight();
/* 412:412 */    boolean hasAlpha = dataSource.getDepth() == 32;
/* 413:    */    
/* 414:414 */    texture.setTextureWidth(dataSource.getTexWidth());
/* 415:415 */    texture.setTextureHeight(dataSource.getTexHeight());
/* 416:    */    
/* 417:417 */    int texWidth = texture.getTextureWidth();
/* 418:418 */    int texHeight = texture.getTextureHeight();
/* 419:    */    
/* 420:420 */    int srcPixelFormat = hasAlpha ? 6408 : 6407;
/* 421:421 */    int componentCount = hasAlpha ? 4 : 3;
/* 422:    */    
/* 423:423 */    texture.setWidth(width);
/* 424:424 */    texture.setHeight(height);
/* 425:425 */    texture.setAlpha(hasAlpha);
/* 426:    */    
/* 427:427 */    IntBuffer temp = BufferUtils.createIntBuffer(16);
/* 428:428 */    GL.glGetInteger(3379, temp);
/* 429:429 */    int max = temp.get(0);
/* 430:430 */    if ((texWidth > max) || (texHeight > max)) {
/* 431:431 */      throw new IOException("Attempt to allocate a texture to big for the current hardware");
/* 432:    */    }
/* 433:    */    
/* 434:434 */    if (this.holdTextureData) {
/* 435:435 */      texture.setTextureData(srcPixelFormat, componentCount, minFilter, magFilter, textureBuffer);
/* 436:    */    }
/* 437:    */    
/* 438:438 */    GL.glTexParameteri(target, 10241, minFilter);
/* 439:439 */    GL.glTexParameteri(target, 10240, magFilter);
/* 440:    */    
/* 442:442 */    GL.glTexImage2D(target, 0, this.dstPixelFormat, get2Fold(width), get2Fold(height), 0, srcPixelFormat, 5121, textureBuffer);
/* 443:    */    
/* 452:452 */    return texture;
/* 453:    */  }
/* 454:    */  
/* 460:    */  public static int get2Fold(int fold)
/* 461:    */  {
/* 462:462 */    int ret = 2;
/* 463:463 */    while (ret < fold) {
/* 464:464 */      ret *= 2;
/* 465:    */    }
/* 466:466 */    return ret;
/* 467:    */  }
/* 468:    */  
/* 475:    */  public static IntBuffer createIntBuffer(int size)
/* 476:    */  {
/* 477:477 */    ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
/* 478:478 */    temp.order(ByteOrder.nativeOrder());
/* 479:    */    
/* 480:480 */    return temp.asIntBuffer();
/* 481:    */  }
/* 482:    */  
/* 485:    */  public void reload()
/* 486:    */  {
/* 487:487 */    Iterator texs = this.texturesLinear.values().iterator();
/* 488:488 */    while (texs.hasNext()) {
/* 489:489 */      ((TextureImpl)texs.next()).reload();
/* 490:    */    }
/* 491:491 */    texs = this.texturesNearest.values().iterator();
/* 492:492 */    while (texs.hasNext()) {
/* 493:493 */      ((TextureImpl)texs.next()).reload();
/* 494:    */    }
/* 495:    */  }
/* 496:    */  
/* 508:    */  public int reload(TextureImpl texture, int srcPixelFormat, int componentCount, int minFilter, int magFilter, ByteBuffer textureBuffer)
/* 509:    */  {
/* 510:510 */    int target = 3553;
/* 511:511 */    int textureID = createTextureID();
/* 512:512 */    GL.glBindTexture(target, textureID);
/* 513:    */    
/* 514:514 */    GL.glTexParameteri(target, 10241, minFilter);
/* 515:515 */    GL.glTexParameteri(target, 10240, magFilter);
/* 516:    */    
/* 518:518 */    GL.glTexImage2D(target, 0, this.dstPixelFormat, texture.getTextureWidth(), texture.getTextureHeight(), 0, srcPixelFormat, 5121, textureBuffer);
/* 519:    */    
/* 528:528 */    return textureID;
/* 529:    */  }
/* 530:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.InternalTextureLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */