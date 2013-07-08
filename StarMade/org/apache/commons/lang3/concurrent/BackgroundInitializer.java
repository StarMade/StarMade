/*   1:    */package org.apache.commons.lang3.concurrent;
/*   2:    */
/*   3:    */import java.util.concurrent.Callable;
/*   4:    */import java.util.concurrent.ExecutionException;
/*   5:    */import java.util.concurrent.ExecutorService;
/*   6:    */import java.util.concurrent.Executors;
/*   7:    */import java.util.concurrent.Future;
/*   8:    */
/*  93:    */public abstract class BackgroundInitializer<T>
/*  94:    */  implements ConcurrentInitializer<T>
/*  95:    */{
/*  96:    */  private ExecutorService externalExecutor;
/*  97:    */  private ExecutorService executor;
/*  98:    */  private Future<T> future;
/*  99:    */  
/* 100:    */  protected BackgroundInitializer()
/* 101:    */  {
/* 102:102 */    this(null);
/* 103:    */  }
/* 104:    */  
/* 114:    */  protected BackgroundInitializer(ExecutorService exec)
/* 115:    */  {
/* 116:116 */    setExternalExecutor(exec);
/* 117:    */  }
/* 118:    */  
/* 123:    */  public final synchronized ExecutorService getExternalExecutor()
/* 124:    */  {
/* 125:125 */    return this.externalExecutor;
/* 126:    */  }
/* 127:    */  
/* 134:    */  public synchronized boolean isStarted()
/* 135:    */  {
/* 136:136 */    return this.future != null;
/* 137:    */  }
/* 138:    */  
/* 153:    */  public final synchronized void setExternalExecutor(ExecutorService externalExecutor)
/* 154:    */  {
/* 155:155 */    if (isStarted()) {
/* 156:156 */      throw new IllegalStateException("Cannot set ExecutorService after start()!");
/* 157:    */    }
/* 158:    */    
/* 160:160 */    this.externalExecutor = externalExecutor;
/* 161:    */  }
/* 162:    */  
/* 173:    */  public synchronized boolean start()
/* 174:    */  {
/* 175:175 */    if (!isStarted())
/* 176:    */    {
/* 180:180 */      this.executor = getExternalExecutor();
/* 181:181 */      ExecutorService tempExec; if (this.executor == null) { ExecutorService tempExec;
/* 182:182 */        this.executor = (tempExec = createExecutor());
/* 183:    */      } else {
/* 184:184 */        tempExec = null;
/* 185:    */      }
/* 186:    */      
/* 187:187 */      this.future = this.executor.submit(createTask(tempExec));
/* 188:    */      
/* 189:189 */      return true;
/* 190:    */    }
/* 191:    */    
/* 192:192 */    return false;
/* 193:    */  }
/* 194:    */  
/* 206:    */  public T get()
/* 207:    */    throws ConcurrentException
/* 208:    */  {
/* 209:    */    try
/* 210:    */    {
/* 211:211 */      return getFuture().get();
/* 212:    */    } catch (ExecutionException execex) {
/* 213:213 */      ConcurrentUtils.handleCause(execex);
/* 214:214 */      return null;
/* 215:    */    }
/* 216:    */    catch (InterruptedException iex) {
/* 217:217 */      Thread.currentThread().interrupt();
/* 218:218 */      throw new ConcurrentException(iex);
/* 219:    */    }
/* 220:    */  }
/* 221:    */  
/* 229:    */  public synchronized Future<T> getFuture()
/* 230:    */  {
/* 231:231 */    if (this.future == null) {
/* 232:232 */      throw new IllegalStateException("start() must be called first!");
/* 233:    */    }
/* 234:    */    
/* 235:235 */    return this.future;
/* 236:    */  }
/* 237:    */  
/* 246:    */  protected final synchronized ExecutorService getActiveExecutor()
/* 247:    */  {
/* 248:248 */    return this.executor;
/* 249:    */  }
/* 250:    */  
/* 261:    */  protected int getTaskCount()
/* 262:    */  {
/* 263:263 */    return 1;
/* 264:    */  }
/* 265:    */  
/* 276:    */  protected abstract T initialize()
/* 277:    */    throws Exception;
/* 278:    */  
/* 288:    */  private Callable<T> createTask(ExecutorService execDestroy)
/* 289:    */  {
/* 290:290 */    return new InitializationTask(execDestroy);
/* 291:    */  }
/* 292:    */  
/* 298:    */  private ExecutorService createExecutor()
/* 299:    */  {
/* 300:300 */    return Executors.newFixedThreadPool(getTaskCount());
/* 301:    */  }
/* 302:    */  
/* 305:    */  private class InitializationTask
/* 306:    */    implements Callable<T>
/* 307:    */  {
/* 308:    */    private final ExecutorService execFinally;
/* 309:    */    
/* 312:    */    public InitializationTask(ExecutorService exec)
/* 313:    */    {
/* 314:314 */      this.execFinally = exec;
/* 315:    */    }
/* 316:    */    
/* 320:    */    public T call()
/* 321:    */      throws Exception
/* 322:    */    {
/* 323:    */      try
/* 324:    */      {
/* 325:325 */        return BackgroundInitializer.this.initialize();
/* 326:    */      } finally {
/* 327:327 */        if (this.execFinally != null) {
/* 328:328 */          this.execFinally.shutdown();
/* 329:    */        }
/* 330:    */      }
/* 331:    */    }
/* 332:    */  }
/* 333:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.BackgroundInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */