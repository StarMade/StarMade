/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */
/* 10:   */public class RemoteByte
/* 11:   */  extends RemoteComparable
/* 12:   */{
/* 13:   */  public RemoteByte(Byte paramByte, NetworkObject paramNetworkObject)
/* 14:   */  {
/* 15:15 */    super(paramByte, paramNetworkObject);
/* 16:   */  }
/* 17:   */  
/* 18:   */  public RemoteByte(NetworkObject paramNetworkObject) {
/* 19:19 */    this(Byte.valueOf((byte)0), paramNetworkObject);
/* 20:   */  }
/* 21:   */  
/* 22:22 */  public RemoteByte(boolean paramBoolean) { this((byte)0, paramBoolean); }
/* 23:   */  
/* 24:   */  public RemoteByte(byte paramByte, boolean paramBoolean)
/* 25:   */  {
/* 26:26 */    super(Byte.valueOf(paramByte), paramBoolean);
/* 27:   */  }
/* 28:   */  
/* 30:   */  public int byteLength()
/* 31:   */  {
/* 32:32 */    return 1;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 36:36 */    paramDataInputStream = paramDataInputStream.readByte();
/* 37:37 */    set(Byte.valueOf(paramDataInputStream));
/* 38:   */  }
/* 39:   */  
/* 40:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 41:   */  {
/* 42:42 */    paramDataOutputStream.writeByte(((Byte)get()).byteValue());
/* 43:43 */    return 1;
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteByte
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */