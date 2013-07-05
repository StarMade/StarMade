/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.List;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.opencl.api.CLImageFormat;
/*     */ import org.lwjgl.opencl.api.Filter;
/*     */ import org.lwjgl.opengl.Drawable;
/*     */ 
/*     */ public final class CLContext extends CLObjectChild<CLPlatform>
/*     */ {
/*  49 */   private static final CLContextUtil util = (CLContextUtil)CLPlatform.getInfoUtilInstance(CLContext.class, "CL_CONTEXT_UTIL");
/*     */   private final CLObjectRegistry<CLCommandQueue> clCommandQueues;
/*     */   private final CLObjectRegistry<CLMem> clMems;
/*     */   private final CLObjectRegistry<CLSampler> clSamplers;
/*     */   private final CLObjectRegistry<CLProgram> clPrograms;
/*     */   private final CLObjectRegistry<CLEvent> clEvents;
/*     */   private long contextCallback;
/*     */   private long printfCallback;
/*     */ 
/*     */   CLContext(long pointer, CLPlatform platform)
/*     */   {
/*  62 */     super(pointer, platform);
/*     */ 
/*  67 */     if (isValid()) {
/*  68 */       this.clCommandQueues = new CLObjectRegistry();
/*  69 */       this.clMems = new CLObjectRegistry();
/*  70 */       this.clSamplers = new CLObjectRegistry();
/*  71 */       this.clPrograms = new CLObjectRegistry();
/*  72 */       this.clEvents = new CLObjectRegistry();
/*     */     } else {
/*  74 */       this.clCommandQueues = null;
/*  75 */       this.clMems = null;
/*  76 */       this.clSamplers = null;
/*  77 */       this.clPrograms = null;
/*  78 */       this.clEvents = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public CLCommandQueue getCLCommandQueue(long id)
/*     */   {
/*  89 */     return (CLCommandQueue)this.clCommandQueues.getObject(id);
/*     */   }
/*     */ 
/*     */   public CLMem getCLMem(long id)
/*     */   {
/*  98 */     return (CLMem)this.clMems.getObject(id);
/*     */   }
/*     */ 
/*     */   public CLSampler getCLSampler(long id)
/*     */   {
/* 107 */     return (CLSampler)this.clSamplers.getObject(id);
/*     */   }
/*     */ 
/*     */   public CLProgram getCLProgram(long id)
/*     */   {
/* 116 */     return (CLProgram)this.clPrograms.getObject(id);
/*     */   }
/*     */ 
/*     */   public CLEvent getCLEvent(long id)
/*     */   {
/* 125 */     return (CLEvent)this.clEvents.getObject(id);
/*     */   }
/*     */ 
/*     */   public static CLContext create(CLPlatform platform, List<CLDevice> devices, IntBuffer errcode_ret)
/*     */     throws LWJGLException
/*     */   {
/* 141 */     return create(platform, devices, null, null, errcode_ret);
/*     */   }
/*     */ 
/*     */   public static CLContext create(CLPlatform platform, List<CLDevice> devices, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/*     */     throws LWJGLException
/*     */   {
/* 157 */     return create(platform, devices, pfn_notify, null, errcode_ret);
/*     */   }
/*     */ 
/*     */   public static CLContext create(CLPlatform platform, List<CLDevice> devices, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret)
/*     */     throws LWJGLException
/*     */   {
/* 173 */     return util.create(platform, devices, pfn_notify, share_drawable, errcode_ret);
/*     */   }
/*     */ 
/*     */   public static CLContext createFromType(CLPlatform platform, long device_type, IntBuffer errcode_ret)
/*     */     throws LWJGLException
/*     */   {
/* 188 */     return util.createFromType(platform, device_type, null, null, errcode_ret);
/*     */   }
/*     */ 
/*     */   public static CLContext createFromType(CLPlatform platform, long device_type, CLContextCallback pfn_notify, IntBuffer errcode_ret)
/*     */     throws LWJGLException
/*     */   {
/* 204 */     return util.createFromType(platform, device_type, pfn_notify, null, errcode_ret);
/*     */   }
/*     */ 
/*     */   public static CLContext createFromType(CLPlatform platform, long device_type, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret)
/*     */     throws LWJGLException
/*     */   {
/* 220 */     return util.createFromType(platform, device_type, pfn_notify, share_drawable, errcode_ret);
/*     */   }
/*     */ 
/*     */   public int getInfoInt(int param_name)
/*     */   {
/* 231 */     return util.getInfoInt(this, param_name);
/*     */   }
/*     */ 
/*     */   public List<CLDevice> getInfoDevices()
/*     */   {
/* 240 */     return util.getInfoDevices(this);
/*     */   }
/*     */ 
/*     */   public List<CLImageFormat> getSupportedImageFormats(long flags, int image_type) {
/* 244 */     return getSupportedImageFormats(flags, image_type, null);
/*     */   }
/*     */ 
/*     */   public List<CLImageFormat> getSupportedImageFormats(long flags, int image_type, Filter<CLImageFormat> filter) {
/* 248 */     return util.getSupportedImageFormats(this, flags, image_type, filter);
/*     */   }
/*     */ 
/*     */   CLObjectRegistry<CLCommandQueue> getCLCommandQueueRegistry()
/*     */   {
/* 266 */     return this.clCommandQueues;
/*     */   }
/* 268 */   CLObjectRegistry<CLMem> getCLMemRegistry() { return this.clMems; } 
/*     */   CLObjectRegistry<CLSampler> getCLSamplerRegistry() {
/* 270 */     return this.clSamplers;
/*     */   }
/* 272 */   CLObjectRegistry<CLProgram> getCLProgramRegistry() { return this.clPrograms; } 
/*     */   CLObjectRegistry<CLEvent> getCLEventRegistry() {
/* 274 */     return this.clEvents;
/*     */   }
/*     */   private boolean checkCallback(long callback, int result) {
/* 277 */     if ((result == 0) && ((callback == 0L) || (isValid()))) {
/* 278 */       return true;
/*     */     }
/* 280 */     if (callback != 0L)
/* 281 */       CallbackUtil.deleteGlobalRef(callback);
/* 282 */     return false;
/*     */   }
/*     */ 
/*     */   void setContextCallback(long callback)
/*     */   {
/* 292 */     if (checkCallback(callback, 0))
/* 293 */       this.contextCallback = callback;
/*     */   }
/*     */ 
/*     */   void setPrintfCallback(long callback, int result)
/*     */   {
/* 303 */     if (checkCallback(callback, result))
/* 304 */       this.printfCallback = callback;
/*     */   }
/*     */ 
/*     */   void releaseImpl()
/*     */   {
/* 313 */     if (release() > 0) {
/* 314 */       return;
/*     */     }
/* 316 */     if (this.contextCallback != 0L)
/* 317 */       CallbackUtil.deleteGlobalRef(this.contextCallback);
/* 318 */     if (this.printfCallback != 0L)
/* 319 */       CallbackUtil.deleteGlobalRef(this.printfCallback);
/*     */   }
/*     */ 
/*     */   static abstract interface CLContextUtil extends InfoUtil<CLContext>
/*     */   {
/*     */     public abstract List<CLDevice> getInfoDevices(CLContext paramCLContext);
/*     */ 
/*     */     public abstract CLContext create(CLPlatform paramCLPlatform, List<CLDevice> paramList, CLContextCallback paramCLContextCallback, Drawable paramDrawable, IntBuffer paramIntBuffer)
/*     */       throws LWJGLException;
/*     */ 
/*     */     public abstract CLContext createFromType(CLPlatform paramCLPlatform, long paramLong, CLContextCallback paramCLContextCallback, Drawable paramDrawable, IntBuffer paramIntBuffer)
/*     */       throws LWJGLException;
/*     */ 
/*     */     public abstract List<CLImageFormat> getSupportedImageFormats(CLContext paramCLContext, long paramLong, int paramInt, Filter<CLImageFormat> paramFilter);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLContext
 * JD-Core Version:    0.6.2
 */