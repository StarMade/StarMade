/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import mf;
/*    */ import mi;
/*    */ import mj;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ import q;
/*    */ 
/*    */ public class RemoteInventoryMultMod extends RemoteField
/*    */ {
/*    */   public RemoteInventoryMultMod(mi parammi, NetworkObject paramNetworkObject)
/*    */   {
/* 21 */     super(parammi, paramNetworkObject);
/*    */   }
/*    */   public RemoteInventoryMultMod(mi parammi, boolean paramBoolean) {
/* 24 */     super(parammi, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 29 */     return 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 34 */     paramInt = paramDataInputStream.readInt();
/* 35 */     int i = paramDataInputStream.readInt();
/* 36 */     int j = paramDataInputStream.readInt();
/*    */ 
/* 38 */     ((mi)get()).jdField_a_of_type_Q = new q(paramInt, i, j);
/*    */ 
/* 40 */     paramInt = paramDataInputStream.readShort();
/*    */ 
/* 42 */     ((mi)get()).jdField_a_of_type_ArrayOfMj = new mj[paramInt];
/*    */ 
/* 44 */     for (i = 0; i < paramInt; i++)
/*    */     {
/* 46 */       ((mi)get()).jdField_a_of_type_ArrayOfMj[i] = new mj();
/*    */ 
/* 48 */       j = paramDataInputStream.readShort();
/*    */       short s;
/* 50 */       if ((
/* 50 */         s = paramDataInputStream.readShort()) != 0)
/*    */       {
/* 51 */         int k = paramDataInputStream.readInt();
/* 52 */         ((mi)get()).jdField_a_of_type_ArrayOfMj[i].b(k);
/*    */       }
/*    */ 
/* 55 */       ((mi)get()).jdField_a_of_type_ArrayOfMj[i].jdField_a_of_type_Int = j;
/* 56 */       ((mi)get()).jdField_a_of_type_ArrayOfMj[i].jdField_a_of_type_Short = s;
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 63 */     mf localmf = ((mi)get()).jdField_a_of_type_Mf;
/* 64 */     if (((mi)get()).jdField_a_of_type_Q != null) {
/* 65 */       paramDataOutputStream.writeInt(((mi)get()).jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 66 */       paramDataOutputStream.writeInt(((mi)get()).jdField_a_of_type_Q.b);
/* 67 */       paramDataOutputStream.writeInt(((mi)get()).jdField_a_of_type_Q.c);
/*    */     } else {
/* 69 */       paramDataOutputStream.writeInt(0);
/* 70 */       paramDataOutputStream.writeInt(0);
/* 71 */       paramDataOutputStream.writeInt(0);
/*    */     }
/*    */ 
/* 74 */     paramDataOutputStream.writeShort((short)((mi)get()).jdField_a_of_type_JavaUtilCollection.size());
/* 75 */     for (Iterator localIterator = ((mi)get()).jdField_a_of_type_JavaUtilCollection.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*    */ 
/* 77 */       paramDataOutputStream.writeShort((short)i);
/* 78 */       int j = localmf.a(i);
/* 79 */       paramDataOutputStream.writeShort(j);
/* 80 */       if (j != 0) {
/* 81 */         paramDataOutputStream.writeInt(localmf.a(i));
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 89 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryMultMod
 * JD-Core Version:    0.6.2
 */