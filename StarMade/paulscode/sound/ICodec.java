package paulscode.sound;

import java.net.URL;
import javax.sound.sampled.AudioFormat;

public abstract interface ICodec
{
  public abstract void reverseByteOrder(boolean paramBoolean);
  
  public abstract boolean initialize(URL paramURL);
  
  public abstract boolean initialized();
  
  public abstract SoundBuffer read();
  
  public abstract SoundBuffer readAll();
  
  public abstract boolean endOfStream();
  
  public abstract void cleanup();
  
  public abstract AudioFormat getAudioFormat();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.ICodec
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */