/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import lL;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */
/* 11:   */public class RemoteCatalogEntry
/* 12:   */  extends RemoteField
/* 13:   */{
/* 14:   */  public RemoteCatalogEntry(lL paramlL, NetworkObject paramNetworkObject)
/* 15:   */  {
/* 16:16 */    super(paramlL, paramNetworkObject);
/* 17:   */  }
/* 18:   */  
/* 19:19 */  public RemoteCatalogEntry(lL paramlL, boolean paramBoolean) { super(paramlL, paramBoolean); }
/* 20:   */  
/* 22:   */  public int byteLength()
/* 23:   */  {
/* 24:24 */    return 1;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 28:   */  {
/* 29:29 */    paramInt = paramDataInputStream.readUTF();
/* 30:30 */    String str1 = paramDataInputStream.readUTF();
/* 31:31 */    String str2 = paramDataInputStream.readUTF();
/* 32:32 */    int i = paramDataInputStream.readInt();
/* 33:33 */    int j = paramDataInputStream.readInt();
/* 34:34 */    boolean bool = paramDataInputStream.readBoolean();
/* 35:35 */    paramDataInputStream = paramDataInputStream.readFloat();
/* 36:   */    
/* 38:38 */    ((lL)get()).jdField_a_of_type_JavaLangString = paramInt;
/* 39:39 */    ((lL)get()).jdField_b_of_type_JavaLangString = str1;
/* 40:40 */    ((lL)get()).jdField_c_of_type_Int = i;
/* 41:41 */    ((lL)get()).jdField_c_of_type_JavaLangString = str2;
/* 42:42 */    ((lL)get()).jdField_b_of_type_Int = j;
/* 43:43 */    ((lL)get()).jdField_a_of_type_Boolean = bool;
/* 44:44 */    ((lL)get()).jdField_a_of_type_Float = paramDataInputStream;
/* 45:   */  }
/* 46:   */  
/* 49:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 50:   */  {
/* 51:51 */    paramDataOutputStream.writeUTF(((lL)get()).jdField_a_of_type_JavaLangString);
/* 52:52 */    paramDataOutputStream.writeUTF(((lL)get()).jdField_b_of_type_JavaLangString);
/* 53:53 */    paramDataOutputStream.writeUTF(((lL)get()).jdField_c_of_type_JavaLangString);
/* 54:54 */    paramDataOutputStream.writeInt(((lL)get()).jdField_c_of_type_Int);
/* 55:55 */    paramDataOutputStream.writeInt(((lL)get()).jdField_b_of_type_Int);
/* 56:56 */    paramDataOutputStream.writeBoolean(((lL)get()).jdField_a_of_type_Boolean);
/* 57:57 */    paramDataOutputStream.writeFloat(((lL)get()).jdField_a_of_type_Float);
/* 58:   */    
/* 59:59 */    return byteLength();
/* 60:   */  }
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteCatalogEntry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */