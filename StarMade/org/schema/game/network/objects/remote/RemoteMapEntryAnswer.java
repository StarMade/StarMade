/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import cC;
/*    */ import cH;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ import q;
/*    */ 
/*    */ public class RemoteMapEntryAnswer extends RemoteField
/*    */ {
/*    */   public RemoteMapEntryAnswer(cH paramcH, NetworkObject paramNetworkObject)
/*    */   {
/* 19 */     super(paramcH, paramNetworkObject);
/*    */   }
/*    */   public RemoteMapEntryAnswer(cH paramcH, boolean paramBoolean) {
/* 22 */     super(paramcH, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 27 */     return 1;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 31 */     ((cH)get()).jdField_a_of_type_Q = new q(paramDataInputStream.readInt(), paramDataInputStream.readInt(), paramDataInputStream.readInt());
/* 32 */     ((cH)get()).jdField_a_of_type_Byte = paramDataInputStream.readByte();
/*    */ 
/* 34 */     ((cH)get()).jdField_a_of_type_ArrayOfCD = cC.a(paramDataInputStream);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 40 */     paramDataOutputStream.writeInt(((cH)get()).jdField_a_of_type_Q.a);
/* 41 */     paramDataOutputStream.writeInt(((cH)get()).jdField_a_of_type_Q.b);
/* 42 */     paramDataOutputStream.writeInt(((cH)get()).jdField_a_of_type_Q.c);
/* 43 */     paramDataOutputStream.writeByte(((cH)get()).jdField_a_of_type_Byte);
/*    */ 
/* 45 */     cC.a(paramDataOutputStream, ((cH)get()).jdField_a_of_type_ArrayOfCD);
/*    */ 
/* 47 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryAnswer
 * JD-Core Version:    0.6.2
 */