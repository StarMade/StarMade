package org.schema.game.network.objects;

import org.schema.game.network.objects.remote.RemoteCatalogEntryBuffer;
import org.schema.game.network.objects.remote.RemoteFactionBuffer;
import org.schema.game.network.objects.remote.RemoteFactionInvitationBuffer;
import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
import org.schema.game.network.objects.remote.RemoteFactionRolesBuffer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkEntity;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteFloat;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public class NetworkGameState
  extends NetworkEntity
{
  public RemoteFactionBuffer factionDel = new RemoteFactionBuffer(this);
  public RemoteFactionBuffer factionAdd = new RemoteFactionBuffer(this);
  public RemoteFactionNewsPostBuffer factionNewsPosts = new RemoteFactionNewsPostBuffer(this);
  public RemoteFactionInvitationBuffer factionInviteAdd = new RemoteFactionInvitationBuffer(this);
  public RemoteFactionInvitationBuffer factionInviteDel = new RemoteFactionInvitationBuffer(this);
  public RemoteCatalogEntryBuffer catalogBuffer = new RemoteCatalogEntryBuffer(this);
  public RemoteCatalogEntryBuffer catalogDeleteBuffer = new RemoteCatalogEntryBuffer(this);
  public RemoteCatalogEntryBuffer catalogChangeRequestBuffer = new RemoteCatalogEntryBuffer(this);
  public RemoteCatalogEntryBuffer catalogDeleteRequestBuffer = new RemoteCatalogEntryBuffer(this);
  public RemoteArrayBuffer catalogRatingBuffer = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
  public RemoteFloat serverShutdown = new RemoteFloat(Float.valueOf(-1.0F), this);
  public RemoteFloat serverMaxSpeed = new RemoteFloat(Float.valueOf(50.0F), this);
  public RemoteFloat linearDamping = new RemoteFloat(Float.valueOf(0.09F), this);
  public RemoteFloat rotationalDamping = new RemoteFloat(Float.valueOf(0.09F), this);
  public RemoteString serverMessage = new RemoteString("", this);
  public RemoteInteger saveSlotsAllowed = new RemoteInteger(Integer.valueOf(0), this);
  public RemoteArrayBuffer factionRelationshipAcceptBuffer = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
  public RemoteArrayBuffer factionMod = new RemoteArrayBuffer(4, RemoteStringArray.class, this);
  public RemoteArrayBuffer factionMemberMod = new RemoteArrayBuffer(4, RemoteStringArray.class, this);
  public RemoteArrayBuffer factionkickMemberRequests = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
  public RemoteArrayBuffer factionRelationships = new RemoteArrayBuffer(3, RemoteIntegerArray.class, this);
  public RemoteArrayBuffer factionHomeBaseChangeBuffer = new RemoteArrayBuffer(6, RemoteStringArray.class, this);
  public RemoteArrayBuffer factionRelationshipOffer = new RemoteArrayBuffer(5, RemoteStringArray.class, this);
  public RemoteFactionRolesBuffer factionRolesBuffer = new RemoteFactionRolesBuffer(this);
  public RemoteArrayBuffer serverConfig = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
  
  public NetworkGameState(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkGameState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */