/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import lW;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */
/* 12:   */public class RemoteFactionNewsPost
/* 13:   */  extends RemoteField
/* 14:   */{
/* 15:   */  public RemoteFactionNewsPost(lW paramlW, NetworkObject paramNetworkObject)
/* 16:   */  {
/* 17:17 */    super(paramlW, paramNetworkObject);
/* 18:   */  }
/* 19:   */  
/* 20:20 */  public RemoteFactionNewsPost(lW paramlW, boolean paramBoolean) { super(paramlW, paramBoolean); }
/* 21:   */  
/* 23:   */  public int byteLength()
/* 24:   */  {
/* 25:25 */    return 4;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 29:   */  {
/* 30:30 */    paramInt = paramDataInputStream.readInt();
/* 31:31 */    String str1 = paramDataInputStream.readUTF();
/* 32:32 */    long l = paramDataInputStream.readLong();
/* 33:33 */    String str2 = paramDataInputStream.readUTF();
/* 34:34 */    paramDataInputStream = paramDataInputStream.readInt();
/* 35:   */    
/* 36:36 */    ((lW)get()).a(paramInt, str1, l, str2, paramDataInputStream);
/* 37:   */  }
/* 38:   */  
/* 41:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 42:   */  {
/* 43:43 */    paramDataOutputStream.writeInt(((lW)get()).b());
/* 44:44 */    paramDataOutputStream.writeUTF(((lW)get()).a());
/* 45:45 */    paramDataOutputStream.writeLong(((lW)get()).a());
/* 46:46 */    paramDataOutputStream.writeUTF(((lW)get()).b());
/* 47:47 */    paramDataOutputStream.writeInt(((lW)get()).a());
/* 48:   */    
/* 51:51 */    return byteLength();
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionNewsPost
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */