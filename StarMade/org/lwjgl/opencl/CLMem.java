/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.opencl.api.CLBufferRegion;
/*     */ import org.lwjgl.opencl.api.CLImageFormat;
/*     */ 
/*     */ public final class CLMem extends CLObjectChild<CLContext>
/*     */ {
/*  48 */   private static final CLMemUtil util = (CLMemUtil)CLPlatform.getInfoUtilInstance(CLMem.class, "CL_MEM_UTIL");
/*     */ 
/*     */   CLMem(long pointer, CLContext context) {
/*  51 */     super(pointer, context);
/*  52 */     if (isValid())
/*  53 */       context.getCLMemRegistry().registerObject(this);
/*     */   }
/*     */ 
/*     */   public static CLMem createImage2D(CLContext context, long flags, CLImageFormat image_format, long image_width, long image_height, long image_row_pitch, Buffer host_ptr, IntBuffer errcode_ret)
/*     */   {
/*  75 */     return util.createImage2D(context, flags, image_format, image_width, image_height, image_row_pitch, host_ptr, errcode_ret);
/*     */   }
/*     */ 
/*     */   public static CLMem createImage3D(CLContext context, long flags, CLImageFormat image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, Buffer host_ptr, IntBuffer errcode_ret)
/*     */   {
/*  97 */     return util.createImage3D(context, flags, image_format, image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, host_ptr, errcode_ret);
/*     */   }
/*     */ 
/*     */   public CLMem createSubBuffer(long flags, int buffer_create_type, CLBufferRegion buffer_create_info, IntBuffer errcode_ret) {
/* 101 */     return util.createSubBuffer(this, flags, buffer_create_type, buffer_create_info, errcode_ret);
/*     */   }
/*     */ 
/*     */   public int getInfoInt(int param_name)
/*     */   {
/* 112 */     return util.getInfoInt(this, param_name);
/*     */   }
/*     */ 
/*     */   public long getInfoSize(int param_name)
/*     */   {
/* 123 */     return util.getInfoSize(this, param_name);
/*     */   }
/*     */ 
/*     */   public long getInfoLong(int param_name)
/*     */   {
/* 135 */     return util.getInfoLong(this, param_name);
/*     */   }
/*     */ 
/*     */   public ByteBuffer getInfoHostBuffer()
/*     */   {
/* 146 */     return util.getInfoHostBuffer(this);
/*     */   }
/*     */ 
/*     */   public long getImageInfoSize(int param_name)
/*     */   {
/* 159 */     return util.getImageInfoSize(this, param_name);
/*     */   }
/*     */ 
/*     */   public CLImageFormat getImageFormat()
/*     */   {
/* 168 */     return util.getImageInfoFormat(this);
/*     */   }
/*     */ 
/*     */   public int getImageChannelOrder()
/*     */   {
/* 177 */     return util.getImageInfoFormat(this, 0);
/*     */   }
/*     */ 
/*     */   public int getImageChannelType()
/*     */   {
/* 186 */     return util.getImageInfoFormat(this, 1);
/*     */   }
/*     */ 
/*     */   public int getGLObjectType()
/*     */   {
/* 198 */     return util.getGLObjectType(this);
/*     */   }
/*     */ 
/*     */   public int getGLObjectName()
/*     */   {
/* 208 */     return util.getGLObjectName(this);
/*     */   }
/*     */ 
/*     */   public int getGLTextureInfoInt(int param_name)
/*     */   {
/* 222 */     return util.getGLTextureInfoInt(this, param_name);
/*     */   }
/*     */ 
/*     */   static CLMem create(long pointer, CLContext context)
/*     */   {
/* 261 */     CLMem clMem = (CLMem)context.getCLMemRegistry().getObject(pointer);
/* 262 */     if (clMem == null)
/* 263 */       clMem = new CLMem(pointer, context);
/*     */     else {
/* 265 */       clMem.retain();
/*     */     }
/* 267 */     return clMem;
/*     */   }
/*     */ 
/*     */   int release() {
/*     */     try {
/* 272 */       return super.release();
/*     */     } finally {
/* 274 */       if (!isValid())
/* 275 */         ((CLContext)getParent()).getCLMemRegistry().unregisterObject(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface CLMemUtil extends InfoUtil<CLMem>
/*     */   {
/*     */     public abstract CLMem createImage2D(CLContext paramCLContext, long paramLong1, CLImageFormat paramCLImageFormat, long paramLong2, long paramLong3, long paramLong4, Buffer paramBuffer, IntBuffer paramIntBuffer);
/*     */ 
/*     */     public abstract CLMem createImage3D(CLContext paramCLContext, long paramLong1, CLImageFormat paramCLImageFormat, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, Buffer paramBuffer, IntBuffer paramIntBuffer);
/*     */ 
/*     */     public abstract CLMem createSubBuffer(CLMem paramCLMem, long paramLong, int paramInt, CLBufferRegion paramCLBufferRegion, IntBuffer paramIntBuffer);
/*     */ 
/*     */     public abstract ByteBuffer getInfoHostBuffer(CLMem paramCLMem);
/*     */ 
/*     */     public abstract long getImageInfoSize(CLMem paramCLMem, int paramInt);
/*     */ 
/*     */     public abstract CLImageFormat getImageInfoFormat(CLMem paramCLMem);
/*     */ 
/*     */     public abstract int getImageInfoFormat(CLMem paramCLMem, int paramInt);
/*     */ 
/*     */     public abstract int getGLObjectType(CLMem paramCLMem);
/*     */ 
/*     */     public abstract int getGLObjectName(CLMem paramCLMem);
/*     */ 
/*     */     public abstract int getGLTextureInfoInt(CLMem paramCLMem, int paramInt);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLMem
 * JD-Core Version:    0.6.2
 */