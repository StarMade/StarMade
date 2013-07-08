/*   1:    */package org.newdawn.slick.openal;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import org.newdawn.slick.loading.DeferredResource;
/*   6:    */import org.newdawn.slick.loading.LoadingList;
/*   7:    */import org.newdawn.slick.util.Log;
/*   8:    */
/*  28:    */public class DeferredSound
/*  29:    */  extends AudioImpl
/*  30:    */  implements DeferredResource
/*  31:    */{
/*  32:    */  public static final int OGG = 1;
/*  33:    */  public static final int WAV = 2;
/*  34:    */  public static final int MOD = 3;
/*  35:    */  public static final int AIF = 4;
/*  36:    */  private int type;
/*  37:    */  private String ref;
/*  38:    */  private Audio target;
/*  39:    */  private InputStream in;
/*  40:    */  
/*  41:    */  public DeferredSound(String ref, InputStream in, int type)
/*  42:    */  {
/*  43: 43 */    this.ref = ref;
/*  44: 44 */    this.type = type;
/*  45:    */    
/*  47: 47 */    if (ref.equals(in.toString())) {
/*  48: 48 */      this.in = in;
/*  49:    */    }
/*  50:    */    
/*  51: 51 */    LoadingList.get().add(this);
/*  52:    */  }
/*  53:    */  
/*  56:    */  private void checkTarget()
/*  57:    */  {
/*  58: 58 */    if (this.target == null) {
/*  59: 59 */      throw new RuntimeException("Attempt to use deferred sound before loading");
/*  60:    */    }
/*  61:    */  }
/*  62:    */  
/*  64:    */  public void load()
/*  65:    */    throws IOException
/*  66:    */  {
/*  67: 67 */    boolean before = SoundStore.get().isDeferredLoading();
/*  68: 68 */    SoundStore.get().setDeferredLoading(false);
/*  69: 69 */    if (this.in != null) {
/*  70: 70 */      switch (this.type) {
/*  71:    */      case 1: 
/*  72: 72 */        this.target = SoundStore.get().getOgg(this.in);
/*  73: 73 */        break;
/*  74:    */      case 2: 
/*  75: 75 */        this.target = SoundStore.get().getWAV(this.in);
/*  76: 76 */        break;
/*  77:    */      case 3: 
/*  78: 78 */        this.target = SoundStore.get().getMOD(this.in);
/*  79: 79 */        break;
/*  80:    */      case 4: 
/*  81: 81 */        this.target = SoundStore.get().getAIF(this.in);
/*  82: 82 */        break;
/*  83:    */      default: 
/*  84: 84 */        Log.error("Unrecognised sound type: " + this.type);
/*  85: 85 */        break;
/*  86:    */      }
/*  87:    */    } else {
/*  88: 88 */      switch (this.type) {
/*  89:    */      case 1: 
/*  90: 90 */        this.target = SoundStore.get().getOgg(this.ref);
/*  91: 91 */        break;
/*  92:    */      case 2: 
/*  93: 93 */        this.target = SoundStore.get().getWAV(this.ref);
/*  94: 94 */        break;
/*  95:    */      case 3: 
/*  96: 96 */        this.target = SoundStore.get().getMOD(this.ref);
/*  97: 97 */        break;
/*  98:    */      case 4: 
/*  99: 99 */        this.target = SoundStore.get().getAIF(this.ref);
/* 100:100 */        break;
/* 101:    */      default: 
/* 102:102 */        Log.error("Unrecognised sound type: " + this.type);
/* 103:    */      }
/* 104:    */      
/* 105:    */    }
/* 106:106 */    SoundStore.get().setDeferredLoading(before);
/* 107:    */  }
/* 108:    */  
/* 111:    */  public boolean isPlaying()
/* 112:    */  {
/* 113:113 */    checkTarget();
/* 114:    */    
/* 115:115 */    return this.target.isPlaying();
/* 116:    */  }
/* 117:    */  
/* 120:    */  public int playAsMusic(float pitch, float gain, boolean loop)
/* 121:    */  {
/* 122:122 */    checkTarget();
/* 123:123 */    return this.target.playAsMusic(pitch, gain, loop);
/* 124:    */  }
/* 125:    */  
/* 128:    */  public int playAsSoundEffect(float pitch, float gain, boolean loop)
/* 129:    */  {
/* 130:130 */    checkTarget();
/* 131:131 */    return this.target.playAsSoundEffect(pitch, gain, loop);
/* 132:    */  }
/* 133:    */  
/* 143:    */  public int playAsSoundEffect(float pitch, float gain, boolean loop, float x, float y, float z)
/* 144:    */  {
/* 145:145 */    checkTarget();
/* 146:146 */    return this.target.playAsSoundEffect(pitch, gain, loop, x, y, z);
/* 147:    */  }
/* 148:    */  
/* 151:    */  public void stop()
/* 152:    */  {
/* 153:153 */    checkTarget();
/* 154:154 */    this.target.stop();
/* 155:    */  }
/* 156:    */  
/* 159:    */  public String getDescription()
/* 160:    */  {
/* 161:161 */    return this.ref;
/* 162:    */  }
/* 163:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.DeferredSound
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */