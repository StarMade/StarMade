/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import lR;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */
/* 12:   */public class RemoteFactionInvitation
/* 13:   */  extends RemoteField
/* 14:   */{
/* 15:   */  public RemoteFactionInvitation(lR paramlR, NetworkObject paramNetworkObject)
/* 16:   */  {
/* 17:17 */    super(paramlR, paramNetworkObject);
/* 18:   */  }
/* 19:   */  
/* 20:20 */  public RemoteFactionInvitation(lR paramlR, boolean paramBoolean) { super(paramlR, paramBoolean); }
/* 21:   */  
/* 23:   */  public int byteLength()
/* 24:   */  {
/* 25:25 */    return 4;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 29:29 */    paramInt = paramDataInputStream.readUTF();
/* 30:30 */    String str = paramDataInputStream.readUTF();
/* 31:31 */    int i = paramDataInputStream.readInt();
/* 32:32 */    long l = paramDataInputStream.readLong();
/* 33:   */    
/* 34:34 */    ((lR)get()).a(paramInt, str, i, l);
/* 35:   */  }
/* 36:   */  
/* 40:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 41:   */  {
/* 42:42 */    paramDataOutputStream.writeUTF(((lR)get()).b());
/* 43:43 */    paramDataOutputStream.writeUTF(((lR)get()).a());
/* 44:44 */    paramDataOutputStream.writeInt(((lR)get()).a());
/* 45:45 */    paramDataOutputStream.writeLong(((lR)get()).a());
/* 46:   */    
/* 48:48 */    return byteLength();
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionInvitation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */