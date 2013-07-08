package org.newdawn.slick.openal;

public abstract interface Audio
{
  public abstract void stop();
  
  public abstract int getBufferID();
  
  public abstract boolean isPlaying();
  
  public abstract int playAsSoundEffect(float paramFloat1, float paramFloat2, boolean paramBoolean);
  
  public abstract int playAsSoundEffect(float paramFloat1, float paramFloat2, boolean paramBoolean, float paramFloat3, float paramFloat4, float paramFloat5);
  
  public abstract int playAsMusic(float paramFloat1, float paramFloat2, boolean paramBoolean);
  
  public abstract boolean setPosition(float paramFloat);
  
  public abstract float getPosition();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.Audio
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */