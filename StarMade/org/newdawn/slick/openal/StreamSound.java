/*   1:    */package org.newdawn.slick.openal;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferUtils;
/*   6:    */import org.lwjgl.openal.AL10;
/*   7:    */import org.newdawn.slick.util.Log;
/*   8:    */
/*  21:    */public class StreamSound
/*  22:    */  extends AudioImpl
/*  23:    */{
/*  24:    */  private OpenALStreamPlayer player;
/*  25:    */  
/*  26:    */  public StreamSound(OpenALStreamPlayer player)
/*  27:    */  {
/*  28: 28 */    this.player = player;
/*  29:    */  }
/*  30:    */  
/*  33:    */  public boolean isPlaying()
/*  34:    */  {
/*  35: 35 */    return SoundStore.get().isPlaying(this.player);
/*  36:    */  }
/*  37:    */  
/*  39:    */  public int playAsMusic(float pitch, float gain, boolean loop)
/*  40:    */  {
/*  41:    */    try
/*  42:    */    {
/*  43: 43 */      cleanUpSource();
/*  44:    */      
/*  45: 45 */      this.player.setup(pitch);
/*  46: 46 */      this.player.play(loop);
/*  47: 47 */      SoundStore.get().setStream(this.player);
/*  48:    */    } catch (IOException e) {
/*  49: 49 */      Log.error("Failed to read OGG source: " + this.player.getSource());
/*  50:    */    }
/*  51:    */    
/*  52: 52 */    return SoundStore.get().getSource(0);
/*  53:    */  }
/*  54:    */  
/*  57:    */  private void cleanUpSource()
/*  58:    */  {
/*  59: 59 */    SoundStore store = SoundStore.get();
/*  60:    */    
/*  61: 61 */    AL10.alSourceStop(store.getSource(0));
/*  62: 62 */    IntBuffer buffer = BufferUtils.createIntBuffer(1);
/*  63: 63 */    int queued = AL10.alGetSourcei(store.getSource(0), 4117);
/*  64:    */    
/*  65: 65 */    while (queued > 0)
/*  66:    */    {
/*  67: 67 */      AL10.alSourceUnqueueBuffers(store.getSource(0), buffer);
/*  68: 68 */      queued--;
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    AL10.alSourcei(store.getSource(0), 4105, 0);
/*  72:    */  }
/*  73:    */  
/*  76:    */  public int playAsSoundEffect(float pitch, float gain, boolean loop, float x, float y, float z)
/*  77:    */  {
/*  78: 78 */    return playAsMusic(pitch, gain, loop);
/*  79:    */  }
/*  80:    */  
/*  83:    */  public int playAsSoundEffect(float pitch, float gain, boolean loop)
/*  84:    */  {
/*  85: 85 */    return playAsMusic(pitch, gain, loop);
/*  86:    */  }
/*  87:    */  
/*  90:    */  public void stop()
/*  91:    */  {
/*  92: 92 */    SoundStore.get().setStream(null);
/*  93:    */  }
/*  94:    */  
/*  97:    */  public boolean setPosition(float position)
/*  98:    */  {
/*  99: 99 */    return this.player.setPosition(position);
/* 100:    */  }
/* 101:    */  
/* 104:    */  public float getPosition()
/* 105:    */  {
/* 106:106 */    return this.player.getPosition();
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.StreamSound
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */