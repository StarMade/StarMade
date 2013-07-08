/*   1:    */package org.lwjgl.util.mapped;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.ByteOrder;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import java.util.concurrent.Callable;
/*   7:    */import java.util.concurrent.ExecutorCompletionService;
/*   8:    */import java.util.concurrent.ExecutorService;
/*   9:    */import java.util.concurrent.Executors;
/*  10:    */import java.util.concurrent.Future;
/*  11:    */import org.lwjgl.LWJGLUtil;
/*  12:    */import org.lwjgl.MemoryUtil;
/*  13:    */import sun.misc.Unsafe;
/*  14:    */
/*  27:    */final class CacheLineSize
/*  28:    */{
/*  29:    */  static int getCacheLineSize()
/*  30:    */  {
/*  31: 31 */    int THREADS = 2;
/*  32: 32 */    int REPEATS = 200000;
/*  33: 33 */    int LOCAL_REPEATS = 100000;
/*  34:    */    
/*  36: 36 */    int MAX_SIZE = LWJGLUtil.getPrivilegedInteger("org.lwjgl.util.mapped.CacheLineMaxSize", 1024).intValue() / 4;
/*  37:    */    
/*  38: 38 */    double TIME_THRESHOLD = 1.0D + LWJGLUtil.getPrivilegedInteger("org.lwjgl.util.mapped.CacheLineTimeThreshold", 50).intValue() / 100.0D;
/*  39:    */    
/*  40: 40 */    ExecutorService executorService = Executors.newFixedThreadPool(2);
/*  41: 41 */    ExecutorCompletionService<Long> completionService = new ExecutorCompletionService(executorService);
/*  42:    */    
/*  43:    */    try
/*  44:    */    {
/*  45: 45 */      IntBuffer memory = getMemory(MAX_SIZE);
/*  46:    */      
/*  49: 49 */      int WARMUP = 10;
/*  50: 50 */      for (int i = 0; i < 10; i++) {
/*  51: 51 */        doTest(2, 100000, 0, memory, completionService);
/*  52:    */      }
/*  53:    */      
/*  55: 55 */      long totalTime = 0L;
/*  56: 56 */      int count = 0;
/*  57: 57 */      int cacheLineSize = 64;
/*  58: 58 */      boolean found = false;
/*  59: 59 */      for (int i = MAX_SIZE; i >= 1; i >>= 1) {
/*  60: 60 */        long time = doTest(2, 100000, i, memory, completionService);
/*  61: 61 */        if (totalTime > 0L) {
/*  62: 62 */          long avgTime = totalTime / count;
/*  63: 63 */          if (time / avgTime > TIME_THRESHOLD) {
/*  64: 64 */            cacheLineSize = (i << 1) * 4;
/*  65: 65 */            found = true;
/*  66: 66 */            break;
/*  67:    */          }
/*  68:    */        }
/*  69: 69 */        totalTime += time;
/*  70: 70 */        count++;
/*  71:    */      }
/*  72:    */      
/*  73: 73 */      if (LWJGLUtil.DEBUG) {
/*  74: 74 */        if (found) {
/*  75: 75 */          LWJGLUtil.log("Cache line size detected: " + cacheLineSize + " bytes");
/*  76:    */        } else {
/*  77: 77 */          LWJGLUtil.log("Failed to detect cache line size, assuming " + cacheLineSize + " bytes");
/*  78:    */        }
/*  79:    */      }
/*  80: 80 */      return cacheLineSize;
/*  81:    */    } finally {
/*  82: 82 */      executorService.shutdown();
/*  83:    */    }
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static void main(String[] args) {
/*  87: 87 */    CacheUtil.getCacheLineSize();
/*  88:    */  }
/*  89:    */  
/*  90:    */  static long memoryLoop(int index, int repeats, IntBuffer memory, int padding) {
/*  91: 91 */    long address = MemoryUtil.getAddress(memory) + index * padding * 4;
/*  92:    */    
/*  93: 93 */    long time = System.nanoTime();
/*  94: 94 */    for (int i = 0; i < repeats; i++)
/*  95:    */    {
/*  96: 96 */      MappedHelper.ivput(MappedHelper.ivget(address) + 1, address);
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    return System.nanoTime() - time;
/* 100:    */  }
/* 101:    */  
/* 102:    */  private static IntBuffer getMemory(int START_SIZE) {
/* 103:103 */    int PAGE_SIZE = MappedObjectUnsafe.INSTANCE.pageSize();
/* 104:    */    
/* 105:105 */    ByteBuffer buffer = ByteBuffer.allocateDirect(START_SIZE * 4 + PAGE_SIZE).order(ByteOrder.nativeOrder());
/* 106:    */    
/* 108:108 */    if (MemoryUtil.getAddress(buffer) % PAGE_SIZE != 0L)
/* 109:    */    {
/* 110:110 */      buffer.position(PAGE_SIZE - (int)(MemoryUtil.getAddress(buffer) & PAGE_SIZE - 1));
/* 111:    */    }
/* 112:    */    
/* 113:113 */    return buffer.asIntBuffer();
/* 114:    */  }
/* 115:    */  
/* 116:    */  private static long doTest(int threads, int repeats, int padding, IntBuffer memory, ExecutorCompletionService<Long> completionService) {
/* 117:117 */    for (int i = 0; i < threads; i++)
/* 118:118 */      submitTest(completionService, i, repeats, memory, padding);
/* 119:119 */    return waitForResults(threads, completionService);
/* 120:    */  }
/* 121:    */  
/* 122:    */  private static void submitTest(ExecutorCompletionService<Long> completionService, int index, final int repeats, final IntBuffer memory, final int padding) {
/* 123:123 */    completionService.submit(new Callable() {
/* 124:    */      public Long call() throws Exception {
/* 125:125 */        return Long.valueOf(CacheLineSize.memoryLoop(this.val$index, repeats, memory, padding));
/* 126:    */      }
/* 127:    */    });
/* 128:    */  }
/* 129:    */  
/* 130:    */  private static long waitForResults(int count, ExecutorCompletionService<Long> completionService) {
/* 131:    */    try {
/* 132:132 */      long totalTime = 0L;
/* 133:133 */      for (int i = 0; i < count; i++)
/* 134:134 */        totalTime += ((Long)completionService.take().get()).longValue();
/* 135:135 */      return totalTime;
/* 136:    */    } catch (Exception e) {
/* 137:137 */      throw new RuntimeException(e);
/* 138:    */    }
/* 139:    */  }
/* 140:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.CacheLineSize
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */