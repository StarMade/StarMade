package org.schema.game.network.objects.remote;

import class_749;
import class_753;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import kc;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public class RemoteControlStructureBuffer
  extends RemoteBuffer
{
  private class_749 segmentProvider;
  private Constructor constructor;
  private static Constructor staticConstructor;
  
  public RemoteControlStructureBuffer(class_749 paramclass_749, NetworkObject paramNetworkObject)
  {
    super(RemoteControlStructure.class, paramNetworkObject);
    this.segmentProvider = paramclass_749;
  }
  
  public RemoteControlStructureBuffer(class_749 paramclass_749, boolean paramBoolean)
  {
    super(RemoteControlStructure.class, paramBoolean);
    this.segmentProvider = paramclass_749;
  }
  
  public void cacheConstructor()
  {
    try
    {
      if (staticConstructor == null) {
        staticConstructor = RemoteControlStructure.class.getConstructor(new Class[] { kc.class, Boolean.TYPE });
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
    for (int j = 0; j < i; j++) {
      ((RemoteControlStructure)this.constructor.newInstance(new Object[] { this.segmentProvider, Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
    }
  }
  
  public boolean add(RemoteControlStructure paramRemoteControlStructure)
  {
    return super.add(paramRemoteControlStructure);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    int i = 0;
    class_753 localclass_753;
    if ((localclass_753 = this.segmentProvider.a48()) != null)
    {
      paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
      i += 4;
      Iterator localIterator = ((ObjectArrayList)get()).iterator();
      while (localIterator.hasNext())
      {
        RemoteControlStructure localRemoteControlStructure = (RemoteControlStructure)localIterator.next();
        i += localRemoteControlStructure.toByteStream(paramDataOutputStream, localclass_753);
      }
    }
    else
    {
      paramDataOutputStream.writeInt(0);
    }
    ((ObjectArrayList)get()).clear();
    return i;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlStructureBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */