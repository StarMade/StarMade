/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.NetworkObject;
/*  4:   */
/*  5:   */public class RemoteByteArray
/*  6:   */  extends RemoteArray
/*  7:   */{
/*  8:   */  private byte[] transientArray;
/*  9:   */  
/* 10:   */  public RemoteByteArray(int paramInt, NetworkObject paramNetworkObject)
/* 11:   */  {
/* 12:12 */    super(new RemoteByte[paramInt], paramNetworkObject);
/* 13:   */  }
/* 14:   */  
/* 15:15 */  public RemoteByteArray(int paramInt, boolean paramBoolean) { super(new RemoteByte[paramInt], paramBoolean); }
/* 16:   */  
/* 19:   */  public int byteLength()
/* 20:   */  {
/* 21:21 */    return ((RemoteField[])get()).length;
/* 22:   */  }
/* 23:   */  
/* 26:   */  public byte[] getTransientArray()
/* 27:   */  {
/* 28:28 */    return this.transientArray;
/* 29:   */  }
/* 30:   */  
/* 31:   */  protected void init(RemoteField[] paramArrayOfRemoteField)
/* 32:   */  {
/* 33:33 */    set(paramArrayOfRemoteField);
/* 34:   */  }
/* 35:   */  
/* 47:   */  public void set(int paramInt, Byte paramByte)
/* 48:   */  {
/* 49:49 */    this.transientArray[paramInt] = paramByte.byteValue();
/* 50:50 */    ((RemoteField[])super.get())[paramInt].set(paramByte, this.forcedClientSending);
/* 51:   */  }
/* 52:   */  
/* 55:   */  public void set(RemoteField[] paramArrayOfRemoteField)
/* 56:   */  {
/* 57:57 */    super.set(paramArrayOfRemoteField);
/* 58:58 */    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 59:59 */      ((RemoteField[])get())[i] = new RemoteByte(0, this.onServer);
/* 60:   */    }
/* 61:61 */    this.transientArray = new byte[paramArrayOfRemoteField.length];
/* 62:62 */    addObservers();
/* 63:   */  }
/* 64:   */  
/* 73:   */  public void setArray(byte[] paramArrayOfByte)
/* 74:   */  {
/* 75:75 */    if (paramArrayOfByte.length != ((RemoteField[])get()).length) {
/* 76:76 */      throw new IllegalArgumentException("Cannot change array size of remote array");
/* 77:   */    }
/* 78:   */    
/* 79:79 */    for (int i = 0; i < this.transientArray.length; i++) {
/* 80:80 */      this.transientArray[i] = paramArrayOfByte[i];
/* 81:81 */      get(i).set(Byte.valueOf(paramArrayOfByte[i]), this.forcedClientSending);
/* 82:   */    }
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteByteArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */