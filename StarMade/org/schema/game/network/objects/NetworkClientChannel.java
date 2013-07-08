/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import org.schema.game.network.objects.remote.RemoteControlledFileStream;
/*  4:   */import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
/*  5:   */import org.schema.game.network.objects.remote.RemoteMapEntryAnswerBuffer;
/*  6:   */import org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer;
/*  7:   */import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
/*  8:   */import org.schema.schine.network.StateInterface;
/*  9:   */import org.schema.schine.network.objects.NetworkObject;
/* 10:   */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/* 11:   */import org.schema.schine.network.objects.remote.RemoteBoolean;
/* 12:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 13:   */import org.schema.schine.network.objects.remote.RemoteInteger;
/* 14:   */import org.schema.schine.network.objects.remote.RemoteLong;
/* 15:   */import org.schema.schine.network.objects.remote.RemoteShort;
/* 16:   */import org.schema.schine.network.objects.remote.RemoteString;
/* 17:   */import org.schema.schine.network.objects.remote.RemoteStringArray;
/* 18:   */
/* 20:   */public class NetworkClientChannel
/* 21:   */  extends NetworkObject
/* 22:   */{
/* 23:23 */  public RemoteBoolean connectionReady = new RemoteBoolean(false, this);
/* 24:   */  
/* 26:26 */  public RemoteMissileUpdateBuffer missileUpdateBuffer = new RemoteMissileUpdateBuffer(this);
/* 27:27 */  public RemoteBuffer missileMissingRequestBuffer = new RemoteBuffer(RemoteShort.class, this);
/* 28:   */  
/* 29:29 */  public RemoteBuffer factionNewsRequests = new RemoteBuffer(RemoteLong.class, this);
/* 30:   */  
/* 31:31 */  public RemoteBuffer fileRequests = new RemoteBuffer(RemoteString.class, this);
/* 32:   */  
/* 33:33 */  public RemoteBuffer timeStampRequests = new RemoteBuffer(RemoteString.class, this);
/* 34:   */  
/* 35:35 */  public RemoteArrayBuffer timeStampResponses = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/* 36:   */  
/* 37:37 */  public RemoteBuffer downloadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
/* 38:   */  
/* 39:39 */  public RemoteInteger playerId = new RemoteInteger(Integer.valueOf(-121212), this);
/* 40:   */  
/* 41:41 */  public RemoteFactionNewsPostBuffer factionNewsPosts = new RemoteFactionNewsPostBuffer(this);
/* 42:   */  
/* 43:43 */  public RemoteMapEntryRequestBuffer mapRequests = new RemoteMapEntryRequestBuffer(this);
/* 44:44 */  public RemoteMapEntryAnswerBuffer mapAnswers = new RemoteMapEntryAnswerBuffer(this);
/* 45:   */  
/* 46:   */  public NetworkClientChannel(StateInterface paramStateInterface) {
/* 47:47 */    super(paramStateInterface);
/* 48:   */  }
/* 49:   */  
/* 50:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 51:   */  
/* 52:   */  public void onInit(StateInterface paramStateInterface) {}
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkClientChannel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */