package org.schema.game.network.objects.remote;

import class_611;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import org.schema.common.util.ByteUtil;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public class RemoteMissileUpdateBuffer
  extends RemoteBuffer
{
  public RemoteMissileUpdateBuffer(NetworkObject paramNetworkObject)
  {
    super(RemoteMissileUpdate.class, paramNetworkObject);
  }
  
  public void cacheConstructor() {}
  
  public void clearReceiveBuffer()
  {
    getReceiveBuffer().clear();
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = ByteUtil.a10(paramDataInputStream);
    for (int i = 0; i < paramInt; i++)
    {
      Object localObject = class_611.a3(paramDataInputStream);
      localObject = new RemoteMissileUpdate((class_611)localObject, this.onServer);
      getReceiveBuffer().add(localObject);
    }
  }
  
  public boolean add(RemoteMissileUpdate paramRemoteMissileUpdate)
  {
    return super.add(paramRemoteMissileUpdate);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    int i = 0;
    synchronized ((ObjectArrayList)get())
    {
      paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
      i += 4;
      Iterator localIterator = ((ObjectArrayList)get()).iterator();
      while (localIterator.hasNext())
      {
        RemoteMissileUpdate localRemoteMissileUpdate = (RemoteMissileUpdate)localIterator.next();
        i += localRemoteMissileUpdate.toByteStream(paramDataOutputStream);
      }
      ((ObjectArrayList)get()).clear();
    }
    return i;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */