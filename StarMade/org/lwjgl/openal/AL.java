/*     */ package org.lwjgl.openal;
/*     */ 
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ public final class AL
/*     */ {
/*     */   static ALCdevice device;
/*     */   static ALCcontext context;
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
/*  87 */     return created;
/*     */   }
/*     */ 
/*     */   public static void create(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized)
/*     */     throws LWJGLException
/*     */   {
/* 102 */     create(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, true);
/*     */   }
/*     */ 
/*     */   public static void create(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized, boolean openDevice)
/*     */     throws LWJGLException
/*     */   {
/* 112 */     if (created)
/* 113 */       throw new IllegalStateException("Only one OpenAL context may be instantiated at any one time.");
/*     */     String libname;
/*     */     String[] library_names;
/* 116 */     switch (LWJGLUtil.getPlatform()) {
/*     */     case 3:
/* 118 */       libname = "OpenAL32";
/* 119 */       library_names = new String[] { "OpenAL64.dll", "OpenAL32.dll" };
/* 120 */       break;
/*     */     case 1:
/* 122 */       libname = "openal";
/* 123 */       library_names = new String[] { "libopenal64.so", "libopenal.so", "libopenal.so.0" };
/* 124 */       break;
/*     */     case 2:
/* 126 */       libname = "openal";
/* 127 */       library_names = new String[] { "openal.dylib" };
/* 128 */       break;
/*     */     default:
/* 130 */       throw new LWJGLException("Unknown platform: " + LWJGLUtil.getPlatform());
/*     */     }
/* 132 */     String[] oalPaths = LWJGLUtil.getLibraryPaths(libname, library_names, AL.class.getClassLoader());
/* 133 */     LWJGLUtil.log("Found " + oalPaths.length + " OpenAL paths");
/* 134 */     for (String oalPath : oalPaths) {
/*     */       try {
/* 136 */         nCreate(oalPath);
/* 137 */         created = true;
/* 138 */         init(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, openDevice);
/*     */       }
/*     */       catch (LWJGLException e) {
/* 141 */         LWJGLUtil.log("Failed to load " + oalPath + ": " + e.getMessage());
/*     */       }
/*     */     }
/* 144 */     if ((!created) && (LWJGLUtil.getPlatform() == 2))
/*     */     {
/* 146 */       nCreateDefault();
/* 147 */       created = true;
/* 148 */       init(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, openDevice);
/*     */     }
/* 150 */     if (!created)
/* 151 */       throw new LWJGLException("Could not locate OpenAL library.");
/*     */   }
/*     */ 
/*     */   private static void init(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized, boolean openDevice) throws LWJGLException {
/*     */     try {
/* 156 */       AL10.initNativeStubs();
/* 157 */       ALC10.initNativeStubs();
/*     */ 
/* 159 */       if (openDevice) {
/* 160 */         device = ALC10.alcOpenDevice(deviceArguments);
/* 161 */         if (device == null) {
/* 162 */           throw new LWJGLException("Could not open ALC device");
/*     */         }
/*     */ 
/* 165 */         if (contextFrequency == -1)
/* 166 */           context = ALC10.alcCreateContext(device, null);
/*     */         else {
/* 168 */           context = ALC10.alcCreateContext(device, ALCcontext.createAttributeList(contextFrequency, contextRefresh, contextSynchronized ? 1 : 0));
/*     */         }
/*     */ 
/* 172 */         ALC10.alcMakeContextCurrent(context);
/*     */       }
/*     */     } catch (LWJGLException e) {
/* 175 */       destroy();
/* 176 */       throw e;
/*     */     }
/*     */ 
/* 179 */     ALC11.initialize();
/*     */ 
/* 188 */     if (ALC10.alcIsExtensionPresent(device, "ALC_EXT_EFX"))
/* 189 */       EFX10.initNativeStubs();
/*     */   }
/*     */ 
/*     */   public static void create()
/*     */     throws LWJGLException
/*     */   {
/* 201 */     create(null, 44100, 60, false);
/*     */   }
/*     */ 
/*     */   public static void destroy()
/*     */   {
/* 208 */     if (context != null) {
/* 209 */       ALC10.alcMakeContextCurrent(null);
/* 210 */       ALC10.alcDestroyContext(context);
/* 211 */       context = null;
/*     */     }
/* 213 */     if (device != null) {
/* 214 */       boolean result = ALC10.alcCloseDevice(device);
/* 215 */       device = null;
/*     */     }
/* 217 */     resetNativeStubs(AL10.class);
/* 218 */     resetNativeStubs(AL11.class);
/* 219 */     resetNativeStubs(ALC10.class);
/* 220 */     resetNativeStubs(ALC11.class);
/* 221 */     resetNativeStubs(EFX10.class);
/*     */ 
/* 223 */     if (created)
/* 224 */       nDestroy();
/* 225 */     created = false;
/*     */   }
/*     */ 
/*     */   private static native void resetNativeStubs(Class paramClass);
/*     */ 
/*     */   public static ALCcontext getContext()
/*     */   {
/* 234 */     return context;
/*     */   }
/*     */ 
/*     */   public static ALCdevice getDevice()
/*     */   {
/* 241 */     return device;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  59 */     Sys.initialize();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.AL
 * JD-Core Version:    0.6.2
 */