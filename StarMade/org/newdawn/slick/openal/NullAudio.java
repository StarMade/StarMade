/*    */ package org.newdawn.slick.openal;
/*    */ 
/*    */ public class NullAudio
/*    */   implements Audio
/*    */ {
/*    */   public int getBufferID()
/*    */   {
/* 14 */     return 0;
/*    */   }
/*    */ 
/*    */   public float getPosition()
/*    */   {
/* 21 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   public boolean isPlaying()
/*    */   {
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   public int playAsMusic(float pitch, float gain, boolean loop)
/*    */   {
/* 35 */     return 0;
/*    */   }
/*    */ 
/*    */   public int playAsSoundEffect(float pitch, float gain, boolean loop)
/*    */   {
/* 42 */     return 0;
/*    */   }
/*    */ 
/*    */   public int playAsSoundEffect(float pitch, float gain, boolean loop, float x, float y, float z)
/*    */   {
/* 50 */     return 0;
/*    */   }
/*    */ 
/*    */   public boolean setPosition(float position)
/*    */   {
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */   public void stop()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.NullAudio
 * JD-Core Version:    0.6.2
 */