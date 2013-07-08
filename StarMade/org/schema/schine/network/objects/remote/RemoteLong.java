/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */
/*  9:   */public class RemoteLong
/* 10:   */  extends RemoteComparable
/* 11:   */{
/* 12:   */  public RemoteLong(Long paramLong, NetworkObject paramNetworkObject)
/* 13:   */  {
/* 14:14 */    super(paramLong, paramNetworkObject);
/* 15:   */  }
/* 16:   */  
/* 17:17 */  public RemoteLong(NetworkObject paramNetworkObject) { this(Long.valueOf(0L), paramNetworkObject); }
/* 18:   */  
/* 19:   */  public RemoteLong(boolean paramBoolean) {
/* 20:20 */    this(0L, paramBoolean);
/* 21:   */  }
/* 22:   */  
/* 24:   */  public RemoteLong(long paramLong, boolean paramBoolean)
/* 25:   */  {
/* 26:26 */    super(Long.valueOf(paramLong), paramBoolean);
/* 27:   */  }
/* 28:   */  
/* 29:   */  public int byteLength() {
/* 30:30 */    return 8;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 34:34 */    set(Long.valueOf(paramDataInputStream.readLong()));
/* 35:   */  }
/* 36:   */  
/* 37:   */  public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 38:38 */    paramDataOutputStream.writeLong(((Long)get()).longValue());
/* 39:39 */    return byteLength();
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLong
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */