/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.InputStream;
/*   4:    */import java.net.URL;
/*   5:    */import java.util.ArrayList;
/*   6:    */import org.newdawn.slick.openal.Audio;
/*   7:    */import org.newdawn.slick.openal.AudioImpl;
/*   8:    */import org.newdawn.slick.openal.SoundStore;
/*   9:    */import org.newdawn.slick.util.Log;
/*  10:    */
/*  23:    */public class Music
/*  24:    */{
/*  25:    */  private static Music currentMusic;
/*  26:    */  private Audio sound;
/*  27:    */  private boolean playing;
/*  28:    */  
/*  29:    */  public static void poll(int delta)
/*  30:    */  {
/*  31: 31 */    if (currentMusic != null) {
/*  32: 32 */      SoundStore.get().poll(delta);
/*  33: 33 */      if (!SoundStore.get().isMusicPlaying()) {
/*  34: 34 */        if (!currentMusic.positioning) {
/*  35: 35 */          Music oldMusic = currentMusic;
/*  36: 36 */          currentMusic = null;
/*  37: 37 */          oldMusic.fireMusicEnded();
/*  38:    */        }
/*  39:    */      } else {
/*  40: 40 */        currentMusic.update(delta);
/*  41:    */      }
/*  42:    */    }
/*  43:    */  }
/*  44:    */  
/*  50: 50 */  private ArrayList listeners = new ArrayList();
/*  51:    */  
/*  52: 52 */  private float volume = 1.0F;
/*  53:    */  
/*  54:    */  private float fadeStartGain;
/*  55:    */  
/*  56:    */  private float fadeEndGain;
/*  57:    */  
/*  58:    */  private int fadeTime;
/*  59:    */  
/*  60:    */  private int fadeDuration;
/*  61:    */  
/*  62:    */  private boolean stopAfterFade;
/*  63:    */  
/*  64:    */  private boolean positioning;
/*  65:    */  
/*  66: 66 */  private float requiredPosition = -1.0F;
/*  67:    */  
/*  72:    */  public Music(String ref)
/*  73:    */    throws SlickException
/*  74:    */  {
/*  75: 75 */    this(ref, false);
/*  76:    */  }
/*  77:    */  
/*  82:    */  public Music(URL ref)
/*  83:    */    throws SlickException
/*  84:    */  {
/*  85: 85 */    this(ref, false);
/*  86:    */  }
/*  87:    */  
/*  92:    */  public Music(InputStream in, String ref)
/*  93:    */    throws SlickException
/*  94:    */  {
/*  95: 95 */    SoundStore.get().init();
/*  96:    */    try
/*  97:    */    {
/*  98: 98 */      if (ref.toLowerCase().endsWith(".ogg")) {
/*  99: 99 */        this.sound = SoundStore.get().getOgg(in);
/* 100:100 */      } else if (ref.toLowerCase().endsWith(".wav")) {
/* 101:101 */        this.sound = SoundStore.get().getWAV(in);
/* 102:102 */      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
/* 103:103 */        this.sound = SoundStore.get().getMOD(in);
/* 104:104 */      } else if ((ref.toLowerCase().endsWith(".aif")) || (ref.toLowerCase().endsWith(".aiff"))) {
/* 105:105 */        this.sound = SoundStore.get().getAIF(in);
/* 106:    */      } else {
/* 107:107 */        throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
/* 108:    */      }
/* 109:    */    } catch (Exception e) {
/* 110:110 */      Log.error(e);
/* 111:111 */      throw new SlickException("Failed to load music: " + ref);
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 120:    */  public Music(URL url, boolean streamingHint)
/* 121:    */    throws SlickException
/* 122:    */  {
/* 123:123 */    SoundStore.get().init();
/* 124:124 */    String ref = url.getFile();
/* 125:    */    try
/* 126:    */    {
/* 127:127 */      if (ref.toLowerCase().endsWith(".ogg")) {
/* 128:128 */        if (streamingHint) {
/* 129:129 */          this.sound = SoundStore.get().getOggStream(url);
/* 130:    */        } else {
/* 131:131 */          this.sound = SoundStore.get().getOgg(url.openStream());
/* 132:    */        }
/* 133:133 */      } else if (ref.toLowerCase().endsWith(".wav")) {
/* 134:134 */        this.sound = SoundStore.get().getWAV(url.openStream());
/* 135:135 */      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
/* 136:136 */        this.sound = SoundStore.get().getMOD(url.openStream());
/* 137:137 */      } else if ((ref.toLowerCase().endsWith(".aif")) || (ref.toLowerCase().endsWith(".aiff"))) {
/* 138:138 */        this.sound = SoundStore.get().getAIF(url.openStream());
/* 139:    */      } else {
/* 140:140 */        throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
/* 141:    */      }
/* 142:    */    } catch (Exception e) {
/* 143:143 */      Log.error(e);
/* 144:144 */      throw new SlickException("Failed to load sound: " + url);
/* 145:    */    }
/* 146:    */  }
/* 147:    */  
/* 153:    */  public Music(String ref, boolean streamingHint)
/* 154:    */    throws SlickException
/* 155:    */  {
/* 156:156 */    SoundStore.get().init();
/* 157:    */    try
/* 158:    */    {
/* 159:159 */      if (ref.toLowerCase().endsWith(".ogg")) {
/* 160:160 */        if (streamingHint) {
/* 161:161 */          this.sound = SoundStore.get().getOggStream(ref);
/* 162:    */        } else {
/* 163:163 */          this.sound = SoundStore.get().getOgg(ref);
/* 164:    */        }
/* 165:165 */      } else if (ref.toLowerCase().endsWith(".wav")) {
/* 166:166 */        this.sound = SoundStore.get().getWAV(ref);
/* 167:167 */      } else if ((ref.toLowerCase().endsWith(".xm")) || (ref.toLowerCase().endsWith(".mod"))) {
/* 168:168 */        this.sound = SoundStore.get().getMOD(ref);
/* 169:169 */      } else if ((ref.toLowerCase().endsWith(".aif")) || (ref.toLowerCase().endsWith(".aiff"))) {
/* 170:170 */        this.sound = SoundStore.get().getAIF(ref);
/* 171:    */      } else {
/* 172:172 */        throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
/* 173:    */      }
/* 174:    */    } catch (Exception e) {
/* 175:175 */      Log.error(e);
/* 176:176 */      throw new SlickException("Failed to load sound: " + ref);
/* 177:    */    }
/* 178:    */  }
/* 179:    */  
/* 184:    */  public void addListener(MusicListener listener)
/* 185:    */  {
/* 186:186 */    this.listeners.add(listener);
/* 187:    */  }
/* 188:    */  
/* 193:    */  public void removeListener(MusicListener listener)
/* 194:    */  {
/* 195:195 */    this.listeners.remove(listener);
/* 196:    */  }
/* 197:    */  
/* 200:    */  private void fireMusicEnded()
/* 201:    */  {
/* 202:202 */    this.playing = false;
/* 203:203 */    for (int i = 0; i < this.listeners.size(); i++) {
/* 204:204 */      ((MusicListener)this.listeners.get(i)).musicEnded(this);
/* 205:    */    }
/* 206:    */  }
/* 207:    */  
/* 212:    */  private void fireMusicSwapped(Music newMusic)
/* 213:    */  {
/* 214:214 */    this.playing = false;
/* 215:215 */    for (int i = 0; i < this.listeners.size(); i++) {
/* 216:216 */      ((MusicListener)this.listeners.get(i)).musicSwapped(this, newMusic);
/* 217:    */    }
/* 218:    */  }
/* 219:    */  
/* 221:    */  public void loop()
/* 222:    */  {
/* 223:223 */    loop(1.0F, 1.0F);
/* 224:    */  }
/* 225:    */  
/* 228:    */  public void play()
/* 229:    */  {
/* 230:230 */    play(1.0F, 1.0F);
/* 231:    */  }
/* 232:    */  
/* 238:    */  public void play(float pitch, float volume)
/* 239:    */  {
/* 240:240 */    startMusic(pitch, volume, false);
/* 241:    */  }
/* 242:    */  
/* 248:    */  public void loop(float pitch, float volume)
/* 249:    */  {
/* 250:250 */    startMusic(pitch, volume, true);
/* 251:    */  }
/* 252:    */  
/* 258:    */  private void startMusic(float pitch, float volume, boolean loop)
/* 259:    */  {
/* 260:260 */    if (currentMusic != null) {
/* 261:261 */      currentMusic.stop();
/* 262:262 */      currentMusic.fireMusicSwapped(this);
/* 263:    */    }
/* 264:    */    
/* 265:265 */    currentMusic = this;
/* 266:266 */    if (volume < 0.0F)
/* 267:267 */      volume = 0.0F;
/* 268:268 */    if (volume > 1.0F) {
/* 269:269 */      volume = 1.0F;
/* 270:    */    }
/* 271:271 */    this.sound.playAsMusic(pitch, volume, loop);
/* 272:272 */    this.playing = true;
/* 273:273 */    setVolume(volume);
/* 274:274 */    if (this.requiredPosition != -1.0F) {
/* 275:275 */      setPosition(this.requiredPosition);
/* 276:    */    }
/* 277:    */  }
/* 278:    */  
/* 281:    */  public void pause()
/* 282:    */  {
/* 283:283 */    this.playing = false;
/* 284:284 */    AudioImpl.pauseMusic();
/* 285:    */  }
/* 286:    */  
/* 289:    */  public void stop()
/* 290:    */  {
/* 291:291 */    this.sound.stop();
/* 292:    */  }
/* 293:    */  
/* 296:    */  public void resume()
/* 297:    */  {
/* 298:298 */    this.playing = true;
/* 299:299 */    AudioImpl.restartMusic();
/* 300:    */  }
/* 301:    */  
/* 306:    */  public boolean playing()
/* 307:    */  {
/* 308:308 */    return (currentMusic == this) && (this.playing);
/* 309:    */  }
/* 310:    */  
/* 316:    */  public void setVolume(float volume)
/* 317:    */  {
/* 318:318 */    if (volume > 1.0F) {
/* 319:319 */      volume = 1.0F;
/* 320:320 */    } else if (volume < 0.0F) {
/* 321:321 */      volume = 0.0F;
/* 322:    */    }
/* 323:    */    
/* 324:324 */    this.volume = volume;
/* 325:    */    
/* 326:326 */    if (currentMusic == this) {
/* 327:327 */      SoundStore.get().setCurrentMusicVolume(volume);
/* 328:    */    }
/* 329:    */  }
/* 330:    */  
/* 334:    */  public float getVolume()
/* 335:    */  {
/* 336:336 */    return this.volume;
/* 337:    */  }
/* 338:    */  
/* 345:    */  public void fade(int duration, float endVolume, boolean stopAfterFade)
/* 346:    */  {
/* 347:347 */    this.stopAfterFade = stopAfterFade;
/* 348:348 */    this.fadeStartGain = this.volume;
/* 349:349 */    this.fadeEndGain = endVolume;
/* 350:350 */    this.fadeDuration = duration;
/* 351:351 */    this.fadeTime = duration;
/* 352:    */  }
/* 353:    */  
/* 359:    */  void update(int delta)
/* 360:    */  {
/* 361:361 */    if (!this.playing) {
/* 362:362 */      return;
/* 363:    */    }
/* 364:    */    
/* 365:365 */    if (this.fadeTime > 0) {
/* 366:366 */      this.fadeTime -= delta;
/* 367:367 */      if (this.fadeTime < 0) {
/* 368:368 */        this.fadeTime = 0;
/* 369:369 */        if (this.stopAfterFade) {
/* 370:370 */          stop();
/* 371:371 */          return;
/* 372:    */        }
/* 373:    */      }
/* 374:    */      
/* 375:375 */      float offset = (this.fadeEndGain - this.fadeStartGain) * (1.0F - this.fadeTime / this.fadeDuration);
/* 376:376 */      setVolume(this.fadeStartGain + offset);
/* 377:    */    }
/* 378:    */  }
/* 379:    */  
/* 386:    */  public boolean setPosition(float position)
/* 387:    */  {
/* 388:388 */    if (this.playing) {
/* 389:389 */      this.requiredPosition = -1.0F;
/* 390:    */      
/* 391:391 */      this.positioning = true;
/* 392:392 */      this.playing = false;
/* 393:393 */      boolean result = this.sound.setPosition(position);
/* 394:394 */      this.playing = true;
/* 395:395 */      this.positioning = false;
/* 396:    */      
/* 397:397 */      return result;
/* 398:    */    }
/* 399:399 */    this.requiredPosition = position;
/* 400:400 */    return false;
/* 401:    */  }
/* 402:    */  
/* 408:    */  public float getPosition()
/* 409:    */  {
/* 410:410 */    return this.sound.getPosition();
/* 411:    */  }
/* 412:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.Music
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */