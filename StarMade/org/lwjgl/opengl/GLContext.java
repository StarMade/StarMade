/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.lang.reflect.Method;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.security.AccessController;
/*   6:    */import java.security.PrivilegedAction;
/*   7:    */import java.security.PrivilegedExceptionAction;
/*   8:    */import java.util.Map;
/*   9:    */import java.util.Set;
/*  10:    */import java.util.StringTokenizer;
/*  11:    */import java.util.WeakHashMap;
/*  12:    */import org.lwjgl.LWJGLException;
/*  13:    */import org.lwjgl.LWJGLUtil;
/*  14:    */import org.lwjgl.MemoryUtil;
/*  15:    */import org.lwjgl.Sys;
/*  16:    */
/*  65:    */public final class GLContext
/*  66:    */{
/*  67: 67 */  private static final ThreadLocal<ContextCapabilities> current_capabilities = new ThreadLocal();
/*  68:    */  
/*  93: 93 */  private static CapabilitiesCacheEntry fast_path_cache = new CapabilitiesCacheEntry(null);
/*  94:    */  
/*  99: 99 */  private static final ThreadLocal<CapabilitiesCacheEntry> thread_cache_entries = new ThreadLocal();
/* 100:    */  
/* 105:105 */  private static final Map<Object, ContextCapabilities> capability_cache = new WeakHashMap();
/* 106:    */  
/* 107:    */  private static int gl_ref_count;
/* 108:    */  private static boolean did_auto_load;
/* 109:    */  
/* 110:    */  static
/* 111:    */  {
/* 112:112 */    Sys.initialize();
/* 113:    */  }
/* 114:    */  
/* 120:    */  public static ContextCapabilities getCapabilities()
/* 121:    */  {
/* 122:122 */    ContextCapabilities caps = getCapabilitiesImpl();
/* 123:123 */    if (caps == null) {
/* 124:124 */      throw new RuntimeException("No OpenGL context found in the current thread.");
/* 125:    */    }
/* 126:126 */    return caps;
/* 127:    */  }
/* 128:    */  
/* 129:    */  private static ContextCapabilities getCapabilitiesImpl() {
/* 130:130 */    CapabilitiesCacheEntry recent_cache_entry = fast_path_cache;
/* 131:    */    
/* 132:132 */    if (recent_cache_entry.owner == Thread.currentThread())
/* 133:    */    {
/* 136:136 */      return recent_cache_entry.capabilities;
/* 137:    */    }
/* 138:138 */    return getThreadLocalCapabilities();
/* 139:    */  }
/* 140:    */  
/* 147:    */  static ContextCapabilities getCapabilities(Object context)
/* 148:    */  {
/* 149:149 */    return (ContextCapabilities)capability_cache.get(context);
/* 150:    */  }
/* 151:    */  
/* 152:    */  private static ContextCapabilities getThreadLocalCapabilities() {
/* 153:153 */    return (ContextCapabilities)current_capabilities.get();
/* 154:    */  }
/* 155:    */  
/* 161:    */  static void setCapabilities(ContextCapabilities capabilities)
/* 162:    */  {
/* 163:163 */    current_capabilities.set(capabilities);
/* 164:    */    
/* 165:165 */    CapabilitiesCacheEntry thread_cache_entry = (CapabilitiesCacheEntry)thread_cache_entries.get();
/* 166:166 */    if (thread_cache_entry == null) {
/* 167:167 */      thread_cache_entry = new CapabilitiesCacheEntry(null);
/* 168:168 */      thread_cache_entries.set(thread_cache_entry);
/* 169:    */    }
/* 170:170 */    thread_cache_entry.owner = Thread.currentThread();
/* 171:171 */    thread_cache_entry.capabilities = capabilities;
/* 172:    */    
/* 173:173 */    fast_path_cache = thread_cache_entry;
/* 174:    */  }
/* 175:    */  
/* 179:    */  static long getPlatformSpecificFunctionAddress(String function_prefix, String[] os_prefixes, String[] os_function_prefixes, String function)
/* 180:    */  {
/* 181:181 */    String os_name = (String)AccessController.doPrivileged(new PrivilegedAction() {
/* 182:    */      public String run() {
/* 183:183 */        return System.getProperty("os.name");
/* 184:    */      }
/* 185:    */    });
/* 186:186 */    for (int i = 0; i < os_prefixes.length; i++)
/* 187:187 */      if (os_name.startsWith(os_prefixes[i])) {
/* 188:188 */        String platform_function_name = function.replaceFirst(function_prefix, os_function_prefixes[i]);
/* 189:189 */        long address = getFunctionAddress(platform_function_name);
/* 190:190 */        return address;
/* 191:    */      }
/* 192:192 */    return 0L;
/* 193:    */  }
/* 194:    */  
/* 201:    */  static long getFunctionAddress(String[] aliases)
/* 202:    */  {
/* 203:203 */    for (String alias : aliases) {
/* 204:204 */      long address = getFunctionAddress(alias);
/* 205:205 */      if (address != 0L)
/* 206:206 */        return address;
/* 207:    */    }
/* 208:208 */    return 0L;
/* 209:    */  }
/* 210:    */  
/* 211:    */  static long getFunctionAddress(String name)
/* 212:    */  {
/* 213:213 */    ByteBuffer buffer = MemoryUtil.encodeASCII(name);
/* 214:214 */    return ngetFunctionAddress(MemoryUtil.getAddress(buffer));
/* 215:    */  }
/* 216:    */  
/* 226:    */  static int getSupportedExtensions(Set<String> supported_extensions)
/* 227:    */  {
/* 228:228 */    String version = GL11.glGetString(7938);
/* 229:229 */    if (version == null) {
/* 230:230 */      throw new IllegalStateException("glGetString(GL_VERSION) returned null - possibly caused by missing current context.");
/* 231:    */    }
/* 232:232 */    StringTokenizer version_tokenizer = new StringTokenizer(version, ". ");
/* 233:233 */    String major_string = version_tokenizer.nextToken();
/* 234:234 */    String minor_string = version_tokenizer.nextToken();
/* 235:    */    
/* 236:236 */    int majorVersion = 0;
/* 237:237 */    int minorVersion = 0;
/* 238:    */    try {
/* 239:239 */      majorVersion = Integer.parseInt(major_string);
/* 240:240 */      minorVersion = Integer.parseInt(minor_string);
/* 241:    */    } catch (NumberFormatException e) {
/* 242:242 */      LWJGLUtil.log("The major and/or minor OpenGL version is malformed: " + e.getMessage());
/* 243:    */    }
/* 244:    */    
/* 245:245 */    int[][] GL_VERSIONS = { { 1, 2, 3, 4, 5 }, { 0, 1 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
/* 246:    */    
/* 252:252 */    for (int major = 1; major <= GL_VERSIONS.length; major++) {
/* 253:253 */      int[] minors = GL_VERSIONS[(major - 1)];
/* 254:254 */      for (int minor : minors) {
/* 255:255 */        if ((major < majorVersion) || ((major == majorVersion) && (minor <= minorVersion))) {
/* 256:256 */          supported_extensions.add("OpenGL" + Integer.toString(major) + Integer.toString(minor));
/* 257:    */        }
/* 258:    */      }
/* 259:    */    }
/* 260:260 */    int profileMask = 0;
/* 261:    */    
/* 262:262 */    if (majorVersion < 3)
/* 263:    */    {
/* 264:264 */      String extensions_string = GL11.glGetString(7939);
/* 265:265 */      if (extensions_string == null) {
/* 266:266 */        throw new IllegalStateException("glGetString(GL_EXTENSIONS) returned null - is there a context current?");
/* 267:    */      }
/* 268:268 */      StringTokenizer tokenizer = new StringTokenizer(extensions_string);
/* 269:269 */      while (tokenizer.hasMoreTokens()) {
/* 270:270 */        supported_extensions.add(tokenizer.nextToken());
/* 271:    */      }
/* 272:    */    } else {
/* 273:273 */      int extensionCount = GL11.glGetInteger(33309);
/* 274:    */      
/* 275:275 */      for (int i = 0; i < extensionCount; i++) {
/* 276:276 */        supported_extensions.add(GL30.glGetStringi(7939, i));
/* 277:    */      }
/* 278:    */      
/* 279:279 */      if ((3 < majorVersion) || (2 <= minorVersion)) {
/* 280:280 */        Util.checkGLError();
/* 281:    */        try
/* 282:    */        {
/* 283:283 */          profileMask = GL11.glGetInteger(37158);
/* 284:    */          
/* 286:286 */          Util.checkGLError();
/* 287:    */        } catch (OpenGLException e) {
/* 288:288 */          LWJGLUtil.log("Failed to retrieve CONTEXT_PROFILE_MASK");
/* 289:    */        }
/* 290:    */      }
/* 291:    */    }
/* 292:    */    
/* 293:293 */    return profileMask;
/* 294:    */  }
/* 295:    */  
/* 299:    */  static void initNativeStubs(Class<?> extension_class, Set supported_extensions, String ext_name)
/* 300:    */  {
/* 301:301 */    resetNativeStubs(extension_class);
/* 302:302 */    if (supported_extensions.contains(ext_name)) {
/* 303:    */      try {
/* 304:304 */        AccessController.doPrivileged(new PrivilegedExceptionAction() {
/* 305:    */          public Object run() throws Exception {
/* 306:306 */            Method init_stubs_method = this.val$extension_class.getDeclaredMethod("initNativeStubs", new Class[0]);
/* 307:307 */            init_stubs_method.invoke(null, new Object[0]);
/* 308:308 */            return null;
/* 309:    */          }
/* 310:    */        });
/* 311:    */      } catch (Exception e) {
/* 312:312 */        LWJGLUtil.log("Failed to initialize extension " + extension_class + " - exception: " + e);
/* 313:313 */        supported_extensions.remove(ext_name);
/* 314:    */      }
/* 315:    */    }
/* 316:    */  }
/* 317:    */  
/* 330:    */  public static synchronized void useContext(Object context)
/* 331:    */    throws LWJGLException
/* 332:    */  {
/* 333:333 */    useContext(context, false);
/* 334:    */  }
/* 335:    */  
/* 351:    */  public static synchronized void useContext(Object context, boolean forwardCompatible)
/* 352:    */    throws LWJGLException
/* 353:    */  {
/* 354:354 */    if (context == null) {
/* 355:355 */      ContextCapabilities.unloadAllStubs();
/* 356:356 */      setCapabilities(null);
/* 357:357 */      if (did_auto_load)
/* 358:358 */        unloadOpenGLLibrary();
/* 359:359 */      return;
/* 360:    */    }
/* 361:361 */    if (gl_ref_count == 0) {
/* 362:362 */      loadOpenGLLibrary();
/* 363:363 */      did_auto_load = true;
/* 364:    */    }
/* 365:    */    try {
/* 366:366 */      ContextCapabilities capabilities = (ContextCapabilities)capability_cache.get(context);
/* 367:367 */      if (capabilities == null)
/* 368:    */      {
/* 374:374 */        new ContextCapabilities(forwardCompatible);
/* 375:375 */        capability_cache.put(context, getCapabilities());
/* 376:    */      } else {
/* 377:377 */        setCapabilities(capabilities);
/* 378:    */      }
/* 379:379 */    } catch (LWJGLException e) { if (did_auto_load)
/* 380:380 */        unloadOpenGLLibrary();
/* 381:381 */      throw e;
/* 382:    */    }
/* 383:    */  }
/* 384:    */  
/* 385:    */  public static synchronized void loadOpenGLLibrary() throws LWJGLException
/* 386:    */  {
/* 387:387 */    if (gl_ref_count == 0)
/* 388:388 */      nLoadOpenGLLibrary();
/* 389:389 */    gl_ref_count += 1;
/* 390:    */  }
/* 391:    */  
/* 394:    */  public static synchronized void unloadOpenGLLibrary()
/* 395:    */  {
/* 396:396 */    gl_ref_count -= 1;
/* 397:    */    
/* 401:401 */    if ((gl_ref_count == 0) && (LWJGLUtil.getPlatform() != 1)) {
/* 402:402 */      nUnloadOpenGLLibrary();
/* 403:    */    }
/* 404:    */  }
/* 405:    */  
/* 406:    */  private static native long ngetFunctionAddress(long paramLong);
/* 407:    */  
/* 408:    */  private static native void nLoadOpenGLLibrary()
/* 409:    */    throws LWJGLException;
/* 410:    */  
/* 411:    */  private static native void nUnloadOpenGLLibrary();
/* 412:    */  
/* 413:    */  static native void resetNativeStubs(Class paramClass);
/* 414:    */  
/* 415:    */  private static final class CapabilitiesCacheEntry
/* 416:    */  {
/* 417:    */    Thread owner;
/* 418:    */    ContextCapabilities capabilities;
/* 419:    */  }
/* 420:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GLContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */