/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */
/* 11:   */public class RemoteFloat
/* 12:   */  extends RemoteComparable
/* 13:   */{
/* 14:   */  public RemoteFloat(Float paramFloat, NetworkObject paramNetworkObject)
/* 15:   */  {
/* 16:16 */    super(paramFloat, paramNetworkObject);
/* 17:   */  }
/* 18:   */  
/* 19:19 */  public RemoteFloat(NetworkObject paramNetworkObject) { this(Float.valueOf(0.0F), paramNetworkObject); }
/* 20:   */  
/* 21:   */  public RemoteFloat(boolean paramBoolean) {
/* 22:22 */    this(0.0F, paramBoolean);
/* 23:   */  }
/* 24:   */  
/* 25:   */  public RemoteFloat(float paramFloat, boolean paramBoolean)
/* 26:   */  {
/* 27:27 */    super(Float.valueOf(paramFloat), paramBoolean);
/* 28:   */  }
/* 29:   */  
/* 30:   */  public int byteLength()
/* 31:   */  {
/* 32:32 */    return 4;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 36:36 */    set(Float.valueOf(paramDataInputStream.readFloat()));
/* 37:   */  }
/* 38:   */  
/* 39:   */  public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 40:40 */    paramDataOutputStream.writeFloat(((Float)get()).floatValue());
/* 41:41 */    return 4;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public String toString() {
/* 45:45 */    return ((Float)get()).toString();
/* 46:   */  }
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */