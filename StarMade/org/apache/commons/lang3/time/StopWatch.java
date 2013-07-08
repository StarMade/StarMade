/*   1:    */package org.apache.commons.lang3.time;
/*   2:    */
/*  12:    */public class StopWatch
/*  13:    */{
/*  14:    */  private static final long NANO_2_MILLIS = 1000000L;
/*  15:    */  
/*  24:    */  private static final int STATE_UNSTARTED = 0;
/*  25:    */  
/*  34:    */  private static final int STATE_RUNNING = 1;
/*  35:    */  
/*  43:    */  private static final int STATE_STOPPED = 2;
/*  44:    */  
/*  52:    */  private static final int STATE_SUSPENDED = 3;
/*  53:    */  
/*  61:    */  private static final int STATE_UNSPLIT = 10;
/*  62:    */  
/*  70:    */  private static final int STATE_SPLIT = 11;
/*  71:    */  
/*  79: 79 */  private int runningState = 0;
/*  80:    */  
/*  84: 84 */  private int splitState = 10;
/*  85:    */  
/*  94:    */  private long startTime;
/*  95:    */  
/* 104:    */  private long startTimeMillis;
/* 105:    */  
/* 113:    */  private long stopTime;
/* 114:    */  
/* 123:    */  public void start()
/* 124:    */  {
/* 125:125 */    if (this.runningState == 2) {
/* 126:126 */      throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
/* 127:    */    }
/* 128:128 */    if (this.runningState != 0) {
/* 129:129 */      throw new IllegalStateException("Stopwatch already started. ");
/* 130:    */    }
/* 131:131 */    this.startTime = System.nanoTime();
/* 132:132 */    this.startTimeMillis = System.currentTimeMillis();
/* 133:133 */    this.runningState = 1;
/* 134:    */  }
/* 135:    */  
/* 147:    */  public void stop()
/* 148:    */  {
/* 149:149 */    if ((this.runningState != 1) && (this.runningState != 3)) {
/* 150:150 */      throw new IllegalStateException("Stopwatch is not running. ");
/* 151:    */    }
/* 152:152 */    if (this.runningState == 1) {
/* 153:153 */      this.stopTime = System.nanoTime();
/* 154:    */    }
/* 155:155 */    this.runningState = 2;
/* 156:    */  }
/* 157:    */  
/* 166:    */  public void reset()
/* 167:    */  {
/* 168:168 */    this.runningState = 0;
/* 169:169 */    this.splitState = 10;
/* 170:    */  }
/* 171:    */  
/* 184:    */  public void split()
/* 185:    */  {
/* 186:186 */    if (this.runningState != 1) {
/* 187:187 */      throw new IllegalStateException("Stopwatch is not running. ");
/* 188:    */    }
/* 189:189 */    this.stopTime = System.nanoTime();
/* 190:190 */    this.splitState = 11;
/* 191:    */  }
/* 192:    */  
/* 205:    */  public void unsplit()
/* 206:    */  {
/* 207:207 */    if (this.splitState != 11) {
/* 208:208 */      throw new IllegalStateException("Stopwatch has not been split. ");
/* 209:    */    }
/* 210:210 */    this.splitState = 10;
/* 211:    */  }
/* 212:    */  
/* 225:    */  public void suspend()
/* 226:    */  {
/* 227:227 */    if (this.runningState != 1) {
/* 228:228 */      throw new IllegalStateException("Stopwatch must be running to suspend. ");
/* 229:    */    }
/* 230:230 */    this.stopTime = System.nanoTime();
/* 231:231 */    this.runningState = 3;
/* 232:    */  }
/* 233:    */  
/* 246:    */  public void resume()
/* 247:    */  {
/* 248:248 */    if (this.runningState != 3) {
/* 249:249 */      throw new IllegalStateException("Stopwatch must be suspended to resume. ");
/* 250:    */    }
/* 251:251 */    this.startTime += System.nanoTime() - this.stopTime;
/* 252:252 */    this.runningState = 1;
/* 253:    */  }
/* 254:    */  
/* 266:    */  public long getTime()
/* 267:    */  {
/* 268:268 */    return getNanoTime() / 1000000L;
/* 269:    */  }
/* 270:    */  
/* 282:    */  public long getNanoTime()
/* 283:    */  {
/* 284:284 */    if ((this.runningState == 2) || (this.runningState == 3))
/* 285:285 */      return this.stopTime - this.startTime;
/* 286:286 */    if (this.runningState == 0)
/* 287:287 */      return 0L;
/* 288:288 */    if (this.runningState == 1) {
/* 289:289 */      return System.nanoTime() - this.startTime;
/* 290:    */    }
/* 291:291 */    throw new RuntimeException("Illegal running state has occured. ");
/* 292:    */  }
/* 293:    */  
/* 308:    */  public long getSplitTime()
/* 309:    */  {
/* 310:310 */    return getSplitNanoTime() / 1000000L;
/* 311:    */  }
/* 312:    */  
/* 326:    */  public long getSplitNanoTime()
/* 327:    */  {
/* 328:328 */    if (this.splitState != 11) {
/* 329:329 */      throw new IllegalStateException("Stopwatch must be split to get the split time. ");
/* 330:    */    }
/* 331:331 */    return this.stopTime - this.startTime;
/* 332:    */  }
/* 333:    */  
/* 341:    */  public long getStartTime()
/* 342:    */  {
/* 343:343 */    if (this.runningState == 0) {
/* 344:344 */      throw new IllegalStateException("Stopwatch has not been started");
/* 345:    */    }
/* 346:    */    
/* 347:347 */    return this.startTimeMillis;
/* 348:    */  }
/* 349:    */  
/* 361:    */  public String toString()
/* 362:    */  {
/* 363:363 */    return DurationFormatUtils.formatDurationHMS(getTime());
/* 364:    */  }
/* 365:    */  
/* 377:    */  public String toSplitString()
/* 378:    */  {
/* 379:379 */    return DurationFormatUtils.formatDurationHMS(getSplitTime());
/* 380:    */  }
/* 381:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.StopWatch
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */