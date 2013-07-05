/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ class Sync
/*     */ {
/*     */   private static final long NANOS_IN_SECOND = 1000000000L;
/*  49 */   private static long nextFrame = 0L;
/*     */ 
/*  52 */   private static boolean initialised = false;
/*     */ 
/*  55 */   private static RunningAvg sleepDurations = new RunningAvg(10);
/*  56 */   private static RunningAvg yieldDurations = new RunningAvg(10);
/*     */ 
/*     */   public static void sync(int fps)
/*     */   {
/*  66 */     if (fps <= 0) return;
/*  67 */     if (!initialised) initialise();
/*     */     try
/*     */     {
/*     */       long t1;
/*  71 */       for (long t0 = getTime(); nextFrame - t0 > sleepDurations.avg(); t0 = t1) {
/*  72 */         Thread.sleep(1L);
/*  73 */         sleepDurations.add((t1 = getTime()) - t0);
/*     */       }
/*     */ 
/*  77 */       sleepDurations.dampenForLowResTicker();
/*     */       long t1;
/*  80 */       for (long t0 = getTime(); nextFrame - t0 > yieldDurations.avg(); t0 = t1) {
/*  81 */         Thread.yield();
/*  82 */         yieldDurations.add((t1 = getTime()) - t0);
/*     */       }
/*     */     }
/*     */     catch (InterruptedException e)
/*     */     {
/*     */     }
/*     */ 
/*  89 */     nextFrame = Math.max(nextFrame + 1000000000L / fps, getTime());
/*     */   }
/*     */ 
/*     */   private static void initialise()
/*     */   {
/*  99 */     initialised = true;
/*     */ 
/* 101 */     sleepDurations.init(1000000L);
/* 102 */     yieldDurations.init((int)(-(getTime() - getTime()) * 1.333D));
/*     */ 
/* 104 */     nextFrame = getTime();
/*     */ 
/* 106 */     String osName = System.getProperty("os.name");
/*     */ 
/* 108 */     if (osName.startsWith("Win"))
/*     */     {
/* 113 */       Thread timerAccuracyThread = new Thread(new Runnable() {
/*     */         public void run() {
/*     */           try {
/* 116 */             Thread.sleep(9223372036854775807L);
/*     */           }
/*     */           catch (Exception e)
/*     */           {
/*     */           }
/*     */         }
/*     */       });
/* 121 */       timerAccuracyThread.setName("LWJGL Timer");
/* 122 */       timerAccuracyThread.setDaemon(true);
/* 123 */       timerAccuracyThread.start();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static long getTime()
/*     */   {
/* 133 */     return Sys.getTime() * 1000000000L / Sys.getTimerResolution();
/*     */   }
/*     */   private static class RunningAvg {
/*     */     private final long[] slots;
/*     */     private int offset;
/*     */     private static final long DAMPEN_THRESHOLD = 10000000L;
/*     */     private static final float DAMPEN_FACTOR = 0.9F;
/*     */ 
/* 144 */     public RunningAvg(int slotCount) { this.slots = new long[slotCount];
/* 145 */       this.offset = 0; }
/*     */ 
/*     */     public void init(long value)
/*     */     {
/* 149 */       while (this.offset < this.slots.length)
/* 150 */         this.slots[(this.offset++)] = value;
/*     */     }
/*     */ 
/*     */     public void add(long value)
/*     */     {
/* 155 */       this.slots[(this.offset++ % this.slots.length)] = value;
/* 156 */       this.offset %= this.slots.length;
/*     */     }
/*     */ 
/*     */     public long avg() {
/* 160 */       long sum = 0L;
/* 161 */       for (int i = 0; i < this.slots.length; i++) {
/* 162 */         sum += this.slots[i];
/*     */       }
/* 164 */       return sum / this.slots.length;
/*     */     }
/*     */ 
/*     */     public void dampenForLowResTicker() {
/* 168 */       if (avg() > 10000000L)
/* 169 */         for (int i = 0; i < this.slots.length; i++)
/*     */         {
/*     */           int tmp27_26 = i;
/*     */           long[] tmp27_23 = this.slots; tmp27_23[tmp27_26] = (()((float)tmp27_23[tmp27_26] * 0.9F));
/*     */         }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.Sync
 * JD-Core Version:    0.6.2
 */