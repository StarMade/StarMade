/*   1:    */package org.schema.schine.network.objects.remote;
/*   2:    */
/*   3:    */import java.io.DataInputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import org.schema.schine.network.objects.NetworkObject;
/*   6:    */
/*   9:    */public class RemoteLongPrimitiveArray
/*  10:    */  implements Streamable, StreamableArray
/*  11:    */{
/*  12:    */  private boolean changed;
/*  13:    */  private NetworkChangeObserver observer;
/*  14:    */  protected boolean keepChanged;
/*  15:    */  private final boolean onServer;
/*  16:    */  private boolean forcedClientSending;
/*  17:    */  private long[] array;
/*  18:    */  
/*  19:    */  public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*  20:    */  {
/*  21: 21 */    this.array = new long[paramInt];
/*  22:    */    
/*  23: 23 */    this.onServer = paramBoolean2;
/*  24: 24 */    this.changed = paramBoolean1;
/*  25:    */  }
/*  26:    */  
/*  27:    */  public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  28: 28 */    this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
/*  29: 29 */    assert (paramNetworkObject != null);
/*  30:    */  }
/*  31:    */  
/*  32: 32 */  public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean) { this(paramInt, false, paramBoolean); }
/*  33:    */  
/*  34:    */  public RemoteLongPrimitiveArray(int paramInt, NetworkObject paramNetworkObject) {
/*  35: 35 */    this(paramInt, false, paramNetworkObject);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public boolean hasChanged()
/*  39:    */  {
/*  40: 40 */    return this.changed;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public void setObserver(NetworkChangeObserver paramNetworkChangeObserver)
/*  44:    */  {
/*  45: 45 */    this.observer = paramNetworkChangeObserver;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public void setChanged(boolean paramBoolean)
/*  49:    */  {
/*  50: 50 */    this.changed = paramBoolean;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public boolean keepChanged()
/*  54:    */  {
/*  55: 55 */    return this.keepChanged;
/*  56:    */  }
/*  57:    */  
/*  58:    */  public int byteLength()
/*  59:    */  {
/*  60: 60 */    return 4;
/*  61:    */  }
/*  62:    */  
/*  64:    */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*  65:    */  {
/*  66: 66 */    for (paramInt = 0; paramInt < this.array.length; paramInt++) {
/*  67: 67 */      set(paramInt, paramDataInputStream.readLong(), this.forcedClientSending);
/*  68:    */    }
/*  69:    */  }
/*  70:    */  
/*  71:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/*  72:    */  {
/*  73: 73 */    for (int i = 0; i < this.array.length; i++) {
/*  74: 74 */      paramDataOutputStream.writeLong(this.array[i]);
/*  75:    */    }
/*  76: 76 */    return 4;
/*  77:    */  }
/*  78:    */  
/*  79: 79 */  public void forceClientUpdates() { this.forcedClientSending = true; }
/*  80:    */  
/*  81:    */  public void set(long[] paramArrayOfLong) {
/*  82: 82 */    set(paramArrayOfLong, this.forcedClientSending);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public void set(Long[] paramArrayOfLong) {
/*  86: 86 */    for (int i = 0; i < paramArrayOfLong.length; i++) {
/*  87: 87 */      set(i, paramArrayOfLong[i].longValue(), this.forcedClientSending);
/*  88:    */    }
/*  89:    */  }
/*  90:    */  
/*  91: 91 */  public void set(int paramInt, long paramLong) { set(paramInt, paramLong, this.forcedClientSending); }
/*  92:    */  
/*  93:    */  public void set(int paramInt, long paramLong, boolean paramBoolean) {
/*  94: 94 */    if ((this.onServer) || (paramBoolean))
/*  95:    */    {
/*  96: 96 */      setChanged((hasChanged()) || (paramLong != this.array[paramInt]));
/*  97:    */    }
/*  98: 98 */    this.array[paramInt] = paramLong;
/*  99:    */    
/* 100:100 */    if ((hasChanged()) && (this.observer != null))
/* 101:    */    {
/* 102:102 */      this.observer.update(this);
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 109:109 */  public long[] getLongArray() { return this.array; }
/* 110:    */  
/* 111:    */  public void set(long[] paramArrayOfLong, boolean paramBoolean) {
/* 112:112 */    for (int i = 0; i < paramArrayOfLong.length; i++) {
/* 113:113 */      set(i, paramArrayOfLong[i], paramBoolean);
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void set(Long[] paramArrayOfLong, boolean paramBoolean) {
/* 118:118 */    for (int i = 0; i < paramArrayOfLong.length; i++) {
/* 119:119 */      set(i, paramArrayOfLong[i].longValue(), paramBoolean);
/* 120:    */    }
/* 121:    */  }
/* 122:    */  
/* 127:    */  public Long[] get()
/* 128:    */  {
/* 129:129 */    if (!$assertionsDisabled) throw new AssertionError();
/* 130:130 */    Long[] arrayOfLong = new Long[this.array.length];
/* 131:131 */    for (int i = 0; i < arrayOfLong.length; i++) {
/* 132:132 */      arrayOfLong[i] = Long.valueOf(this.array[i]);
/* 133:    */    }
/* 134:134 */    return arrayOfLong;
/* 135:    */  }
/* 136:    */  
/* 139:    */  public void cleanAtRelease() {}
/* 140:    */  
/* 143:    */  public int arrayLength()
/* 144:    */  {
/* 145:145 */    return this.array.length;
/* 146:    */  }
/* 147:    */  
/* 149:    */  public boolean initialSynchUpdateOnly()
/* 150:    */  {
/* 151:151 */    return false;
/* 152:    */  }
/* 153:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongPrimitiveArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */