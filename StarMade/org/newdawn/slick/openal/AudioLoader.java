/*    */ package org.newdawn.slick.openal;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class AudioLoader
/*    */ {
/*    */   private static final String AIF = "AIF";
/*    */   private static final String WAV = "WAV";
/*    */   private static final String OGG = "OGG";
/*    */   private static final String MOD = "MOD";
/*    */   private static final String XM = "XM";
/* 26 */   private static boolean inited = false;
/*    */ 
/*    */   private static void init()
/*    */   {
/* 32 */     if (!inited) {
/* 33 */       SoundStore.get().init();
/* 34 */       inited = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   public static Audio getAudio(String format, InputStream in)
/*    */     throws IOException
/*    */   {
/* 48 */     init();
/*    */ 
/* 50 */     if (format.equals("AIF")) {
/* 51 */       return SoundStore.get().getAIF(in);
/*    */     }
/* 53 */     if (format.equals("WAV")) {
/* 54 */       return SoundStore.get().getWAV(in);
/*    */     }
/* 56 */     if (format.equals("OGG")) {
/* 57 */       return SoundStore.get().getOgg(in);
/*    */     }
/*    */ 
/* 60 */     throw new IOException("Unsupported format for non-streaming Audio: " + format);
/*    */   }
/*    */ 
/*    */   public static Audio getStreamingAudio(String format, URL url)
/*    */     throws IOException
/*    */   {
/* 73 */     init();
/*    */ 
/* 75 */     if (format.equals("OGG")) {
/* 76 */       return SoundStore.get().getOggStream(url);
/*    */     }
/* 78 */     if (format.equals("MOD")) {
/* 79 */       return SoundStore.get().getMOD(url.openStream());
/*    */     }
/* 81 */     if (format.equals("XM")) {
/* 82 */       return SoundStore.get().getMOD(url.openStream());
/*    */     }
/*    */ 
/* 85 */     throw new IOException("Unsupported format for streaming Audio: " + format);
/*    */   }
/*    */ 
/*    */   public static void update()
/*    */   {
/* 92 */     init();
/*    */ 
/* 94 */     SoundStore.get().poll(0);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.AudioLoader
 * JD-Core Version:    0.6.2
 */