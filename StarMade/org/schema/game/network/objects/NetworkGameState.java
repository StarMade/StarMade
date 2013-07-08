/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import org.schema.game.network.objects.remote.RemoteCatalogEntryBuffer;
/*  4:   */import org.schema.game.network.objects.remote.RemoteFactionBuffer;
/*  5:   */import org.schema.game.network.objects.remote.RemoteFactionInvitationBuffer;
/*  6:   */import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
/*  7:   */import org.schema.game.network.objects.remote.RemoteFactionRolesBuffer;
/*  8:   */import org.schema.schine.network.StateInterface;
/*  9:   */import org.schema.schine.network.objects.NetworkEntity;
/* 10:   */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/* 11:   */import org.schema.schine.network.objects.remote.RemoteFloat;
/* 12:   */import org.schema.schine.network.objects.remote.RemoteInteger;
/* 13:   */import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/* 14:   */import org.schema.schine.network.objects.remote.RemoteString;
/* 15:   */import org.schema.schine.network.objects.remote.RemoteStringArray;
/* 16:   */
/* 21:   */public class NetworkGameState
/* 22:   */  extends NetworkEntity
/* 23:   */{
/* 24:24 */  public RemoteFactionBuffer factionDel = new RemoteFactionBuffer(this);
/* 25:25 */  public RemoteFactionBuffer factionAdd = new RemoteFactionBuffer(this);
/* 26:26 */  public RemoteFactionNewsPostBuffer factionNewsPosts = new RemoteFactionNewsPostBuffer(this);
/* 27:27 */  public RemoteFactionInvitationBuffer factionInviteAdd = new RemoteFactionInvitationBuffer(this);
/* 28:28 */  public RemoteFactionInvitationBuffer factionInviteDel = new RemoteFactionInvitationBuffer(this);
/* 29:   */  
/* 31:31 */  public RemoteCatalogEntryBuffer catalogBuffer = new RemoteCatalogEntryBuffer(this);
/* 32:32 */  public RemoteCatalogEntryBuffer catalogDeleteBuffer = new RemoteCatalogEntryBuffer(this);
/* 33:33 */  public RemoteCatalogEntryBuffer catalogChangeRequestBuffer = new RemoteCatalogEntryBuffer(this);
/* 34:34 */  public RemoteCatalogEntryBuffer catalogDeleteRequestBuffer = new RemoteCatalogEntryBuffer(this);
/* 35:35 */  public RemoteArrayBuffer catalogRatingBuffer = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
/* 36:   */  
/* 37:37 */  public RemoteFloat serverShutdown = new RemoteFloat(Float.valueOf(-1.0F), this);
/* 38:38 */  public RemoteFloat serverMaxSpeed = new RemoteFloat(Float.valueOf(50.0F), this);
/* 39:39 */  public RemoteFloat linearDamping = new RemoteFloat(Float.valueOf(0.09F), this);
/* 40:40 */  public RemoteFloat rotationalDamping = new RemoteFloat(Float.valueOf(0.09F), this);
/* 41:   */  
/* 42:42 */  public RemoteString serverMessage = new RemoteString("", this);
/* 43:   */  
/* 44:44 */  public RemoteInteger saveSlotsAllowed = new RemoteInteger(Integer.valueOf(0), this);
/* 45:   */  
/* 50:50 */  public RemoteArrayBuffer factionRelationshipAcceptBuffer = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
/* 51:   */  
/* 58:58 */  public RemoteArrayBuffer factionMod = new RemoteArrayBuffer(4, RemoteStringArray.class, this);
/* 59:   */  
/* 66:66 */  public RemoteArrayBuffer factionMemberMod = new RemoteArrayBuffer(4, RemoteStringArray.class, this);
/* 67:   */  
/* 73:73 */  public RemoteArrayBuffer factionkickMemberRequests = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
/* 74:   */  
/* 80:80 */  public RemoteArrayBuffer factionRelationships = new RemoteArrayBuffer(3, RemoteIntegerArray.class, this);
/* 81:81 */  public RemoteArrayBuffer factionHomeBaseChangeBuffer = new RemoteArrayBuffer(6, RemoteStringArray.class, this);
/* 82:82 */  public RemoteArrayBuffer factionRelationshipOffer = new RemoteArrayBuffer(5, RemoteStringArray.class, this);
/* 83:83 */  public RemoteFactionRolesBuffer factionRolesBuffer = new RemoteFactionRolesBuffer(this);
/* 84:   */  
/* 85:85 */  public RemoteArrayBuffer serverConfig = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/* 86:   */  
/* 88:   */  public NetworkGameState(StateInterface paramStateInterface)
/* 89:   */  {
/* 90:90 */    super(paramStateInterface);
/* 91:   */  }
/* 92:   */  
/* 93:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 94:   */  
/* 95:   */  public void onInit(StateInterface paramStateInterface) {}
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkGameState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */