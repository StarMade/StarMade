package org.schema.game.network.objects.remote;

import class_645;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import mi;
import org.schema.common.util.ByteUtil;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public class RemoteInventoryMultModBuffer
  extends RemoteBuffer
{
  private static Constructor staticConstructor;
  
  public RemoteInventoryMultModBuffer(NetworkObject paramNetworkObject)
  {
    super(RemoteInventoryMultMod.class, paramNetworkObject);
  }
  
  public RemoteInventoryMultModBuffer(boolean paramBoolean)
  {
    super(RemoteInventoryMultMod.class, paramBoolean);
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
      RemoteInventoryMultMod localRemoteInventoryMultMod;
      (localRemoteInventoryMultMod = (RemoteInventoryMultMod)staticConstructor.newInstance(new Object[] { new class_645(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
      getReceiveBuffer().add(localRemoteInventoryMultMod);
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
        RemoteInventoryMultMod localRemoteInventoryMultMod = (RemoteInventoryMultMod)localIterator.next();
        i += localRemoteInventoryMultMod.toByteStream(paramDataOutputStream);
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
        staticConstructor = RemoteInventoryMultMod.class.getConstructor(new Class[] { mi.class, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */