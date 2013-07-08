/*   1:    */package net.rudp.impl;
/*   2:    */
/*   6:    */public class Timer
/*   7:    */  extends Thread
/*   8:    */{
/*   9:    */  private Runnable _task;
/*  10:    */  
/*  13:    */  private long _delay;
/*  14:    */  
/*  17:    */  private long _period;
/*  18:    */  
/*  21:    */  private boolean _canceled;
/*  22:    */  
/*  25:    */  private boolean _scheduled;
/*  26:    */  
/*  28:    */  private boolean _reset;
/*  29:    */  
/*  31:    */  private boolean _stopped;
/*  32:    */  
/*  35:    */  public Timer(String paramString, Runnable paramRunnable)
/*  36:    */  {
/*  37: 37 */    super(paramString);
/*  38: 38 */    setDaemon(true);
/*  39:    */    
/*  40: 40 */    this._task = paramRunnable;
/*  41: 41 */    this._delay = 0L;
/*  42: 42 */    this._period = 0L;
/*  43: 43 */    start();
/*  44:    */  }
/*  45:    */  
/*  46:    */  public void run()
/*  47:    */  {
/*  48: 48 */    while (!this._stopped)
/*  49:    */    {
/*  50: 50 */      synchronized (this)
/*  51:    */      {
/*  52: 52 */        while ((!this._scheduled) && (!this._stopped)) {
/*  53:    */          try {
/*  54: 54 */            wait();
/*  55:    */          }
/*  56:    */          catch (InterruptedException localInterruptedException1) {
/*  57: 57 */            localInterruptedException1.printStackTrace();
/*  58:    */          }
/*  59:    */        }
/*  60:    */        
/*  61: 61 */        if (this._stopped) {
/*  62: 62 */          break;
/*  63:    */        }
/*  64:    */      }
/*  65:    */      
/*  66: 66 */      synchronized (this._lock)
/*  67:    */      {
/*  68: 68 */        this._reset = false;
/*  69: 69 */        this._canceled = false;
/*  70:    */        
/*  71: 71 */        if (this._delay > 0L) {
/*  72:    */          try {
/*  73: 73 */            this._lock.wait(this._delay);
/*  74:    */          }
/*  75:    */          catch (InterruptedException localInterruptedException2) {
/*  76: 76 */            localInterruptedException2.printStackTrace();
/*  77:    */          }
/*  78:    */        }
/*  79:    */        
/*  80: 80 */        if (this._canceled) {
/*  81: 81 */          continue;
/*  82:    */        }
/*  83:    */      }
/*  84:    */      
/*  85: 85 */      if (!this._reset) {
/*  86: 86 */        this._task.run();
/*  87:    */      }
/*  88:    */      
/*  89: 89 */      if (this._period > 0L)
/*  90:    */      {
/*  91:    */        for (;;)
/*  92:    */        {
/*  93: 93 */          synchronized (this._lock)
/*  94:    */          {
/*  95: 95 */            this._reset = false;
/*  96:    */            try
/*  97:    */            {
/*  98: 98 */              this._lock.wait(this._period);
/*  99:    */            }
/* 100:    */            catch (InterruptedException localInterruptedException3) {
/* 101:101 */              localInterruptedException3.printStackTrace();
/* 102:    */            }
/* 103:    */            
/* 104:104 */            if (this._canceled) {
/* 105:    */              break;
/* 106:    */            }
/* 107:    */            
/* 108:108 */            if (this._reset) {
/* 109:109 */              continue;
/* 110:    */            }
/* 111:    */          }
/* 112:    */          
/* 113:113 */          this._task.run();
/* 114:    */        }
/* 115:    */      }
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 120:    */  public synchronized void schedule(long paramLong)
/* 121:    */  {
/* 122:122 */    schedule(paramLong, 0L);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public synchronized void schedule(long paramLong1, long paramLong2)
/* 126:    */  {
/* 127:127 */    this._delay = paramLong1;
/* 128:128 */    this._period = paramLong2;
/* 129:    */    
/* 131:131 */    if (this._scheduled) {
/* 132:132 */      throw new IllegalStateException("already scheduled");
/* 133:    */    }
/* 134:    */    
/* 135:135 */    this._scheduled = true;
/* 136:136 */    notify();
/* 137:    */    
/* 138:138 */    synchronized (this._lock) {
/* 139:139 */      this._lock.notify();
/* 140:    */    }
/* 141:    */  }
/* 142:    */  
/* 143:    */  public synchronized boolean isScheduled()
/* 144:    */  {
/* 145:145 */    return this._scheduled;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public synchronized boolean isIdle()
/* 149:    */  {
/* 150:150 */    return !isScheduled();
/* 151:    */  }
/* 152:    */  
/* 153:    */  public synchronized void reset()
/* 154:    */  {
/* 155:155 */    synchronized (this._lock) {
/* 156:156 */      this._reset = true;
/* 157:157 */      this._lock.notify();
/* 158:    */    }
/* 159:    */  }
/* 160:    */  
/* 161:    */  public synchronized void cancel()
/* 162:    */  {
/* 163:163 */    this._scheduled = false;
/* 164:164 */    synchronized (this._lock) {
/* 165:165 */      this._canceled = true;
/* 166:166 */      this._lock.notify();
/* 167:    */    }
/* 168:    */  }
/* 169:    */  
/* 170:    */  public synchronized void destroy()
/* 171:    */  {
/* 172:172 */    cancel();
/* 173:173 */    this._stopped = true;
/* 174:174 */    notify();
/* 175:    */  }
/* 176:    */  
/* 184:184 */  private Object _lock = new Object();
/* 185:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.impl.Timer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */