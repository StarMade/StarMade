/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.game.network.objects.DockingRequest;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */import q;
/*  9:   */
/* 13:   */public class RemoteDockLand
/* 14:   */  extends RemoteField
/* 15:   */{
/* 16:   */  public RemoteDockLand(DockingRequest paramDockingRequest, NetworkObject paramNetworkObject)
/* 17:   */  {
/* 18:18 */    super(paramDockingRequest, paramNetworkObject);
/* 19:   */  }
/* 20:   */  
/* 21:21 */  public RemoteDockLand(DockingRequest paramDockingRequest, boolean paramBoolean) { super(paramDockingRequest, paramBoolean); }
/* 22:   */  
/* 24:   */  public int byteLength()
/* 25:   */  {
/* 26:26 */    return 12 + ((DockingRequest)get()).id.length() + 4 + 1;
/* 27:   */  }
/* 28:   */  
/* 31:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 32:   */  {
/* 33:33 */    paramInt = paramDataInputStream.readBoolean();
/* 34:34 */    String str = paramDataInputStream.readUTF();
/* 35:35 */    int i = paramDataInputStream.readInt();
/* 36:36 */    int j = paramDataInputStream.readInt();
/* 37:37 */    paramDataInputStream = paramDataInputStream.readInt();
/* 38:   */    
/* 39:39 */    ((DockingRequest)get()).set(paramInt, str, new q(i, j, paramDataInputStream));
/* 40:   */  }
/* 41:   */  
/* 42:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 43:   */  {
/* 44:44 */    paramDataOutputStream.writeBoolean(((DockingRequest)get()).dock);
/* 45:45 */    paramDataOutputStream.writeUTF(((DockingRequest)get()).id);
/* 46:46 */    paramDataOutputStream.writeInt(((DockingRequest)get()).pos.a);
/* 47:47 */    paramDataOutputStream.writeInt(((DockingRequest)get()).pos.b);
/* 48:48 */    paramDataOutputStream.writeInt(((DockingRequest)get()).pos.c);
/* 49:   */    
/* 50:50 */    return byteLength();
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteDockLand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */