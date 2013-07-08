/*   1:    */package paulscode.sound;
/*   2:    */
/*  17:    */public class SimpleThread
/*  18:    */  extends Thread
/*  19:    */{
/*  20:    */  private static final boolean GET = false;
/*  21:    */  
/*  35:    */  private static final boolean SET = true;
/*  36:    */  
/*  50:    */  private static final boolean XXX = false;
/*  51:    */  
/*  65: 65 */  private boolean alive = true;
/*  66:    */  
/*  70: 70 */  private boolean kill = false;
/*  71:    */  
/*  79:    */  protected void cleanup()
/*  80:    */  {
/*  81: 81 */    kill(true, true);
/*  82: 82 */    alive(true, false);
/*  83:    */  }
/*  84:    */  
/*  99:    */  public void run()
/* 100:    */  {
/* 101:101 */    cleanup();
/* 102:    */  }
/* 103:    */  
/* 108:    */  public void restart()
/* 109:    */  {
/* 110:110 */    new Thread()
/* 111:    */    {
/* 113:    */      public void run()
/* 114:    */      {
/* 115:115 */        SimpleThread.this.rerun();
/* 116:    */      }
/* 117:    */    }.start();
/* 118:    */  }
/* 119:    */  
/* 123:    */  private void rerun()
/* 124:    */  {
/* 125:125 */    kill(true, true);
/* 126:126 */    while (alive(false, false))
/* 127:    */    {
/* 128:128 */      snooze(100L);
/* 129:    */    }
/* 130:130 */    alive(true, true);
/* 131:131 */    kill(true, false);
/* 132:132 */    run();
/* 133:    */  }
/* 134:    */  
/* 140:    */  public boolean alive()
/* 141:    */  {
/* 142:142 */    return alive(false, false);
/* 143:    */  }
/* 144:    */  
/* 149:    */  public void kill()
/* 150:    */  {
/* 151:151 */    kill(true, true);
/* 152:    */  }
/* 153:    */  
/* 158:    */  protected boolean dying()
/* 159:    */  {
/* 160:160 */    return kill(false, false);
/* 161:    */  }
/* 162:    */  
/* 169:    */  private synchronized boolean alive(boolean action, boolean value)
/* 170:    */  {
/* 171:171 */    if (action == true)
/* 172:172 */      this.alive = value;
/* 173:173 */    return this.alive;
/* 174:    */  }
/* 175:    */  
/* 182:    */  private synchronized boolean kill(boolean action, boolean value)
/* 183:    */  {
/* 184:184 */    if (action == true)
/* 185:185 */      this.kill = value;
/* 186:186 */    return this.kill;
/* 187:    */  }
/* 188:    */  
/* 192:    */  protected void snooze(long milliseconds)
/* 193:    */  {
/* 194:    */    try
/* 195:    */    {
/* 196:196 */      Thread.sleep(milliseconds);
/* 197:    */    }
/* 198:    */    catch (InterruptedException e) {}
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SimpleThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */