/*   1:    */package org.newdawn.slick.openal;
/*   2:    */
/*   3:    */import org.lwjgl.openal.AL10;
/*   4:    */
/*  13:    */public class AudioImpl
/*  14:    */  implements Audio
/*  15:    */{
/*  16:    */  private SoundStore store;
/*  17:    */  private int buffer;
/*  18: 18 */  private int index = -1;
/*  19:    */  
/*  23:    */  private float length;
/*  24:    */  
/*  28:    */  AudioImpl(SoundStore store, int buffer)
/*  29:    */  {
/*  30: 30 */    this.store = store;
/*  31: 31 */    this.buffer = buffer;
/*  32:    */    
/*  33: 33 */    int bytes = AL10.alGetBufferi(buffer, 8196);
/*  34: 34 */    int bits = AL10.alGetBufferi(buffer, 8194);
/*  35: 35 */    int channels = AL10.alGetBufferi(buffer, 8195);
/*  36: 36 */    int freq = AL10.alGetBufferi(buffer, 8193);
/*  37:    */    
/*  38: 38 */    int samples = bytes / (bits / 8);
/*  39: 39 */    this.length = (samples / freq / channels);
/*  40:    */  }
/*  41:    */  
/*  47:    */  public int getBufferID()
/*  48:    */  {
/*  49: 49 */    return this.buffer;
/*  50:    */  }
/*  51:    */  
/*  56:    */  protected AudioImpl() {}
/*  57:    */  
/*  61:    */  public void stop()
/*  62:    */  {
/*  63: 63 */    if (this.index != -1) {
/*  64: 64 */      this.store.stopSource(this.index);
/*  65: 65 */      this.index = -1;
/*  66:    */    }
/*  67:    */  }
/*  68:    */  
/*  71:    */  public boolean isPlaying()
/*  72:    */  {
/*  73: 73 */    if (this.index != -1) {
/*  74: 74 */      return this.store.isPlaying(this.index);
/*  75:    */    }
/*  76:    */    
/*  77: 77 */    return false;
/*  78:    */  }
/*  79:    */  
/*  82:    */  public int playAsSoundEffect(float pitch, float gain, boolean loop)
/*  83:    */  {
/*  84: 84 */    this.index = this.store.playAsSound(this.buffer, pitch, gain, loop);
/*  85: 85 */    return this.store.getSource(this.index);
/*  86:    */  }
/*  87:    */  
/*  91:    */  public int playAsSoundEffect(float pitch, float gain, boolean loop, float x, float y, float z)
/*  92:    */  {
/*  93: 93 */    this.index = this.store.playAsSoundAt(this.buffer, pitch, gain, loop, x, y, z);
/*  94: 94 */    return this.store.getSource(this.index);
/*  95:    */  }
/*  96:    */  
/*  99:    */  public int playAsMusic(float pitch, float gain, boolean loop)
/* 100:    */  {
/* 101:101 */    this.store.playAsMusic(this.buffer, pitch, gain, loop);
/* 102:102 */    this.index = 0;
/* 103:103 */    return this.store.getSource(0);
/* 104:    */  }
/* 105:    */  
/* 108:    */  public static void pauseMusic()
/* 109:    */  {
/* 110:110 */    SoundStore.get().pauseLoop();
/* 111:    */  }
/* 112:    */  
/* 115:    */  public static void restartMusic()
/* 116:    */  {
/* 117:117 */    SoundStore.get().restartLoop();
/* 118:    */  }
/* 119:    */  
/* 122:    */  public boolean setPosition(float position)
/* 123:    */  {
/* 124:124 */    position %= this.length;
/* 125:    */    
/* 126:126 */    AL10.alSourcef(this.store.getSource(this.index), 4132, position);
/* 127:127 */    if (AL10.alGetError() != 0) {
/* 128:128 */      return false;
/* 129:    */    }
/* 130:130 */    return true;
/* 131:    */  }
/* 132:    */  
/* 135:    */  public float getPosition()
/* 136:    */  {
/* 137:137 */    return AL10.alGetSourcef(this.store.getSource(this.index), 4132);
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.AudioImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */