/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ final class CLChecks
/*     */ {
/*     */   static int calculateBufferRectSize(PointerBuffer offset, PointerBuffer region, long row_pitch, long slice_pitch)
/*     */   {
/*  64 */     if (!LWJGLUtil.CHECKS) {
/*  65 */       return 0;
/*     */     }
/*  67 */     long x = offset.get(0);
/*  68 */     long y = offset.get(1);
/*  69 */     long z = offset.get(2);
/*     */ 
/*  71 */     if ((LWJGLUtil.DEBUG) && ((x < 0L) || (y < 0L) || (z < 0L))) {
/*  72 */       throw new IllegalArgumentException("Invalid cl_mem host offset: " + x + ", " + y + ", " + z);
/*     */     }
/*  74 */     long w = region.get(0);
/*  75 */     long h = region.get(1);
/*  76 */     long d = region.get(2);
/*     */ 
/*  78 */     if ((LWJGLUtil.DEBUG) && ((w < 1L) || (h < 1L) || (d < 1L))) {
/*  79 */       throw new IllegalArgumentException("Invalid cl_mem rectangle region dimensions: " + w + " x " + h + " x " + d);
/*     */     }
/*  81 */     if (row_pitch == 0L)
/*  82 */       row_pitch = w;
/*  83 */     else if ((LWJGLUtil.DEBUG) && (row_pitch < w)) {
/*  84 */       throw new IllegalArgumentException("Invalid host row pitch specified: " + row_pitch);
/*     */     }
/*  86 */     if (slice_pitch == 0L)
/*  87 */       slice_pitch = row_pitch * h;
/*  88 */     else if ((LWJGLUtil.DEBUG) && (slice_pitch < row_pitch * h)) {
/*  89 */       throw new IllegalArgumentException("Invalid host slice pitch specified: " + slice_pitch);
/*     */     }
/*  91 */     return (int)(z * slice_pitch + y * row_pitch + x + w * h * d);
/*     */   }
/*     */ 
/*     */   static int calculateImageSize(PointerBuffer region, long row_pitch, long slice_pitch)
/*     */   {
/* 106 */     if (!LWJGLUtil.CHECKS) {
/* 107 */       return 0;
/*     */     }
/* 109 */     long w = region.get(0);
/* 110 */     long h = region.get(1);
/* 111 */     long d = region.get(2);
/*     */ 
/* 113 */     if ((LWJGLUtil.DEBUG) && ((w < 1L) || (h < 1L) || (d < 1L))) {
/* 114 */       throw new IllegalArgumentException("Invalid cl_mem image region dimensions: " + w + " x " + h + " x " + d);
/*     */     }
/* 116 */     if (row_pitch == 0L)
/* 117 */       row_pitch = w;
/* 118 */     else if ((LWJGLUtil.DEBUG) && (row_pitch < w)) {
/* 119 */       throw new IllegalArgumentException("Invalid row pitch specified: " + row_pitch);
/*     */     }
/* 121 */     if (slice_pitch == 0L)
/* 122 */       slice_pitch = row_pitch * h;
/* 123 */     else if ((LWJGLUtil.DEBUG) && (slice_pitch < row_pitch * h)) {
/* 124 */       throw new IllegalArgumentException("Invalid slice pitch specified: " + slice_pitch);
/*     */     }
/* 126 */     return (int)(slice_pitch * d);
/*     */   }
/*     */ 
/*     */   static int calculateImage2DSize(ByteBuffer format, long w, long h, long row_pitch)
/*     */   {
/* 141 */     if (!LWJGLUtil.CHECKS) {
/* 142 */       return 0;
/*     */     }
/* 144 */     if ((LWJGLUtil.DEBUG) && ((w < 1L) || (h < 1L))) {
/* 145 */       throw new IllegalArgumentException("Invalid 2D image dimensions: " + w + " x " + h);
/*     */     }
/* 147 */     int elementSize = getElementSize(format);
/*     */ 
/* 149 */     if (row_pitch == 0L)
/* 150 */       row_pitch = w * elementSize;
/* 151 */     else if ((LWJGLUtil.DEBUG) && ((row_pitch < w * elementSize) || (row_pitch % elementSize != 0L))) {
/* 152 */       throw new IllegalArgumentException("Invalid image_row_pitch specified: " + row_pitch);
/*     */     }
/* 154 */     return (int)(row_pitch * h);
/*     */   }
/*     */ 
/*     */   static int calculateImage3DSize(ByteBuffer format, long w, long h, long d, long row_pitch, long slice_pitch)
/*     */   {
/* 170 */     if (!LWJGLUtil.CHECKS) {
/* 171 */       return 0;
/*     */     }
/* 173 */     if ((LWJGLUtil.DEBUG) && ((w < 1L) || (h < 1L) || (d < 2L))) {
/* 174 */       throw new IllegalArgumentException("Invalid 3D image dimensions: " + w + " x " + h + " x " + d);
/*     */     }
/* 176 */     int elementSize = getElementSize(format);
/*     */ 
/* 178 */     if (row_pitch == 0L)
/* 179 */       row_pitch = w * elementSize;
/* 180 */     else if ((LWJGLUtil.DEBUG) && ((row_pitch < w * elementSize) || (row_pitch % elementSize != 0L))) {
/* 181 */       throw new IllegalArgumentException("Invalid image_row_pitch specified: " + row_pitch);
/*     */     }
/* 183 */     if (slice_pitch == 0L)
/* 184 */       slice_pitch = row_pitch * h;
/* 185 */     else if ((LWJGLUtil.DEBUG) && ((row_pitch < row_pitch * h) || (slice_pitch % row_pitch != 0L))) {
/* 186 */       throw new IllegalArgumentException("Invalid image_slice_pitch specified: " + row_pitch);
/*     */     }
/* 188 */     return (int)(slice_pitch * d);
/*     */   }
/*     */ 
/*     */   private static int getElementSize(ByteBuffer format)
/*     */   {
/* 199 */     int channelOrder = format.getInt(format.position() + 0);
/* 200 */     int channelType = format.getInt(format.position() + 4);
/*     */ 
/* 202 */     return getChannelCount(channelOrder) * getChannelSize(channelType);
/*     */   }
/*     */ 
/*     */   private static int getChannelCount(int channelOrder)
/*     */   {
/* 213 */     switch (channelOrder) {
/*     */     case 4272:
/*     */     case 4273:
/*     */     case 4280:
/*     */     case 4281:
/*     */     case 4282:
/* 219 */       return 1;
/*     */     case 4274:
/*     */     case 4275:
/*     */     case 4283:
/* 223 */       return 2;
/*     */     case 4276:
/*     */     case 4284:
/* 226 */       return 3;
/*     */     case 4277:
/*     */     case 4278:
/*     */     case 4279:
/* 230 */       return 4;
/*     */     }
/* 232 */     throw new IllegalArgumentException("Invalid cl_channel_order specified: " + LWJGLUtil.toHexString(channelOrder));
/*     */   }
/*     */ 
/*     */   private static int getChannelSize(int channelType)
/*     */   {
/* 244 */     switch (channelType) {
/*     */     case 4304:
/*     */     case 4306:
/*     */     case 4311:
/*     */     case 4314:
/* 249 */       return 1;
/*     */     case 4305:
/*     */     case 4307:
/*     */     case 4308:
/*     */     case 4309:
/*     */     case 4312:
/*     */     case 4315:
/*     */     case 4317:
/* 257 */       return 2;
/*     */     case 4310:
/*     */     case 4313:
/*     */     case 4316:
/*     */     case 4318:
/* 262 */       return 4;
/*     */     }
/* 264 */     throw new IllegalArgumentException("Invalid cl_channel_type specified: " + LWJGLUtil.toHexString(channelType));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLChecks
 * JD-Core Version:    0.6.2
 */