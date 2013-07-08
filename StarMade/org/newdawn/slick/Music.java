package org.newdawn.slick;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioImpl;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.Log;

public class Music
{
  private static Music currentMusic;
  private Audio sound;
  private boolean playing;
  private ArrayList listeners = new ArrayList();
  private float volume = 1.0F;
  private float fadeStartGain;
  private float fadeEndGain;
  private int fadeTime;
  private int fadeDuration;
  private boolean stopAfterFade;
  private boolean positioning;
  private float requiredPosition = -1.0F;
  
  public static void poll(int delta)
  {
    if (currentMusic != null)
    {
      SoundStore.get().poll(delta);
      if (!SoundStore.get().isMusicPlaying())
      {
        if (!currentMusic.positioning)
        {
          Music oldMusic = currentMusic;
          currentMusic = null;
          oldMusic.fireMusicEnded();
        }
      }
      else {
        currentMusic.update(delta);
      }
    }
  }
  
  public Music(String ref)
    throws SlickException
  {
    this(ref, false);
  }
  
  public Music(URL ref)
    throws SlickException
  {
    this(ref, false);
  }
  
  public Music(InputStream local_in, String ref)
    throws SlickException
  {
    SoundStore.get().init();
    try
    {
      if (ref.toLowerCase().endsWith(".ogg")) {
        this.sound = SoundStore.get().getOgg(local_in);
      } else if (ref.toLowerCase().endsWith(".wav")) {
        this.sound = SoundStore.get().getWAV(local_in);
      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
        this.sound = SoundStore.get().getMOD(local_in);
      } else if ((ref.toLowerCase().endsWith(".aif")) || (ref.toLowerCase().endsWith(".aiff"))) {
        this.sound = SoundStore.get().getAIF(local_in);
      } else {
        throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
      }
    }
    catch (Exception local_e)
    {
      Log.error(local_e);
      throw new SlickException("Failed to load music: " + ref);
    }
  }
  
  public Music(URL url, boolean streamingHint)
    throws SlickException
  {
    SoundStore.get().init();
    String ref = url.getFile();
    try
    {
      if (ref.toLowerCase().endsWith(".ogg"))
      {
        if (streamingHint) {
          this.sound = SoundStore.get().getOggStream(url);
        } else {
          this.sound = SoundStore.get().getOgg(url.openStream());
        }
      }
      else if (ref.toLowerCase().endsWith(".wav")) {
        this.sound = SoundStore.get().getWAV(url.openStream());
      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
        this.sound = SoundStore.get().getMOD(url.openStream());
      } else if ((ref.toLowerCase().endsWith(".aif")) || (ref.toLowerCase().endsWith(".aiff"))) {
        this.sound = SoundStore.get().getAIF(url.openStream());
      } else {
        throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
      }
    }
    catch (Exception local_e)
    {
      Log.error(local_e);
      throw new SlickException("Failed to load sound: " + url);
    }
  }
  
  public Music(String ref, boolean streamingHint)
    throws SlickException
  {
    SoundStore.get().init();
    try
    {
      if (ref.toLowerCase().endsWith(".ogg"))
      {
        if (streamingHint) {
          this.sound = SoundStore.get().getOggStream(ref);
        } else {
          this.sound = SoundStore.get().getOgg(ref);
        }
      }
      else if (ref.toLowerCase().endsWith(".wav")) {
        this.sound = SoundStore.get().getWAV(ref);
      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
        this.sound = SoundStore.get().getMOD(ref);
      } else if ((ref.toLowerCase().endsWith(".aif")) || (ref.toLowerCase().endsWith(".aiff"))) {
        this.sound = SoundStore.get().getAIF(ref);
      } else {
        throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
      }
    }
    catch (Exception local_e)
    {
      Log.error(local_e);
      throw new SlickException("Failed to load sound: " + ref);
    }
  }
  
  public void addListener(MusicListener listener)
  {
    this.listeners.add(listener);
  }
  
  public void removeListener(MusicListener listener)
  {
    this.listeners.remove(listener);
  }
  
  private void fireMusicEnded()
  {
    this.playing = false;
    for (int local_i = 0; local_i < this.listeners.size(); local_i++) {
      ((MusicListener)this.listeners.get(local_i)).musicEnded(this);
    }
  }
  
  private void fireMusicSwapped(Music newMusic)
  {
    this.playing = false;
    for (int local_i = 0; local_i < this.listeners.size(); local_i++) {
      ((MusicListener)this.listeners.get(local_i)).musicSwapped(this, newMusic);
    }
  }
  
  public void loop()
  {
    loop(1.0F, 1.0F);
  }
  
  public void play()
  {
    play(1.0F, 1.0F);
  }
  
  public void play(float pitch, float volume)
  {
    startMusic(pitch, volume, false);
  }
  
  public void loop(float pitch, float volume)
  {
    startMusic(pitch, volume, true);
  }
  
  private void startMusic(float pitch, float volume, boolean loop)
  {
    if (currentMusic != null)
    {
      currentMusic.stop();
      currentMusic.fireMusicSwapped(this);
    }
    currentMusic = this;
    if (volume < 0.0F) {
      volume = 0.0F;
    }
    if (volume > 1.0F) {
      volume = 1.0F;
    }
    this.sound.playAsMusic(pitch, volume, loop);
    this.playing = true;
    setVolume(volume);
    if (this.requiredPosition != -1.0F) {
      setPosition(this.requiredPosition);
    }
  }
  
  public void pause()
  {
    this.playing = false;
    AudioImpl.pauseMusic();
  }
  
  public void stop()
  {
    this.sound.stop();
  }
  
  public void resume()
  {
    this.playing = true;
    AudioImpl.restartMusic();
  }
  
  public boolean playing()
  {
    return (currentMusic == this) && (this.playing);
  }
  
  public void setVolume(float volume)
  {
    if (volume > 1.0F) {
      volume = 1.0F;
    } else if (volume < 0.0F) {
      volume = 0.0F;
    }
    this.volume = volume;
    if (currentMusic == this) {
      SoundStore.get().setCurrentMusicVolume(volume);
    }
  }
  
  public float getVolume()
  {
    return this.volume;
  }
  
  public void fade(int duration, float endVolume, boolean stopAfterFade)
  {
    this.stopAfterFade = stopAfterFade;
    this.fadeStartGain = this.volume;
    this.fadeEndGain = endVolume;
    this.fadeDuration = duration;
    this.fadeTime = duration;
  }
  
  void update(int delta)
  {
    if (!this.playing) {
      return;
    }
    if (this.fadeTime > 0)
    {
      this.fadeTime -= delta;
      if (this.fadeTime < 0)
      {
        this.fadeTime = 0;
        if (this.stopAfterFade)
        {
          stop();
          return;
        }
      }
      float offset = (this.fadeEndGain - this.fadeStartGain) * (1.0F - this.fadeTime / this.fadeDuration);
      setVolume(this.fadeStartGain + offset);
    }
  }
  
  public boolean setPosition(float position)
  {
    if (this.playing)
    {
      this.requiredPosition = -1.0F;
      this.positioning = true;
      this.playing = false;
      boolean result = this.sound.setPosition(position);
      this.playing = true;
      this.positioning = false;
      return result;
    }
    this.requiredPosition = position;
    return false;
  }
  
  public float getPosition()
  {
    return this.sound.getPosition();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.Music
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */