/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.InputStream;
/*   4:    */import java.net.URL;
/*   5:    */import org.newdawn.slick.openal.Audio;
/*   6:    */import org.newdawn.slick.openal.SoundStore;
/*   7:    */import org.newdawn.slick.util.Log;
/*   8:    */
/*  21:    */public class Sound
/*  22:    */{
/*  23:    */  private Audio sound;
/*  24:    */  
/*  25:    */  public Sound(InputStream in, String ref)
/*  26:    */    throws SlickException
/*  27:    */  {
/*  28: 28 */    SoundStore.get().init();
/*  29:    */    try
/*  30:    */    {
/*  31: 31 */      if (ref.toLowerCase().endsWith(".ogg")) {
/*  32: 32 */        this.sound = SoundStore.get().getOgg(in);
/*  33: 33 */      } else if (ref.toLowerCase().endsWith(".wav")) {
/*  34: 34 */        this.sound = SoundStore.get().getWAV(in);
/*  35: 35 */      } else if (ref.toLowerCase().endsWith(".aif")) {
/*  36: 36 */        this.sound = SoundStore.get().getAIF(in);
/*  37: 37 */      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
/*  38: 38 */        this.sound = SoundStore.get().getMOD(in);
/*  39:    */      } else {
/*  40: 40 */        throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
/*  41:    */      }
/*  42:    */    } catch (Exception e) {
/*  43: 43 */      Log.error(e);
/*  44: 44 */      throw new SlickException("Failed to load sound: " + ref);
/*  45:    */    }
/*  46:    */  }
/*  47:    */  
/*  52:    */  public Sound(URL url)
/*  53:    */    throws SlickException
/*  54:    */  {
/*  55: 55 */    SoundStore.get().init();
/*  56: 56 */    String ref = url.getFile();
/*  57:    */    try
/*  58:    */    {
/*  59: 59 */      if (ref.toLowerCase().endsWith(".ogg")) {
/*  60: 60 */        this.sound = SoundStore.get().getOgg(url.openStream());
/*  61: 61 */      } else if (ref.toLowerCase().endsWith(".wav")) {
/*  62: 62 */        this.sound = SoundStore.get().getWAV(url.openStream());
/*  63: 63 */      } else if (ref.toLowerCase().endsWith(".aif")) {
/*  64: 64 */        this.sound = SoundStore.get().getAIF(url.openStream());
/*  65: 65 */      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
/*  66: 66 */        this.sound = SoundStore.get().getMOD(url.openStream());
/*  67:    */      } else {
/*  68: 68 */        throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
/*  69:    */      }
/*  70:    */    } catch (Exception e) {
/*  71: 71 */      Log.error(e);
/*  72: 72 */      throw new SlickException("Failed to load sound: " + ref);
/*  73:    */    }
/*  74:    */  }
/*  75:    */  
/*  80:    */  public Sound(String ref)
/*  81:    */    throws SlickException
/*  82:    */  {
/*  83: 83 */    SoundStore.get().init();
/*  84:    */    try
/*  85:    */    {
/*  86: 86 */      if (ref.toLowerCase().endsWith(".ogg")) {
/*  87: 87 */        this.sound = SoundStore.get().getOgg(ref);
/*  88: 88 */      } else if (ref.toLowerCase().endsWith(".wav")) {
/*  89: 89 */        this.sound = SoundStore.get().getWAV(ref);
/*  90: 90 */      } else if (ref.toLowerCase().endsWith(".aif")) {
/*  91: 91 */        this.sound = SoundStore.get().getAIF(ref);
/*  92: 92 */      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
/*  93: 93 */        this.sound = SoundStore.get().getMOD(ref);
/*  94:    */      } else {
/*  95: 95 */        throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
/*  96:    */      }
/*  97:    */    } catch (Exception e) {
/*  98: 98 */      Log.error(e);
/*  99: 99 */      throw new SlickException("Failed to load sound: " + ref);
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 105:    */  public void play()
/* 106:    */  {
/* 107:107 */    play(1.0F, 1.0F);
/* 108:    */  }
/* 109:    */  
/* 115:    */  public void play(float pitch, float volume)
/* 116:    */  {
/* 117:117 */    this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false);
/* 118:    */  }
/* 119:    */  
/* 126:    */  public void playAt(float x, float y, float z)
/* 127:    */  {
/* 128:128 */    playAt(1.0F, 1.0F, x, y, z);
/* 129:    */  }
/* 130:    */  
/* 139:    */  public void playAt(float pitch, float volume, float x, float y, float z)
/* 140:    */  {
/* 141:141 */    this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false, x, y, z);
/* 142:    */  }
/* 143:    */  
/* 145:    */  public void loop()
/* 146:    */  {
/* 147:147 */    loop(1.0F, 1.0F);
/* 148:    */  }
/* 149:    */  
/* 155:    */  public void loop(float pitch, float volume)
/* 156:    */  {
/* 157:157 */    this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), true);
/* 158:    */  }
/* 159:    */  
/* 164:    */  public boolean playing()
/* 165:    */  {
/* 166:166 */    return this.sound.isPlaying();
/* 167:    */  }
/* 168:    */  
/* 171:    */  public void stop()
/* 172:    */  {
/* 173:173 */    this.sound.stop();
/* 174:    */  }
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.Sound
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */