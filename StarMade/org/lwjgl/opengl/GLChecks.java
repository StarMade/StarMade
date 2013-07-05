/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ class GLChecks
/*     */ {
/*     */   static int getBufferObjectSize(ContextCapabilities caps, int buffer_enum)
/*     */   {
/*  69 */     return GL15.glGetBufferParameteri(buffer_enum, 34660);
/*     */   }
/*     */ 
/*     */   static int getBufferObjectSizeARB(ContextCapabilities caps, int buffer_enum) {
/*  73 */     return ARBBufferObject.glGetBufferParameteriARB(buffer_enum, 34660);
/*     */   }
/*     */ 
/*     */   static int getBufferObjectSizeATI(ContextCapabilities caps, int buffer) {
/*  77 */     return ATIVertexArrayObject.glGetObjectBufferiATI(buffer, 34660);
/*     */   }
/*     */ 
/*     */   static int getNamedBufferObjectSize(ContextCapabilities caps, int buffer) {
/*  81 */     return EXTDirectStateAccess.glGetNamedBufferParameterEXT(buffer, 34660);
/*     */   }
/*     */ 
/*     */   static void ensureArrayVBOdisabled(ContextCapabilities caps)
/*     */   {
/*  86 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).arrayBuffer != 0))
/*  87 */       throw new OpenGLException("Cannot use Buffers when Array Buffer Object is enabled");
/*     */   }
/*     */ 
/*     */   static void ensureArrayVBOenabled(ContextCapabilities caps)
/*     */   {
/*  92 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).arrayBuffer == 0))
/*  93 */       throw new OpenGLException("Cannot use offsets when Array Buffer Object is disabled");
/*     */   }
/*     */ 
/*     */   static void ensureElementVBOdisabled(ContextCapabilities caps)
/*     */   {
/*  98 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getElementArrayBufferBound(caps) != 0))
/*  99 */       throw new OpenGLException("Cannot use Buffers when Element Array Buffer Object is enabled");
/*     */   }
/*     */ 
/*     */   static void ensureElementVBOenabled(ContextCapabilities caps)
/*     */   {
/* 104 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getElementArrayBufferBound(caps) == 0))
/* 105 */       throw new OpenGLException("Cannot use offsets when Element Array Buffer Object is disabled");
/*     */   }
/*     */ 
/*     */   static void ensureIndirectBOdisabled(ContextCapabilities caps)
/*     */   {
/* 110 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).indirectBuffer != 0))
/* 111 */       throw new OpenGLException("Cannot use Buffers when Draw Indirect Object is enabled");
/*     */   }
/*     */ 
/*     */   static void ensureIndirectBOenabled(ContextCapabilities caps)
/*     */   {
/* 116 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).indirectBuffer == 0))
/* 117 */       throw new OpenGLException("Cannot use offsets when Draw Indirect Object is disabled");
/*     */   }
/*     */ 
/*     */   static void ensurePackPBOdisabled(ContextCapabilities caps)
/*     */   {
/* 122 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).pixelPackBuffer != 0))
/* 123 */       throw new OpenGLException("Cannot use Buffers when Pixel Pack Buffer Object is enabled");
/*     */   }
/*     */ 
/*     */   static void ensurePackPBOenabled(ContextCapabilities caps)
/*     */   {
/* 128 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).pixelPackBuffer == 0))
/* 129 */       throw new OpenGLException("Cannot use offsets when Pixel Pack Buffer Object is disabled");
/*     */   }
/*     */ 
/*     */   static void ensureUnpackPBOdisabled(ContextCapabilities caps)
/*     */   {
/* 134 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).pixelUnpackBuffer != 0))
/* 135 */       throw new OpenGLException("Cannot use Buffers when Pixel Unpack Buffer Object is enabled");
/*     */   }
/*     */ 
/*     */   static void ensureUnpackPBOenabled(ContextCapabilities caps)
/*     */   {
/* 140 */     if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).pixelUnpackBuffer == 0))
/* 141 */       throw new OpenGLException("Cannot use offsets when Pixel Unpack Buffer Object is disabled");
/*     */   }
/*     */ 
/*     */   static int calculateImageStorage(Buffer buffer, int format, int type, int width, int height, int depth)
/*     */   {
/* 156 */     return LWJGLUtil.CHECKS ? calculateImageStorage(format, type, width, height, depth) >> BufferUtils.getElementSizeExponent(buffer) : 0;
/*     */   }
/*     */ 
/*     */   static int calculateTexImage1DStorage(Buffer buffer, int format, int type, int width) {
/* 160 */     return LWJGLUtil.CHECKS ? calculateTexImage1DStorage(format, type, width) >> BufferUtils.getElementSizeExponent(buffer) : 0;
/*     */   }
/*     */ 
/*     */   static int calculateTexImage2DStorage(Buffer buffer, int format, int type, int width, int height) {
/* 164 */     return LWJGLUtil.CHECKS ? calculateTexImage2DStorage(format, type, width, height) >> BufferUtils.getElementSizeExponent(buffer) : 0;
/*     */   }
/*     */ 
/*     */   static int calculateTexImage3DStorage(Buffer buffer, int format, int type, int width, int height, int depth) {
/* 168 */     return LWJGLUtil.CHECKS ? calculateTexImage3DStorage(format, type, width, height, depth) >> BufferUtils.getElementSizeExponent(buffer) : 0;
/*     */   }
/*     */ 
/*     */   private static int calculateImageStorage(int format, int type, int width, int height, int depth)
/*     */   {
/* 183 */     return calculateBytesPerPixel(format, type) * width * height * depth;
/*     */   }
/*     */ 
/*     */   private static int calculateTexImage1DStorage(int format, int type, int width) {
/* 187 */     return calculateBytesPerPixel(format, type) * width;
/*     */   }
/*     */ 
/*     */   private static int calculateTexImage2DStorage(int format, int type, int width, int height) {
/* 191 */     return calculateTexImage1DStorage(format, type, width) * height;
/*     */   }
/*     */ 
/*     */   private static int calculateTexImage3DStorage(int format, int type, int width, int height, int depth) {
/* 195 */     return calculateTexImage2DStorage(format, type, width, height) * depth;
/*     */   }
/*     */ 
/*     */   private static int calculateBytesPerPixel(int format, int type)
/*     */   {
/*     */     int bpe;
/* 200 */     switch (type) {
/*     */     case 5120:
/*     */     case 5121:
/* 203 */       bpe = 1;
/* 204 */       break;
/*     */     case 5122:
/*     */     case 5123:
/* 207 */       bpe = 2;
/* 208 */       break;
/*     */     case 5124:
/*     */     case 5125:
/*     */     case 5126:
/* 212 */       bpe = 4;
/* 213 */       break;
/*     */     default:
/* 216 */       return 0;
/*     */     }
/*     */     int epp;
/* 220 */     switch (format) {
/*     */     case 6406:
/*     */     case 6409:
/* 223 */       epp = 1;
/* 224 */       break;
/*     */     case 6410:
/* 227 */       epp = 2;
/* 228 */       break;
/*     */     case 6407:
/*     */     case 32992:
/* 231 */       epp = 3;
/* 232 */       break;
/*     */     case 6408:
/*     */     case 32768:
/*     */     case 32993:
/* 236 */       epp = 4;
/* 237 */       break;
/*     */     default:
/* 240 */       return 0;
/*     */     }
/*     */ 
/* 245 */     return bpe * epp;
/*     */   }
/*     */ 
/*     */   static int calculateBytesPerCharCode(int type)
/*     */   {
/* 251 */     switch (type) {
/*     */     case 5121:
/*     */     case 37018:
/* 254 */       return 1;
/*     */     case 5123:
/*     */     case 5127:
/*     */     case 37019:
/* 258 */       return 2;
/*     */     case 5128:
/* 260 */       return 3;
/*     */     case 5129:
/* 262 */       return 4;
/*     */     }
/* 264 */     throw new IllegalArgumentException("Unsupported charcode type: " + type);
/*     */   }
/*     */ 
/*     */   static int calculateBytesPerPathName(int pathNameType)
/*     */   {
/* 269 */     switch (pathNameType) {
/*     */     case 5120:
/*     */     case 5121:
/*     */     case 37018:
/* 273 */       return 1;
/*     */     case 5122:
/*     */     case 5123:
/*     */     case 5127:
/*     */     case 37019:
/* 278 */       return 2;
/*     */     case 5128:
/* 280 */       return 3;
/*     */     case 5124:
/*     */     case 5125:
/*     */     case 5126:
/*     */     case 5129:
/* 285 */       return 4;
/*     */     }
/* 287 */     throw new IllegalArgumentException("Unsupported path name type: " + pathNameType);
/*     */   }
/*     */ 
/*     */   static int calculateTransformPathValues(int transformType)
/*     */   {
/* 292 */     switch (transformType) {
/*     */     case 0:
/* 294 */       return 0;
/*     */     case 37006:
/*     */     case 37007:
/* 297 */       return 1;
/*     */     case 37008:
/* 299 */       return 2;
/*     */     case 37009:
/* 301 */       return 3;
/*     */     case 37010:
/*     */     case 37014:
/* 304 */       return 6;
/*     */     case 37012:
/*     */     case 37016:
/* 307 */       return 12;
/*     */     }
/* 309 */     throw new IllegalArgumentException("Unsupported transform type: " + transformType);
/*     */   }
/*     */ 
/*     */   static int calculatePathColorGenCoeffsCount(int genMode, int colorFormat)
/*     */   {
/* 314 */     int coeffsPerComponent = calculatePathGenCoeffsPerComponent(genMode);
/*     */ 
/* 316 */     switch (colorFormat) {
/*     */     case 6407:
/* 318 */       return 3 * coeffsPerComponent;
/*     */     case 6408:
/* 320 */       return 4 * coeffsPerComponent;
/*     */     }
/* 322 */     return coeffsPerComponent;
/*     */   }
/*     */ 
/*     */   static int calculatePathTextGenCoeffsPerComponent(FloatBuffer coeffs, int genMode)
/*     */   {
/* 327 */     if (genMode == 0) {
/* 328 */       return 0;
/*     */     }
/* 330 */     return coeffs.remaining() / calculatePathGenCoeffsPerComponent(genMode);
/*     */   }
/*     */ 
/*     */   private static int calculatePathGenCoeffsPerComponent(int genMode) {
/* 334 */     switch (genMode) {
/*     */     case 0:
/* 336 */       return 0;
/*     */     case 9217:
/*     */     case 37002:
/* 339 */       return 3;
/*     */     case 9216:
/* 341 */       return 4;
/*     */     }
/* 343 */     throw new IllegalArgumentException("Unsupported gen mode: " + genMode);
/*     */   }
/*     */ 
/*     */   static int calculateMetricsSize(int metricQueryMask, int stride)
/*     */   {
/* 348 */     if ((LWJGLUtil.DEBUG) && ((stride < 0) || (stride % 4 != 0))) {
/* 349 */       throw new IllegalArgumentException("Invalid stride value: " + stride);
/*     */     }
/* 351 */     int metrics = Integer.bitCount(metricQueryMask);
/*     */ 
/* 353 */     if ((LWJGLUtil.DEBUG) && (stride >> 2 < metrics)) {
/* 354 */       throw new IllegalArgumentException("The queried metrics do not fit in the specified stride: " + stride);
/*     */     }
/* 356 */     return stride == 0 ? metrics : stride >> 2;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GLChecks
 * JD-Core Version:    0.6.2
 */