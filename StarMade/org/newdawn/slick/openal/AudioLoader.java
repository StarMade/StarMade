package org.newdawn.slick.openal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AudioLoader
{
  private static final String AIF = "AIF";
  private static final String WAV = "WAV";
  private static final String OGG = "OGG";
  private static final String MOD = "MOD";
  private static final String field_2249 = "XM";
  private static boolean inited = false;
  
  private static void init()
  {
    if (!inited)
    {
      SoundStore.get().init();
      inited = true;
    }
  }
  
  public static Audio getAudio(String format, InputStream local_in)
    throws IOException
  {
    
    if (format.equals("AIF")) {
      return SoundStore.get().getAIF(local_in);
    }
    if (format.equals("WAV")) {
      return SoundStore.get().getWAV(local_in);
    }
    if (format.equals("OGG")) {
      return SoundStore.get().getOgg(local_in);
    }
    throw new IOException("Unsupported format for non-streaming Audio: " + format);
  }
  
  public static Audio getStreamingAudio(String format, URL url)
    throws IOException
  {
    
    if (format.equals("OGG")) {
      return SoundStore.get().getOggStream(url);
    }
    if (format.equals("MOD")) {
      return SoundStore.get().getMOD(url.openStream());
    }
    if (format.equals("XM")) {
      return SoundStore.get().getMOD(url.openStream());
    }
    throw new IOException("Unsupported format for streaming Audio: " + format);
  }
  
  public static void update()
  {
    init();
    SoundStore.get().poll(0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.openal.AudioLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */