/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.util.Arrays;
/*  4:   */import org.schema.schine.network.objects.NetworkObject;
/*  5:   */
/*  6:   */public class RemoteStringArray extends RemoteArray
/*  7:   */{
/*  8:   */  public RemoteStringArray(int paramInt, NetworkObject paramNetworkObject)
/*  9:   */  {
/* 10:10 */    super(new RemoteString[paramInt], paramNetworkObject);
/* 11:   */    
/* 12:12 */    for (int i = 0; i < paramInt; i++) {
/* 13:13 */      ((RemoteField[])get())[i] = new RemoteString(paramNetworkObject);
/* 14:   */    }
/* 15:15 */    addObservers();
/* 16:   */  }
/* 17:   */  
/* 18:   */  public RemoteStringArray(int paramInt, boolean paramBoolean) {
/* 19:19 */    super(new RemoteString[paramInt], paramBoolean);
/* 20:   */    
/* 21:21 */    for (int i = 0; i < paramInt; i++) {
/* 22:22 */      ((RemoteField[])get())[i] = new RemoteString(paramBoolean);
/* 23:   */    }
/* 24:24 */    addObservers();
/* 25:   */  }
/* 26:   */  
/* 28:   */  public int byteLength()
/* 29:   */  {
/* 30:30 */    int i = 0;
/* 31:31 */    for (int j = 0; j < ((RemoteField[])get()).length; j++) {
/* 32:32 */      i += ((RemoteField[])get())[j].byteLength();
/* 33:   */    }
/* 34:34 */    return i;
/* 35:   */  }
/* 36:   */  
/* 37:   */  protected void init(RemoteField[] paramArrayOfRemoteField)
/* 38:   */  {
/* 39:39 */    set(paramArrayOfRemoteField);
/* 40:   */  }
/* 41:   */  
/* 44:   */  public void set(int paramInt, String paramString)
/* 45:   */  {
/* 46:46 */    ((RemoteField[])super.get())[paramInt].set(paramString, this.forcedClientSending);
/* 47:   */  }
/* 48:   */  
/* 51:   */  public void set(RemoteField[] paramArrayOfRemoteField)
/* 52:   */  {
/* 53:53 */    super.set(paramArrayOfRemoteField);
/* 54:54 */    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 55:55 */      ((RemoteField[])get())[i] = new RemoteString(this.onServer);
/* 56:56 */      ((RemoteField[])get())[i].observer = this;
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 64:   */  public String toString()
/* 65:   */  {
/* 66:66 */    return "RemoteStringArray" + Arrays.toString((Object[])get());
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteStringArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */