/*     */ package org.newdawn.slick.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ import org.newdawn.slick.opengl.renderer.Renderer;
/*     */ import org.newdawn.slick.opengl.renderer.SGL;
/*     */ import org.newdawn.slick.util.GlUtil;
/*     */ import org.newdawn.slick.util.Log;
/*     */ 
/*     */ public class TextureImpl
/*     */   implements Texture
/*     */ {
/*  28 */   protected static SGL GL = Renderer.get();
/*     */   static Texture lastBind;
/*     */   private int target;
/*     */   private int textureID;
/*     */   private int height;
/*     */   private int width;
/*     */   private int texWidth;
/*     */   private int texHeight;
/*     */   private float widthRatio;
/*     */   private float heightRatio;
/*     */   private boolean alpha;
/*     */   private String ref;
/*     */   private String cacheName;
/*     */   private ReloadData reloadData;
/*     */ 
/*     */   public static Texture getLastBind()
/*     */   {
/*  39 */     return lastBind;
/*     */   }
/*     */ 
/*     */   protected TextureImpl()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TextureImpl(String ref, int target, int textureID)
/*     */   {
/*  82 */     this.target = target;
/*  83 */     this.ref = ref;
/*  84 */     this.textureID = textureID;
/*  85 */     lastBind = this;
/*     */   }
/*     */ 
/*     */   public void setCacheName(String cacheName)
/*     */   {
/*  94 */     this.cacheName = cacheName;
/*     */   }
/*     */ 
/*     */   public boolean hasAlpha()
/*     */   {
/* 101 */     return this.alpha;
/*     */   }
/*     */ 
/*     */   public String getTextureRef()
/*     */   {
/* 108 */     return this.ref;
/*     */   }
/*     */ 
/*     */   public void setAlpha(boolean alpha)
/*     */   {
/* 117 */     this.alpha = alpha;
/*     */   }
/*     */ 
/*     */   public static void bindNone()
/*     */   {
/* 124 */     lastBind = null;
/* 125 */     GL.glDisable(3553);
/*     */   }
/*     */ 
/*     */   public static void unbind()
/*     */   {
/* 134 */     lastBind = null;
/*     */   }
/*     */ 
/*     */   public void bind()
/*     */   {
/* 141 */     if (lastBind != this) {
/* 142 */       lastBind = this;
/* 143 */       GL.glEnable(3553);
/* 144 */       GL.glBindTexture(this.target, this.textureID);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setHeight(int height)
/*     */   {
/* 154 */     this.height = height;
/* 155 */     setHeight();
/*     */   }
/*     */ 
/*     */   public void setWidth(int width)
/*     */   {
/* 164 */     this.width = width;
/* 165 */     setWidth();
/*     */   }
/*     */ 
/*     */   public int getImageHeight()
/*     */   {
/* 172 */     return this.height;
/*     */   }
/*     */ 
/*     */   public int getImageWidth()
/*     */   {
/* 179 */     return this.width;
/*     */   }
/*     */ 
/*     */   public float getHeight()
/*     */   {
/* 186 */     return this.heightRatio;
/*     */   }
/*     */ 
/*     */   public float getWidth()
/*     */   {
/* 193 */     return this.widthRatio;
/*     */   }
/*     */ 
/*     */   public int getTextureHeight()
/*     */   {
/* 200 */     return this.texHeight;
/*     */   }
/*     */ 
/*     */   public int getTextureWidth()
/*     */   {
/* 207 */     return this.texWidth;
/*     */   }
/*     */ 
/*     */   public void setTextureHeight(int texHeight)
/*     */   {
/* 216 */     this.texHeight = texHeight;
/* 217 */     setHeight();
/*     */   }
/*     */ 
/*     */   public void setTextureWidth(int texWidth)
/*     */   {
/* 226 */     this.texWidth = texWidth;
/* 227 */     setWidth();
/*     */   }
/*     */ 
/*     */   private void setHeight()
/*     */   {
/* 235 */     if (this.texHeight != 0)
/* 236 */       this.heightRatio = (this.height / this.texHeight);
/*     */   }
/*     */ 
/*     */   private void setWidth()
/*     */   {
/* 245 */     if (this.texWidth != 0)
/* 246 */       this.widthRatio = (this.width / this.texWidth);
/*     */   }
/*     */ 
/*     */   public void release()
/*     */   {
/* 254 */     IntBuffer texBuf = createIntBuffer(1);
/* 255 */     texBuf.put(this.textureID);
/* 256 */     texBuf.flip();
/*     */ 
/* 258 */     GL.glDeleteTextures(texBuf);
/*     */ 
/* 260 */     if (lastBind == this) {
/* 261 */       bindNone();
/*     */     }
/*     */ 
/* 264 */     if (this.cacheName != null)
/* 265 */       InternalTextureLoader.get().clear(this.cacheName);
/*     */     else
/* 267 */       InternalTextureLoader.get().clear(this.ref);
/*     */   }
/*     */ 
/*     */   public int getTextureID()
/*     */   {
/* 275 */     return this.textureID;
/*     */   }
/*     */ 
/*     */   public void setTextureID(int textureID)
/*     */   {
/* 284 */     this.textureID = textureID;
/*     */   }
/*     */ 
/*     */   protected IntBuffer createIntBuffer(int size)
/*     */   {
/* 295 */     ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
/* 296 */     temp.order(ByteOrder.nativeOrder());
/*     */ 
/* 298 */     return temp.asIntBuffer();
/*     */   }
/*     */ 
/*     */   public byte[] getTextureData()
/*     */   {
/* 305 */     ByteBuffer buffer = GlUtil.getDynamicByteBuffer((hasAlpha() ? 4 : 3) * this.texWidth * this.texHeight);
/* 306 */     bind();
/* 307 */     GL.glGetTexImage(3553, 0, hasAlpha() ? 6408 : 6407, 5121, buffer);
/*     */ 
/* 309 */     byte[] data = new byte[buffer.limit()];
/* 310 */     buffer.get(data);
/* 311 */     buffer.clear();
/*     */ 
/* 313 */     return data;
/*     */   }
/*     */ 
/*     */   public void setTextureFilter(int textureFilter)
/*     */   {
/* 320 */     bind();
/* 321 */     GL.glTexParameteri(this.target, 10241, textureFilter);
/* 322 */     GL.glTexParameteri(this.target, 10240, textureFilter);
/*     */   }
/*     */ 
/*     */   public void setTextureData(int srcPixelFormat, int componentCount, int minFilter, int magFilter, ByteBuffer textureBuffer)
/*     */   {
/* 336 */     this.reloadData = new ReloadData(null);
/* 337 */     this.reloadData.srcPixelFormat = srcPixelFormat;
/* 338 */     this.reloadData.componentCount = componentCount;
/* 339 */     this.reloadData.minFilter = minFilter;
/* 340 */     this.reloadData.magFilter = magFilter;
/* 341 */     this.reloadData.textureBuffer = textureBuffer;
/*     */   }
/*     */ 
/*     */   public void reload()
/*     */   {
/* 348 */     if (this.reloadData != null)
/* 349 */       this.textureID = this.reloadData.reload();
/*     */   }
/*     */ 
/*     */   private class ReloadData
/*     */   {
/*     */     private int srcPixelFormat;
/*     */     private int componentCount;
/*     */     private int minFilter;
/*     */     private int magFilter;
/*     */     private ByteBuffer textureBuffer;
/*     */ 
/*     */     private ReloadData()
/*     */     {
/*     */     }
/*     */ 
/*     */     public int reload()
/*     */     {
/* 374 */       Log.error("Reloading texture: " + TextureImpl.this.ref);
/* 375 */       return InternalTextureLoader.get().reload(TextureImpl.this, this.srcPixelFormat, this.componentCount, this.minFilter, this.magFilter, this.textureBuffer);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.TextureImpl
 * JD-Core Version:    0.6.2
 */