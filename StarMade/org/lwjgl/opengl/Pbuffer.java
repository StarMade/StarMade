/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ public final class Pbuffer extends DrawableGL
/*     */ {
/*     */   public static final int PBUFFER_SUPPORTED = 1;
/*     */   public static final int RENDER_TEXTURE_SUPPORTED = 2;
/*     */   public static final int RENDER_TEXTURE_RECTANGLE_SUPPORTED = 4;
/*     */   public static final int RENDER_DEPTH_TEXTURE_SUPPORTED = 8;
/*     */   public static final int MIPMAP_LEVEL = 8315;
/*     */   public static final int CUBE_MAP_FACE = 8316;
/*     */   public static final int TEXTURE_CUBE_MAP_POSITIVE_X = 8317;
/*     */   public static final int TEXTURE_CUBE_MAP_NEGATIVE_X = 8318;
/*     */   public static final int TEXTURE_CUBE_MAP_POSITIVE_Y = 8319;
/*     */   public static final int TEXTURE_CUBE_MAP_NEGATIVE_Y = 8320;
/*     */   public static final int TEXTURE_CUBE_MAP_POSITIVE_Z = 8321;
/*     */   public static final int TEXTURE_CUBE_MAP_NEGATIVE_Z = 8322;
/*     */   public static final int FRONT_LEFT_BUFFER = 8323;
/*     */   public static final int FRONT_RIGHT_BUFFER = 8324;
/*     */   public static final int BACK_LEFT_BUFFER = 8325;
/*     */   public static final int BACK_RIGHT_BUFFER = 8326;
/*     */   public static final int DEPTH_BUFFER = 8359;
/*     */   private final int width;
/*     */   private final int height;
/*     */ 
/*     */   public Pbuffer(int width, int height, PixelFormat pixel_format, Drawable shared_drawable)
/*     */     throws LWJGLException
/*     */   {
/* 166 */     this(width, height, pixel_format, null, shared_drawable);
/*     */   }
/*     */ 
/*     */   public Pbuffer(int width, int height, PixelFormat pixel_format, RenderTexture renderTexture, Drawable shared_drawable)
/*     */     throws LWJGLException
/*     */   {
/* 190 */     this(width, height, pixel_format, renderTexture, shared_drawable, null);
/*     */   }
/*     */ 
/*     */   public Pbuffer(int width, int height, PixelFormat pixel_format, RenderTexture renderTexture, Drawable shared_drawable, ContextAttribs attribs)
/*     */     throws LWJGLException
/*     */   {
/* 215 */     if (pixel_format == null)
/* 216 */       throw new NullPointerException("Pixel format must be non-null");
/* 217 */     this.width = width;
/* 218 */     this.height = height;
/* 219 */     this.peer_info = createPbuffer(width, height, pixel_format, attribs, renderTexture);
/* 220 */     Context shared_context = null;
/* 221 */     if (shared_drawable == null)
/* 222 */       shared_drawable = Display.getDrawable();
/* 223 */     if (shared_drawable != null)
/* 224 */       shared_context = ((DrawableLWJGL)shared_drawable).getContext();
/* 225 */     this.context = new ContextGL(this.peer_info, attribs, (ContextGL)shared_context);
/*     */   }
/*     */ 
/*     */   private static PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, RenderTexture renderTexture) throws LWJGLException {
/* 229 */     if (renderTexture == null)
/*     */     {
/* 233 */       IntBuffer defaultAttribs = BufferUtils.createIntBuffer(1);
/* 234 */       return Display.getImplementation().createPbuffer(width, height, pixel_format, attribs, null, defaultAttribs);
/*     */     }
/* 236 */     return Display.getImplementation().createPbuffer(width, height, pixel_format, attribs, renderTexture.pixelFormatCaps, renderTexture.pBufferAttribs);
/*     */   }
/*     */ 
/*     */   public synchronized boolean isBufferLost()
/*     */   {
/* 249 */     checkDestroyed();
/* 250 */     return Display.getImplementation().isBufferLost(this.peer_info);
/*     */   }
/*     */ 
/*     */   public static int getCapabilities()
/*     */   {
/* 259 */     return Display.getImplementation().getPbufferCapabilities();
/*     */   }
/*     */ 
/*     */   public synchronized void setAttrib(int attrib, int value)
/*     */   {
/* 279 */     checkDestroyed();
/* 280 */     Display.getImplementation().setPbufferAttrib(this.peer_info, attrib, value);
/*     */   }
/*     */ 
/*     */   public synchronized void bindTexImage(int buffer)
/*     */   {
/* 291 */     checkDestroyed();
/* 292 */     Display.getImplementation().bindTexImageToPbuffer(this.peer_info, buffer);
/*     */   }
/*     */ 
/*     */   public synchronized void releaseTexImage(int buffer)
/*     */   {
/* 301 */     checkDestroyed();
/* 302 */     Display.getImplementation().releaseTexImageFromPbuffer(this.peer_info, buffer);
/*     */   }
/*     */ 
/*     */   public synchronized int getHeight()
/*     */   {
/* 309 */     checkDestroyed();
/* 310 */     return this.height;
/*     */   }
/*     */ 
/*     */   public synchronized int getWidth()
/*     */   {
/* 317 */     checkDestroyed();
/* 318 */     return this.width;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 148 */     Sys.initialize();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.Pbuffer
 * JD-Core Version:    0.6.2
 */