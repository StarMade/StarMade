/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ public final class CL
/*     */ {
/*     */   private static boolean created;
/*     */ 
/*     */   private static native void nCreate(String paramString)
/*     */     throws LWJGLException;
/*     */ 
/*     */   private static native void nCreateDefault()
/*     */     throws LWJGLException;
/*     */ 
/*     */   private static native void nDestroy();
/*     */ 
/*     */   public static boolean isCreated()
/*     */   {
/*  76 */     return created;
/*     */   }
/*     */ 
/*     */   public static void create() throws LWJGLException {
/*  80 */     if (created)
/*     */       return;
/*     */     String libname;
/*     */     String[] library_names;
/*  86 */     switch (LWJGLUtil.getPlatform()) {
/*     */     case 3:
/*  88 */       libname = "OpenCL";
/*  89 */       library_names = new String[] { "OpenCL.dll" };
/*  90 */       break;
/*     */     case 1:
/*  92 */       libname = "OpenCL";
/*  93 */       library_names = new String[] { "libOpenCL64.so", "libOpenCL.so" };
/*  94 */       break;
/*     */     case 2:
/*  96 */       libname = "OpenCL";
/*  97 */       library_names = new String[] { "OpenCL.dylib" };
/*  98 */       break;
/*     */     default:
/* 100 */       throw new LWJGLException("Unknown platform: " + LWJGLUtil.getPlatform());
/*     */     }
/*     */ 
/* 103 */     String[] oclPaths = LWJGLUtil.getLibraryPaths(libname, library_names, CL.class.getClassLoader());
/* 104 */     LWJGLUtil.log("Found " + oclPaths.length + " OpenCL paths");
/* 105 */     for (String oclPath : oclPaths) {
/*     */       try {
/* 107 */         nCreate(oclPath);
/* 108 */         created = true;
/*     */       }
/*     */       catch (LWJGLException e) {
/* 111 */         LWJGLUtil.log("Failed to load " + oclPath + ": " + e.getMessage());
/*     */       }
/*     */     }
/*     */ 
/* 115 */     if ((!created) && (LWJGLUtil.getPlatform() == 2))
/*     */     {
/* 117 */       nCreateDefault();
/* 118 */       created = true;
/*     */     }
/*     */ 
/* 121 */     if (!created) {
/* 122 */       throw new LWJGLException("Could not locate OpenCL library.");
/*     */     }
/* 124 */     if (!CLCapabilities.OpenCL10)
/* 125 */       throw new RuntimeException("OpenCL 1.0 not supported.");
/*     */   }
/*     */ 
/*     */   public static void destroy()
/*     */   {
/*     */   }
/*     */ 
/*     */   static long getFunctionAddress(String[] aliases)
/*     */   {
/* 139 */     for (String aliase : aliases) {
/* 140 */       long address = getFunctionAddress(aliase);
/* 141 */       if (address != 0L)
/* 142 */         return address;
/*     */     }
/* 144 */     return 0L;
/*     */   }
/*     */ 
/*     */   static long getFunctionAddress(String name)
/*     */   {
/* 149 */     ByteBuffer buffer = MemoryUtil.encodeASCII(name);
/* 150 */     return ngetFunctionAddress(MemoryUtil.getAddress(buffer));
/*     */   }
/*     */ 
/*     */   private static native long ngetFunctionAddress(long paramLong);
/*     */ 
/*     */   static native ByteBuffer getHostBuffer(long paramLong, int paramInt);
/*     */ 
/*     */   private static native void resetNativeStubs(Class paramClass);
/*     */ 
/*     */   static
/*     */   {
/*  52 */     Sys.initialize();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL
 * JD-Core Version:    0.6.2
 */