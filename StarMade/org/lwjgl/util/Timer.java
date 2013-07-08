/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import org.lwjgl.Sys;
/*   4:    */
/*  48:    */public class Timer
/*  49:    */{
/*  50: 50 */  private static long resolution = ;
/*  51:    */  
/*  53:    */  private static final int QUERY_INTERVAL = 50;
/*  54:    */  
/*  56:    */  private static int queryCount;
/*  57:    */  
/*  58:    */  private static long currentTime;
/*  59:    */  
/*  60:    */  private long startTime;
/*  61:    */  
/*  62:    */  private long lastTime;
/*  63:    */  
/*  64:    */  private boolean paused;
/*  65:    */  
/*  67:    */  static
/*  68:    */  {
/*  69: 69 */    tick();
/*  70:    */  }
/*  71:    */  
/*  74:    */  public Timer()
/*  75:    */  {
/*  76: 76 */    reset();
/*  77: 77 */    resume();
/*  78:    */  }
/*  79:    */  
/*  82:    */  public float getTime()
/*  83:    */  {
/*  84: 84 */    if (!this.paused) {
/*  85: 85 */      this.lastTime = (currentTime - this.startTime);
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    return (float)(this.lastTime / resolution);
/*  89:    */  }
/*  90:    */  
/*  92:    */  public boolean isPaused()
/*  93:    */  {
/*  94: 94 */    return this.paused;
/*  95:    */  }
/*  96:    */  
/* 102:    */  public void pause()
/* 103:    */  {
/* 104:104 */    this.paused = true;
/* 105:    */  }
/* 106:    */  
/* 110:    */  public void reset()
/* 111:    */  {
/* 112:112 */    set(0.0F);
/* 113:    */  }
/* 114:    */  
/* 118:    */  public void resume()
/* 119:    */  {
/* 120:120 */    this.paused = false;
/* 121:121 */    this.startTime = (currentTime - this.lastTime);
/* 122:    */  }
/* 123:    */  
/* 127:    */  public void set(float newTime)
/* 128:    */  {
/* 129:129 */    long newTimeInTicks = (newTime * resolution);
/* 130:130 */    this.startTime = (currentTime - newTimeInTicks);
/* 131:131 */    this.lastTime = newTimeInTicks;
/* 132:    */  }
/* 133:    */  
/* 138:    */  public static void tick()
/* 139:    */  {
/* 140:140 */    currentTime = Sys.getTime();
/* 141:    */    
/* 143:143 */    queryCount += 1;
/* 144:144 */    if (queryCount > 50) {
/* 145:145 */      queryCount = 0;
/* 146:146 */      resolution = Sys.getTimerResolution();
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 152:    */  public String toString()
/* 153:    */  {
/* 154:154 */    return "Timer[Time=" + getTime() + ", Paused=" + this.paused + "]";
/* 155:    */  }
/* 156:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Timer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */