/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */
/* 10:   */public class RemoteBoolean
/* 11:   */  extends RemoteComparable
/* 12:   */{
/* 13:   */  public RemoteBoolean(boolean paramBoolean, NetworkObject paramNetworkObject)
/* 14:   */  {
/* 15:15 */    super(Boolean.valueOf(paramBoolean), paramNetworkObject);
/* 16:   */  }
/* 17:   */  
/* 18:18 */  public RemoteBoolean(NetworkObject paramNetworkObject) { this(false, paramNetworkObject); }
/* 19:   */  
/* 20:   */  public RemoteBoolean(boolean paramBoolean) {
/* 21:21 */    this(false, paramBoolean);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public RemoteBoolean(boolean paramBoolean1, boolean paramBoolean2) {
/* 25:25 */    super(Boolean.valueOf(paramBoolean1), paramBoolean2);
/* 26:   */  }
/* 27:   */  
/* 28:   */  public int byteLength() {
/* 29:29 */    return 1;
/* 30:   */  }
/* 31:   */  
/* 34:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 35:   */  {
/* 36:36 */    set(Boolean.valueOf(paramDataInputStream.readBoolean()));
/* 37:   */  }
/* 38:   */  
/* 39:   */  public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 40:40 */    paramDataOutputStream.writeBoolean(((Boolean)get()).booleanValue());
/* 41:41 */    return 1;
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBoolean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */