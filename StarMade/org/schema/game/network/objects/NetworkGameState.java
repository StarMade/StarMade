/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import org.schema.game.network.objects.remote.RemoteCatalogEntryBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteFactionBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteFactionInvitationBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteFactionRolesBuffer;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkEntity;
/*    */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteFloat;
/*    */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*    */ import org.schema.schine.network.objects.remote.RemoteString;
/*    */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*    */ 
/*    */ public class NetworkGameState extends NetworkEntity
/*    */ {
/* 24 */   public RemoteFactionBuffer factionDel = new RemoteFactionBuffer(this);
/* 25 */   public RemoteFactionBuffer factionAdd = new RemoteFactionBuffer(this);
/* 26 */   public RemoteFactionNewsPostBuffer factionNewsPosts = new RemoteFactionNewsPostBuffer(this);
/* 27 */   public RemoteFactionInvitationBuffer factionInviteAdd = new RemoteFactionInvitationBuffer(this);
/* 28 */   public RemoteFactionInvitationBuffer factionInviteDel = new RemoteFactionInvitationBuffer(this);
/*    */ 
/* 31 */   public RemoteCatalogEntryBuffer catalogBuffer = new RemoteCatalogEntryBuffer(this);
/* 32 */   public RemoteCatalogEntryBuffer catalogDeleteBuffer = new RemoteCatalogEntryBuffer(this);
/* 33 */   public RemoteCatalogEntryBuffer catalogChangeRequestBuffer = new RemoteCatalogEntryBuffer(this);
/* 34 */   public RemoteCatalogEntryBuffer catalogDeleteRequestBuffer = new RemoteCatalogEntryBuffer(this);
/* 35 */   public RemoteArrayBuffer catalogRatingBuffer = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
/*    */ 
/* 37 */   public RemoteFloat serverShutdown = new RemoteFloat(Float.valueOf(-1.0F), this);
/* 38 */   public RemoteFloat serverMaxSpeed = new RemoteFloat(Float.valueOf(50.0F), this);
/* 39 */   public RemoteFloat linearDamping = new RemoteFloat(Float.valueOf(0.09F), this);
/* 40 */   public RemoteFloat rotationalDamping = new RemoteFloat(Float.valueOf(0.09F), this);
/*    */ 
/* 42 */   public RemoteString serverMessage = new RemoteString("", this);
/*    */ 
/* 44 */   public RemoteInteger saveSlotsAllowed = new RemoteInteger(Integer.valueOf(0), this);
/*    */ 
/* 50 */   public RemoteArrayBuffer factionRelationshipAcceptBuffer = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
/*    */ 
/* 58 */   public RemoteArrayBuffer factionMod = new RemoteArrayBuffer(4, RemoteStringArray.class, this);
/*    */ 
/* 66 */   public RemoteArrayBuffer factionMemberMod = new RemoteArrayBuffer(4, RemoteStringArray.class, this);
/*    */ 
/* 73 */   public RemoteArrayBuffer factionkickMemberRequests = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
/*    */ 
/* 80 */   public RemoteArrayBuffer factionRelationships = new RemoteArrayBuffer(3, RemoteIntegerArray.class, this);
/* 81 */   public RemoteArrayBuffer factionHomeBaseChangeBuffer = new RemoteArrayBuffer(6, RemoteStringArray.class, this);
/* 82 */   public RemoteArrayBuffer factionRelationshipOffer = new RemoteArrayBuffer(5, RemoteStringArray.class, this);
/* 83 */   public RemoteFactionRolesBuffer factionRolesBuffer = new RemoteFactionRolesBuffer(this);
/*    */ 
/* 85 */   public RemoteArrayBuffer serverConfig = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/*    */ 
/*    */   public NetworkGameState(StateInterface paramStateInterface)
/*    */   {
/* 90 */     super(paramStateInterface);
/*    */   }
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
 * Qualified Name:     org.schema.game.network.objects.NetworkGameState
 * JD-Core Version:    0.6.2
 */