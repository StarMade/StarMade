package org.newdawn.slick.openal;

import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.util.Log;

public class DeferredSound
  extends AudioImpl
  implements DeferredResource
{
  public static final int OGG = 1;
  public static final int WAV = 2;
  public static final int MOD = 3;
  public static final int AIF = 4;
  private int type;
  private String ref;
  private Audio target;
  private InputStream field_304;
  
  public DeferredSound(String ref, InputStream local_in, int type)
  {
    this.ref = ref;
    this.type = type;
    if (ref.equals(local_in.toString())) {
      this.field_304 = local_in;
    }
    LoadingList.get().add(this);
  }
  
  private void checkTarget()
  {
    if (this.target == null) {
      throw new RuntimeException("Attempt to use deferred sound before loading");
    }
  }
  
  public void load()
    throws IOException
  {
    boolean before = SoundStore.get().isDeferredLoading();
    SoundStore.get().setDeferredLoading(false);
    if (this.field_304 != null) {
      switch (this.type)
      {
      case 1: 
        this.target = SoundStore.get().getOgg(this.field_304);
        break;
      case 2: 
        this.target = SoundStore.get().getWAV(this.field_304);
        break;
      case 3: 
        this.target = SoundStore.get().getMOD(this.field_304);
        break;
      case 4: 
        this.target = SoundStore.get().getAIF(this.field_304);
        break;
      default: 
        Log.error("Unrecognised sound type: " + this.type);
        break;
      }
    } else {
      switch (this.type)
      {
      case 1: 
        this.target = SoundStore.get().getOgg(this.ref);
        break;
      case 2: 
        this.target = SoundStore.get().getWAV(this.ref);
        break;
      case 3: 
        this.target = SoundStore.get().getMOD(this.ref);
        break;
      case 4: 
        this.target = SoundStore.get().getAIF(this.ref);
        break;
      default: 
        Log.error("Unrecognised sound type: " + this.type);
      }
    }
    SoundStore.get().setDeferredLoading(before);
  }
  
  public boolean isPlaying()
  {
    checkTarget();
    return this.target.isPlaying();
  }
  
  public int playAsMusic(float pitch, float gain, boolean loop)
  {
    checkTarget();
    return this.target.playAsMusic(pitch, gain, loop);
  }
  
  public int playAsSoundEffect(float pitch, float gain, boolean loop)
  {
    checkTarget();
    return this.target.playAsSoundEffect(pitch, gain, loop);
  }
  
  public int playAsSoundEffect(float pitch, float gain, boolean loop, float local_x, float local_y, float local_z)
  {
    checkTarget();
    return this.target.playAsSoundEffect(pitch, gain, loop, local_x, local_y, local_z);
  }
  
  public void stop()
  {
    checkTarget();
    this.target.stop();
  }
  
  public String getDescription()
  {
    return this.ref;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.openal.DeferredSound
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */