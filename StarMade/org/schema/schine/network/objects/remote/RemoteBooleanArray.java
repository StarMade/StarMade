/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.NetworkObject;
/*  4:   */
/*  7:   */public class RemoteBooleanArray
/*  8:   */  extends RemoteArray
/*  9:   */{
/* 10:   */  private boolean[] transientArray;
/* 11:   */  
/* 12:   */  public RemoteBooleanArray(int paramInt, NetworkObject paramNetworkObject)
/* 13:   */  {
/* 14:14 */    super(new RemoteBoolean[paramInt], paramNetworkObject);
/* 15:   */  }
/* 16:   */  
/* 17:   */  public RemoteBooleanArray(int paramInt, boolean paramBoolean) {
/* 18:18 */    super(new RemoteBoolean[paramInt], paramBoolean);
/* 19:   */  }
/* 20:   */  
/* 22:   */  public int byteLength()
/* 23:   */  {
/* 24:24 */    return ((RemoteField[])get()).length;
/* 25:   */  }
/* 26:   */  
/* 29:   */  public boolean[] getTransientArray()
/* 30:   */  {
/* 31:31 */    return this.transientArray;
/* 32:   */  }
/* 33:   */  
/* 34:   */  protected void init(RemoteField[] paramArrayOfRemoteField)
/* 35:   */  {
/* 36:36 */    set(paramArrayOfRemoteField);
/* 37:   */  }
/* 38:   */  
/* 58:   */  public void set(int paramInt, Boolean paramBoolean)
/* 59:   */  {
/* 60:60 */    this.transientArray[paramInt] = paramBoolean.booleanValue();
/* 61:61 */    ((RemoteField[])super.get())[paramInt].set(paramBoolean, this.forcedClientSending);
/* 62:   */  }
/* 63:   */  
/* 65:   */  public void set(RemoteField[] paramArrayOfRemoteField)
/* 66:   */  {
/* 67:67 */    super.set(paramArrayOfRemoteField);
/* 68:   */    
/* 69:69 */    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 70:70 */      ((RemoteField[])get())[i] = new RemoteBoolean(false, this.onServer);
/* 71:   */    }
/* 72:72 */    this.transientArray = new boolean[paramArrayOfRemoteField.length];
/* 73:73 */    addObservers();
/* 74:   */  }
/* 75:   */  
/* 77:   */  public void setArray(boolean[] paramArrayOfBoolean)
/* 78:   */  {
/* 79:79 */    if (paramArrayOfBoolean.length != ((RemoteField[])get()).length) {
/* 80:80 */      throw new IllegalArgumentException("Cannot change array size of remote array");
/* 81:   */    }
/* 82:   */    
/* 83:83 */    for (int i = 0; i < this.transientArray.length; i++)
/* 84:   */    {
/* 85:85 */      this.transientArray[i] = paramArrayOfBoolean[i];
/* 86:86 */      get(i).set(Boolean.valueOf(paramArrayOfBoolean[i]), this.forcedClientSending);
/* 87:   */    }
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBooleanArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */