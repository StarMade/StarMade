/*   1:    */package org.apache.commons.lang3.concurrent;
/*   2:    */
/*   3:    */import java.util.concurrent.Executors;
/*   4:    */import java.util.concurrent.ThreadFactory;
/*   5:    */import java.util.concurrent.atomic.AtomicLong;
/*   6:    */import org.apache.commons.lang3.builder.Builder;
/*   7:    */
/* 104:    */public class BasicThreadFactory
/* 105:    */  implements ThreadFactory
/* 106:    */{
/* 107:    */  private final AtomicLong threadCounter;
/* 108:    */  private final ThreadFactory wrappedFactory;
/* 109:    */  private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
/* 110:    */  private final String namingPattern;
/* 111:    */  private final Integer priority;
/* 112:    */  private final Boolean daemonFlag;
/* 113:    */  
/* 114:    */  private BasicThreadFactory(Builder builder)
/* 115:    */  {
/* 116:116 */    if (builder.wrappedFactory == null) {
/* 117:117 */      this.wrappedFactory = Executors.defaultThreadFactory();
/* 118:    */    } else {
/* 119:119 */      this.wrappedFactory = builder.wrappedFactory;
/* 120:    */    }
/* 121:    */    
/* 122:122 */    this.namingPattern = builder.namingPattern;
/* 123:123 */    this.priority = builder.priority;
/* 124:124 */    this.daemonFlag = builder.daemonFlag;
/* 125:125 */    this.uncaughtExceptionHandler = builder.exceptionHandler;
/* 126:    */    
/* 127:127 */    this.threadCounter = new AtomicLong();
/* 128:    */  }
/* 129:    */  
/* 137:    */  public final ThreadFactory getWrappedFactory()
/* 138:    */  {
/* 139:139 */    return this.wrappedFactory;
/* 140:    */  }
/* 141:    */  
/* 147:    */  public final String getNamingPattern()
/* 148:    */  {
/* 149:149 */    return this.namingPattern;
/* 150:    */  }
/* 151:    */  
/* 159:    */  public final Boolean getDaemonFlag()
/* 160:    */  {
/* 161:161 */    return this.daemonFlag;
/* 162:    */  }
/* 163:    */  
/* 169:    */  public final Integer getPriority()
/* 170:    */  {
/* 171:171 */    return this.priority;
/* 172:    */  }
/* 173:    */  
/* 179:    */  public final Thread.UncaughtExceptionHandler getUncaughtExceptionHandler()
/* 180:    */  {
/* 181:181 */    return this.uncaughtExceptionHandler;
/* 182:    */  }
/* 183:    */  
/* 190:    */  public long getThreadCount()
/* 191:    */  {
/* 192:192 */    return this.threadCounter.get();
/* 193:    */  }
/* 194:    */  
/* 202:    */  public Thread newThread(Runnable r)
/* 203:    */  {
/* 204:204 */    Thread t = getWrappedFactory().newThread(r);
/* 205:205 */    initializeThread(t);
/* 206:    */    
/* 207:207 */    return t;
/* 208:    */  }
/* 209:    */  
/* 218:    */  private void initializeThread(Thread t)
/* 219:    */  {
/* 220:220 */    if (getNamingPattern() != null) {
/* 221:221 */      Long count = Long.valueOf(this.threadCounter.incrementAndGet());
/* 222:222 */      t.setName(String.format(getNamingPattern(), new Object[] { count }));
/* 223:    */    }
/* 224:    */    
/* 225:225 */    if (getUncaughtExceptionHandler() != null) {
/* 226:226 */      t.setUncaughtExceptionHandler(getUncaughtExceptionHandler());
/* 227:    */    }
/* 228:    */    
/* 229:229 */    if (getPriority() != null) {
/* 230:230 */      t.setPriority(getPriority().intValue());
/* 231:    */    }
/* 232:    */    
/* 233:233 */    if (getDaemonFlag() != null) {
/* 234:234 */      t.setDaemon(getDaemonFlag().booleanValue());
/* 235:    */    }
/* 236:    */  }
/* 237:    */  
/* 244:    */  public static class Builder
/* 245:    */    implements Builder<BasicThreadFactory>
/* 246:    */  {
/* 247:    */    private ThreadFactory wrappedFactory;
/* 248:    */    
/* 254:    */    private Thread.UncaughtExceptionHandler exceptionHandler;
/* 255:    */    
/* 261:    */    private String namingPattern;
/* 262:    */    
/* 267:    */    private Integer priority;
/* 268:    */    
/* 273:    */    private Boolean daemonFlag;
/* 274:    */    
/* 280:    */    public Builder wrappedFactory(ThreadFactory factory)
/* 281:    */    {
/* 282:282 */      if (factory == null) {
/* 283:283 */        throw new NullPointerException("Wrapped ThreadFactory must not be null!");
/* 284:    */      }
/* 285:    */      
/* 287:287 */      this.wrappedFactory = factory;
/* 288:288 */      return this;
/* 289:    */    }
/* 290:    */    
/* 298:    */    public Builder namingPattern(String pattern)
/* 299:    */    {
/* 300:300 */      if (pattern == null) {
/* 301:301 */        throw new NullPointerException("Naming pattern must not be null!");
/* 302:    */      }
/* 303:    */      
/* 305:305 */      this.namingPattern = pattern;
/* 306:306 */      return this;
/* 307:    */    }
/* 308:    */    
/* 316:    */    public Builder daemon(boolean f)
/* 317:    */    {
/* 318:318 */      this.daemonFlag = Boolean.valueOf(f);
/* 319:319 */      return this;
/* 320:    */    }
/* 321:    */    
/* 328:    */    public Builder priority(int prio)
/* 329:    */    {
/* 330:330 */      this.priority = Integer.valueOf(prio);
/* 331:331 */      return this;
/* 332:    */    }
/* 333:    */    
/* 343:    */    public Builder uncaughtExceptionHandler(Thread.UncaughtExceptionHandler handler)
/* 344:    */    {
/* 345:345 */      if (handler == null) {
/* 346:346 */        throw new NullPointerException("Uncaught exception handler must not be null!");
/* 347:    */      }
/* 348:    */      
/* 350:350 */      this.exceptionHandler = handler;
/* 351:351 */      return this;
/* 352:    */    }
/* 353:    */    
/* 359:    */    public void reset()
/* 360:    */    {
/* 361:361 */      this.wrappedFactory = null;
/* 362:362 */      this.exceptionHandler = null;
/* 363:363 */      this.namingPattern = null;
/* 364:364 */      this.priority = null;
/* 365:365 */      this.daemonFlag = null;
/* 366:    */    }
/* 367:    */    
/* 374:    */    public BasicThreadFactory build()
/* 375:    */    {
/* 376:376 */      BasicThreadFactory factory = new BasicThreadFactory(this, null);
/* 377:377 */      reset();
/* 378:378 */      return factory;
/* 379:    */    }
/* 380:    */  }
/* 381:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.BasicThreadFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */