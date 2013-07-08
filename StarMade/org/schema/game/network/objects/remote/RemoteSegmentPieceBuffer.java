package org.schema.game.network.objects.remote;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import le;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public class RemoteSegmentPieceBuffer
  extends RemoteBuffer
{
  private static final int SEGMENT_BUFF_MAX_BATCH = 128;
  private SegmentController segmentController;
  private static Constructor staticConstructor;
  
  public RemoteSegmentPieceBuffer(SegmentController paramSegmentController, NetworkObject paramNetworkObject)
  {
    super(RemoteSegmentPiece.class, paramNetworkObject);
    this.segmentController = paramSegmentController;
  }
  
  public RemoteSegmentPieceBuffer(SegmentController paramSegmentController, boolean paramBoolean)
  {
    super(RemoteSegmentPiece.class, paramBoolean);
    this.segmentController = paramSegmentController;
  }
  
  public void cacheConstructor() {}
  
  public void clearReceiveBuffer()
  {
    getReceiveBuffer().clear();
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    int i = paramDataInputStream.readInt();
    for (int j = 0; j < i; j++)
    {
      RemoteSegmentPiece localRemoteSegmentPiece;
      (localRemoteSegmentPiece = (RemoteSegmentPiece)staticConstructor.newInstance(new Object[] { null, this.segmentController, Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
      getReceiveBuffer().add(localRemoteSegmentPiece);
    }
  }
  
  public int toByteStream2(DataOutputStream paramDataOutputStream)
  {
    int i = 0;
    synchronized ((ObjectArrayList)get())
    {
      paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
      i += 4;
      Iterator localIterator = ((ObjectArrayList)get()).iterator();
      while (localIterator.hasNext())
      {
        RemoteSegmentPiece localRemoteSegmentPiece = (RemoteSegmentPiece)localIterator.next();
        i += localRemoteSegmentPiece.toByteStream(paramDataOutputStream);
      }
      ((ObjectArrayList)get()).clear();
    }
    return i;
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    int i = Math.min(128, ((ObjectArrayList)get()).size());
    int j = 4;
    paramDataOutputStream.writeInt(i);
    for (int k = 0; k < i; k++)
    {
      RemoteSegmentPiece localRemoteSegmentPiece = (RemoteSegmentPiece)((ObjectArrayList)get()).remove(0);
      j += localRemoteSegmentPiece.toByteStream(paramDataOutputStream);
      localRemoteSegmentPiece.setChanged(false);
    }
    this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
    return j;
  }
  
  static
  {
    try
    {
      if (staticConstructor == null) {
        staticConstructor = RemoteSegmentPiece.class.getConstructor(new Class[] { le.class, SegmentController.class, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */