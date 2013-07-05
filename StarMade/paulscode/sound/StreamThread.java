/*     */ package paulscode.sound;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ public class StreamThread extends SimpleThread
/*     */ {
/*     */   private SoundSystemLogger logger;
/*     */   private List<Source> streamingSources;
/*  61 */   private final Object listLock = new Object();
/*     */ 
/*     */   public StreamThread()
/*     */   {
/*  70 */     this.logger = SoundSystemConfig.getLogger();
/*     */ 
/*  72 */     this.streamingSources = new LinkedList();
/*     */   }
/*     */ 
/*     */   protected void cleanup()
/*     */   {
/*  83 */     kill();
/*  84 */     super.cleanup();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*  98 */     snooze(3600000L);
/*     */ 
/* 100 */     while (!dying())
/*     */     {
/* 102 */       while ((!dying()) && (!this.streamingSources.isEmpty()))
/*     */       {
/* 105 */         synchronized (this.listLock)
/*     */         {
/* 107 */           ListIterator iter = this.streamingSources.listIterator();
/* 108 */           while ((!dying()) && (iter.hasNext()))
/*     */           {
/* 110 */             Source src = (Source)iter.next();
/* 111 */             if (src == null)
/*     */             {
/* 113 */               iter.remove();
/*     */             }
/* 115 */             else if (src.stopped())
/*     */             {
/* 117 */               if (!src.rawDataStream)
/* 118 */                 iter.remove();
/*     */             }
/* 120 */             else if (!src.active())
/*     */             {
/* 122 */               if ((src.toLoop) || (src.rawDataStream))
/* 123 */                 src.toPlay = true;
/* 124 */               iter.remove();
/*     */             }
/* 126 */             else if (!src.paused())
/*     */             {
/* 128 */               src.checkFadeOut();
/* 129 */               if ((!src.stream()) && (!src.rawDataStream) && (
/* 131 */                 (src.channel == null) || (!src.channel.processBuffer())))
/*     */               {
/* 134 */                 if (src.nextCodec == null)
/*     */                 {
/* 136 */                   src.readBuffersFromNextSoundInSequence();
/*     */                 }
/*     */ 
/* 145 */                 if (src.toLoop)
/*     */                 {
/* 148 */                   if (!src.playing())
/*     */                   {
/* 151 */                     SoundSystemConfig.notifyEOS(src.sourcename, src.getSoundSequenceQueueSize());
/*     */ 
/* 157 */                     if (src.checkFadeOut())
/*     */                     {
/* 162 */                       src.preLoad = true;
/*     */                     }
/*     */                     else
/*     */                     {
/* 170 */                       src.incrementSoundSequence();
/* 171 */                       src.preLoad = true;
/*     */                     }
/*     */ 
/*     */                   }
/*     */ 
/*     */                 }
/* 178 */                 else if (!src.playing())
/*     */                 {
/* 181 */                   SoundSystemConfig.notifyEOS(src.sourcename, src.getSoundSequenceQueueSize());
/*     */ 
/* 187 */                   if (!src.checkFadeOut())
/*     */                   {
/* 192 */                     if (src.incrementSoundSequence())
/*     */                     {
/* 194 */                       src.preLoad = true;
/*     */                     }
/* 196 */                     else iter.remove();
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 205 */         if ((!dying()) && (!this.streamingSources.isEmpty()))
/* 206 */           snooze(20L);
/*     */       }
/* 208 */       if ((!dying()) && (this.streamingSources.isEmpty())) {
/* 209 */         snooze(3600000L);
/*     */       }
/*     */     }
/* 212 */     cleanup();
/*     */   }
/*     */ 
/*     */   public void watch(Source source)
/*     */   {
/* 224 */     if (source == null) {
/* 225 */       return;
/*     */     }
/*     */ 
/* 228 */     if (this.streamingSources.contains(source)) {
/* 229 */       return;
/*     */     }
/*     */ 
/* 235 */     synchronized (this.listLock)
/*     */     {
/* 240 */       ListIterator iter = this.streamingSources.listIterator();
/* 241 */       while (iter.hasNext())
/*     */       {
/* 243 */         Source src = (Source)iter.next();
/* 244 */         if (src == null)
/*     */         {
/* 246 */           iter.remove();
/*     */         }
/* 248 */         else if (source.channel == src.channel)
/*     */         {
/* 250 */           src.stop();
/* 251 */           iter.remove();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 256 */       this.streamingSources.add(source);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void message(String message)
/*     */   {
/* 266 */     this.logger.message(message, 0);
/*     */   }
/*     */ 
/*     */   private void importantMessage(String message)
/*     */   {
/* 275 */     this.logger.importantMessage(message, 0);
/*     */   }
/*     */ 
/*     */   private boolean errorCheck(boolean error, String message)
/*     */   {
/* 286 */     return this.logger.errorCheck(error, "StreamThread", message, 0);
/*     */   }
/*     */ 
/*     */   private void errorMessage(String message)
/*     */   {
/* 295 */     this.logger.errorMessage("StreamThread", message, 0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.StreamThread
 * JD-Core Version:    0.6.2
 */