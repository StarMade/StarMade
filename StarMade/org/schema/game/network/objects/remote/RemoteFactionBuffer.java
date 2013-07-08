package org.schema.game.network.objects.remote;

import class_773;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import lP;
import org.schema.common.util.ByteUtil;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public class RemoteFactionBuffer
  extends RemoteBuffer
{
  private static Constructor staticConstructor;
  
  public RemoteFactionBuffer(NetworkObject paramNetworkObject)
  {
    super(RemoteFaction.class, paramNetworkObject);
  }
  
  public void cacheConstructor() {}
  
  public void clearReceiveBuffer()
  {
    getReceiveBuffer().clear();
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    int i = ByteUtil.a10(paramDataInputStream);
    for (int j = 0; j < i; j++)
    {
      RemoteFaction localRemoteFaction;
      (localRemoteFaction = (RemoteFaction)staticConstructor.newInstance(new Object[] { new class_773(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
      getReceiveBuffer().add(localRemoteFaction);
    }
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
        RemoteFaction localRemoteFaction = (RemoteFaction)localIterator.next();
        i += localRemoteFaction.toByteStream(paramDataOutputStream);
      }
      ((ObjectArrayList)get()).clear();
    }
    return i;
  }
  
  static
  {
    try
    {
      if (staticConstructor == null) {
        staticConstructor = RemoteFaction.class.getConstructor(new Class[] { lP.class, Boolean.TYPE });
      }
      return;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException;
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException;
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */