/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.NetworkObject;
/*  4:   */
/*  6:   */public class RemoteIntegerArray
/*  7:   */  extends RemoteArray
/*  8:   */{
/*  9:   */  private int[] transientArray;
/* 10:   */  
/* 11:   */  public RemoteIntegerArray(int paramInt, NetworkObject paramNetworkObject)
/* 12:   */  {
/* 13:13 */    super(new RemoteInteger[paramInt], paramNetworkObject);
/* 14:   */  }
/* 15:   */  
/* 16:   */  public RemoteIntegerArray(int paramInt, boolean paramBoolean) {
/* 17:17 */    super(new RemoteInteger[paramInt], paramBoolean);
/* 18:   */  }
/* 19:   */  
/* 21:   */  public int byteLength()
/* 22:   */  {
/* 23:23 */    return ((RemoteField[])get()).length << 2;
/* 24:   */  }
/* 25:   */  
/* 28:   */  public int[] getTransientArray()
/* 29:   */  {
/* 30:30 */    return this.transientArray;
/* 31:   */  }
/* 32:   */  
/* 33:   */  protected void init(RemoteField[] paramArrayOfRemoteField)
/* 34:   */  {
/* 35:35 */    set(paramArrayOfRemoteField);
/* 36:   */  }
/* 37:   */  
/* 43:   */  public void set(int paramInt, Integer paramInteger)
/* 44:   */  {
/* 45:45 */    this.transientArray[paramInt] = paramInteger.intValue();
/* 46:46 */    ((RemoteField[])super.get())[paramInt].set(paramInteger, this.forcedClientSending);
/* 47:   */  }
/* 48:   */  
/* 51:   */  public void set(RemoteField[] paramArrayOfRemoteField)
/* 52:   */  {
/* 53:53 */    super.set(paramArrayOfRemoteField);
/* 54:54 */    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 55:55 */      ((RemoteField[])get())[i] = new RemoteInteger(Integer.valueOf(0), this.onServer);
/* 56:   */    }
/* 57:57 */    this.transientArray = new int[paramArrayOfRemoteField.length];
/* 58:58 */    addObservers();
/* 59:   */  }
/* 60:   */  
/* 62:   */  public void setArray(int[] paramArrayOfInt)
/* 63:   */  {
/* 64:64 */    if (paramArrayOfInt.length != ((RemoteField[])get()).length) {
/* 65:65 */      throw new IllegalArgumentException("Cannot change array size of remote array");
/* 66:   */    }
/* 67:   */    
/* 68:68 */    for (int i = 0; i < this.transientArray.length; i++) {
/* 69:69 */      this.transientArray[i] = paramArrayOfInt[i];
/* 70:70 */      get(i).set(Integer.valueOf(paramArrayOfInt[i]), this.forcedClientSending);
/* 71:   */    }
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteIntegerArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */