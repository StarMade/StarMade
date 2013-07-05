/*     */ package paulscode.sound;
/*     */ 
/*     */ public class SimpleThread extends Thread
/*     */ {
/*     */   private static final boolean GET = false;
/*     */   private static final boolean SET = true;
/*     */   private static final boolean XXX = false;
/*  65 */   private boolean alive = true;
/*     */ 
/*  70 */   private boolean kill = false;
/*     */ 
/*     */   protected void cleanup()
/*     */   {
/*  81 */     kill(true, true);
/*  82 */     alive(true, false);
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 101 */     cleanup();
/*     */   }
/*     */ 
/*     */   public void restart()
/*     */   {
/* 110 */     new Thread()
/*     */     {
/*     */       public void run()
/*     */       {
/* 115 */         SimpleThread.this.rerun();
/*     */       }
/*     */     }
/* 110 */     .start();
/*     */   }
/*     */ 
/*     */   private void rerun()
/*     */   {
/* 125 */     kill(true, true);
/* 126 */     while (alive(false, false))
/*     */     {
/* 128 */       snooze(100L);
/*     */     }
/* 130 */     alive(true, true);
/* 131 */     kill(true, false);
/* 132 */     run();
/*     */   }
/*     */ 
/*     */   public boolean alive()
/*     */   {
/* 142 */     return alive(false, false);
/*     */   }
/*     */ 
/*     */   public void kill()
/*     */   {
/* 151 */     kill(true, true);
/*     */   }
/*     */ 
/*     */   protected boolean dying()
/*     */   {
/* 160 */     return kill(false, false);
/*     */   }
/*     */ 
/*     */   private synchronized boolean alive(boolean action, boolean value)
/*     */   {
/* 171 */     if (action == true)
/* 172 */       this.alive = value;
/* 173 */     return this.alive;
/*     */   }
/*     */ 
/*     */   private synchronized boolean kill(boolean action, boolean value)
/*     */   {
/* 184 */     if (action == true)
/* 185 */       this.kill = value;
/* 186 */     return this.kill;
/*     */   }
/*     */ 
/*     */   protected void snooze(long milliseconds)
/*     */   {
/*     */     try
/*     */     {
/* 196 */       Thread.sleep(milliseconds);
/*     */     }
/*     */     catch (InterruptedException e)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SimpleThread
 * JD-Core Version:    0.6.2
 */