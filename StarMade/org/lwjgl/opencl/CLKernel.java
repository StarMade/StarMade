/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ public final class CLKernel extends CLObjectChild<CLProgram>
/*     */ {
/*  41 */   private static final CLKernelUtil util = (CLKernelUtil)CLPlatform.getInfoUtilInstance(CLKernel.class, "CL_KERNEL_UTIL");
/*     */ 
/*     */   CLKernel(long pointer, CLProgram program) {
/*  44 */     super(pointer, program);
/*  45 */     if (isValid())
/*  46 */       program.getCLKernelRegistry().registerObject(this);
/*     */   }
/*     */ 
/*     */   public CLKernel setArg(int index, byte value)
/*     */   {
/*  63 */     util.setArg(this, index, value);
/*  64 */     return this;
/*     */   }
/*     */ 
/*     */   public CLKernel setArg(int index, short value)
/*     */   {
/*  77 */     util.setArg(this, index, value);
/*  78 */     return this;
/*     */   }
/*     */ 
/*     */   public CLKernel setArg(int index, int value)
/*     */   {
/*  91 */     util.setArg(this, index, value);
/*  92 */     return this;
/*     */   }
/*     */ 
/*     */   public CLKernel setArg(int index, long value)
/*     */   {
/* 105 */     util.setArg(this, index, value);
/* 106 */     return this;
/*     */   }
/*     */ 
/*     */   public CLKernel setArg(int index, float value)
/*     */   {
/* 119 */     util.setArg(this, index, value);
/* 120 */     return this;
/*     */   }
/*     */ 
/*     */   public CLKernel setArg(int index, double value)
/*     */   {
/* 133 */     util.setArg(this, index, value);
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */   public CLKernel setArg(int index, CLObject value)
/*     */   {
/* 147 */     util.setArg(this, index, value);
/* 148 */     return this;
/*     */   }
/*     */ 
/*     */   public CLKernel setArgSize(int index, long size)
/*     */   {
/* 160 */     util.setArgSize(this, index, size);
/* 161 */     return this;
/*     */   }
/*     */ 
/*     */   public String getInfoString(int param_name)
/*     */   {
/* 174 */     return util.getInfoString(this, param_name);
/*     */   }
/*     */ 
/*     */   public int getInfoInt(int param_name)
/*     */   {
/* 185 */     return util.getInfoInt(this, param_name);
/*     */   }
/*     */ 
/*     */   public long getWorkGroupInfoSize(CLDevice device, int param_name)
/*     */   {
/* 198 */     return util.getWorkGroupInfoSize(this, device, param_name);
/*     */   }
/*     */ 
/*     */   public long[] getWorkGroupInfoSizeArray(CLDevice device, int param_name)
/*     */   {
/* 209 */     return util.getWorkGroupInfoSizeArray(this, device, param_name);
/*     */   }
/*     */ 
/*     */   public long getWorkGroupInfoLong(CLDevice device, int param_name)
/*     */   {
/* 221 */     return util.getWorkGroupInfoLong(this, device, param_name);
/*     */   }
/*     */ 
/*     */   int release()
/*     */   {
/*     */     try
/*     */     {
/* 255 */       return super.release();
/*     */     } finally {
/* 257 */       if (!isValid())
/* 258 */         ((CLProgram)getParent()).getCLKernelRegistry().unregisterObject(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface CLKernelUtil extends InfoUtil<CLKernel>
/*     */   {
/*     */     public abstract void setArg(CLKernel paramCLKernel, int paramInt, byte paramByte);
/*     */ 
/*     */     public abstract void setArg(CLKernel paramCLKernel, int paramInt, short paramShort);
/*     */ 
/*     */     public abstract void setArg(CLKernel paramCLKernel, int paramInt1, int paramInt2);
/*     */ 
/*     */     public abstract void setArg(CLKernel paramCLKernel, int paramInt, long paramLong);
/*     */ 
/*     */     public abstract void setArg(CLKernel paramCLKernel, int paramInt, float paramFloat);
/*     */ 
/*     */     public abstract void setArg(CLKernel paramCLKernel, int paramInt, double paramDouble);
/*     */ 
/*     */     public abstract void setArg(CLKernel paramCLKernel, int paramInt, CLObject paramCLObject);
/*     */ 
/*     */     public abstract void setArgSize(CLKernel paramCLKernel, int paramInt, long paramLong);
/*     */ 
/*     */     public abstract long getWorkGroupInfoSize(CLKernel paramCLKernel, CLDevice paramCLDevice, int paramInt);
/*     */ 
/*     */     public abstract long[] getWorkGroupInfoSizeArray(CLKernel paramCLKernel, CLDevice paramCLDevice, int paramInt);
/*     */ 
/*     */     public abstract long getWorkGroupInfoLong(CLKernel paramCLKernel, CLDevice paramCLDevice, int paramInt);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLKernel
 * JD-Core Version:    0.6.2
 */