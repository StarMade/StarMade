/*     */ package paulscode.sound;
/*     */ 
/*     */ public class CommandThread extends SimpleThread
/*     */ {
/*     */   protected SoundSystemLogger logger;
/*     */   private SoundSystem soundSystem;
/*  65 */   protected String className = "CommandThread";
/*     */ 
/*     */   public CommandThread(SoundSystem s)
/*     */   {
/*  74 */     this.logger = SoundSystemConfig.getLogger();
/*     */ 
/*  76 */     this.soundSystem = s;
/*     */   }
/*     */ 
/*     */   protected void cleanup()
/*     */   {
/*  86 */     kill();
/*     */ 
/*  88 */     this.logger = null;
/*  89 */     this.soundSystem = null;
/*     */ 
/*  91 */     super.cleanup();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 102 */     long previousTime = System.currentTimeMillis();
/* 103 */     long currentTime = previousTime;
/*     */ 
/* 105 */     if (this.soundSystem == null)
/*     */     {
/* 107 */       errorMessage("SoundSystem was null in method run().", 0);
/* 108 */       cleanup();
/* 109 */       return;
/*     */     }
/*     */ 
/* 113 */     snooze(3600000L);
/*     */ 
/* 115 */     while (!dying())
/*     */     {
/* 118 */       this.soundSystem.ManageSources();
/*     */ 
/* 121 */       this.soundSystem.CommandQueue(null);
/*     */ 
/* 124 */       currentTime = System.currentTimeMillis();
/* 125 */       if ((!dying()) && (currentTime - previousTime > 10000L))
/*     */       {
/* 127 */         previousTime = currentTime;
/* 128 */         this.soundSystem.removeTemporarySources();
/*     */       }
/*     */ 
/* 132 */       if (!dying()) {
/* 133 */         snooze(3600000L);
/*     */       }
/*     */     }
/* 136 */     cleanup();
/*     */   }
/*     */ 
/*     */   protected void message(String message, int indent)
/*     */   {
/* 145 */     this.logger.message(message, indent);
/*     */   }
/*     */ 
/*     */   protected void importantMessage(String message, int indent)
/*     */   {
/* 154 */     this.logger.importantMessage(message, indent);
/*     */   }
/*     */ 
/*     */   protected boolean errorCheck(boolean error, String message)
/*     */   {
/* 165 */     return this.logger.errorCheck(error, this.className, message, 0);
/*     */   }
/*     */ 
/*     */   protected void errorMessage(String message, int indent)
/*     */   {
/* 174 */     this.logger.errorMessage(this.className, message, indent);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.CommandThread
 * JD-Core Version:    0.6.2
 */