package org.schema.game.network.commands;

import class_1041;
import class_48;
import class_672;
import class_749;
import class_753;
import class_802;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.network.objects.NetworkSegmentProvider;
import org.schema.game.network.objects.remote.RemoteSegmentSignature;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.Command;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestSegmentSignatureUnblocked
  extends Command
{
  public RequestSegmentSignatureUnblocked()
  {
    this.mode = 0;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    int i = ((Integer)???[0]).intValue();
    int j = ((Integer)???[1]).intValue();
    int k = ((Integer)???[2]).intValue();
    ??? = ((Integer)???[3]).intValue();
    try
    {
      class_749 localclass_749;
      class_753 localclass_753;
      if ((localclass_753 = (localclass_749 = (class_749)paramServerProcessor.getClient().getLocalAndRemoteObjectContainer().getLocalObjects().get(i)).a48()) == null) {
        throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + i + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
      }
      class_672 localclass_672;
      if ((localclass_672 = (class_672)localclass_753.getSegmentFromCache(j, k, ???)) != null)
      {
        createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(localclass_672.a44()), Boolean.valueOf(localclass_672.g()) });
        paramServerProcessor = localclass_749.a47();
        System.currentTimeMillis();
        class_672.d();
        synchronized (paramServerProcessor)
        {
          paramServerProcessor.signatureBuffer.add(new RemoteSegmentSignature(new class_802(new class_48(localclass_672.field_34), localclass_672.a44(), localclass_672.a15().getId(), localclass_672.g()), paramServerProcessor));
          return;
        }
      }
      paramServerProcessor = localclass_749.a47();
      ((class_1041)paramServerStateInterface).a59().b10(localclass_753, new class_48(j, k, ???), paramServerProcessor);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      System.err.println("Exception catched for ID: " + i);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentSignatureUnblocked
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */