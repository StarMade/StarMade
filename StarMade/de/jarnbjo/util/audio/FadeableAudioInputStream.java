/*    */ package de.jarnbjo.util.audio;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.sound.sampled.AudioInputStream;
/*    */ 
/*    */ public class FadeableAudioInputStream extends AudioInputStream
/*    */ {
/*    */   private AudioInputStream stream;
/*  9 */   private boolean fading = false;
/* 10 */   private double phi = 0.0D;
/*    */ 
/*    */   public FadeableAudioInputStream(AudioInputStream stream) throws IOException {
/* 13 */     super(stream, stream.getFormat(), -1L);
/*    */   }
/*    */ 
/*    */   public void fadeOut() {
/* 17 */     this.fading = true;
/* 18 */     this.phi = 0.0D;
/*    */   }
/*    */ 
/*    */   public int read(byte[] b) throws IOException {
/* 22 */     return read(b, 0, b.length);
/*    */   }
/*    */ 
/*    */   public int read(byte[] b, int offset, int length) throws IOException {
/* 26 */     int read = super.read(b, offset, length);
/*    */ 
/* 30 */     if (this.fading) {
/* 31 */       int j = 0; int l = 0; int r = 0;
/* 32 */       double gain = 0.0D;
/*    */ 
/* 34 */       for (int i = offset; i < offset + read; i += 4) {
/* 35 */         j = i;
/* 36 */         l = b[(j++)] & 0xFF;
/* 37 */         l |= b[(j++)] << 8;
/* 38 */         r = b[(j++)] & 0xFF;
/* 39 */         r |= b[j] << 8;
/*    */ 
/* 41 */         if (this.phi < 1.570796326794897D) {
/* 42 */           this.phi += 1.5E-005D;
/*    */         }
/*    */ 
/* 45 */         gain = Math.cos(this.phi);
/*    */ 
/* 48 */         l = (int)(l * gain);
/* 49 */         r = (int)(r * gain);
/*    */ 
/* 51 */         j = i;
/* 52 */         b[(j++)] = ((byte)(l & 0xFF));
/* 53 */         b[(j++)] = ((byte)(l >> 8 & 0xFF));
/* 54 */         b[(j++)] = ((byte)(r & 0xFF));
/* 55 */         b[(j++)] = ((byte)(r >> 8 & 0xFF));
/*    */       }
/*    */     }
/*    */ 
/* 59 */     return read;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.util.audio.FadeableAudioInputStream
 * JD-Core Version:    0.6.2
 */