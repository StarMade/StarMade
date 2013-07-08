package org.schema.game.network.commands;

import class_1041;
import class_1204;
import class_371;
import class_48;
import class_672;
import class_886;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.util.Map;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.DeserializationException;
import org.schema.schine.network.Command;
import org.schema.schine.network.Header;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestSegmentData
  extends Command
{
  protected static final boolean USE_IMMEDIATE_REQUEST = true;
  
  public RequestSegmentData()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    paramArrayOfObject = ((class_371)paramClientStateInterface).a1(paramShort);
    assert (paramArrayOfObject != null) : ("not found: " + paramShort + ": " + ((class_371)paramClientStateInterface).a22());
    assert ((paramArrayOfObject.g()) || (paramArrayOfObject.a16() != null));
    try
    {
      paramArrayOfObject.a43(paramClientStateInterface.getProcessor().getIn(), -1, false);
      System.currentTimeMillis();
      class_672.d();
    }
    catch (DeserializationException localDeserializationException)
    {
      localDeserializationException.printStackTrace();
      System.err.println("CRITICAL: SERVER PROVIDED CORRUPT SEGEMNT");
    }
    if ((paramClientStateInterface.getProcessor().getIn().available() > 0) && (!$assertionsDisabled)) {
      throw new AssertionError(" Failed to fully deserialize " + paramArrayOfObject.field_34 + "; still available: " + paramClientStateInterface.getProcessor().getIn().available());
    }
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface arg3, short arg4)
  {
    paramArrayOfObject = new Header(RequestSegmentData.class, ???.getId(), paramServerProcessor.getClient().getId(), ???, (byte)123);
    ??? = (class_1041)???;
    ??? = paramServerProcessor.getIn().readInt();
    int i = paramServerProcessor.getIn().readInt();
    int j = paramServerProcessor.getIn().readInt();
    int k = paramServerProcessor.getIn().readInt();
    if ((??? = (SegmentController)???.getLocalAndRemoteObjectContainer().getLocalObjects().get(???)) != null)
    {
      if (???.getSegmentBuffer().a2(i, j, k))
      {
        ??? = (class_672)???.getSegmentBuffer().a4(i, j, k);
        synchronized (paramServerProcessor.getLock())
        {
          assert ((???.field_34.field_475 == i) && (???.field_34.field_476 == j) && (???.field_34.field_477 == k)) : (" serializing " + i + ", " + j + ", " + k + "; toSerialize " + ???.field_34);
          paramArrayOfObject.write(paramServerProcessor.getOut());
          ???.a45(paramServerProcessor.getOut());
          paramServerProcessor.flushDoubleOutBuffer();
          return;
        }
      }
      ??? = (class_672)((class_1204)???.getSegmentProvider()).a12(i, j, i);
      synchronized (paramServerProcessor.getLock())
      {
        assert ((???.field_34.field_475 == i) && (???.field_34.field_476 == j) && (???.field_34.field_477 == k)) : (" serializing " + i + ", " + j + ", " + k + "; toSerialize " + ???.field_34);
        paramArrayOfObject.write(paramServerProcessor.getOut());
        ???.a45(paramServerProcessor.getOut());
        paramServerProcessor.flushDoubleOutBuffer();
        return;
      }
    }
    System.err.println("[SERVER][ERROR] Exception: Requested SegmentController not found " + ??? + ". sending back dummy");
    synchronized (paramServerProcessor.getLock())
    {
      ??? = new class_672(null);
      paramArrayOfObject.write(paramServerProcessor.getOut());
      ???.a45(paramServerProcessor.getOut());
      paramServerProcessor.flushDoubleOutBuffer();
      return;
    }
  }
  
  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int arg3, short paramShort, NetworkProcessor paramNetworkProcessor)
  {
    assert ((paramNetworkProcessor.getState() instanceof class_371));
    paramArrayOfObject = new Header(getId(), paramShort, (byte)123);
    paramInt1 = null;
    synchronized (((class_371)paramNetworkProcessor.getState()).a22())
    {
      paramInt1 = (class_672)((class_371)paramNetworkProcessor.getState()).a22().get(Short.valueOf(paramShort));
    }
    assert (paramInt1 != null);
    synchronized (paramNetworkProcessor.getLock())
    {
      paramArrayOfObject.write(paramNetworkProcessor.getOut());
      paramNetworkProcessor.getOut().writeInt(paramInt1.a15().getId());
      System.currentTimeMillis();
      class_672.d();
      paramNetworkProcessor.getOut().writeInt(paramInt1.field_34.field_475);
      paramNetworkProcessor.getOut().writeInt(paramInt1.field_34.field_476);
      paramNetworkProcessor.getOut().writeInt(paramInt1.field_34.field_477);
      paramNetworkProcessor.flushDoubleOutBuffer();
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */