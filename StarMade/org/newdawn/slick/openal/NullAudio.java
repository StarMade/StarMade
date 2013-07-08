package org.newdawn.slick.openal;

public class NullAudio
  implements Audio
{
  public int getBufferID()
  {
    return 0;
  }
  
  public float getPosition()
  {
    return 0.0F;
  }
  
  public boolean isPlaying()
  {
    return false;
  }
  
  public int playAsMusic(float pitch, float gain, boolean loop)
  {
    return 0;
  }
  
  public int playAsSoundEffect(float pitch, float gain, boolean loop)
  {
    return 0;
  }
  
  public int playAsSoundEffect(float pitch, float gain, boolean loop, float local_x, float local_y, float local_z)
  {
    return 0;
  }
  
  public boolean setPosition(float position)
  {
    return false;
  }
  
  public void stop() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.openal.NullAudio
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */