/*   1:    */package org.apache.commons.lang3.concurrent;
/*   2:    */
/*   3:    */import java.util.concurrent.ScheduledExecutorService;
/*   4:    */import java.util.concurrent.ScheduledFuture;
/*   5:    */import java.util.concurrent.ScheduledThreadPoolExecutor;
/*   6:    */import java.util.concurrent.TimeUnit;
/*   7:    */
/* 167:    */public class TimedSemaphore
/* 168:    */{
/* 169:    */  public static final int NO_LIMIT = 0;
/* 170:    */  private static final int THREAD_POOL_SIZE = 1;
/* 171:    */  private final ScheduledExecutorService executorService;
/* 172:    */  private final long period;
/* 173:    */  private final TimeUnit unit;
/* 174:    */  private final boolean ownExecutor;
/* 175:    */  private ScheduledFuture<?> task;
/* 176:    */  private long totalAcquireCount;
/* 177:    */  private long periodCount;
/* 178:    */  private int limit;
/* 179:    */  private int acquireCount;
/* 180:    */  private int lastCallsPerPeriod;
/* 181:    */  private boolean shutdown;
/* 182:    */  
/* 183:    */  public TimedSemaphore(long timePeriod, TimeUnit timeUnit, int limit)
/* 184:    */  {
/* 185:185 */    this(null, timePeriod, timeUnit, limit);
/* 186:    */  }
/* 187:    */  
/* 200:    */  public TimedSemaphore(ScheduledExecutorService service, long timePeriod, TimeUnit timeUnit, int limit)
/* 201:    */  {
/* 202:202 */    if (timePeriod <= 0L) {
/* 203:203 */      throw new IllegalArgumentException("Time period must be greater 0!");
/* 204:    */    }
/* 205:    */    
/* 206:206 */    this.period = timePeriod;
/* 207:207 */    this.unit = timeUnit;
/* 208:    */    
/* 209:209 */    if (service != null) {
/* 210:210 */      this.executorService = service;
/* 211:211 */      this.ownExecutor = false;
/* 212:    */    } else {
/* 213:213 */      ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(1);
/* 214:    */      
/* 215:215 */      s.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
/* 216:216 */      s.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
/* 217:217 */      this.executorService = s;
/* 218:218 */      this.ownExecutor = true;
/* 219:    */    }
/* 220:    */    
/* 221:221 */    setLimit(limit);
/* 222:    */  }
/* 223:    */  
/* 230:    */  public final synchronized int getLimit()
/* 231:    */  {
/* 232:232 */    return this.limit;
/* 233:    */  }
/* 234:    */  
/* 244:    */  public final synchronized void setLimit(int limit)
/* 245:    */  {
/* 246:246 */    this.limit = limit;
/* 247:    */  }
/* 248:    */  
/* 253:    */  public synchronized void shutdown()
/* 254:    */  {
/* 255:255 */    if (!this.shutdown)
/* 256:    */    {
/* 257:257 */      if (this.ownExecutor)
/* 258:    */      {
/* 260:260 */        getExecutorService().shutdownNow();
/* 261:    */      }
/* 262:262 */      if (this.task != null) {
/* 263:263 */        this.task.cancel(false);
/* 264:    */      }
/* 265:    */      
/* 266:266 */      this.shutdown = true;
/* 267:    */    }
/* 268:    */  }
/* 269:    */  
/* 276:    */  public synchronized boolean isShutdown()
/* 277:    */  {
/* 278:278 */    return this.shutdown;
/* 279:    */  }
/* 280:    */  
/* 290:    */  public synchronized void acquire()
/* 291:    */    throws InterruptedException
/* 292:    */  {
/* 293:293 */    if (isShutdown()) {
/* 294:294 */      throw new IllegalStateException("TimedSemaphore is shut down!");
/* 295:    */    }
/* 296:    */    
/* 297:297 */    if (this.task == null) {
/* 298:298 */      this.task = startTimer();
/* 299:    */    }
/* 300:    */    
/* 301:301 */    boolean canPass = false;
/* 302:    */    do {
/* 303:303 */      canPass = (getLimit() <= 0) || (this.acquireCount < getLimit());
/* 304:304 */      if (!canPass) {
/* 305:305 */        wait();
/* 306:    */      } else {
/* 307:307 */        this.acquireCount += 1;
/* 308:    */      }
/* 309:309 */    } while (!canPass);
/* 310:    */  }
/* 311:    */  
/* 321:    */  public synchronized int getLastAcquiresPerPeriod()
/* 322:    */  {
/* 323:323 */    return this.lastCallsPerPeriod;
/* 324:    */  }
/* 325:    */  
/* 331:    */  public synchronized int getAcquireCount()
/* 332:    */  {
/* 333:333 */    return this.acquireCount;
/* 334:    */  }
/* 335:    */  
/* 346:    */  public synchronized int getAvailablePermits()
/* 347:    */  {
/* 348:348 */    return getLimit() - getAcquireCount();
/* 349:    */  }
/* 350:    */  
/* 359:    */  public synchronized double getAverageCallsPerPeriod()
/* 360:    */  {
/* 361:361 */    return this.periodCount == 0L ? 0.0D : this.totalAcquireCount / this.periodCount;
/* 362:    */  }
/* 363:    */  
/* 371:    */  public long getPeriod()
/* 372:    */  {
/* 373:373 */    return this.period;
/* 374:    */  }
/* 375:    */  
/* 380:    */  public TimeUnit getUnit()
/* 381:    */  {
/* 382:382 */    return this.unit;
/* 383:    */  }
/* 384:    */  
/* 389:    */  protected ScheduledExecutorService getExecutorService()
/* 390:    */  {
/* 391:391 */    return this.executorService;
/* 392:    */  }
/* 393:    */  
/* 400:    */  protected ScheduledFuture<?> startTimer()
/* 401:    */  {
/* 402:402 */    getExecutorService().scheduleAtFixedRate(new Runnable()
/* 403:    */    {
/* 404:404 */      public void run() { TimedSemaphore.this.endOfPeriod(); } } , getPeriod(), getPeriod(), getUnit());
/* 405:    */  }
/* 406:    */  
/* 413:    */  synchronized void endOfPeriod()
/* 414:    */  {
/* 415:415 */    this.lastCallsPerPeriod = this.acquireCount;
/* 416:416 */    this.totalAcquireCount += this.acquireCount;
/* 417:417 */    this.periodCount += 1L;
/* 418:418 */    this.acquireCount = 0;
/* 419:419 */    notifyAll();
/* 420:    */  }
/* 421:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.TimedSemaphore
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */