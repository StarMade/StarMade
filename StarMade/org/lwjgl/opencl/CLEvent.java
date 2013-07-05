/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ public final class CLEvent extends CLObjectChild<CLContext>
/*     */ {
/*  41 */   private static final CLEventUtil util = (CLEventUtil)CLPlatform.getInfoUtilInstance(CLEvent.class, "CL_EVENT_UTIL");
/*     */   private final CLCommandQueue queue;
/*     */ 
/*     */   CLEvent(long pointer, CLContext context)
/*     */   {
/*  46 */     this(pointer, context, null);
/*     */   }
/*     */ 
/*     */   CLEvent(long pointer, CLCommandQueue queue) {
/*  50 */     this(pointer, (CLContext)queue.getParent(), queue);
/*     */   }
/*     */ 
/*     */   CLEvent(long pointer, CLContext context, CLCommandQueue queue) {
/*  54 */     super(pointer, context);
/*  55 */     if (isValid()) {
/*  56 */       this.queue = queue;
/*  57 */       if (queue == null)
/*  58 */         context.getCLEventRegistry().registerObject(this);
/*     */       else
/*  60 */         queue.getCLEventRegistry().registerObject(this);
/*     */     } else {
/*  62 */       this.queue = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public CLCommandQueue getCLCommandQueue()
/*     */   {
/*  72 */     return this.queue;
/*     */   }
/*     */ 
/*     */   public int getInfoInt(int param_name)
/*     */   {
/*  85 */     return util.getInfoInt(this, param_name);
/*     */   }
/*     */ 
/*     */   public long getProfilingInfoLong(int param_name)
/*     */   {
/*  99 */     return util.getProfilingInfoLong(this, param_name);
/*     */   }
/*     */ 
/*     */   CLObjectRegistry<CLEvent> getParentRegistry()
/*     */   {
/* 112 */     if (this.queue == null) {
/* 113 */       return ((CLContext)getParent()).getCLEventRegistry();
/*     */     }
/* 115 */     return this.queue.getCLEventRegistry();
/*     */   }
/*     */ 
/*     */   int release() {
/*     */     try {
/* 120 */       return super.release();
/*     */     } finally {
/* 122 */       if (!isValid())
/* 123 */         if (this.queue == null)
/* 124 */           ((CLContext)getParent()).getCLEventRegistry().unregisterObject(this);
/*     */         else
/* 126 */           this.queue.getCLEventRegistry().unregisterObject(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface CLEventUtil extends InfoUtil<CLEvent>
/*     */   {
/*     */     public abstract long getProfilingInfoLong(CLEvent paramCLEvent, int paramInt);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLEvent
 * JD-Core Version:    0.6.2
 */