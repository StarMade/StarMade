/*     */ package org.newdawn.slick.openal;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.HashMap;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.Sys;
/*     */ import org.lwjgl.openal.AL;
/*     */ import org.lwjgl.openal.AL10;
/*     */ import org.lwjgl.openal.OpenALException;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.newdawn.slick.util.ResourceLoader;
/*     */ 
/*     */ public class SoundStore
/*     */ {
/*  30 */   private static SoundStore store = new SoundStore();
/*     */   private boolean sounds;
/*     */   private boolean music;
/*     */   private boolean soundWorks;
/*     */   private int sourceCount;
/*  41 */   private HashMap loaded = new HashMap();
/*     */ 
/*  43 */   private int currentMusic = -1;
/*     */   private IntBuffer sources;
/*     */   private int nextSource;
/*  49 */   private boolean inited = false;
/*     */   private MODSound mod;
/*     */   private OpenALStreamPlayer stream;
/*  56 */   private float musicVolume = 1.0F;
/*     */ 
/*  58 */   private float soundVolume = 1.0F;
/*     */ 
/*  60 */   private float lastCurrentMusicVolume = 1.0F;
/*     */   private boolean paused;
/*     */   private boolean deferred;
/*  68 */   private FloatBuffer sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
/*     */ 
/*  70 */   private FloatBuffer sourcePos = BufferUtils.createFloatBuffer(3);
/*     */ 
/*  73 */   private int maxSources = 64;
/*     */   public static boolean sound3d;
/*     */ 
/*     */   public void clear()
/*     */   {
/*  85 */     store = new SoundStore();
/*     */   }
/*     */ 
/*     */   public void disable()
/*     */   {
/*  92 */     this.inited = true;
/*     */   }
/*     */ 
/*     */   public void setDeferredLoading(boolean deferred)
/*     */   {
/* 102 */     this.deferred = deferred;
/*     */   }
/*     */ 
/*     */   public boolean isDeferredLoading()
/*     */   {
/* 111 */     return this.deferred;
/*     */   }
/*     */ 
/*     */   public void setMusicOn(boolean music)
/*     */   {
/* 120 */     if (this.soundWorks) {
/* 121 */       this.music = music;
/* 122 */       if (music) {
/* 123 */         restartLoop();
/* 124 */         setMusicVolume(this.musicVolume);
/*     */       } else {
/* 126 */         pauseLoop();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isMusicOn()
/*     */   {
/* 137 */     return this.music;
/*     */   }
/*     */ 
/*     */   public void setMusicVolume(float volume)
/*     */   {
/* 146 */     if (volume < 0.0F) {
/* 147 */       volume = 0.0F;
/*     */     }
/* 149 */     if (volume > 1.0F) {
/* 150 */       volume = 1.0F;
/*     */     }
/*     */ 
/* 153 */     this.musicVolume = volume;
/* 154 */     if (this.soundWorks)
/* 155 */       AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
/*     */   }
/*     */ 
/*     */   public float getCurrentMusicVolume()
/*     */   {
/* 165 */     return this.lastCurrentMusicVolume;
/*     */   }
/*     */ 
/*     */   public void setCurrentMusicVolume(float volume)
/*     */   {
/* 174 */     if (volume < 0.0F) {
/* 175 */       volume = 0.0F;
/*     */     }
/* 177 */     if (volume > 1.0F) {
/* 178 */       volume = 1.0F;
/*     */     }
/*     */ 
/* 181 */     if (this.soundWorks) {
/* 182 */       this.lastCurrentMusicVolume = volume;
/* 183 */       AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setSoundVolume(float volume)
/*     */   {
/* 193 */     if (volume < 0.0F) {
/* 194 */       volume = 0.0F;
/*     */     }
/* 196 */     this.soundVolume = volume;
/*     */   }
/*     */ 
/*     */   public boolean soundWorks()
/*     */   {
/* 205 */     return this.soundWorks;
/*     */   }
/*     */ 
/*     */   public boolean musicOn()
/*     */   {
/* 214 */     return this.music;
/*     */   }
/*     */ 
/*     */   public float getSoundVolume()
/*     */   {
/* 223 */     return this.soundVolume;
/*     */   }
/*     */ 
/*     */   public float getMusicVolume()
/*     */   {
/* 232 */     return this.musicVolume;
/*     */   }
/*     */ 
/*     */   public int getSource(int index)
/*     */   {
/* 242 */     if (!this.soundWorks) {
/* 243 */       return -1;
/*     */     }
/* 245 */     if (index < 0) {
/* 246 */       return -1;
/*     */     }
/* 248 */     return this.sources.get(index);
/*     */   }
/*     */ 
/*     */   public void setSoundsOn(boolean sounds)
/*     */   {
/* 257 */     if (this.soundWorks)
/* 258 */       this.sounds = sounds;
/*     */   }
/*     */ 
/*     */   public boolean soundsOn()
/*     */   {
/* 268 */     return this.sounds;
/*     */   }
/*     */ 
/*     */   public void setMaxSources(int max)
/*     */   {
/* 278 */     this.maxSources = max;
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/* 286 */     if (this.inited) {
/* 287 */       return;
/*     */     }
/* 289 */     Log.info("Initialising sounds..");
/* 290 */     this.inited = true;
/*     */ 
/* 292 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */     {
/*     */       public Object run()
/*     */       {
/*     */         try {
/* 297 */           if (!SoundStore.sound3d)
/* 298 */             AL.create();
/*     */           else {
/* 300 */             AL.create("DirectSound3D", 44100, 15, false);
/*     */           }
/* 302 */           SoundStore.this.soundWorks = true;
/* 303 */           SoundStore.this.sounds = true;
/* 304 */           SoundStore.this.music = true;
/* 305 */           Log.info("- Sound works");
/*     */         } catch (Exception e) {
/* 307 */           Log.error("Sound initialisation failure.");
/* 308 */           Log.error(e);
/* 309 */           SoundStore.this.soundWorks = false;
/* 310 */           SoundStore.this.sounds = false;
/* 311 */           SoundStore.this.music = false;
/*     */         }
/*     */ 
/* 314 */         return null;
/*     */       }
/*     */     });
/* 317 */     if (this.soundWorks) {
/* 318 */       this.sourceCount = 0;
/* 319 */       this.sources = BufferUtils.createIntBuffer(this.maxSources);
/* 320 */       while (AL10.alGetError() == 0) {
/* 321 */         IntBuffer temp = BufferUtils.createIntBuffer(1);
/*     */         try
/*     */         {
/* 324 */           AL10.alGenSources(temp);
/*     */ 
/* 326 */           if (AL10.alGetError() == 0) {
/* 327 */             this.sourceCount += 1;
/* 328 */             this.sources.put(temp.get(0));
/* 329 */             if (this.sourceCount > this.maxSources - 1)
/* 330 */               break;
/*     */           }
/*     */         }
/*     */         catch (OpenALException e)
/*     */         {
/* 335 */           break;
/*     */         }
/*     */       }
/* 338 */       Log.info("- " + this.sourceCount + " OpenAL source available");
/*     */ 
/* 340 */       if (AL10.alGetError() != 0) {
/* 341 */         this.sounds = false;
/* 342 */         this.music = false;
/* 343 */         this.soundWorks = false;
/* 344 */         Log.error("- AL init failed");
/*     */       } else {
/* 346 */         FloatBuffer listenerOri = BufferUtils.createFloatBuffer(6).put(new float[] { 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F });
/*     */ 
/* 348 */         FloatBuffer listenerVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
/*     */ 
/* 350 */         FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
/*     */ 
/* 352 */         listenerPos.flip();
/* 353 */         listenerVel.flip();
/* 354 */         listenerOri.flip();
/* 355 */         AL10.alListener(4100, listenerPos);
/* 356 */         AL10.alListener(4102, listenerVel);
/* 357 */         AL10.alListener(4111, listenerOri);
/*     */ 
/* 359 */         Log.info("- Sounds source generated");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   void stopSource(int index)
/*     */   {
/* 370 */     AL10.alSourceStop(this.sources.get(index));
/*     */   }
/*     */ 
/*     */   int playAsSound(int buffer, float pitch, float gain, boolean loop)
/*     */   {
/* 384 */     return playAsSoundAt(buffer, pitch, gain, loop, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   int playAsSoundAt(int buffer, float pitch, float gain, boolean loop, float x, float y, float z)
/*     */   {
/* 401 */     gain *= this.soundVolume;
/* 402 */     if (gain == 0.0F) {
/* 403 */       gain = 0.001F;
/*     */     }
/* 405 */     if ((this.soundWorks) && 
/* 406 */       (this.sounds)) {
/* 407 */       int nextSource = findFreeSource();
/* 408 */       if (nextSource == -1) {
/* 409 */         return -1;
/*     */       }
/*     */ 
/* 412 */       AL10.alSourceStop(this.sources.get(nextSource));
/*     */ 
/* 414 */       AL10.alSourcei(this.sources.get(nextSource), 4105, buffer);
/* 415 */       AL10.alSourcef(this.sources.get(nextSource), 4099, pitch);
/* 416 */       AL10.alSourcef(this.sources.get(nextSource), 4106, gain);
/* 417 */       AL10.alSourcei(this.sources.get(nextSource), 4103, loop ? 1 : 0);
/*     */ 
/* 419 */       this.sourcePos.clear();
/* 420 */       this.sourceVel.clear();
/* 421 */       this.sourceVel.put(new float[] { 0.0F, 0.0F, 0.0F });
/* 422 */       this.sourcePos.put(new float[] { x, y, z });
/* 423 */       this.sourcePos.flip();
/* 424 */       this.sourceVel.flip();
/* 425 */       AL10.alSource(this.sources.get(nextSource), 4100, this.sourcePos);
/* 426 */       AL10.alSource(this.sources.get(nextSource), 4102, this.sourceVel);
/*     */ 
/* 428 */       AL10.alSourcePlay(this.sources.get(nextSource));
/*     */ 
/* 430 */       return nextSource;
/*     */     }
/*     */ 
/* 434 */     return -1;
/*     */   }
/*     */ 
/*     */   boolean isPlaying(int index)
/*     */   {
/* 443 */     int state = AL10.alGetSourcei(this.sources.get(index), 4112);
/*     */ 
/* 445 */     return state == 4114;
/*     */   }
/*     */ 
/*     */   private int findFreeSource()
/*     */   {
/* 454 */     for (int i = 1; i < this.sourceCount - 1; i++) {
/* 455 */       int state = AL10.alGetSourcei(this.sources.get(i), 4112);
/*     */ 
/* 457 */       if ((state != 4114) && (state != 4115)) {
/* 458 */         return i;
/*     */       }
/*     */     }
/*     */ 
/* 462 */     return -1;
/*     */   }
/*     */ 
/*     */   void playAsMusic(int buffer, float pitch, float gain, boolean loop)
/*     */   {
/* 474 */     this.paused = false;
/*     */ 
/* 476 */     setMOD(null);
/*     */ 
/* 478 */     if (this.soundWorks) {
/* 479 */       if (this.currentMusic != -1) {
/* 480 */         AL10.alSourceStop(this.sources.get(0));
/*     */       }
/*     */ 
/* 483 */       getMusicSource();
/*     */ 
/* 485 */       AL10.alSourcei(this.sources.get(0), 4105, buffer);
/* 486 */       AL10.alSourcef(this.sources.get(0), 4099, pitch);
/* 487 */       AL10.alSourcei(this.sources.get(0), 4103, loop ? 1 : 0);
/*     */ 
/* 489 */       this.currentMusic = this.sources.get(0);
/*     */ 
/* 491 */       if (!this.music)
/* 492 */         pauseLoop();
/*     */       else
/* 494 */         AL10.alSourcePlay(this.sources.get(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   private int getMusicSource()
/*     */   {
/* 505 */     return this.sources.get(0);
/*     */   }
/*     */ 
/*     */   public void setMusicPitch(float pitch)
/*     */   {
/* 514 */     if (this.soundWorks)
/* 515 */       AL10.alSourcef(this.sources.get(0), 4099, pitch);
/*     */   }
/*     */ 
/*     */   public void pauseLoop()
/*     */   {
/* 523 */     if ((this.soundWorks) && (this.currentMusic != -1)) {
/* 524 */       this.paused = true;
/* 525 */       AL10.alSourcePause(this.currentMusic);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void restartLoop()
/*     */   {
/* 533 */     if ((this.music) && (this.soundWorks) && (this.currentMusic != -1)) {
/* 534 */       this.paused = false;
/* 535 */       AL10.alSourcePlay(this.currentMusic);
/*     */     }
/*     */   }
/*     */ 
/*     */   boolean isPlaying(OpenALStreamPlayer player)
/*     */   {
/* 547 */     return this.stream == player;
/*     */   }
/*     */ 
/*     */   public Audio getMOD(String ref)
/*     */     throws IOException
/*     */   {
/* 558 */     return getMOD(ref, ResourceLoader.getResourceAsStream(ref));
/*     */   }
/*     */ 
/*     */   public Audio getMOD(InputStream in)
/*     */     throws IOException
/*     */   {
/* 569 */     return getMOD(in.toString(), in);
/*     */   }
/*     */ 
/*     */   public Audio getMOD(String ref, InputStream in)
/*     */     throws IOException
/*     */   {
/* 581 */     if (!this.soundWorks) {
/* 582 */       return new NullAudio();
/*     */     }
/* 584 */     if (!this.inited) {
/* 585 */       throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
/*     */     }
/* 587 */     if (this.deferred) {
/* 588 */       return new DeferredSound(ref, in, 3);
/*     */     }
/*     */ 
/* 591 */     return new MODSound(this, in);
/*     */   }
/*     */ 
/*     */   public Audio getAIF(String ref)
/*     */     throws IOException
/*     */   {
/* 602 */     return getAIF(ref, ResourceLoader.getResourceAsStream(ref));
/*     */   }
/*     */ 
/*     */   public Audio getAIF(InputStream in)
/*     */     throws IOException
/*     */   {
/* 614 */     return getAIF(in.toString(), in);
/*     */   }
/*     */ 
/*     */   public Audio getAIF(String ref, InputStream in)
/*     */     throws IOException
/*     */   {
/* 626 */     in = new BufferedInputStream(in);
/*     */ 
/* 628 */     if (!this.soundWorks) {
/* 629 */       return new NullAudio();
/*     */     }
/* 631 */     if (!this.inited) {
/* 632 */       throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
/*     */     }
/* 634 */     if (this.deferred) {
/* 635 */       return new DeferredSound(ref, in, 4);
/*     */     }
/*     */ 
/* 638 */     int buffer = -1;
/*     */ 
/* 640 */     if (this.loaded.get(ref) != null)
/* 641 */       buffer = ((Integer)this.loaded.get(ref)).intValue();
/*     */     else {
/*     */       try {
/* 644 */         IntBuffer buf = BufferUtils.createIntBuffer(1);
/*     */ 
/* 646 */         AiffData data = AiffData.create(in);
/* 647 */         AL10.alGenBuffers(buf);
/* 648 */         AL10.alBufferData(buf.get(0), data.format, data.data, data.samplerate);
/*     */ 
/* 650 */         this.loaded.put(ref, new Integer(buf.get(0)));
/* 651 */         buffer = buf.get(0);
/*     */       } catch (Exception e) {
/* 653 */         Log.error(e);
/* 654 */         IOException x = new IOException("Failed to load: " + ref);
/* 655 */         x.initCause(e);
/*     */ 
/* 657 */         throw x;
/*     */       }
/*     */     }
/*     */ 
/* 661 */     if (buffer == -1) {
/* 662 */       throw new IOException("Unable to load: " + ref);
/*     */     }
/*     */ 
/* 665 */     return new AudioImpl(this, buffer);
/*     */   }
/*     */ 
/*     */   public Audio getWAV(String ref)
/*     */     throws IOException
/*     */   {
/* 678 */     return getWAV(ref, ResourceLoader.getResourceAsStream(ref));
/*     */   }
/*     */ 
/*     */   public Audio getWAV(InputStream in)
/*     */     throws IOException
/*     */   {
/* 689 */     return getWAV(in.toString(), in);
/*     */   }
/*     */ 
/*     */   public Audio getWAV(String ref, InputStream in)
/*     */     throws IOException
/*     */   {
/* 701 */     if (!this.soundWorks) {
/* 702 */       return new NullAudio();
/*     */     }
/* 704 */     if (!this.inited) {
/* 705 */       throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
/*     */     }
/* 707 */     if (this.deferred) {
/* 708 */       return new DeferredSound(ref, in, 2);
/*     */     }
/*     */ 
/* 711 */     int buffer = -1;
/*     */ 
/* 713 */     if (this.loaded.get(ref) != null)
/* 714 */       buffer = ((Integer)this.loaded.get(ref)).intValue();
/*     */     else {
/*     */       try {
/* 717 */         IntBuffer buf = BufferUtils.createIntBuffer(1);
/*     */ 
/* 719 */         WaveData data = WaveData.create(in);
/* 720 */         AL10.alGenBuffers(buf);
/* 721 */         AL10.alBufferData(buf.get(0), data.format, data.data, data.samplerate);
/*     */ 
/* 723 */         this.loaded.put(ref, new Integer(buf.get(0)));
/* 724 */         buffer = buf.get(0);
/*     */       } catch (Exception e) {
/* 726 */         Log.error(e);
/* 727 */         IOException x = new IOException("Failed to load: " + ref);
/* 728 */         x.initCause(e);
/*     */ 
/* 730 */         throw x;
/*     */       }
/*     */     }
/*     */ 
/* 734 */     if (buffer == -1) {
/* 735 */       throw new IOException("Unable to load: " + ref);
/*     */     }
/*     */ 
/* 738 */     return new AudioImpl(this, buffer);
/*     */   }
/*     */ 
/*     */   public Audio getOggStream(String ref)
/*     */     throws IOException
/*     */   {
/* 749 */     if (!this.soundWorks) {
/* 750 */       return new NullAudio();
/*     */     }
/*     */ 
/* 753 */     setMOD(null);
/* 754 */     setStream(null);
/*     */ 
/* 756 */     if (this.currentMusic != -1) {
/* 757 */       AL10.alSourceStop(this.sources.get(0));
/*     */     }
/*     */ 
/* 760 */     getMusicSource();
/* 761 */     this.currentMusic = this.sources.get(0);
/*     */ 
/* 763 */     return new StreamSound(new OpenALStreamPlayer(this.currentMusic, ref));
/*     */   }
/*     */ 
/*     */   public Audio getOggStream(URL ref)
/*     */     throws IOException
/*     */   {
/* 774 */     if (!this.soundWorks) {
/* 775 */       return new NullAudio();
/*     */     }
/*     */ 
/* 778 */     setMOD(null);
/* 779 */     setStream(null);
/*     */ 
/* 781 */     if (this.currentMusic != -1) {
/* 782 */       AL10.alSourceStop(this.sources.get(0));
/*     */     }
/*     */ 
/* 785 */     getMusicSource();
/* 786 */     this.currentMusic = this.sources.get(0);
/*     */ 
/* 788 */     return new StreamSound(new OpenALStreamPlayer(this.currentMusic, ref));
/*     */   }
/*     */ 
/*     */   public Audio getOgg(String ref)
/*     */     throws IOException
/*     */   {
/* 799 */     return getOgg(ref, ResourceLoader.getResourceAsStream(ref));
/*     */   }
/*     */ 
/*     */   public Audio getOgg(InputStream in)
/*     */     throws IOException
/*     */   {
/* 810 */     return getOgg(in.toString(), in);
/*     */   }
/*     */ 
/*     */   public Audio getOgg(String ref, InputStream in)
/*     */     throws IOException
/*     */   {
/* 822 */     if (!this.soundWorks) {
/* 823 */       return new NullAudio();
/*     */     }
/* 825 */     if (!this.inited) {
/* 826 */       throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
/*     */     }
/* 828 */     if (this.deferred) {
/* 829 */       return new DeferredSound(ref, in, 1);
/*     */     }
/*     */ 
/* 832 */     int buffer = -1;
/*     */ 
/* 834 */     if (this.loaded.get(ref) != null)
/* 835 */       buffer = ((Integer)this.loaded.get(ref)).intValue();
/*     */     else {
/*     */       try {
/* 838 */         IntBuffer buf = BufferUtils.createIntBuffer(1);
/*     */ 
/* 840 */         OggDecoder decoder = new OggDecoder();
/* 841 */         OggData ogg = decoder.getData(in);
/*     */ 
/* 843 */         AL10.alGenBuffers(buf);
/* 844 */         AL10.alBufferData(buf.get(0), ogg.channels > 1 ? 4355 : 4353, ogg.data, ogg.rate);
/*     */ 
/* 846 */         this.loaded.put(ref, new Integer(buf.get(0)));
/*     */ 
/* 848 */         buffer = buf.get(0);
/*     */       } catch (Exception e) {
/* 850 */         Log.error(e);
/* 851 */         Sys.alert("Error", "Failed to load: " + ref + " - " + e.getMessage());
/* 852 */         throw new IOException("Unable to load: " + ref);
/*     */       }
/*     */     }
/*     */ 
/* 856 */     if (buffer == -1) {
/* 857 */       throw new IOException("Unable to load: " + ref);
/*     */     }
/*     */ 
/* 860 */     return new AudioImpl(this, buffer);
/*     */   }
/*     */ 
/*     */   void setMOD(MODSound sound)
/*     */   {
/* 869 */     if (!this.soundWorks) {
/* 870 */       return;
/*     */     }
/*     */ 
/* 873 */     this.currentMusic = this.sources.get(0);
/* 874 */     stopSource(0);
/*     */ 
/* 876 */     this.mod = sound;
/* 877 */     if (sound != null) {
/* 878 */       this.stream = null;
/*     */     }
/* 880 */     this.paused = false;
/*     */   }
/*     */ 
/*     */   void setStream(OpenALStreamPlayer stream)
/*     */   {
/* 889 */     if (!this.soundWorks) {
/* 890 */       return;
/*     */     }
/*     */ 
/* 893 */     this.currentMusic = this.sources.get(0);
/* 894 */     this.stream = stream;
/* 895 */     if (stream != null) {
/* 896 */       this.mod = null;
/*     */     }
/* 898 */     this.paused = false;
/*     */   }
/*     */ 
/*     */   public void poll(int delta)
/*     */   {
/* 907 */     if (!this.soundWorks) {
/* 908 */       return;
/*     */     }
/* 910 */     if (this.paused) {
/* 911 */       return;
/*     */     }
/*     */ 
/* 914 */     if (this.music) {
/* 915 */       if (this.mod != null) {
/*     */         try {
/* 917 */           this.mod.poll();
/*     */         } catch (OpenALException e) {
/* 919 */           Log.error("Error with OpenGL MOD Player on this this platform");
/* 920 */           Log.error(e);
/* 921 */           this.mod = null;
/*     */         }
/*     */       }
/* 924 */       if (this.stream != null)
/*     */         try {
/* 926 */           this.stream.update();
/*     */         } catch (OpenALException e) {
/* 928 */           Log.error("Error with OpenGL Streaming Player on this this platform");
/* 929 */           Log.error(e);
/* 930 */           this.mod = null;
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isMusicPlaying()
/*     */   {
/* 943 */     if (!this.soundWorks) {
/* 944 */       return false;
/*     */     }
/*     */ 
/* 947 */     int state = AL10.alGetSourcei(this.sources.get(0), 4112);
/* 948 */     return (state == 4114) || (state == 4115);
/*     */   }
/*     */ 
/*     */   public static SoundStore get()
/*     */   {
/* 957 */     return store;
/*     */   }
/*     */ 
/*     */   public void stopSoundEffect(int id)
/*     */   {
/* 968 */     AL10.alSourceStop(id);
/*     */   }
/*     */ 
/*     */   public int getSourceCount()
/*     */   {
/* 978 */     return this.sourceCount;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.SoundStore
 * JD-Core Version:    0.6.2
 */