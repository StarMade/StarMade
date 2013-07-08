/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */
/* 10:   */public class RemoteInteger
/* 11:   */  extends RemoteComparable
/* 12:   */{
/* 13:   */  public RemoteInteger(Integer paramInteger, boolean paramBoolean)
/* 14:   */  {
/* 15:15 */    super(paramInteger, paramBoolean);
/* 16:   */  }
/* 17:   */  
/* 18:   */  public RemoteInteger(Integer paramInteger, NetworkObject paramNetworkObject) {
/* 19:19 */    super(paramInteger, paramNetworkObject);
/* 20:   */  }
/* 21:   */  
/* 22:22 */  public RemoteInteger(NetworkObject paramNetworkObject) { this(Integer.valueOf(0), paramNetworkObject); }
/* 23:   */  
/* 24:   */  public RemoteInteger(boolean paramBoolean) {
/* 25:25 */    this(Integer.valueOf(0), paramBoolean);
/* 26:   */  }
/* 27:   */  
/* 28:   */  public int byteLength() {
/* 29:29 */    return 4;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 33:33 */    set(Integer.valueOf(paramDataInputStream.readInt()));
/* 34:   */  }
/* 35:   */  
/* 36:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 37:   */  {
/* 38:38 */    paramDataOutputStream.writeInt(((Integer)get()).intValue());
/* 39:39 */    return 1;
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteInteger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */