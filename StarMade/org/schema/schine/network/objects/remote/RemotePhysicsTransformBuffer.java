package org.schema.schine.network.objects.remote;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.NetworkTransformation;

public class RemotePhysicsTransformBuffer
  extends RemoteBuffer
{
  private Constructor constructor;
  private static Constructor staticConstructor;
  
  public RemotePhysicsTransformBuffer(NetworkObject paramNetworkObject)
  {
    super(RemotePhysicsTransform.class, paramNetworkObject);
  }
  
  public void cacheConstructor()
  {
    try
    {
      if (staticConstructor == null) {
        staticConstructor = RemotePhysicsTransform.class.getConstructor(new Class[] { NetworkTransformation.class, Boolean.TYPE });
      }
      this.constructor = staticConstructor;
      return;
    }
    catch (SecurityException localSecurityException)
    {
      System.err.println("CLASS " + this.clazz);
      localSecurityException.printStackTrace();
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      System.err.println("CLASS " + this.clazz);
      localNoSuchMethodException.printStackTrace();
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
    }
  }
  
  public void clearReceiveBuffer()
  {
    getReceiveBuffer().clear();
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    int i = paramDataInputStream.readInt();
    for (int j = 0; j < i; j++)
    {
      RemotePhysicsTransform localRemotePhysicsTransform;
      (localRemotePhysicsTransform = (RemotePhysicsTransform)this.constructor.newInstance(new Object[] { new NetworkTransformation(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
      getReceiveBuffer().add(localRemotePhysicsTransform);
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    int i = 0;
    paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
    i += 4;
    Iterator localIterator = ((ObjectArrayList)get()).iterator();
    while (localIterator.hasNext())
    {
      RemotePhysicsTransform localRemotePhysicsTransform = (RemotePhysicsTransform)localIterator.next();
      i += localRemotePhysicsTransform.toByteStream(paramDataOutputStream);
    }
    ((ObjectArrayList)get()).clear();
    if (!this.onServer) {
      System.err.println("###########################----------------> SENT " + this);
    }
    return i;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemotePhysicsTransformBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */