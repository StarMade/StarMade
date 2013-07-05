/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ public final class CLSampler extends CLObjectChild<CLContext>
/*    */ {
/* 41 */   private static final InfoUtil<CLSampler> util = CLPlatform.getInfoUtilInstance(CLSampler.class, "CL_SAMPLER_UTIL");
/*    */ 
/*    */   CLSampler(long pointer, CLContext context) {
/* 44 */     super(pointer, context);
/* 45 */     if (isValid())
/* 46 */       context.getCLSamplerRegistry().registerObject(this);
/*    */   }
/*    */ 
/*    */   public int getInfoInt(int param_name)
/*    */   {
/* 59 */     return util.getInfoInt(this, param_name);
/*    */   }
/*    */ 
/*    */   public long getInfoLong(int param_name)
/*    */   {
/* 71 */     return util.getInfoLong(this, param_name);
/*    */   }
/*    */ 
/*    */   int release()
/*    */   {
/*    */     try
/*    */     {
/* 78 */       return super.release();
/*    */     } finally {
/* 80 */       if (!isValid())
/* 81 */         ((CLContext)getParent()).getCLSamplerRegistry().unregisterObject(this);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLSampler
 * JD-Core Version:    0.6.2
 */