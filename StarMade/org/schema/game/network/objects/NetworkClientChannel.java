package org.schema.game.network.objects;

import org.schema.game.network.objects.remote.RemoteControlledFileStream;
import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
import org.schema.game.network.objects.remote.RemoteMapEntryAnswerBuffer;
import org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer;
import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteLong;
import org.schema.schine.network.objects.remote.RemoteShort;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public class NetworkClientChannel
  extends NetworkObject
{
  public RemoteBoolean connectionReady = new RemoteBoolean(false, this);
  public RemoteMissileUpdateBuffer missileUpdateBuffer = new RemoteMissileUpdateBuffer(this);
  public RemoteBuffer missileMissingRequestBuffer = new RemoteBuffer(RemoteShort.class, this);
  public RemoteBuffer factionNewsRequests = new RemoteBuffer(RemoteLong.class, this);
  public RemoteBuffer fileRequests = new RemoteBuffer(RemoteString.class, this);
  public RemoteBuffer timeStampRequests = new RemoteBuffer(RemoteString.class, this);
  public RemoteArrayBuffer timeStampResponses = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
  public RemoteBuffer downloadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
  public RemoteInteger playerId = new RemoteInteger(Integer.valueOf(-121212), this);
  public RemoteFactionNewsPostBuffer factionNewsPosts = new RemoteFactionNewsPostBuffer(this);
  public RemoteMapEntryRequestBuffer mapRequests = new RemoteMapEntryRequestBuffer(this);
  public RemoteMapEntryAnswerBuffer mapAnswers = new RemoteMapEntryAnswerBuffer(this);
  
  public NetworkClientChannel(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkClientChannel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */