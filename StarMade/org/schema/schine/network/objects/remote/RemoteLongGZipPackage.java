/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ 
/*    */ public class RemoteLongGZipPackage extends RemoteField
/*    */ {
/*    */   public RemoteLongGZipPackage()
/*    */   {
/* 11 */     super(null, null);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 17 */     return 0;
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 61 */     if (!$assertionsDisabled) throw new AssertionError("deprecated");
/*    */ 
/* 92 */     return -1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongGZipPackage
 * JD-Core Version:    0.6.2
 */