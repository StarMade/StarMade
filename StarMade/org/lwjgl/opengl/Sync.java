/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import org.lwjgl.Sys;
/*   4:    */
/*  46:    */class Sync
/*  47:    */{
/*  48:    */  private static final long NANOS_IN_SECOND = 1000000000L;
/*  49: 49 */  private static long nextFrame = 0L;
/*  50:    */  
/*  52: 52 */  private static boolean initialised = false;
/*  53:    */  
/*  55: 55 */  private static RunningAvg sleepDurations = new RunningAvg(10);
/*  56: 56 */  private static RunningAvg yieldDurations = new RunningAvg(10);
/*  57:    */  
/*  64:    */  public static void sync(int fps)
/*  65:    */  {
/*  66: 66 */    if (fps <= 0) return;
/*  67: 67 */    if (!initialised) initialise();
/*  68:    */    try
/*  69:    */    {
/*  70:    */      long t1;
/*  71: 71 */      for (long t0 = getTime(); nextFrame - t0 > sleepDurations.avg(); t0 = t1) {
/*  72: 72 */        Thread.sleep(1L);
/*  73: 73 */        sleepDurations.add((t1 = getTime()) - t0);
/*  74:    */      }
/*  75:    */      
/*  77: 77 */      sleepDurations.dampenForLowResTicker();
/*  78:    */      
/*  79:    */      long t1;
/*  80: 80 */      for (long t0 = getTime(); nextFrame - t0 > yieldDurations.avg(); t0 = t1) {
/*  81: 81 */        Thread.yield();
/*  82: 82 */        yieldDurations.add((t1 = getTime()) - t0);
/*  83:    */      }
/*  84:    */    }
/*  85:    */    catch (InterruptedException e) {}
/*  86:    */    
/*  89: 89 */    nextFrame = Math.max(nextFrame + 1000000000L / fps, getTime());
/*  90:    */  }
/*  91:    */  
/*  97:    */  private static void initialise()
/*  98:    */  {
/*  99: 99 */    initialised = true;
/* 100:    */    
/* 101:101 */    sleepDurations.init(1000000L);
/* 102:102 */    yieldDurations.init((int)(-(getTime() - getTime()) * 1.333D));
/* 103:    */    
/* 104:104 */    nextFrame = getTime();
/* 105:    */    
/* 106:106 */    String osName = System.getProperty("os.name");
/* 107:    */    
/* 108:108 */    if (osName.startsWith("Win"))
/* 109:    */    {
/* 113:113 */      Thread timerAccuracyThread = (new Runnable() {
/* 114:    */        public void run() {
/* 115:    */          try {
/* 116:116 */            Thread.sleep(9223372036854775807L);
/* 117:    */          }
/* 118:    */          catch (Exception e) {}
/* 119:    */        }
/* 120:120 */      });
/* 121:121 */      timerAccuracyThread.setName("LWJGL Timer");
/* 122:122 */      timerAccuracyThread.setDaemon(true);
/* 123:123 */      timerAccuracyThread.start();
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 131:    */  private static long getTime()
/* 132:    */  {
/* 133:133 */    return Sys.getTime() * 1000000000L / Sys.getTimerResolution();
/* 134:    */  }
/* 135:    */  
/* 136:    */  private static class RunningAvg
/* 137:    */  {
/* 138:    */    private final long[] slots;
/* 139:    */    private int offset;
/* 140:    */    private static final long DAMPEN_THRESHOLD = 10000000L;
/* 141:    */    private static final float DAMPEN_FACTOR = 0.9F;
/* 142:    */    
/* 143:    */    public RunningAvg(int slotCount) {
/* 144:144 */      this.slots = new long[slotCount];
/* 145:145 */      this.offset = 0;
/* 146:    */    }
/* 147:    */    
/* 148:    */    public void init(long value) {
/* 149:149 */      while (this.offset < this.slots.length) {
/* 150:150 */        this.slots[(this.offset++)] = value;
/* 151:    */      }
/* 152:    */    }
/* 153:    */    
/* 154:    */    public void add(long value) {
/* 155:155 */      this.slots[(this.offset++ % this.slots.length)] = value;
/* 156:156 */      this.offset %= this.slots.length;
/* 157:    */    }
/* 158:    */    
/* 159:    */    public long avg() {
/* 160:160 */      long sum = 0L;
/* 161:161 */      for (int i = 0; i < this.slots.length; i++) {
/* 162:162 */        sum += this.slots[i];
/* 163:    */      }
/* 164:164 */      return sum / this.slots.length;
/* 165:    */    }
/* 166:    */    
/* 167:    */    public void dampenForLowResTicker() {
/* 168:168 */      if (avg() > 10000000L) {
/* 169:169 */        for (int i = 0; i < this.slots.length; i++) {
/* 170:170 */          int tmp27_26 = i; long[] tmp27_23 = this.slots;tmp27_23[tmp27_26] = (((float)tmp27_23[tmp27_26] * 0.9F));
/* 171:    */        }
/* 172:    */      }
/* 173:    */    }
/* 174:    */  }
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.Sync
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */