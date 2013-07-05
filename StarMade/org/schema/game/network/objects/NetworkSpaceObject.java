/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkEntity;
/*    */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*    */ 
/*    */ public class NetworkSpaceObject extends NetworkEntity
/*    */ {
/*    */   public static final int TYPE_ASTEROID = 0;
/*    */   public static final int TYPE_ASTEROID_STYLE_1 = 0;
/* 13 */   public RemoteInteger starSystemId = new RemoteInteger(Integer.valueOf(-1), this);
/*    */ 
/* 15 */   public RemoteInteger objectType = new RemoteInteger(Integer.valueOf(-1), this);
/* 16 */   public RemoteInteger objectSubtype = new RemoteInteger(Integer.valueOf(-1), this);
/*    */ 
/* 18 */   public NetworkSpaceObject(StateInterface paramStateInterface) { super(paramStateInterface); }
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
 * Qualified Name:     org.schema.game.network.objects.NetworkSpaceObject
 * JD-Core Version:    0.6.2
 */