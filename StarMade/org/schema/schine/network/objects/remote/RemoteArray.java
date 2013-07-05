/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public abstract class RemoteArray extends RemoteField
/*    */   implements NetworkChangeObserver, StreamableArray
/*    */ {
/*    */   public RemoteArray(RemoteField[] paramArrayOfRemoteField, NetworkObject paramNetworkObject)
/*    */   {
/* 15 */     super(paramArrayOfRemoteField, paramNetworkObject);
/* 16 */     init(paramArrayOfRemoteField);
/*    */   }
/*    */   public RemoteArray(RemoteField[] paramArrayOfRemoteField, boolean paramBoolean) {
/* 19 */     super(paramArrayOfRemoteField, paramBoolean);
/* 20 */     init(paramArrayOfRemoteField);
/*    */   }
/*    */ 
/*    */   protected void addObservers()
/*    */   {
/* 25 */     for (int i = 0; i < ((RemoteField[])get()).length; i++)
/* 26 */       ((RemoteField[])get())[i].observer = this;
/*    */   }
/*    */ 
/*    */   public int arrayLength() {
/* 30 */     return ((RemoteField[])get()).length;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 40 */     for (int i = 0; i < ((RemoteField[])get()).length; i++) {
/* 41 */       ((RemoteField[])get())[i].fromByteStream(paramDataInputStream, paramInt);
/* 42 */       set(i, (Comparable)get(i).get());
/*    */     }
/*    */   }
/*    */ 
/*    */   public RemoteField get(int paramInt) {
/* 47 */     return ((RemoteField[])super.get())[paramInt];
/*    */   }
/*    */   protected abstract void init(RemoteField[] paramArrayOfRemoteField);
/*    */ 
/*    */   public abstract void set(int paramInt, Comparable paramComparable);
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 54 */     for (int i = 0; i < ((RemoteField[])get()).length; i++) {
/* 55 */       ((RemoteField[])get())[i].toByteStream(paramDataOutputStream);
/*    */     }
/* 57 */     return byteLength();
/*    */   }
/*    */ 
/*    */   public void update(Streamable paramStreamable)
/*    */   {
/* 65 */     setChanged(true);
/*    */ 
/* 67 */     paramStreamable.setChanged(false);
/* 68 */     if (this.observer != null)
/* 69 */       this.observer.update(this);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteArray
 * JD-Core Version:    0.6.2
 */