/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkEntity;
/*    */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*    */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*    */ import org.schema.schine.network.objects.remote.RemoteString;
/*    */ 
/*    */ public class NetworkPlayerCharacter extends NetworkEntity
/*    */ {
/* 12 */   public RemoteInteger clientOwnerId = new RemoteInteger(Integer.valueOf(-1), this);
/*    */ 
/* 14 */   public RemoteString uniqueId = new RemoteString(this);
/* 15 */   public RemoteBoolean spawnedOnServer = new RemoteBoolean(this);
/*    */ 
/* 17 */   public NetworkPlayerCharacter(StateInterface paramStateInterface) { super(paramStateInterface); }
/*    */ 
/*    */ 
/*    */   public void onDelete(StateInterface paramStateInterface)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void onInit(StateInterface paramStateInterface)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkPlayerCharacter
 * JD-Core Version:    0.6.2
 */