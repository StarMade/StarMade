/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.ByteOrder;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*   7:    */import org.newdawn.slick.opengl.renderer.SGL;
/*   8:    */import org.newdawn.slick.util.GlUtil;
/*   9:    */import org.newdawn.slick.util.Log;
/*  10:    */
/*  25:    */public class TextureImpl
/*  26:    */  implements Texture
/*  27:    */{
/*  28: 28 */  protected static SGL GL = ;
/*  29:    */  
/*  30:    */  static Texture lastBind;
/*  31:    */  private int target;
/*  32:    */  private int textureID;
/*  33:    */  private int height;
/*  34:    */  private int width;
/*  35:    */  private int texWidth;
/*  36:    */  
/*  37:    */  public static Texture getLastBind()
/*  38:    */  {
/*  39: 39 */    return lastBind;
/*  40:    */  }
/*  41:    */  
/*  46:    */  private int texHeight;
/*  47:    */  
/*  51:    */  private float widthRatio;
/*  52:    */  
/*  55:    */  private float heightRatio;
/*  56:    */  
/*  59:    */  private boolean alpha;
/*  60:    */  
/*  63:    */  private String ref;
/*  64:    */  
/*  67:    */  private String cacheName;
/*  68:    */  
/*  71:    */  private ReloadData reloadData;
/*  72:    */  
/*  75:    */  protected TextureImpl() {}
/*  76:    */  
/*  80:    */  public TextureImpl(String ref, int target, int textureID)
/*  81:    */  {
/*  82: 82 */    this.target = target;
/*  83: 83 */    this.ref = ref;
/*  84: 84 */    this.textureID = textureID;
/*  85: 85 */    lastBind = this;
/*  86:    */  }
/*  87:    */  
/*  92:    */  public void setCacheName(String cacheName)
/*  93:    */  {
/*  94: 94 */    this.cacheName = cacheName;
/*  95:    */  }
/*  96:    */  
/*  99:    */  public boolean hasAlpha()
/* 100:    */  {
/* 101:101 */    return this.alpha;
/* 102:    */  }
/* 103:    */  
/* 106:    */  public String getTextureRef()
/* 107:    */  {
/* 108:108 */    return this.ref;
/* 109:    */  }
/* 110:    */  
/* 115:    */  public void setAlpha(boolean alpha)
/* 116:    */  {
/* 117:117 */    this.alpha = alpha;
/* 118:    */  }
/* 119:    */  
/* 122:    */  public static void bindNone()
/* 123:    */  {
/* 124:124 */    lastBind = null;
/* 125:125 */    GL.glDisable(3553);
/* 126:    */  }
/* 127:    */  
/* 132:    */  public static void unbind()
/* 133:    */  {
/* 134:134 */    lastBind = null;
/* 135:    */  }
/* 136:    */  
/* 139:    */  public void bind()
/* 140:    */  {
/* 141:141 */    if (lastBind != this) {
/* 142:142 */      lastBind = this;
/* 143:143 */      GL.glEnable(3553);
/* 144:144 */      GL.glBindTexture(this.target, this.textureID);
/* 145:    */    }
/* 146:    */  }
/* 147:    */  
/* 152:    */  public void setHeight(int height)
/* 153:    */  {
/* 154:154 */    this.height = height;
/* 155:155 */    setHeight();
/* 156:    */  }
/* 157:    */  
/* 162:    */  public void setWidth(int width)
/* 163:    */  {
/* 164:164 */    this.width = width;
/* 165:165 */    setWidth();
/* 166:    */  }
/* 167:    */  
/* 170:    */  public int getImageHeight()
/* 171:    */  {
/* 172:172 */    return this.height;
/* 173:    */  }
/* 174:    */  
/* 177:    */  public int getImageWidth()
/* 178:    */  {
/* 179:179 */    return this.width;
/* 180:    */  }
/* 181:    */  
/* 184:    */  public float getHeight()
/* 185:    */  {
/* 186:186 */    return this.heightRatio;
/* 187:    */  }
/* 188:    */  
/* 191:    */  public float getWidth()
/* 192:    */  {
/* 193:193 */    return this.widthRatio;
/* 194:    */  }
/* 195:    */  
/* 198:    */  public int getTextureHeight()
/* 199:    */  {
/* 200:200 */    return this.texHeight;
/* 201:    */  }
/* 202:    */  
/* 205:    */  public int getTextureWidth()
/* 206:    */  {
/* 207:207 */    return this.texWidth;
/* 208:    */  }
/* 209:    */  
/* 214:    */  public void setTextureHeight(int texHeight)
/* 215:    */  {
/* 216:216 */    this.texHeight = texHeight;
/* 217:217 */    setHeight();
/* 218:    */  }
/* 219:    */  
/* 224:    */  public void setTextureWidth(int texWidth)
/* 225:    */  {
/* 226:226 */    this.texWidth = texWidth;
/* 227:227 */    setWidth();
/* 228:    */  }
/* 229:    */  
/* 233:    */  private void setHeight()
/* 234:    */  {
/* 235:235 */    if (this.texHeight != 0) {
/* 236:236 */      this.heightRatio = (this.height / this.texHeight);
/* 237:    */    }
/* 238:    */  }
/* 239:    */  
/* 243:    */  private void setWidth()
/* 244:    */  {
/* 245:245 */    if (this.texWidth != 0) {
/* 246:246 */      this.widthRatio = (this.width / this.texWidth);
/* 247:    */    }
/* 248:    */  }
/* 249:    */  
/* 252:    */  public void release()
/* 253:    */  {
/* 254:254 */    IntBuffer texBuf = createIntBuffer(1);
/* 255:255 */    texBuf.put(this.textureID);
/* 256:256 */    texBuf.flip();
/* 257:    */    
/* 258:258 */    GL.glDeleteTextures(texBuf);
/* 259:    */    
/* 260:260 */    if (lastBind == this) {
/* 261:261 */      bindNone();
/* 262:    */    }
/* 263:    */    
/* 264:264 */    if (this.cacheName != null) {
/* 265:265 */      InternalTextureLoader.get().clear(this.cacheName);
/* 266:    */    } else {
/* 267:267 */      InternalTextureLoader.get().clear(this.ref);
/* 268:    */    }
/* 269:    */  }
/* 270:    */  
/* 273:    */  public int getTextureID()
/* 274:    */  {
/* 275:275 */    return this.textureID;
/* 276:    */  }
/* 277:    */  
/* 282:    */  public void setTextureID(int textureID)
/* 283:    */  {
/* 284:284 */    this.textureID = textureID;
/* 285:    */  }
/* 286:    */  
/* 293:    */  protected IntBuffer createIntBuffer(int size)
/* 294:    */  {
/* 295:295 */    ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
/* 296:296 */    temp.order(ByteOrder.nativeOrder());
/* 297:    */    
/* 298:298 */    return temp.asIntBuffer();
/* 299:    */  }
/* 300:    */  
/* 303:    */  public byte[] getTextureData()
/* 304:    */  {
/* 305:305 */    ByteBuffer buffer = GlUtil.getDynamicByteBuffer((hasAlpha() ? 4 : 3) * this.texWidth * this.texHeight);
/* 306:306 */    bind();
/* 307:307 */    GL.glGetTexImage(3553, 0, hasAlpha() ? 6408 : 6407, 5121, buffer);
/* 308:    */    
/* 309:309 */    byte[] data = new byte[buffer.limit()];
/* 310:310 */    buffer.get(data);
/* 311:311 */    buffer.clear();
/* 312:    */    
/* 313:313 */    return data;
/* 314:    */  }
/* 315:    */  
/* 318:    */  public void setTextureFilter(int textureFilter)
/* 319:    */  {
/* 320:320 */    bind();
/* 321:321 */    GL.glTexParameteri(this.target, 10241, textureFilter);
/* 322:322 */    GL.glTexParameteri(this.target, 10240, textureFilter);
/* 323:    */  }
/* 324:    */  
/* 334:    */  public void setTextureData(int srcPixelFormat, int componentCount, int minFilter, int magFilter, ByteBuffer textureBuffer)
/* 335:    */  {
/* 336:336 */    this.reloadData = new ReloadData(null);
/* 337:337 */    this.reloadData.srcPixelFormat = srcPixelFormat;
/* 338:338 */    this.reloadData.componentCount = componentCount;
/* 339:339 */    this.reloadData.minFilter = minFilter;
/* 340:340 */    this.reloadData.magFilter = magFilter;
/* 341:341 */    this.reloadData.textureBuffer = textureBuffer;
/* 342:    */  }
/* 343:    */  
/* 346:    */  public void reload()
/* 347:    */  {
/* 348:348 */    if (this.reloadData != null) {
/* 349:349 */      this.textureID = this.reloadData.reload();
/* 350:    */    }
/* 351:    */  }
/* 352:    */  
/* 355:    */  private class ReloadData
/* 356:    */  {
/* 357:    */    private int srcPixelFormat;
/* 358:    */    
/* 360:    */    private int componentCount;
/* 361:    */    
/* 362:    */    private int minFilter;
/* 363:    */    
/* 364:    */    private int magFilter;
/* 365:    */    
/* 366:    */    private ByteBuffer textureBuffer;
/* 367:    */    
/* 369:    */    private ReloadData() {}
/* 370:    */    
/* 372:    */    public int reload()
/* 373:    */    {
/* 374:374 */      Log.error("Reloading texture: " + TextureImpl.this.ref);
/* 375:375 */      return InternalTextureLoader.get().reload(TextureImpl.this, this.srcPixelFormat, this.componentCount, this.minFilter, this.magFilter, this.textureBuffer);
/* 376:    */    }
/* 377:    */  }
/* 378:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.TextureImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */