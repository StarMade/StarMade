/*     */ package paulscode.sound;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ 
/*     */ public class Channel
/*     */ {
/*  51 */   protected Class libraryType = Library.class;
/*     */   public int channelType;
/*     */   private SoundSystemLogger logger;
/*  68 */   public Source attachedSource = null;
/*     */ 
/*  73 */   public int buffersUnqueued = 0;
/*     */ 
/*     */   public Channel(int type)
/*     */   {
/*  84 */     this.logger = SoundSystemConfig.getLogger();
/*     */ 
/*  86 */     this.channelType = type;
/*     */   }
/*     */ 
/*     */   public void cleanup()
/*     */   {
/*  94 */     this.logger = null;
/*     */   }
/*     */ 
/*     */   public boolean preLoadBuffers(LinkedList<byte[]> bufferList)
/*     */   {
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean queueBuffer(byte[] buffer)
/*     */   {
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   public int feedRawAudioData(byte[] buffer)
/*     */   {
/* 124 */     return 1;
/*     */   }
/*     */ 
/*     */   public int buffersProcessed()
/*     */   {
/* 133 */     return 0;
/*     */   }
/*     */ 
/*     */   public float millisecondsPlayed()
/*     */   {
/* 142 */     return -1.0F;
/*     */   }
/*     */ 
/*     */   public boolean processBuffer()
/*     */   {
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */   public void setAudioFormat(AudioFormat audioFormat)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void flush()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void play()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void pause()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void rewind()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean playing()
/*     */   {
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */   public String getClassName()
/*     */   {
/* 216 */     String libTitle = SoundSystemConfig.getLibraryTitle(this.libraryType);
/*     */ 
/* 218 */     if (libTitle.equals("No Sound")) {
/* 219 */       return "Channel";
/*     */     }
/* 221 */     return "Channel" + libTitle;
/*     */   }
/*     */ 
/*     */   protected void message(String message)
/*     */   {
/* 230 */     this.logger.message(message, 0);
/*     */   }
/*     */ 
/*     */   protected void importantMessage(String message)
/*     */   {
/* 239 */     this.logger.importantMessage(message, 0);
/*     */   }
/*     */ 
/*     */   protected boolean errorCheck(boolean error, String message)
/*     */   {
/* 250 */     return this.logger.errorCheck(error, getClassName(), message, 0);
/*     */   }
/*     */ 
/*     */   protected void errorMessage(String message)
/*     */   {
/* 259 */     this.logger.errorMessage(getClassName(), message, 0);
/*     */   }
/*     */ 
/*     */   protected void printStackTrace(Exception e)
/*     */   {
/* 268 */     this.logger.printStackTrace(e, 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.Channel
 * JD-Core Version:    0.6.2
 */