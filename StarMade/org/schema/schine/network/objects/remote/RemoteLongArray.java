/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.NetworkObject;
/*  4:   */
/*  6:   */public class RemoteLongArray
/*  7:   */  extends RemoteArray
/*  8:   */{
/*  9:   */  private long[] transientArray;
/* 10:   */  
/* 11:   */  public RemoteLongArray(int paramInt, NetworkObject paramNetworkObject)
/* 12:   */  {
/* 13:13 */    super(new RemoteLong[paramInt], paramNetworkObject);
/* 14:   */  }
/* 15:   */  
/* 16:16 */  public RemoteLongArray(int paramInt, boolean paramBoolean) { super(new RemoteLong[paramInt], paramBoolean); }
/* 17:   */  
/* 19:   */  public int byteLength()
/* 20:   */  {
/* 21:21 */    return ((RemoteField[])get()).length << 3;
/* 22:   */  }
/* 23:   */  
/* 26:   */  public long[] getTransientArray()
/* 27:   */  {
/* 28:28 */    return this.transientArray;
/* 29:   */  }
/* 30:   */  
/* 31:   */  protected void init(RemoteField[] paramArrayOfRemoteField)
/* 32:   */  {
/* 33:33 */    set(paramArrayOfRemoteField);
/* 34:   */  }
/* 35:   */  
/* 45:   */  public void set(int paramInt, Long paramLong)
/* 46:   */  {
/* 47:47 */    this.transientArray[paramInt] = paramLong.longValue();
/* 48:48 */    ((RemoteField[])super.get())[paramInt].set(paramLong, this.forcedClientSending);
/* 49:   */  }
/* 50:   */  
/* 53:   */  public void set(RemoteField[] paramArrayOfRemoteField)
/* 54:   */  {
/* 55:55 */    super.set(paramArrayOfRemoteField);
/* 56:56 */    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 57:57 */      ((RemoteField[])get())[i] = new RemoteLong(0L, this.onServer);
/* 58:   */    }
/* 59:59 */    this.transientArray = new long[paramArrayOfRemoteField.length];
/* 60:60 */    addObservers();
/* 61:   */  }
/* 62:   */  
/* 64:   */  public void setArray(long[] paramArrayOfLong)
/* 65:   */  {
/* 66:66 */    if (paramArrayOfLong.length != ((RemoteField[])get()).length) {
/* 67:67 */      throw new IllegalArgumentException("Cannot change array size of remote array");
/* 68:   */    }
/* 69:   */    
/* 70:70 */    for (int i = 0; i < this.transientArray.length; i++) {
/* 71:71 */      this.transientArray[i] = paramArrayOfLong[i];
/* 72:72 */      get(i).set(Long.valueOf(paramArrayOfLong[i]), this.forcedClientSending);
/* 73:   */    }
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */