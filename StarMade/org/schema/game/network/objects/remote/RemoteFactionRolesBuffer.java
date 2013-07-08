package org.schema.game.network.objects.remote;

import class_625;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import mc;
import org.schema.common.util.ByteUtil;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public class RemoteFactionRolesBuffer
  extends RemoteBuffer
{
  private static Constructor staticConstructor;
  
  public RemoteFactionRolesBuffer(boolean paramBoolean)
  {
    super(RemoteFactionRoles.class, paramBoolean);
  }
  
  public RemoteFactionRolesBuffer(NetworkObject paramNetworkObject)
  {
    super(RemoteFactionRoles.class, paramNetworkObject);
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
      RemoteFactionRoles localRemoteFactionRoles;
      (localRemoteFactionRoles = (RemoteFactionRoles)staticConstructor.newInstance(new Object[] { new class_625(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
      getReceiveBuffer().add(localRemoteFactionRoles);
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
        RemoteFactionRoles localRemoteFactionRoles = (RemoteFactionRoles)localIterator.next();
        i += localRemoteFactionRoles.toByteStream(paramDataOutputStream);
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
        staticConstructor = RemoteFactionRoles.class.getConstructor(new Class[] { mc.class, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionRolesBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */