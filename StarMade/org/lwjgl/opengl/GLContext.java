/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.WeakHashMap;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ public final class GLContext
/*     */ {
/*  67 */   private static final ThreadLocal<ContextCapabilities> current_capabilities = new ThreadLocal();
/*     */ 
/*  93 */   private static CapabilitiesCacheEntry fast_path_cache = new CapabilitiesCacheEntry(null);
/*     */ 
/*  99 */   private static final ThreadLocal<CapabilitiesCacheEntry> thread_cache_entries = new ThreadLocal();
/*     */ 
/* 105 */   private static final Map<Object, ContextCapabilities> capability_cache = new WeakHashMap();
/*     */   private static int gl_ref_count;
/*     */   private static boolean did_auto_load;
/*     */ 
/*     */   public static ContextCapabilities getCapabilities()
/*     */   {
/* 122 */     ContextCapabilities caps = getCapabilitiesImpl();
/* 123 */     if (caps == null) {
/* 124 */       throw new RuntimeException("No OpenGL context found in the current thread.");
/*     */     }
/* 126 */     return caps;
/*     */   }
/*     */ 
/*     */   private static ContextCapabilities getCapabilitiesImpl() {
/* 130 */     CapabilitiesCacheEntry recent_cache_entry = fast_path_cache;
/*     */ 
/* 132 */     if (recent_cache_entry.owner == Thread.currentThread())
/*     */     {
/* 136 */       return recent_cache_entry.capabilities;
/*     */     }
/* 138 */     return getThreadLocalCapabilities();
/*     */   }
/*     */ 
/*     */   static ContextCapabilities getCapabilities(Object context)
/*     */   {
/* 149 */     return (ContextCapabilities)capability_cache.get(context);
/*     */   }
/*     */ 
/*     */   private static ContextCapabilities getThreadLocalCapabilities() {
/* 153 */     return (ContextCapabilities)current_capabilities.get();
/*     */   }
/*     */ 
/*     */   static void setCapabilities(ContextCapabilities capabilities)
/*     */   {
/* 163 */     current_capabilities.set(capabilities);
/*     */ 
/* 165 */     CapabilitiesCacheEntry thread_cache_entry = (CapabilitiesCacheEntry)thread_cache_entries.get();
/* 166 */     if (thread_cache_entry == null) {
/* 167 */       thread_cache_entry = new CapabilitiesCacheEntry(null);
/* 168 */       thread_cache_entries.set(thread_cache_entry);
/*     */     }
/* 170 */     thread_cache_entry.owner = Thread.currentThread();
/* 171 */     thread_cache_entry.capabilities = capabilities;
/*     */ 
/* 173 */     fast_path_cache = thread_cache_entry;
/*     */   }
/*     */ 
/*     */   static long getPlatformSpecificFunctionAddress(String function_prefix, String[] os_prefixes, String[] os_function_prefixes, String function)
/*     */   {
/* 181 */     String os_name = (String)AccessController.doPrivileged(new PrivilegedAction() {
/*     */       public String run() {
/* 183 */         return System.getProperty("os.name");
/*     */       }
/*     */     });
/* 186 */     for (int i = 0; i < os_prefixes.length; i++)
/* 187 */       if (os_name.startsWith(os_prefixes[i])) {
/* 188 */         String platform_function_name = function.replaceFirst(function_prefix, os_function_prefixes[i]);
/* 189 */         long address = getFunctionAddress(platform_function_name);
/* 190 */         return address;
/*     */       }
/* 192 */     return 0L;
/*     */   }
/*     */ 
/*     */   static long getFunctionAddress(String[] aliases)
/*     */   {
/* 203 */     for (String alias : aliases) {
/* 204 */       long address = getFunctionAddress(alias);
/* 205 */       if (address != 0L)
/* 206 */         return address;
/*     */     }
/* 208 */     return 0L;
/*     */   }
/*     */ 
/*     */   static long getFunctionAddress(String name)
/*     */   {
/* 213 */     ByteBuffer buffer = MemoryUtil.encodeASCII(name);
/* 214 */     return ngetFunctionAddress(MemoryUtil.getAddress(buffer));
/*     */   }
/*     */ 
/*     */   private static native long ngetFunctionAddress(long paramLong);
/*     */ 
/*     */   static int getSupportedExtensions(Set<String> supported_extensions)
/*     */   {
/* 228 */     String version = GL11.glGetString(7938);
/* 229 */     if (version == null) {
/* 230 */       throw new IllegalStateException("glGetString(GL_VERSION) returned null - possibly caused by missing current context.");
/*     */     }
/* 232 */     StringTokenizer version_tokenizer = new StringTokenizer(version, ". ");
/* 233 */     String major_string = version_tokenizer.nextToken();
/* 234 */     String minor_string = version_tokenizer.nextToken();
/*     */ 
/* 236 */     int majorVersion = 0;
/* 237 */     int minorVersion = 0;
/*     */     try {
/* 239 */       majorVersion = Integer.parseInt(major_string);
/* 240 */       minorVersion = Integer.parseInt(minor_string);
/*     */     } catch (NumberFormatException e) {
/* 242 */       LWJGLUtil.log("The major and/or minor OpenGL version is malformed: " + e.getMessage());
/*     */     }
/*     */ 
/* 245 */     int[][] GL_VERSIONS = { { 1, 2, 3, 4, 5 }, { 0, 1 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
/*     */ 
/* 252 */     for (int major = 1; major <= GL_VERSIONS.length; major++) {
/* 253 */       int[] minors = GL_VERSIONS[(major - 1)];
/* 254 */       for (int minor : minors) {
/* 255 */         if ((major < majorVersion) || ((major == majorVersion) && (minor <= minorVersion))) {
/* 256 */           supported_extensions.add("OpenGL" + Integer.toString(major) + Integer.toString(minor));
/*     */         }
/*     */       }
/*     */     }
/* 260 */     int profileMask = 0;
/*     */ 
/* 262 */     if (majorVersion < 3)
/*     */     {
/* 264 */       String extensions_string = GL11.glGetString(7939);
/* 265 */       if (extensions_string == null) {
/* 266 */         throw new IllegalStateException("glGetString(GL_EXTENSIONS) returned null - is there a context current?");
/*     */       }
/* 268 */       StringTokenizer tokenizer = new StringTokenizer(extensions_string);
/* 269 */       while (tokenizer.hasMoreTokens())
/* 270 */         supported_extensions.add(tokenizer.nextToken());
/*     */     }
/*     */     else {
/* 273 */       int extensionCount = GL11.glGetInteger(33309);
/*     */ 
/* 275 */       for (int i = 0; i < extensionCount; i++) {
/* 276 */         supported_extensions.add(GL30.glGetStringi(7939, i));
/*     */       }
/*     */ 
/* 279 */       if ((3 < majorVersion) || (2 <= minorVersion)) {
/* 280 */         Util.checkGLError();
/*     */         try
/*     */         {
/* 283 */           profileMask = GL11.glGetInteger(37158);
/*     */ 
/* 286 */           Util.checkGLError();
/*     */         } catch (OpenGLException e) {
/* 288 */           LWJGLUtil.log("Failed to retrieve CONTEXT_PROFILE_MASK");
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 293 */     return profileMask;
/*     */   }
/*     */ 
/*     */   static void initNativeStubs(Class<?> extension_class, Set supported_extensions, String ext_name)
/*     */   {
/* 301 */     resetNativeStubs(extension_class);
/* 302 */     if (supported_extensions.contains(ext_name))
/*     */       try {
/* 304 */         AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */           public Object run() throws Exception {
/* 306 */             Method init_stubs_method = this.val$extension_class.getDeclaredMethod("initNativeStubs", new Class[0]);
/* 307 */             init_stubs_method.invoke(null, new Object[0]);
/* 308 */             return null;
/*     */           } } );
/*     */       }
/*     */       catch (Exception e) {
/* 312 */         LWJGLUtil.log("Failed to initialize extension " + extension_class + " - exception: " + e);
/* 313 */         supported_extensions.remove(ext_name);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static synchronized void useContext(Object context)
/*     */     throws LWJGLException
/*     */   {
/* 333 */     useContext(context, false);
/*     */   }
/*     */ 
/*     */   public static synchronized void useContext(Object context, boolean forwardCompatible)
/*     */     throws LWJGLException
/*     */   {
/* 354 */     if (context == null) {
/* 355 */       ContextCapabilities.unloadAllStubs();
/* 356 */       setCapabilities(null);
/* 357 */       if (did_auto_load)
/* 358 */         unloadOpenGLLibrary();
/* 359 */       return;
/*     */     }
/* 361 */     if (gl_ref_count == 0) {
/* 362 */       loadOpenGLLibrary();
/* 363 */       did_auto_load = true;
/*     */     }
/*     */     try {
/* 366 */       ContextCapabilities capabilities = (ContextCapabilities)capability_cache.get(context);
/* 367 */       if (capabilities == null)
/*     */       {
/* 374 */         new ContextCapabilities(forwardCompatible);
/* 375 */         capability_cache.put(context, getCapabilities());
/*     */       } else {
/* 377 */         setCapabilities(capabilities);
/*     */       }
/*     */     } catch (LWJGLException e) { if (did_auto_load)
/* 380 */         unloadOpenGLLibrary();
/* 381 */       throw e; }
/*     */   }
/*     */ 
/*     */   public static synchronized void loadOpenGLLibrary()
/*     */     throws LWJGLException
/*     */   {
/* 387 */     if (gl_ref_count == 0)
/* 388 */       nLoadOpenGLLibrary();
/* 389 */     gl_ref_count += 1;
/*     */   }
/*     */ 
/*     */   private static native void nLoadOpenGLLibrary() throws LWJGLException;
/*     */ 
/*     */   public static synchronized void unloadOpenGLLibrary()
/*     */   {
/* 396 */     gl_ref_count -= 1;
/*     */ 
/* 401 */     if ((gl_ref_count == 0) && (LWJGLUtil.getPlatform() != 1))
/* 402 */       nUnloadOpenGLLibrary();
/*     */   }
/*     */ 
/*     */   private static native void nUnloadOpenGLLibrary();
/*     */ 
/*     */   static native void resetNativeStubs(Class paramClass);
/*     */ 
/*     */   static
/*     */   {
/* 112 */     Sys.initialize();
/*     */   }
/*     */ 
/*     */   private static final class CapabilitiesCacheEntry
/*     */   {
/*     */     Thread owner;
/*     */     ContextCapabilities capabilities;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GLContext
 * JD-Core Version:    0.6.2
 */