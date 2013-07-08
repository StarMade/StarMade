/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import java.util.List;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */import org.lwjgl.opencl.api.CLImageFormat;
/*   7:    */import org.lwjgl.opencl.api.Filter;
/*   8:    */import org.lwjgl.opengl.Drawable;
/*   9:    */
/*  46:    */public final class CLContext
/*  47:    */  extends CLObjectChild<CLPlatform>
/*  48:    */{
/*  49: 49 */  private static final CLContextUtil util = (CLContextUtil)CLPlatform.getInfoUtilInstance(CLContext.class, "CL_CONTEXT_UTIL");
/*  50:    */  
/*  51:    */  private final CLObjectRegistry<CLCommandQueue> clCommandQueues;
/*  52:    */  
/*  53:    */  private final CLObjectRegistry<CLMem> clMems;
/*  54:    */  private final CLObjectRegistry<CLSampler> clSamplers;
/*  55:    */  private final CLObjectRegistry<CLProgram> clPrograms;
/*  56:    */  private final CLObjectRegistry<CLEvent> clEvents;
/*  57:    */  private long contextCallback;
/*  58:    */  private long printfCallback;
/*  59:    */  
/*  60:    */  CLContext(long pointer, CLPlatform platform)
/*  61:    */  {
/*  62: 62 */    super(pointer, platform);
/*  63:    */    
/*  67: 67 */    if (isValid()) {
/*  68: 68 */      this.clCommandQueues = new CLObjectRegistry();
/*  69: 69 */      this.clMems = new CLObjectRegistry();
/*  70: 70 */      this.clSamplers = new CLObjectRegistry();
/*  71: 71 */      this.clPrograms = new CLObjectRegistry();
/*  72: 72 */      this.clEvents = new CLObjectRegistry();
/*  73:    */    } else {
/*  74: 74 */      this.clCommandQueues = null;
/*  75: 75 */      this.clMems = null;
/*  76: 76 */      this.clSamplers = null;
/*  77: 77 */      this.clPrograms = null;
/*  78: 78 */      this.clEvents = null;
/*  79:    */    }
/*  80:    */  }
/*  81:    */  
/*  87:    */  public CLCommandQueue getCLCommandQueue(long id)
/*  88:    */  {
/*  89: 89 */    return (CLCommandQueue)this.clCommandQueues.getObject(id);
/*  90:    */  }
/*  91:    */  
/*  96:    */  public CLMem getCLMem(long id)
/*  97:    */  {
/*  98: 98 */    return (CLMem)this.clMems.getObject(id);
/*  99:    */  }
/* 100:    */  
/* 105:    */  public CLSampler getCLSampler(long id)
/* 106:    */  {
/* 107:107 */    return (CLSampler)this.clSamplers.getObject(id);
/* 108:    */  }
/* 109:    */  
/* 114:    */  public CLProgram getCLProgram(long id)
/* 115:    */  {
/* 116:116 */    return (CLProgram)this.clPrograms.getObject(id);
/* 117:    */  }
/* 118:    */  
/* 123:    */  public CLEvent getCLEvent(long id)
/* 124:    */  {
/* 125:125 */    return (CLEvent)this.clEvents.getObject(id);
/* 126:    */  }
/* 127:    */  
/* 138:    */  public static CLContext create(CLPlatform platform, List<CLDevice> devices, IntBuffer errcode_ret)
/* 139:    */    throws LWJGLException
/* 140:    */  {
/* 141:141 */    return create(platform, devices, null, null, errcode_ret);
/* 142:    */  }
/* 143:    */  
/* 154:    */  public static CLContext create(CLPlatform platform, List<CLDevice> devices, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/* 155:    */    throws LWJGLException
/* 156:    */  {
/* 157:157 */    return create(platform, devices, pfn_notify, null, errcode_ret);
/* 158:    */  }
/* 159:    */  
/* 170:    */  public static CLContext create(CLPlatform platform, List<CLDevice> devices, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret)
/* 171:    */    throws LWJGLException
/* 172:    */  {
/* 173:173 */    return util.create(platform, devices, pfn_notify, share_drawable, errcode_ret);
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static CLContext createFromType(CLPlatform platform, long device_type, IntBuffer errcode_ret)
/* 186:    */    throws LWJGLException
/* 187:    */  {
/* 188:188 */    return util.createFromType(platform, device_type, null, null, errcode_ret);
/* 189:    */  }
/* 190:    */  
/* 201:    */  public static CLContext createFromType(CLPlatform platform, long device_type, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/* 202:    */    throws LWJGLException
/* 203:    */  {
/* 204:204 */    return util.createFromType(platform, device_type, pfn_notify, null, errcode_ret);
/* 205:    */  }
/* 206:    */  
/* 217:    */  public static CLContext createFromType(CLPlatform platform, long device_type, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret)
/* 218:    */    throws LWJGLException
/* 219:    */  {
/* 220:220 */    return util.createFromType(platform, device_type, pfn_notify, share_drawable, errcode_ret);
/* 221:    */  }
/* 222:    */  
/* 229:    */  public int getInfoInt(int param_name)
/* 230:    */  {
/* 231:231 */    return util.getInfoInt(this, param_name);
/* 232:    */  }
/* 233:    */  
/* 238:    */  public List<CLDevice> getInfoDevices()
/* 239:    */  {
/* 240:240 */    return util.getInfoDevices(this);
/* 241:    */  }
/* 242:    */  
/* 243:    */  public List<CLImageFormat> getSupportedImageFormats(long flags, int image_type) {
/* 244:244 */    return getSupportedImageFormats(flags, image_type, null);
/* 245:    */  }
/* 246:    */  
/* 247:    */  public List<CLImageFormat> getSupportedImageFormats(long flags, int image_type, Filter<CLImageFormat> filter) {
/* 248:248 */    return util.getSupportedImageFormats(this, flags, image_type, filter);
/* 249:    */  }
/* 250:    */  
/* 266:266 */  CLObjectRegistry<CLCommandQueue> getCLCommandQueueRegistry() { return this.clCommandQueues; }
/* 267:    */  
/* 268:268 */  CLObjectRegistry<CLMem> getCLMemRegistry() { return this.clMems; }
/* 269:    */  
/* 270:270 */  CLObjectRegistry<CLSampler> getCLSamplerRegistry() { return this.clSamplers; }
/* 271:    */  
/* 272:272 */  CLObjectRegistry<CLProgram> getCLProgramRegistry() { return this.clPrograms; }
/* 273:    */  
/* 274:274 */  CLObjectRegistry<CLEvent> getCLEventRegistry() { return this.clEvents; }
/* 275:    */  
/* 276:    */  private boolean checkCallback(long callback, int result) {
/* 277:277 */    if ((result == 0) && ((callback == 0L) || (isValid()))) {
/* 278:278 */      return true;
/* 279:    */    }
/* 280:280 */    if (callback != 0L)
/* 281:281 */      CallbackUtil.deleteGlobalRef(callback);
/* 282:282 */    return false;
/* 283:    */  }
/* 284:    */  
/* 290:    */  void setContextCallback(long callback)
/* 291:    */  {
/* 292:292 */    if (checkCallback(callback, 0)) {
/* 293:293 */      this.contextCallback = callback;
/* 294:    */    }
/* 295:    */  }
/* 296:    */  
/* 301:    */  void setPrintfCallback(long callback, int result)
/* 302:    */  {
/* 303:303 */    if (checkCallback(callback, result)) {
/* 304:304 */      this.printfCallback = callback;
/* 305:    */    }
/* 306:    */  }
/* 307:    */  
/* 311:    */  void releaseImpl()
/* 312:    */  {
/* 313:313 */    if (release() > 0) {
/* 314:314 */      return;
/* 315:    */    }
/* 316:316 */    if (this.contextCallback != 0L)
/* 317:317 */      CallbackUtil.deleteGlobalRef(this.contextCallback);
/* 318:318 */    if (this.printfCallback != 0L) {
/* 319:319 */      CallbackUtil.deleteGlobalRef(this.printfCallback);
/* 320:    */    }
/* 321:    */  }
/* 322:    */  
/* 323:    */  static abstract interface CLContextUtil
/* 324:    */    extends InfoUtil<CLContext>
/* 325:    */  {
/* 326:    */    public abstract List<CLDevice> getInfoDevices(CLContext paramCLContext);
/* 327:    */    
/* 328:    */    public abstract CLContext create(CLPlatform paramCLPlatform, List<CLDevice> paramList, CLContextCallback paramCLContextCallback, Drawable paramDrawable, IntBuffer paramIntBuffer)
/* 329:    */      throws LWJGLException;
/* 330:    */    
/* 331:    */    public abstract CLContext createFromType(CLPlatform paramCLPlatform, long paramLong, CLContextCallback paramCLContextCallback, Drawable paramDrawable, IntBuffer paramIntBuffer)
/* 332:    */      throws LWJGLException;
/* 333:    */    
/* 334:    */    public abstract List<CLImageFormat> getSupportedImageFormats(CLContext paramCLContext, long paramLong, int paramInt, Filter<CLImageFormat> paramFilter);
/* 335:    */  }
/* 336:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */