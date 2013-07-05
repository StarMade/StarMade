/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import org.schema.game.network.objects.remote.RemoteControlledFileStream;
/*    */ import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteMapEntryAnswerBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*    */ import org.schema.schine.network.objects.remote.RemoteLong;
/*    */ import org.schema.schine.network.objects.remote.RemoteShort;
/*    */ import org.schema.schine.network.objects.remote.RemoteString;
/*    */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*    */ 
/*    */ public class NetworkClientChannel extends NetworkObject
/*    */ {
/* 23 */   public RemoteBoolean connectionReady = new RemoteBoolean(false, this);
/*    */ 
/* 26 */   public RemoteMissileUpdateBuffer missileUpdateBuffer = new RemoteMissileUpdateBuffer(this);
/* 27 */   public RemoteBuffer missileMissingRequestBuffer = new RemoteBuffer(RemoteShort.class, this);
/*    */ 
/* 29 */   public RemoteBuffer factionNewsRequests = new RemoteBuffer(RemoteLong.class, this);
/*    */ 
/* 31 */   public RemoteBuffer fileRequests = new RemoteBuffer(RemoteString.class, this);
/*    */ 
/* 33 */   public RemoteBuffer timeStampRequests = new RemoteBuffer(RemoteString.class, this);
/*    */ 
/* 35 */   public RemoteArrayBuffer timeStampResponses = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/*    */ 
/* 37 */   public RemoteBuffer downloadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
/*    */ 
/* 39 */   public RemoteInteger playerId = new RemoteInteger(Integer.valueOf(-121212), this);
/*    */ 
/* 41 */   public RemoteFactionNewsPostBuffer factionNewsPosts = new RemoteFactionNewsPostBuffer(this);
/*    */ 
/* 43 */   public RemoteMapEntryRequestBuffer mapRequests = new RemoteMapEntryRequestBuffer(this);
/* 44 */   public RemoteMapEntryAnswerBuffer mapAnswers = new RemoteMapEntryAnswerBuffer(this);
/*    */ 
/*    */   public NetworkClientChannel(StateInterface paramStateInterface) {
/* 47 */     super(paramStateInterface);
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
 * Qualified Name:     org.schema.game.network.objects.NetworkClientChannel
 * JD-Core Version:    0.6.2
 */