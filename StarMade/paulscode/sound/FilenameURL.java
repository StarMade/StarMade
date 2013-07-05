/*     */ package paulscode.sound;
/*     */ 
/*     */ import java.net.URL;
/*     */ 
/*     */ public class FilenameURL
/*     */ {
/*     */   private SoundSystemLogger logger;
/*  52 */   private String filename = null;
/*     */ 
/*  57 */   private URL url = null;
/*     */ 
/*     */   public FilenameURL(URL url, String identifier)
/*     */   {
/*  69 */     this.logger = SoundSystemConfig.getLogger();
/*     */ 
/*  71 */     this.filename = identifier;
/*  72 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public FilenameURL(String filename)
/*     */   {
/*  85 */     this.logger = SoundSystemConfig.getLogger();
/*     */ 
/*  87 */     this.filename = filename;
/*  88 */     this.url = null;
/*     */   }
/*     */ 
/*     */   public String getFilename()
/*     */   {
/*  97 */     return this.filename;
/*     */   }
/*     */ 
/*     */   public URL getURL()
/*     */   {
/* 108 */     if (this.url == null)
/*     */     {
/* 111 */       if (this.filename.matches(SoundSystemConfig.PREFIX_URL))
/*     */       {
/*     */         try
/*     */         {
/* 116 */           this.url = new URL(this.filename);
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/* 120 */           errorMessage("Unable to access online URL in method 'getURL'");
/*     */ 
/* 122 */           printStackTrace(e);
/* 123 */           return null;
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 129 */         this.url = getClass().getClassLoader().getResource(SoundSystemConfig.getSoundFilesPackage() + this.filename);
/*     */       }
/*     */     }
/*     */ 
/* 133 */     return this.url;
/*     */   }
/*     */ 
/*     */   private void errorMessage(String message)
/*     */   {
/* 142 */     this.logger.errorMessage("MidiChannel", message, 0);
/*     */   }
/*     */ 
/*     */   private void printStackTrace(Exception e)
/*     */   {
/* 151 */     this.logger.printStackTrace(e, 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.FilenameURL
 * JD-Core Version:    0.6.2
 */