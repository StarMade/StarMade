/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Canvas;
/*   4:    */import java.awt.Dimension;
/*   5:    */import java.awt.Graphics;
/*   6:    */import java.awt.GraphicsDevice;
/*   7:    */import java.awt.GraphicsEnvironment;
/*   8:    */import java.awt.Point;
/*   9:    */import java.awt.event.ComponentEvent;
/*  10:    */import java.awt.event.ComponentListener;
/*  11:    */import java.awt.event.HierarchyEvent;
/*  12:    */import java.awt.event.HierarchyListener;
/*  13:    */import org.lwjgl.LWJGLException;
/*  14:    */import org.lwjgl.LWJGLUtil;
/*  15:    */import org.lwjgl.PointerBuffer;
/*  16:    */import org.lwjgl.Sys;
/*  17:    */
/*  51:    */public class AWTGLCanvas
/*  52:    */  extends Canvas
/*  53:    */  implements DrawableLWJGL, ComponentListener, HierarchyListener
/*  54:    */{
/*  55:    */  private boolean first_run;
/*  56:    */  private int reentry_count;
/*  57:    */  private ContextGL context;
/*  58:    */  private PeerInfo peer_info;
/*  59:    */  private final ContextAttribs attribs;
/*  60:    */  private final Drawable drawable;
/*  61:    */  private final PixelFormat pixel_format;
/*  62: 62 */  private Object SYNC_LOCK = new Object();
/*  63:    */  
/*  75:    */  private boolean update_context;
/*  76:    */  
/*  88: 88 */  private static final AWTCanvasImplementation implementation = createImplementation();
/*  89:    */  private static final long serialVersionUID = 1L;
/*  90:    */  
/*  91:    */  static AWTCanvasImplementation createImplementation() {
/*  92: 92 */    switch () {
/*  93:    */    case 1: 
/*  94: 94 */      return new LinuxCanvasImplementation();
/*  95:    */    case 3: 
/*  96: 96 */      return new WindowsCanvasImplementation();
/*  97:    */    case 2: 
/*  98: 98 */      return new MacOSXCanvasImplementation();
/*  99:    */    }
/* 100:100 */    throw new IllegalStateException("Unsupported platform");
/* 101:    */  }
/* 102:    */  
/* 103:    */  private void setUpdate()
/* 104:    */  {
/* 105:105 */    synchronized (this.SYNC_LOCK) {
/* 106:106 */      this.update_context = true;
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setPixelFormat(PixelFormatLWJGL pf) throws LWJGLException {
/* 111:111 */    throw new UnsupportedOperationException();
/* 112:    */  }
/* 113:    */  
/* 114:    */  public void setPixelFormat(PixelFormatLWJGL pf, ContextAttribs attribs) throws LWJGLException {
/* 115:115 */    throw new UnsupportedOperationException();
/* 116:    */  }
/* 117:    */  
/* 118:    */  public PixelFormatLWJGL getPixelFormat() {
/* 119:119 */    return this.pixel_format;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public ContextGL getContext()
/* 123:    */  {
/* 124:124 */    return this.context;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public ContextGL createSharedContext() throws LWJGLException
/* 128:    */  {
/* 129:129 */    synchronized (this.SYNC_LOCK) {
/* 130:130 */      if (this.context == null) { throw new IllegalStateException("Canvas not yet displayable");
/* 131:    */      }
/* 132:132 */      return new ContextGL(this.peer_info, this.context.getContextAttribs(), this.context);
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 137:    */  public void checkGLError() {}
/* 138:    */  
/* 140:    */  public void initContext(float r, float g, float b)
/* 141:    */  {
/* 142:142 */    GL11.glClearColor(r, g, b, 0.0F);
/* 143:    */    
/* 144:144 */    GL11.glClear(16384);
/* 145:    */  }
/* 146:    */  
/* 147:    */  public AWTGLCanvas() throws LWJGLException
/* 148:    */  {
/* 149:149 */    this(new PixelFormat());
/* 150:    */  }
/* 151:    */  
/* 155:    */  public AWTGLCanvas(PixelFormat pixel_format)
/* 156:    */    throws LWJGLException
/* 157:    */  {
/* 158:158 */    this(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(), pixel_format);
/* 159:    */  }
/* 160:    */  
/* 165:    */  public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format)
/* 166:    */    throws LWJGLException
/* 167:    */  {
/* 168:168 */    this(device, pixel_format, null);
/* 169:    */  }
/* 170:    */  
/* 176:    */  public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format, Drawable drawable)
/* 177:    */    throws LWJGLException
/* 178:    */  {
/* 179:179 */    this(device, pixel_format, drawable, null);
/* 180:    */  }
/* 181:    */  
/* 188:    */  public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format, Drawable drawable, ContextAttribs attribs)
/* 189:    */    throws LWJGLException
/* 190:    */  {
/* 191:191 */    super(implementation.findConfiguration(device, pixel_format));
/* 192:192 */    if (pixel_format == null)
/* 193:193 */      throw new NullPointerException("Pixel format must be non-null");
/* 194:194 */    addHierarchyListener(this);
/* 195:195 */    addComponentListener(this);
/* 196:196 */    this.drawable = drawable;
/* 197:197 */    this.pixel_format = pixel_format;
/* 198:198 */    this.attribs = attribs;
/* 199:    */  }
/* 200:    */  
/* 203:    */  public void addNotify()
/* 204:    */  {
/* 205:205 */    super.addNotify();
/* 206:    */  }
/* 207:    */  
/* 210:    */  public void removeNotify()
/* 211:    */  {
/* 212:212 */    synchronized (this.SYNC_LOCK) {
/* 213:213 */      destroy();
/* 214:214 */      super.removeNotify();
/* 215:    */    }
/* 216:    */  }
/* 217:    */  
/* 218:    */  public void setSwapInterval(int swap_interval)
/* 219:    */  {
/* 220:220 */    synchronized (this.SYNC_LOCK) {
/* 221:221 */      if (this.context == null)
/* 222:222 */        throw new IllegalStateException("Canvas not yet displayable");
/* 223:223 */      ContextGL.setSwapInterval(swap_interval);
/* 224:    */    }
/* 225:    */  }
/* 226:    */  
/* 227:    */  public void setVSyncEnabled(boolean enabled)
/* 228:    */  {
/* 229:229 */    setSwapInterval(enabled ? 1 : 0);
/* 230:    */  }
/* 231:    */  
/* 232:    */  public void swapBuffers() throws LWJGLException
/* 233:    */  {
/* 234:234 */    synchronized (this.SYNC_LOCK) {
/* 235:235 */      if (this.context == null)
/* 236:236 */        throw new IllegalStateException("Canvas not yet displayable");
/* 237:237 */      ContextGL.swapBuffers();
/* 238:    */    }
/* 239:    */  }
/* 240:    */  
/* 241:    */  public boolean isCurrent() throws LWJGLException {
/* 242:242 */    synchronized (this.SYNC_LOCK) {
/* 243:243 */      if (this.context == null) { throw new IllegalStateException("Canvas not yet displayable");
/* 244:    */      }
/* 245:245 */      return this.context.isCurrent();
/* 246:    */    }
/* 247:    */  }
/* 248:    */  
/* 251:    */  public void makeCurrent()
/* 252:    */    throws LWJGLException
/* 253:    */  {
/* 254:254 */    synchronized (this.SYNC_LOCK) {
/* 255:255 */      if (this.context == null)
/* 256:256 */        throw new IllegalStateException("Canvas not yet displayable");
/* 257:257 */      this.context.makeCurrent();
/* 258:    */    }
/* 259:    */  }
/* 260:    */  
/* 261:    */  public void releaseContext() throws LWJGLException {
/* 262:262 */    synchronized (this.SYNC_LOCK) {
/* 263:263 */      if (this.context == null)
/* 264:264 */        throw new IllegalStateException("Canvas not yet displayable");
/* 265:265 */      if (this.context.isCurrent()) {
/* 266:266 */        this.context.releaseCurrent();
/* 267:    */      }
/* 268:    */    }
/* 269:    */  }
/* 270:    */  
/* 271:    */  public final void destroy() {
/* 272:272 */    synchronized (this.SYNC_LOCK) {
/* 273:    */      try {
/* 274:274 */        if (this.context != null) {
/* 275:275 */          this.context.forceDestroy();
/* 276:276 */          this.context = null;
/* 277:277 */          this.reentry_count = 0;
/* 278:278 */          this.peer_info.destroy();
/* 279:279 */          this.peer_info = null;
/* 280:    */        }
/* 281:    */      } catch (LWJGLException e) {
/* 282:282 */        throw new RuntimeException(e);
/* 283:    */      }
/* 284:    */    }
/* 285:    */  }
/* 286:    */  
/* 287:    */  public final void setCLSharingProperties(PointerBuffer properties) throws LWJGLException {
/* 288:288 */    synchronized (this.SYNC_LOCK) {
/* 289:289 */      if (this.context == null)
/* 290:290 */        throw new IllegalStateException("Canvas not yet displayable");
/* 291:291 */      this.context.setCLSharingProperties(properties);
/* 292:    */    }
/* 293:    */  }
/* 294:    */  
/* 299:    */  protected void initGL() {}
/* 300:    */  
/* 305:    */  protected void paintGL() {}
/* 306:    */  
/* 310:    */  public final void paint(Graphics g)
/* 311:    */  {
/* 312:312 */    LWJGLException exception = null;
/* 313:313 */    synchronized (this.SYNC_LOCK) {
/* 314:314 */      if (!isDisplayable())
/* 315:315 */        return;
/* 316:    */      try {
/* 317:317 */        if (this.peer_info == null) {
/* 318:318 */          this.peer_info = implementation.createPeerInfo(this, this.pixel_format, this.attribs);
/* 319:    */        }
/* 320:320 */        this.peer_info.lockAndGetHandle();
/* 321:    */        try {
/* 322:322 */          if (this.context == null) {
/* 323:323 */            this.context = new ContextGL(this.peer_info, this.attribs, this.drawable != null ? (ContextGL)((DrawableLWJGL)this.drawable).getContext() : null);
/* 324:324 */            this.first_run = true;
/* 325:    */          }
/* 326:    */          
/* 327:327 */          if (this.reentry_count == 0)
/* 328:328 */            this.context.makeCurrent();
/* 329:329 */          this.reentry_count += 1;
/* 330:    */          try {
/* 331:331 */            if (this.update_context) {
/* 332:332 */              this.context.update();
/* 333:333 */              this.update_context = false;
/* 334:    */            }
/* 335:335 */            if (this.first_run) {
/* 336:336 */              this.first_run = false;
/* 337:337 */              initGL();
/* 338:    */            }
/* 339:339 */            paintGL();
/* 340:    */          } finally {
/* 341:341 */            this.reentry_count -= 1;
/* 342:342 */            if (this.reentry_count == 0)
/* 343:343 */              this.context.releaseCurrent();
/* 344:    */          }
/* 345:    */        } finally {
/* 346:346 */          this.peer_info.unlock();
/* 347:    */        }
/* 348:    */      } catch (LWJGLException e) {
/* 349:349 */        exception = e;
/* 350:    */      }
/* 351:    */    }
/* 352:352 */    if (exception != null) {
/* 353:353 */      exceptionOccurred(exception);
/* 354:    */    }
/* 355:    */  }
/* 356:    */  
/* 361:    */  protected void exceptionOccurred(LWJGLException exception)
/* 362:    */  {
/* 363:363 */    LWJGLUtil.log("Unhandled exception occurred, skipping paint(): " + exception);
/* 364:    */  }
/* 365:    */  
/* 366:    */  public void update(Graphics g)
/* 367:    */  {
/* 368:368 */    paint(g);
/* 369:    */  }
/* 370:    */  
/* 372:    */  public void componentShown(ComponentEvent e) {}
/* 373:    */  
/* 374:    */  public void componentHidden(ComponentEvent e) {}
/* 375:    */  
/* 376:    */  public void componentResized(ComponentEvent e)
/* 377:    */  {
/* 378:378 */    setUpdate();
/* 379:    */  }
/* 380:    */  
/* 381:    */  public void componentMoved(ComponentEvent e) {
/* 382:382 */    setUpdate();
/* 383:    */  }
/* 384:    */  
/* 385:    */  public void setLocation(int x, int y) {
/* 386:386 */    super.setLocation(x, y);
/* 387:387 */    setUpdate();
/* 388:    */  }
/* 389:    */  
/* 390:    */  public void setLocation(Point p) {
/* 391:391 */    super.setLocation(p);
/* 392:392 */    setUpdate();
/* 393:    */  }
/* 394:    */  
/* 395:    */  public void setSize(Dimension d) {
/* 396:396 */    super.setSize(d);
/* 397:397 */    setUpdate();
/* 398:    */  }
/* 399:    */  
/* 400:    */  public void setSize(int width, int height) {
/* 401:401 */    super.setSize(width, height);
/* 402:402 */    setUpdate();
/* 403:    */  }
/* 404:    */  
/* 405:    */  public void setBounds(int x, int y, int width, int height) {
/* 406:406 */    super.setBounds(x, y, width, height);
/* 407:407 */    setUpdate();
/* 408:    */  }
/* 409:    */  
/* 410:    */  public void hierarchyChanged(HierarchyEvent e) {
/* 411:411 */    setUpdate();
/* 412:    */  }
/* 413:    */  
/* 414:    */  static {}
/* 415:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AWTGLCanvas
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */