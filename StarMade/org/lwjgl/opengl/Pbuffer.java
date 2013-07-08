/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */import org.lwjgl.Sys;
/*   7:    */
/* 140:    */public final class Pbuffer
/* 141:    */  extends DrawableGL
/* 142:    */{
/* 143:    */  public static final int PBUFFER_SUPPORTED = 1;
/* 144:    */  public static final int RENDER_TEXTURE_SUPPORTED = 2;
/* 145:    */  public static final int RENDER_TEXTURE_RECTANGLE_SUPPORTED = 4;
/* 146:    */  public static final int RENDER_DEPTH_TEXTURE_SUPPORTED = 8;
/* 147:    */  public static final int MIPMAP_LEVEL = 8315;
/* 148:    */  public static final int CUBE_MAP_FACE = 8316;
/* 149:    */  public static final int TEXTURE_CUBE_MAP_POSITIVE_X = 8317;
/* 150:    */  public static final int TEXTURE_CUBE_MAP_NEGATIVE_X = 8318;
/* 151:    */  public static final int TEXTURE_CUBE_MAP_POSITIVE_Y = 8319;
/* 152:    */  public static final int TEXTURE_CUBE_MAP_NEGATIVE_Y = 8320;
/* 153:    */  public static final int TEXTURE_CUBE_MAP_POSITIVE_Z = 8321;
/* 154:    */  public static final int TEXTURE_CUBE_MAP_NEGATIVE_Z = 8322;
/* 155:    */  public static final int FRONT_LEFT_BUFFER = 8323;
/* 156:    */  public static final int FRONT_RIGHT_BUFFER = 8324;
/* 157:    */  public static final int BACK_LEFT_BUFFER = 8325;
/* 158:    */  public static final int BACK_RIGHT_BUFFER = 8326;
/* 159:    */  public static final int DEPTH_BUFFER = 8359;
/* 160:    */  private final int width;
/* 161:    */  private final int height;
/* 162:    */  
/* 163:    */  public Pbuffer(int width, int height, PixelFormat pixel_format, Drawable shared_drawable)
/* 164:    */    throws LWJGLException
/* 165:    */  {
/* 166:166 */    this(width, height, pixel_format, null, shared_drawable);
/* 167:    */  }
/* 168:    */  
/* 187:    */  public Pbuffer(int width, int height, PixelFormat pixel_format, RenderTexture renderTexture, Drawable shared_drawable)
/* 188:    */    throws LWJGLException
/* 189:    */  {
/* 190:190 */    this(width, height, pixel_format, renderTexture, shared_drawable, null);
/* 191:    */  }
/* 192:    */  
/* 212:    */  public Pbuffer(int width, int height, PixelFormat pixel_format, RenderTexture renderTexture, Drawable shared_drawable, ContextAttribs attribs)
/* 213:    */    throws LWJGLException
/* 214:    */  {
/* 215:215 */    if (pixel_format == null)
/* 216:216 */      throw new NullPointerException("Pixel format must be non-null");
/* 217:217 */    this.width = width;
/* 218:218 */    this.height = height;
/* 219:219 */    this.peer_info = createPbuffer(width, height, pixel_format, attribs, renderTexture);
/* 220:220 */    Context shared_context = null;
/* 221:221 */    if (shared_drawable == null)
/* 222:222 */      shared_drawable = Display.getDrawable();
/* 223:223 */    if (shared_drawable != null)
/* 224:224 */      shared_context = ((DrawableLWJGL)shared_drawable).getContext();
/* 225:225 */    this.context = new ContextGL(this.peer_info, attribs, (ContextGL)shared_context);
/* 226:    */  }
/* 227:    */  
/* 228:    */  private static PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, RenderTexture renderTexture) throws LWJGLException {
/* 229:229 */    if (renderTexture == null)
/* 230:    */    {
/* 233:233 */      IntBuffer defaultAttribs = BufferUtils.createIntBuffer(1);
/* 234:234 */      return Display.getImplementation().createPbuffer(width, height, pixel_format, attribs, null, defaultAttribs);
/* 235:    */    }
/* 236:236 */    return Display.getImplementation().createPbuffer(width, height, pixel_format, attribs, renderTexture.pixelFormatCaps, renderTexture.pBufferAttribs);
/* 237:    */  }
/* 238:    */  
/* 247:    */  public synchronized boolean isBufferLost()
/* 248:    */  {
/* 249:249 */    checkDestroyed();
/* 250:250 */    return Display.getImplementation().isBufferLost(this.peer_info);
/* 251:    */  }
/* 252:    */  
/* 257:    */  public static int getCapabilities()
/* 258:    */  {
/* 259:259 */    return Display.getImplementation().getPbufferCapabilities();
/* 260:    */  }
/* 261:    */  
/* 277:    */  public synchronized void setAttrib(int attrib, int value)
/* 278:    */  {
/* 279:279 */    checkDestroyed();
/* 280:280 */    Display.getImplementation().setPbufferAttrib(this.peer_info, attrib, value);
/* 281:    */  }
/* 282:    */  
/* 289:    */  public synchronized void bindTexImage(int buffer)
/* 290:    */  {
/* 291:291 */    checkDestroyed();
/* 292:292 */    Display.getImplementation().bindTexImageToPbuffer(this.peer_info, buffer);
/* 293:    */  }
/* 294:    */  
/* 299:    */  public synchronized void releaseTexImage(int buffer)
/* 300:    */  {
/* 301:301 */    checkDestroyed();
/* 302:302 */    Display.getImplementation().releaseTexImageFromPbuffer(this.peer_info, buffer);
/* 303:    */  }
/* 304:    */  
/* 307:    */  public synchronized int getHeight()
/* 308:    */  {
/* 309:309 */    checkDestroyed();
/* 310:310 */    return this.height;
/* 311:    */  }
/* 312:    */  
/* 315:    */  public synchronized int getWidth()
/* 316:    */  {
/* 317:317 */    checkDestroyed();
/* 318:318 */    return this.width;
/* 319:    */  }
/* 320:    */  
/* 321:    */  static {}
/* 322:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.Pbuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */