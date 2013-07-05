/*    */ package paulscode.sound;
/*    */ 
/*    */ import javax.sound.sampled.AudioFormat;
/*    */ 
/*    */ public class SoundBuffer
/*    */ {
/*    */   public byte[] audioData;
/*    */   public AudioFormat audioFormat;
/*    */ 
/*    */   public SoundBuffer(byte[] audioData, AudioFormat audioFormat)
/*    */   {
/* 60 */     this.audioData = audioData;
/* 61 */     this.audioFormat = audioFormat;
/*    */   }
/*    */ 
/*    */   public void cleanup()
/*    */   {
/* 69 */     this.audioData = null;
/* 70 */     this.audioFormat = null;
/*    */   }
/*    */ 
/*    */   public void trimData(int maxLength)
/*    */   {
/* 81 */     if ((this.audioData == null) || (maxLength == 0)) {
/* 82 */       this.audioData = null;
/* 83 */     } else if (this.audioData.length > maxLength)
/*    */     {
/* 85 */       byte[] trimmedArray = new byte[maxLength];
/* 86 */       System.arraycopy(this.audioData, 0, trimmedArray, 0, maxLength);
/*    */ 
/* 88 */       this.audioData = trimmedArray;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundBuffer
 * JD-Core Version:    0.6.2
 */