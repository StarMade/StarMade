/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */
/* 10:   */public class RemoteShort
/* 11:   */  extends RemoteComparable
/* 12:   */{
/* 13:   */  public RemoteShort(Short paramShort, NetworkObject paramNetworkObject)
/* 14:   */  {
/* 15:15 */    super(paramShort, paramNetworkObject);
/* 16:   */  }
/* 17:   */  
/* 18:   */  public RemoteShort(NetworkObject paramNetworkObject) {
/* 19:19 */    this(Short.valueOf((short)0), paramNetworkObject);
/* 20:   */  }
/* 21:   */  
/* 22:   */  public RemoteShort(boolean paramBoolean) {
/* 23:23 */    this((short)0, paramBoolean);
/* 24:   */  }
/* 25:   */  
/* 26:   */  public RemoteShort(short paramShort, boolean paramBoolean) {
/* 27:27 */    super(Short.valueOf(paramShort), paramBoolean);
/* 28:   */  }
/* 29:   */  
/* 30:   */  public int byteLength()
/* 31:   */  {
/* 32:32 */    return 2;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 36:36 */    paramDataInputStream = paramDataInputStream.readShort();
/* 37:37 */    set(Short.valueOf(paramDataInputStream));
/* 38:   */  }
/* 39:   */  
/* 40:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 41:   */  {
/* 42:42 */    paramDataOutputStream.writeShort(((Short)get()).shortValue());
/* 43:43 */    return 2;
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteShort
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */