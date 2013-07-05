/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ public final class CL10GL
/*     */ {
/*     */   public static final int CL_GL_OBJECT_BUFFER = 8192;
/*     */   public static final int CL_GL_OBJECT_TEXTURE2D = 8193;
/*     */   public static final int CL_GL_OBJECT_TEXTURE3D = 8194;
/*     */   public static final int CL_GL_OBJECT_RENDERBUFFER = 8195;
/*     */   public static final int CL_GL_TEXTURE_TARGET = 8196;
/*     */   public static final int CL_GL_MIPMAP_LEVEL = 8197;
/*     */ 
/*     */   public static CLMem clCreateFromGLBuffer(CLContext context, long flags, int bufobj, IntBuffer errcode_ret)
/*     */   {
/*  30 */     long function_pointer = CLCapabilities.clCreateFromGLBuffer;
/*  31 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  32 */     if (errcode_ret != null)
/*  33 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  34 */     CLMem __result = new CLMem(nclCreateFromGLBuffer(context.getPointer(), flags, bufobj, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  35 */     return __result;
/*     */   }
/*     */   static native long nclCreateFromGLBuffer(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static CLMem clCreateFromGLTexture2D(CLContext context, long flags, int target, int miplevel, int texture, IntBuffer errcode_ret) {
/*  40 */     long function_pointer = CLCapabilities.clCreateFromGLTexture2D;
/*  41 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  42 */     if (errcode_ret != null)
/*  43 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  44 */     CLMem __result = new CLMem(nclCreateFromGLTexture2D(context.getPointer(), flags, target, miplevel, texture, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  45 */     return __result;
/*     */   }
/*     */   static native long nclCreateFromGLTexture2D(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static CLMem clCreateFromGLTexture3D(CLContext context, long flags, int target, int miplevel, int texture, IntBuffer errcode_ret) {
/*  50 */     long function_pointer = CLCapabilities.clCreateFromGLTexture3D;
/*  51 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  52 */     if (errcode_ret != null)
/*  53 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  54 */     CLMem __result = new CLMem(nclCreateFromGLTexture3D(context.getPointer(), flags, target, miplevel, texture, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  55 */     return __result;
/*     */   }
/*     */   static native long nclCreateFromGLTexture3D(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static CLMem clCreateFromGLRenderbuffer(CLContext context, long flags, int renderbuffer, IntBuffer errcode_ret) {
/*  60 */     long function_pointer = CLCapabilities.clCreateFromGLRenderbuffer;
/*  61 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  62 */     if (errcode_ret != null)
/*  63 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  64 */     CLMem __result = new CLMem(nclCreateFromGLRenderbuffer(context.getPointer(), flags, renderbuffer, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*  65 */     return __result;
/*     */   }
/*     */   static native long nclCreateFromGLRenderbuffer(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static int clGetGLObjectInfo(CLMem memobj, IntBuffer gl_object_type, IntBuffer gl_object_name) {
/*  70 */     long function_pointer = CLCapabilities.clGetGLObjectInfo;
/*  71 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  72 */     if (gl_object_type != null)
/*  73 */       BufferChecks.checkBuffer(gl_object_type, 1);
/*  74 */     if (gl_object_name != null)
/*  75 */       BufferChecks.checkBuffer(gl_object_name, 1);
/*  76 */     int __result = nclGetGLObjectInfo(memobj.getPointer(), MemoryUtil.getAddressSafe(gl_object_type), MemoryUtil.getAddressSafe(gl_object_name), function_pointer);
/*  77 */     return __result;
/*     */   }
/*     */   static native int nclGetGLObjectInfo(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static int clGetGLTextureInfo(CLMem memobj, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/*  82 */     long function_pointer = CLCapabilities.clGetGLTextureInfo;
/*  83 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  84 */     if (param_value != null)
/*  85 */       BufferChecks.checkDirect(param_value);
/*  86 */     if (param_value_size_ret != null)
/*  87 */       BufferChecks.checkBuffer(param_value_size_ret, 1);
/*  88 */     int __result = nclGetGLTextureInfo(memobj.getPointer(), param_name, param_value == null ? 0 : param_value.remaining(), MemoryUtil.getAddressSafe(param_value), MemoryUtil.getAddressSafe(param_value_size_ret), function_pointer);
/*  89 */     return __result;
/*     */   }
/*     */   static native int nclGetGLTextureInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static int clEnqueueAcquireGLObjects(CLCommandQueue command_queue, PointerBuffer mem_objects, PointerBuffer event_wait_list, PointerBuffer event) {
/*  94 */     long function_pointer = CLCapabilities.clEnqueueAcquireGLObjects;
/*  95 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  96 */     BufferChecks.checkBuffer(mem_objects, 1);
/*  97 */     if (event_wait_list != null)
/*  98 */       BufferChecks.checkDirect(event_wait_list);
/*  99 */     if (event != null)
/* 100 */       BufferChecks.checkBuffer(event, 1);
/* 101 */     int __result = nclEnqueueAcquireGLObjects(command_queue.getPointer(), mem_objects.remaining(), MemoryUtil.getAddress(mem_objects), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 102 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 103 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nclEnqueueAcquireGLObjects(long paramLong1, int paramInt1, long paramLong2, int paramInt2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static int clEnqueueAcquireGLObjects(CLCommandQueue command_queue, CLMem mem_object, PointerBuffer event_wait_list, PointerBuffer event) {
/* 109 */     long function_pointer = CLCapabilities.clEnqueueAcquireGLObjects;
/* 110 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 111 */     if (event_wait_list != null)
/* 112 */       BufferChecks.checkDirect(event_wait_list);
/* 113 */     if (event != null)
/* 114 */       BufferChecks.checkBuffer(event, 1);
/* 115 */     int __result = nclEnqueueAcquireGLObjects(command_queue.getPointer(), 1, APIUtil.getPointer(mem_object), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 116 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 117 */     return __result;
/*     */   }
/*     */ 
/*     */   public static int clEnqueueReleaseGLObjects(CLCommandQueue command_queue, PointerBuffer mem_objects, PointerBuffer event_wait_list, PointerBuffer event) {
/* 121 */     long function_pointer = CLCapabilities.clEnqueueReleaseGLObjects;
/* 122 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 123 */     BufferChecks.checkBuffer(mem_objects, 1);
/* 124 */     if (event_wait_list != null)
/* 125 */       BufferChecks.checkDirect(event_wait_list);
/* 126 */     if (event != null)
/* 127 */       BufferChecks.checkBuffer(event, 1);
/* 128 */     int __result = nclEnqueueReleaseGLObjects(command_queue.getPointer(), mem_objects.remaining(), MemoryUtil.getAddress(mem_objects), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 129 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 130 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nclEnqueueReleaseGLObjects(long paramLong1, int paramInt1, long paramLong2, int paramInt2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static int clEnqueueReleaseGLObjects(CLCommandQueue command_queue, CLMem mem_object, PointerBuffer event_wait_list, PointerBuffer event) {
/* 136 */     long function_pointer = CLCapabilities.clEnqueueReleaseGLObjects;
/* 137 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 138 */     if (event_wait_list != null)
/* 139 */       BufferChecks.checkDirect(event_wait_list);
/* 140 */     if (event != null)
/* 141 */       BufferChecks.checkBuffer(event, 1);
/* 142 */     int __result = nclEnqueueReleaseGLObjects(command_queue.getPointer(), 1, APIUtil.getPointer(mem_object), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 143 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 144 */     return __result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL10GL
 * JD-Core Version:    0.6.2
 */