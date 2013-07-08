/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import mc;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */
/* 12:   */public class RemoteFactionRoles
/* 13:   */  extends RemoteField
/* 14:   */{
/* 15:   */  public RemoteFactionRoles(mc parammc, NetworkObject paramNetworkObject)
/* 16:   */  {
/* 17:17 */    super(parammc, paramNetworkObject);
/* 18:   */  }
/* 19:   */  
/* 20:20 */  public RemoteFactionRoles(mc parammc, boolean paramBoolean) { super(parammc, paramBoolean); }
/* 21:   */  
/* 23:   */  public int byteLength()
/* 24:   */  {
/* 25:25 */    return 4;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 29:29 */    ((mc)get()).a = paramDataInputStream.readInt();
/* 30:30 */    for (paramInt = 0; paramInt < 5; paramInt++) {
/* 31:31 */      ((mc)get()).a()[paramInt] = paramDataInputStream.readLong();
/* 32:32 */      ((mc)get()).a()[paramInt] = paramDataInputStream.readUTF();
/* 33:   */    }
/* 34:   */  }
/* 35:   */  
/* 36:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 37:   */  {
/* 38:38 */    paramDataOutputStream.writeInt(((mc)get()).a);
/* 39:39 */    for (int i = 0; i < 5; i++) {
/* 40:40 */      paramDataOutputStream.writeLong(((mc)get()).a()[i]);
/* 41:41 */      paramDataOutputStream.writeUTF(((mc)get()).a()[i]);
/* 42:   */    }
/* 43:   */    
/* 44:44 */    return byteLength();
/* 45:   */  }
/* 46:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionRoles
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */