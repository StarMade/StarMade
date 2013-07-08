package org.schema.game.network.objects.remote;

import class_635;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import mf;
import mh;
import org.schema.common.util.ByteUtil;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public class RemoteInventoryCluster
  extends RemoteBuffer
{
  private class_635 holder;
  private static Constructor staticConstructor;
  
  public RemoteInventoryCluster(class_635 paramclass_635, NetworkObject paramNetworkObject)
  {
    super(RemoteInventory.class, paramNetworkObject);
    this.holder = paramclass_635;
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
      RemoteInventory localRemoteInventory;
      (localRemoteInventory = (RemoteInventory)staticConstructor.newInstance(new Object[] { null, this.holder, Boolean.valueOf(false), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
      getReceiveBuffer().add(localRemoteInventory);
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
        RemoteInventory localRemoteInventory = (RemoteInventory)localIterator.next();
        i += localRemoteInventory.toByteStream(paramDataOutputStream);
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
        staticConstructor = RemoteInventory.class.getConstructor(new Class[] { mf.class, mh.class, Boolean.TYPE, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryCluster
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */