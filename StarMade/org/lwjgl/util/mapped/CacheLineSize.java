/*     */ package org.lwjgl.util.mapped;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutorCompletionService;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import sun.misc.Unsafe;
/*     */ 
/*     */ final class CacheLineSize
/*     */ {
/*     */   static int getCacheLineSize()
/*     */   {
/*  31 */     int THREADS = 2;
/*  32 */     int REPEATS = 200000;
/*  33 */     int LOCAL_REPEATS = 100000;
/*     */ 
/*  36 */     int MAX_SIZE = LWJGLUtil.getPrivilegedInteger("org.lwjgl.util.mapped.CacheLineMaxSize", 1024).intValue() / 4;
/*     */ 
/*  38 */     double TIME_THRESHOLD = 1.0D + LWJGLUtil.getPrivilegedInteger("org.lwjgl.util.mapped.CacheLineTimeThreshold", 50).intValue() / 100.0D;
/*     */ 
/*  40 */     ExecutorService executorService = Executors.newFixedThreadPool(2);
/*  41 */     ExecutorCompletionService completionService = new ExecutorCompletionService(executorService);
/*     */     try
/*     */     {
/*  45 */       IntBuffer memory = getMemory(MAX_SIZE);
/*     */ 
/*  49 */       int WARMUP = 10;
/*  50 */       for (int i = 0; i < 10; i++) {
/*  51 */         doTest(2, 100000, 0, memory, completionService);
/*     */       }
/*     */ 
/*  55 */       long totalTime = 0L;
/*  56 */       int count = 0;
/*  57 */       int cacheLineSize = 64;
/*  58 */       boolean found = false;
/*  59 */       for (int i = MAX_SIZE; i >= 1; i >>= 1) {
/*  60 */         long time = doTest(2, 100000, i, memory, completionService);
/*  61 */         if (totalTime > 0L) {
/*  62 */           long avgTime = totalTime / count;
/*  63 */           if (time / avgTime > TIME_THRESHOLD) {
/*  64 */             cacheLineSize = (i << 1) * 4;
/*  65 */             found = true;
/*  66 */             break;
/*     */           }
/*     */         }
/*  69 */         totalTime += time;
/*  70 */         count++;
/*     */       }
/*     */ 
/*  73 */       if (LWJGLUtil.DEBUG) {
/*  74 */         if (found)
/*  75 */           LWJGLUtil.log("Cache line size detected: " + cacheLineSize + " bytes");
/*     */         else {
/*  77 */           LWJGLUtil.log("Failed to detect cache line size, assuming " + cacheLineSize + " bytes");
/*     */         }
/*     */       }
/*  80 */       return cacheLineSize;
/*     */     } finally {
/*  82 */       executorService.shutdown();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/*  87 */     CacheUtil.getCacheLineSize();
/*     */   }
/*     */ 
/*     */   static long memoryLoop(int index, int repeats, IntBuffer memory, int padding) {
/*  91 */     long address = MemoryUtil.getAddress(memory) + index * padding * 4;
/*     */ 
/*  93 */     long time = System.nanoTime();
/*  94 */     for (int i = 0; i < repeats; i++)
/*     */     {
/*  96 */       MappedHelper.ivput(MappedHelper.ivget(address) + 1, address);
/*     */     }
/*     */ 
/*  99 */     return System.nanoTime() - time;
/*     */   }
/*     */ 
/*     */   private static IntBuffer getMemory(int START_SIZE) {
/* 103 */     int PAGE_SIZE = MappedObjectUnsafe.INSTANCE.pageSize();
/*     */ 
/* 105 */     ByteBuffer buffer = ByteBuffer.allocateDirect(START_SIZE * 4 + PAGE_SIZE).order(ByteOrder.nativeOrder());
/*     */ 
/* 108 */     if (MemoryUtil.getAddress(buffer) % PAGE_SIZE != 0L)
/*     */     {
/* 110 */       buffer.position(PAGE_SIZE - (int)(MemoryUtil.getAddress(buffer) & PAGE_SIZE - 1));
/*     */     }
/*     */ 
/* 113 */     return buffer.asIntBuffer();
/*     */   }
/*     */ 
/*     */   private static long doTest(int threads, int repeats, int padding, IntBuffer memory, ExecutorCompletionService<Long> completionService) {
/* 117 */     for (int i = 0; i < threads; i++)
/* 118 */       submitTest(completionService, i, repeats, memory, padding);
/* 119 */     return waitForResults(threads, completionService);
/*     */   }
/*     */ 
/*     */   private static void submitTest(ExecutorCompletionService<Long> completionService, int index, final int repeats, final IntBuffer memory, final int padding) {
/* 123 */     completionService.submit(new Callable() {
/*     */       public Long call() throws Exception {
/* 125 */         return Long.valueOf(CacheLineSize.memoryLoop(this.val$index, repeats, memory, padding));
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private static long waitForResults(int count, ExecutorCompletionService<Long> completionService) {
/*     */     try {
/* 132 */       long totalTime = 0L;
/* 133 */       for (int i = 0; i < count; i++)
/* 134 */         totalTime += ((Long)completionService.take().get()).longValue();
/* 135 */       return totalTime;
/*     */     } catch (Exception e) {
/* 137 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.CacheLineSize
 * JD-Core Version:    0.6.2
 */