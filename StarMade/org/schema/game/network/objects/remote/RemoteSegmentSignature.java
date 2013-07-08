/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import lf;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */import q;
/*  9:   */
/* 10:   */public class RemoteSegmentSignature
/* 11:   */  extends RemoteField
/* 12:   */{
/* 13:13 */  static int len = 24;
/* 14:   */  
/* 15:15 */  public RemoteSegmentSignature(NetworkObject paramNetworkObject) { super(new lf(), paramNetworkObject); }
/* 16:   */  
/* 17:   */  public RemoteSegmentSignature(lf paramlf, NetworkObject paramNetworkObject) {
/* 18:18 */    super(paramlf, paramNetworkObject);
/* 19:   */  }
/* 20:   */  
/* 21:21 */  public RemoteSegmentSignature(boolean paramBoolean) { super(new lf(), paramBoolean); }
/* 22:   */  
/* 23:   */  public RemoteSegmentSignature(lf paramlf, boolean paramBoolean) {
/* 24:24 */    super(paramlf, paramBoolean);
/* 25:   */  }
/* 26:   */  
/* 27:   */  public int byteLength() {
/* 28:28 */    return len;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 32:   */  {
/* 33:33 */    long l = paramDataInputStream.readLong();
/* 34:34 */    paramInt = paramDataInputStream.readInt();
/* 35:35 */    int i = paramDataInputStream.readInt();
/* 36:36 */    int j = paramDataInputStream.readInt();
/* 37:37 */    int k = paramDataInputStream.readInt();
/* 38:38 */    paramDataInputStream = paramDataInputStream.readBoolean();
/* 39:39 */    set(new lf(new q(i, j, k), l, paramInt, paramDataInputStream));
/* 40:   */  }
/* 41:   */  
/* 42:   */  public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 43:43 */    paramDataOutputStream.writeLong(((lf)get()).jdField_a_of_type_Long);
/* 44:44 */    paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Int);
/* 45:45 */    paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 46:46 */    paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.b);
/* 47:47 */    paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.c);
/* 48:48 */    paramDataOutputStream.writeBoolean(((lf)get()).jdField_a_of_type_Boolean);
/* 49:49 */    return byteLength();
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentSignature
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */