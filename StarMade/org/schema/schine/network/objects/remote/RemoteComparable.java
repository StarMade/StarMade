/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public abstract class RemoteComparable extends RemoteField
/*    */   implements Comparable
/*    */ {
/*    */   public RemoteComparable(Comparable paramComparable, NetworkObject paramNetworkObject)
/*    */   {
/*  9 */     super(paramComparable, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public RemoteComparable(Comparable paramComparable, boolean paramBoolean)
/*    */   {
/* 14 */     super(paramComparable, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int compareTo(Comparable paramComparable)
/*    */   {
/* 23 */     return ((Comparable)get()).compareTo(paramComparable);
/*    */   }
/*    */ 
/*    */   public void set(Comparable paramComparable)
/*    */   {
/* 28 */     set(paramComparable, this.forcedClientSending);
/*    */   }
/*    */ 
/*    */   public void set(Comparable paramComparable, boolean paramBoolean)
/*    */   {
/* 35 */     if ((this.onServer) || (paramBoolean))
/*    */     {
/* 37 */       setChanged((hasChanged()) || (!paramComparable.equals(get())));
/*    */     }
/*    */ 
/* 40 */     super.set(paramComparable);
/*    */ 
/* 42 */     if ((hasChanged()) && (this.observer != null))
/*    */     {
/* 44 */       this.observer.update(this);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteComparable
 * JD-Core Version:    0.6.2
 */