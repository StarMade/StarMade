/*  1:   */package org.newdawn.slick.openal;
/*  2:   */
/*  9:   */public class NullAudio
/* 10:   */  implements Audio
/* 11:   */{
/* 12:   */  public int getBufferID()
/* 13:   */  {
/* 14:14 */    return 0;
/* 15:   */  }
/* 16:   */  
/* 19:   */  public float getPosition()
/* 20:   */  {
/* 21:21 */    return 0.0F;
/* 22:   */  }
/* 23:   */  
/* 26:   */  public boolean isPlaying()
/* 27:   */  {
/* 28:28 */    return false;
/* 29:   */  }
/* 30:   */  
/* 33:   */  public int playAsMusic(float pitch, float gain, boolean loop)
/* 34:   */  {
/* 35:35 */    return 0;
/* 36:   */  }
/* 37:   */  
/* 40:   */  public int playAsSoundEffect(float pitch, float gain, boolean loop)
/* 41:   */  {
/* 42:42 */    return 0;
/* 43:   */  }
/* 44:   */  
/* 48:   */  public int playAsSoundEffect(float pitch, float gain, boolean loop, float x, float y, float z)
/* 49:   */  {
/* 50:50 */    return 0;
/* 51:   */  }
/* 52:   */  
/* 55:   */  public boolean setPosition(float position)
/* 56:   */  {
/* 57:57 */    return false;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public void stop() {}
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.NullAudio
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */