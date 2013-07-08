package org.schema.game.network.objects.remote;

import class_637;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import me;
import org.schema.common.util.ByteUtil;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public class RemoteItemBuffer
  extends RemoteBuffer
{
  private Constructor constructor;
  private static Constructor staticConstructor;
  
  public RemoteItemBuffer(NetworkObject paramNetworkObject)
  {
    super(RemoteItem.class, paramNetworkObject);
  }
  
  public RemoteItemBuffer(boolean paramBoolean)
  {
    super(RemoteItem.class, paramBoolean);
  }
  
  public void cacheConstructor()
  {
    this.constructor = staticConstructor;
  }
  
  public void clearReceiveBuffer()
  {
    getReceiveBuffer().clear();
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    int i = ByteUtil.a10(paramDataInputStream);
    for (int j = 0; j < i; j++)
    {
      RemoteItem localRemoteItem;
      (localRemoteItem = (RemoteItem)this.constructor.newInstance(new Object[] { new class_637(), Boolean.valueOf(false), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
      getReceiveBuffer().add(localRemoteItem);
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
        RemoteItem localRemoteItem = (RemoteItem)localIterator.next();
        i += localRemoteItem.toByteStream(paramDataOutputStream);
      }
      ((ObjectArrayList)get()).clear();
    }
    return i;
  }
  
  static
  {
    try
    {
      staticConstructor = RemoteItem.class.getConstructor(new Class[] { me.class, Boolean.class, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteItemBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */