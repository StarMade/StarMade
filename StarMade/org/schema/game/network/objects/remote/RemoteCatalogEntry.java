/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import lL;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteCatalogEntry extends RemoteField
/*    */ {
/*    */   public RemoteCatalogEntry(lL paramlL, NetworkObject paramNetworkObject)
/*    */   {
/* 16 */     super(paramlL, paramNetworkObject);
/*    */   }
/*    */   public RemoteCatalogEntry(lL paramlL, boolean paramBoolean) {
/* 19 */     super(paramlL, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 24 */     return 1;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 29 */     paramInt = paramDataInputStream.readUTF();
/* 30 */     String str1 = paramDataInputStream.readUTF();
/* 31 */     String str2 = paramDataInputStream.readUTF();
/* 32 */     int i = paramDataInputStream.readInt();
/* 33 */     int j = paramDataInputStream.readInt();
/* 34 */     boolean bool = paramDataInputStream.readBoolean();
/* 35 */     paramDataInputStream = paramDataInputStream.readFloat();
/*    */ 
/* 38 */     ((lL)get()).jdField_a_of_type_JavaLangString = paramInt;
/* 39 */     ((lL)get()).jdField_b_of_type_JavaLangString = str1;
/* 40 */     ((lL)get()).jdField_c_of_type_Int = i;
/* 41 */     ((lL)get()).jdField_c_of_type_JavaLangString = str2;
/* 42 */     ((lL)get()).jdField_b_of_type_Int = j;
/* 43 */     ((lL)get()).jdField_a_of_type_Boolean = bool;
/* 44 */     ((lL)get()).jdField_a_of_type_Float = paramDataInputStream;
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 51 */     paramDataOutputStream.writeUTF(((lL)get()).jdField_a_of_type_JavaLangString);
/* 52 */     paramDataOutputStream.writeUTF(((lL)get()).jdField_b_of_type_JavaLangString);
/* 53 */     paramDataOutputStream.writeUTF(((lL)get()).jdField_c_of_type_JavaLangString);
/* 54 */     paramDataOutputStream.writeInt(((lL)get()).jdField_c_of_type_Int);
/* 55 */     paramDataOutputStream.writeInt(((lL)get()).jdField_b_of_type_Int);
/* 56 */     paramDataOutputStream.writeBoolean(((lL)get()).jdField_a_of_type_Boolean);
/* 57 */     paramDataOutputStream.writeFloat(((lL)get()).jdField_a_of_type_Float);
/*    */ 
/* 59 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteCatalogEntry
 * JD-Core Version:    0.6.2
 */