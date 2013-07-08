/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */
/* 10:   */public class RemoteString
/* 11:   */  extends RemoteComparable
/* 12:   */{
/* 13:   */  public RemoteString(NetworkObject paramNetworkObject)
/* 14:   */  {
/* 15:15 */    super("", paramNetworkObject);
/* 16:   */  }
/* 17:   */  
/* 18:18 */  public RemoteString(String paramString, NetworkObject paramNetworkObject) { super(paramString, paramNetworkObject); }
/* 19:   */  
/* 24:   */  public RemoteString(boolean paramBoolean)
/* 25:   */  {
/* 26:26 */    super("", paramBoolean);
/* 27:   */  }
/* 28:   */  
/* 29:   */  public int byteLength() {
/* 30:30 */    return ((String)get()).length() + 4;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 34:   */  {
/* 35:35 */    set(paramDataInputStream.readUTF());
/* 36:   */  }
/* 37:   */  
/* 38:   */  public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 39:39 */    paramDataOutputStream.writeUTF((String)get());
/* 40:40 */    return byteLength();
/* 41:   */  }
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteString
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */