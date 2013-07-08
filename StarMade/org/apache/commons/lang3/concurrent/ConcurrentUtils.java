/*   1:    */package org.apache.commons.lang3.concurrent;
/*   2:    */
/*   3:    */import java.util.concurrent.ConcurrentMap;
/*   4:    */import java.util.concurrent.ExecutionException;
/*   5:    */import java.util.concurrent.Future;
/*   6:    */import java.util.concurrent.TimeUnit;
/*   7:    */
/*  57:    */public class ConcurrentUtils
/*  58:    */{
/*  59:    */  public static ConcurrentException extractCause(ExecutionException ex)
/*  60:    */  {
/*  61: 61 */    if ((ex == null) || (ex.getCause() == null)) {
/*  62: 62 */      return null;
/*  63:    */    }
/*  64:    */    
/*  65: 65 */    throwCause(ex);
/*  66: 66 */    return new ConcurrentException(ex.getMessage(), ex.getCause());
/*  67:    */  }
/*  68:    */  
/*  81:    */  public static ConcurrentRuntimeException extractCauseUnchecked(ExecutionException ex)
/*  82:    */  {
/*  83: 83 */    if ((ex == null) || (ex.getCause() == null)) {
/*  84: 84 */      return null;
/*  85:    */    }
/*  86:    */    
/*  87: 87 */    throwCause(ex);
/*  88: 88 */    return new ConcurrentRuntimeException(ex.getMessage(), ex.getCause());
/*  89:    */  }
/*  90:    */  
/* 103:    */  public static void handleCause(ExecutionException ex)
/* 104:    */    throws ConcurrentException
/* 105:    */  {
/* 106:106 */    ConcurrentException cex = extractCause(ex);
/* 107:    */    
/* 108:108 */    if (cex != null) {
/* 109:109 */      throw cex;
/* 110:    */    }
/* 111:    */  }
/* 112:    */  
/* 125:    */  public static void handleCauseUnchecked(ExecutionException ex)
/* 126:    */  {
/* 127:127 */    ConcurrentRuntimeException crex = extractCauseUnchecked(ex);
/* 128:    */    
/* 129:129 */    if (crex != null) {
/* 130:130 */      throw crex;
/* 131:    */    }
/* 132:    */  }
/* 133:    */  
/* 142:    */  static Throwable checkedException(Throwable ex)
/* 143:    */  {
/* 144:144 */    if ((ex != null) && (!(ex instanceof RuntimeException)) && (!(ex instanceof Error)))
/* 145:    */    {
/* 146:146 */      return ex;
/* 147:    */    }
/* 148:148 */    throw new IllegalArgumentException("Not a checked exception: " + ex);
/* 149:    */  }
/* 150:    */  
/* 157:    */  private static void throwCause(ExecutionException ex)
/* 158:    */  {
/* 159:159 */    if ((ex.getCause() instanceof RuntimeException)) {
/* 160:160 */      throw ((RuntimeException)ex.getCause());
/* 161:    */    }
/* 162:    */    
/* 163:163 */    if ((ex.getCause() instanceof Error)) {
/* 164:164 */      throw ((Error)ex.getCause());
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 181:    */  public static <T> T initialize(ConcurrentInitializer<T> initializer)
/* 182:    */    throws ConcurrentException
/* 183:    */  {
/* 184:184 */    return initializer != null ? initializer.get() : null;
/* 185:    */  }
/* 186:    */  
/* 198:    */  public static <T> T initializeUnchecked(ConcurrentInitializer<T> initializer)
/* 199:    */  {
/* 200:    */    try
/* 201:    */    {
/* 202:202 */      return initialize(initializer);
/* 203:    */    } catch (ConcurrentException cex) {
/* 204:204 */      throw new ConcurrentRuntimeException(cex.getCause());
/* 205:    */    }
/* 206:    */  }
/* 207:    */  
/* 240:    */  public static <K, V> V putIfAbsent(ConcurrentMap<K, V> map, K key, V value)
/* 241:    */  {
/* 242:242 */    if (map == null) {
/* 243:243 */      return null;
/* 244:    */    }
/* 245:    */    
/* 246:246 */    V result = map.putIfAbsent(key, value);
/* 247:247 */    return result != null ? result : value;
/* 248:    */  }
/* 249:    */  
/* 269:    */  public static <K, V> V createIfAbsent(ConcurrentMap<K, V> map, K key, ConcurrentInitializer<V> init)
/* 270:    */    throws ConcurrentException
/* 271:    */  {
/* 272:272 */    if ((map == null) || (init == null)) {
/* 273:273 */      return null;
/* 274:    */    }
/* 275:    */    
/* 276:276 */    V value = map.get(key);
/* 277:277 */    if (value == null) {
/* 278:278 */      return putIfAbsent(map, key, init.get());
/* 279:    */    }
/* 280:280 */    return value;
/* 281:    */  }
/* 282:    */  
/* 297:    */  public static <K, V> V createIfAbsentUnchecked(ConcurrentMap<K, V> map, K key, ConcurrentInitializer<V> init)
/* 298:    */  {
/* 299:    */    try
/* 300:    */    {
/* 301:301 */      return createIfAbsent(map, key, init);
/* 302:    */    } catch (ConcurrentException cex) {
/* 303:303 */      throw new ConcurrentRuntimeException(cex.getCause());
/* 304:    */    }
/* 305:    */  }
/* 306:    */  
/* 322:    */  public static <T> Future<T> constantFuture(T value)
/* 323:    */  {
/* 324:324 */    return new ConstantFuture(value);
/* 325:    */  }
/* 326:    */  
/* 331:    */  static final class ConstantFuture<T>
/* 332:    */    implements Future<T>
/* 333:    */  {
/* 334:    */    private final T value;
/* 335:    */    
/* 340:    */    ConstantFuture(T value)
/* 341:    */    {
/* 342:342 */      this.value = value;
/* 343:    */    }
/* 344:    */    
/* 349:    */    public boolean isDone()
/* 350:    */    {
/* 351:351 */      return true;
/* 352:    */    }
/* 353:    */    
/* 356:    */    public T get()
/* 357:    */    {
/* 358:358 */      return this.value;
/* 359:    */    }
/* 360:    */    
/* 364:    */    public T get(long timeout, TimeUnit unit)
/* 365:    */    {
/* 366:366 */      return this.value;
/* 367:    */    }
/* 368:    */    
/* 372:    */    public boolean isCancelled()
/* 373:    */    {
/* 374:374 */      return false;
/* 375:    */    }
/* 376:    */    
/* 380:    */    public boolean cancel(boolean mayInterruptIfRunning)
/* 381:    */    {
/* 382:382 */      return false;
/* 383:    */    }
/* 384:    */  }
/* 385:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */