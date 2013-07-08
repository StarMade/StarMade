package org.newdawn.slick;

import java.io.InputStream;
import java.net.URL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.Log;

public class Sound
{
  private Audio sound;
  
  public Sound(InputStream local_in, String ref)
    throws SlickException
  {
    SoundStore.get().init();
    try
    {
      if (ref.toLowerCase().endsWith(".ogg")) {
        this.sound = SoundStore.get().getOgg(local_in);
      } else if (ref.toLowerCase().endsWith(".wav")) {
        this.sound = SoundStore.get().getWAV(local_in);
      } else if (ref.toLowerCase().endsWith(".aif")) {
        this.sound = SoundStore.get().getAIF(local_in);
      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
        this.sound = SoundStore.get().getMOD(local_in);
      } else {
        throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
      }
    }
    catch (Exception local_e)
    {
      Log.error(local_e);
      throw new SlickException("Failed to load sound: " + ref);
    }
  }
  
  public Sound(URL url)
    throws SlickException
  {
    SoundStore.get().init();
    String ref = url.getFile();
    try
    {
      if (ref.toLowerCase().endsWith(".ogg")) {
        this.sound = SoundStore.get().getOgg(url.openStream());
      } else if (ref.toLowerCase().endsWith(".wav")) {
        this.sound = SoundStore.get().getWAV(url.openStream());
      } else if (ref.toLowerCase().endsWith(".aif")) {
        this.sound = SoundStore.get().getAIF(url.openStream());
      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
        this.sound = SoundStore.get().getMOD(url.openStream());
      } else {
        throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
      }
    }
    catch (Exception local_e)
    {
      Log.error(local_e);
      throw new SlickException("Failed to load sound: " + ref);
    }
  }
  
  public Sound(String ref)
    throws SlickException
  {
    SoundStore.get().init();
    try
    {
      if (ref.toLowerCase().endsWith(".ogg")) {
        this.sound = SoundStore.get().getOgg(ref);
      } else if (ref.toLowerCase().endsWith(".wav")) {
        this.sound = SoundStore.get().getWAV(ref);
      } else if (ref.toLowerCase().endsWith(".aif")) {
        this.sound = SoundStore.get().getAIF(ref);
      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
        this.sound = SoundStore.get().getMOD(ref);
      } else {
        throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
      }
    }
    catch (Exception local_e)
    {
      Log.error(local_e);
      throw new SlickException("Failed to load sound: " + ref);
    }
  }
  
  public void play()
  {
    play(1.0F, 1.0F);
  }
  
  public void play(float pitch, float volume)
  {
    this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false);
  }
  
  public void playAt(float local_x, float local_y, float local_z)
  {
    playAt(1.0F, 1.0F, local_x, local_y, local_z);
  }
  
  public void playAt(float pitch, float volume, float local_x, float local_y, float local_z)
  {
    this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false, local_x, local_y, local_z);
  }
  
  public void loop()
  {
    loop(1.0F, 1.0F);
  }
  
  public void loop(float pitch, float volume)
  {
    this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), true);
  }
  
  public boolean playing()
  {
    return this.sound.isPlaying();
  }
  
  public void stop()
  {
    this.sound.stop();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.Sound
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */