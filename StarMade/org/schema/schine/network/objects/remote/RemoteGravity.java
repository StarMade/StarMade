/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */import org.schema.schine.network.NetworkGravity;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */
/* 11:   */public class RemoteGravity
/* 12:   */  extends RemoteField
/* 13:   */{
/* 14:   */  public RemoteGravity(NetworkGravity paramNetworkGravity, NetworkObject paramNetworkObject)
/* 15:   */  {
/* 16:16 */    super(paramNetworkGravity, paramNetworkObject);
/* 17:   */  }
/* 18:   */  
/* 19:19 */  public RemoteGravity(NetworkGravity paramNetworkGravity, boolean paramBoolean) { super(paramNetworkGravity, paramBoolean); }
/* 20:   */  
/* 22:   */  public int byteLength()
/* 23:   */  {
/* 24:24 */    return 1;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 28:28 */    ((NetworkGravity)get()).gravityIdReceive = paramDataInputStream.readInt();
/* 29:29 */    ((NetworkGravity)get()).gravityReceive.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 30:30 */    ((NetworkGravity)get()).gravityReceived = true;
/* 31:   */  }
/* 32:   */  
/* 35:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 36:   */  {
/* 37:37 */    paramDataOutputStream.writeInt(((NetworkGravity)get()).gravityId);
/* 38:38 */    paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.x);
/* 39:39 */    paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.y);
/* 40:40 */    paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.z);
/* 41:   */    
/* 42:42 */    return 1;
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteGravity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */