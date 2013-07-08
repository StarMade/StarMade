package org.schema.schine.network.objects;

import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public class NetworkChat
  extends NetworkObject
{
  public RemoteBuffer chatLogBuffer = new RemoteBuffer(RemoteString.class, this);
  public RemoteBuffer chatServerLogBuffer = new RemoteBuffer(RemoteString.class, this);
  public RemoteInteger owner = new RemoteInteger(Integer.valueOf(-1), this);
  public RemoteArrayBuffer chatWisperBuffer = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
  
  public NetworkChat(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.NetworkChat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */