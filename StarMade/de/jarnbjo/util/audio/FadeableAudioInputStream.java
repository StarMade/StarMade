package de.jarnbjo.util.audio;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;

public class FadeableAudioInputStream
  extends AudioInputStream
{
  private AudioInputStream stream;
  private boolean fading = false;
  private double phi = 0.0D;
  
  public FadeableAudioInputStream(AudioInputStream stream)
    throws IOException
  {
    super(stream, stream.getFormat(), -1L);
  }
  
  public void fadeOut()
  {
    this.fading = true;
    this.phi = 0.0D;
  }
  
  public int read(byte[] local_b)
    throws IOException
  {
    return read(local_b, 0, local_b.length);
  }
  
  public int read(byte[] local_b, int offset, int length)
    throws IOException
  {
    int read = super.read(local_b, offset, length);
    if (this.fading)
    {
      int local_j = 0;
      int local_l = 0;
      int local_r = 0;
      double gain = 0.0D;
      for (int local_i = offset; local_i < offset + read; local_i += 4)
      {
        local_j = local_i;
        local_l = local_b[(local_j++)] & 0xFF;
        local_l |= local_b[(local_j++)] << 8;
        local_r = local_b[(local_j++)] & 0xFF;
        local_r |= local_b[local_j] << 8;
        if (this.phi < 1.570796326794897D) {
          this.phi += 1.5E-005D;
        }
        gain = Math.cos(this.phi);
        local_l = (int)(local_l * gain);
        local_r = (int)(local_r * gain);
        local_j = local_i;
        local_b[(local_j++)] = ((byte)(local_l & 0xFF));
        local_b[(local_j++)] = ((byte)(local_l >> 8 & 0xFF));
        local_b[(local_j++)] = ((byte)(local_r & 0xFF));
        local_b[(local_j++)] = ((byte)(local_r >> 8 & 0xFF));
      }
    }
    return read;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.util.audio.FadeableAudioInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */