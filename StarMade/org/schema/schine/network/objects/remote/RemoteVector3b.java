/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import o;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteVector3b extends RemoteByteArray
/*    */ {
/*    */   public RemoteVector3b(NetworkObject paramNetworkObject)
/*    */   {
/*  9 */     super(3, paramNetworkObject);
/*    */   }
/*    */   public RemoteVector3b(boolean paramBoolean) {
/* 12 */     super(3, paramBoolean);
/*    */   }
/*    */ 
/*    */   public o getVector() {
/* 16 */     return getVector(new o());
/*    */   }
/*    */   public o getVector(o paramo) {
/* 19 */     paramo.b(((Byte)((RemoteField[])super.get())[0].get()).byteValue(), ((Byte)((RemoteField[])super.get())[1].get()).byteValue(), ((Byte)((RemoteField[])super.get())[2].get()).byteValue());
/* 20 */     return paramo;
/*    */   }
/*    */   public void set(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 23 */     super.set(0, Byte.valueOf(paramByte1));
/* 24 */     super.set(1, Byte.valueOf(paramByte2));
/* 25 */     super.set(2, Byte.valueOf(paramByte3));
/*    */   }
/*    */ 
/*    */   public void set(o paramo) {
/* 29 */     super.set(0, Byte.valueOf(paramo.a));
/* 30 */     super.set(1, Byte.valueOf(paramo.b));
/* 31 */     super.set(2, Byte.valueOf(paramo.c));
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 36 */     return "(r" + getVector(new o()) + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3b
 * JD-Core Version:    0.6.2
 */