/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteStringArray extends RemoteArray
/*    */ {
/*    */   public RemoteStringArray(int paramInt, NetworkObject paramNetworkObject)
/*    */   {
/* 10 */     super(new RemoteString[paramInt], paramNetworkObject);
/*    */ 
/* 12 */     for (int i = 0; i < paramInt; i++) {
/* 13 */       ((RemoteField[])get())[i] = new RemoteString(paramNetworkObject);
/*    */     }
/* 15 */     addObservers();
/*    */   }
/*    */ 
/*    */   public RemoteStringArray(int paramInt, boolean paramBoolean) {
/* 19 */     super(new RemoteString[paramInt], paramBoolean);
/*    */ 
/* 21 */     for (int i = 0; i < paramInt; i++) {
/* 22 */       ((RemoteField[])get())[i] = new RemoteString(paramBoolean);
/*    */     }
/* 24 */     addObservers();
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 30 */     int i = 0;
/* 31 */     for (int j = 0; j < ((RemoteField[])get()).length; j++) {
/* 32 */       i += ((RemoteField[])get())[j].byteLength();
/*    */     }
/* 34 */     return i;
/*    */   }
/*    */ 
/*    */   protected void init(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 39 */     set(paramArrayOfRemoteField);
/*    */   }
/*    */ 
/*    */   public void set(int paramInt, String paramString)
/*    */   {
/* 46 */     ((RemoteField[])super.get())[paramInt].set(paramString, this.forcedClientSending);
/*    */   }
/*    */ 
/*    */   public void set(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 53 */     super.set(paramArrayOfRemoteField);
/* 54 */     for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 55 */       ((RemoteField[])get())[i] = new RemoteString(this.onServer);
/* 56 */       ((RemoteField[])get())[i].observer = this;
/*    */     }
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 66 */     return "RemoteStringArray" + Arrays.toString((Object[])get());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteStringArray
 * JD-Core Version:    0.6.2
 */