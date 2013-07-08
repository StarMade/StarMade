/*   1:    */package org.newdawn.slick.openal;
/*   2:    */
/*   3:    */import ibxm.Module;
/*   4:    */import ibxm.OpenALMODPlayer;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.InputStream;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */import org.lwjgl.openal.AL10;
/*  10:    */
/*  17:    */public class MODSound
/*  18:    */  extends AudioImpl
/*  19:    */{
/*  20: 20 */  private static OpenALMODPlayer player = new OpenALMODPlayer();
/*  21:    */  
/*  24:    */  private Module module;
/*  25:    */  
/*  28:    */  private SoundStore store;
/*  29:    */  
/*  32:    */  public MODSound(SoundStore store, InputStream in)
/*  33:    */    throws IOException
/*  34:    */  {
/*  35: 35 */    this.store = store;
/*  36: 36 */    this.module = OpenALMODPlayer.loadModule(in);
/*  37:    */  }
/*  38:    */  
/*  41:    */  public int playAsMusic(float pitch, float gain, boolean loop)
/*  42:    */  {
/*  43: 43 */    cleanUpSource();
/*  44:    */    
/*  45: 45 */    player.play(this.module, this.store.getSource(0), loop, SoundStore.get().isMusicOn());
/*  46: 46 */    player.setup(pitch, 1.0F);
/*  47: 47 */    this.store.setCurrentMusicVolume(gain);
/*  48:    */    
/*  49: 49 */    this.store.setMOD(this);
/*  50:    */    
/*  51: 51 */    return this.store.getSource(0);
/*  52:    */  }
/*  53:    */  
/*  56:    */  private void cleanUpSource()
/*  57:    */  {
/*  58: 58 */    AL10.alSourceStop(this.store.getSource(0));
/*  59: 59 */    IntBuffer buffer = BufferUtils.createIntBuffer(1);
/*  60: 60 */    int queued = AL10.alGetSourcei(this.store.getSource(0), 4117);
/*  61:    */    
/*  62: 62 */    while (queued > 0)
/*  63:    */    {
/*  64: 64 */      AL10.alSourceUnqueueBuffers(this.store.getSource(0), buffer);
/*  65: 65 */      queued--;
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    AL10.alSourcei(this.store.getSource(0), 4105, 0);
/*  69:    */  }
/*  70:    */  
/*  73:    */  public void poll()
/*  74:    */  {
/*  75: 75 */    player.update();
/*  76:    */  }
/*  77:    */  
/*  80:    */  public int playAsSoundEffect(float pitch, float gain, boolean loop)
/*  81:    */  {
/*  82: 82 */    return -1;
/*  83:    */  }
/*  84:    */  
/*  87:    */  public void stop()
/*  88:    */  {
/*  89: 89 */    this.store.setMOD(null);
/*  90:    */  }
/*  91:    */  
/*  94:    */  public float getPosition()
/*  95:    */  {
/*  96: 96 */    throw new RuntimeException("Positioning on modules is not currently supported");
/*  97:    */  }
/*  98:    */  
/* 101:    */  public boolean setPosition(float position)
/* 102:    */  {
/* 103:103 */    throw new RuntimeException("Positioning on modules is not currently supported");
/* 104:    */  }
/* 105:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.MODSound
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */