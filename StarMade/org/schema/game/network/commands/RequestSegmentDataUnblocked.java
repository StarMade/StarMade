package org.schema.game.network.commands;

import class_1041;
import class_48;
import class_672;
import class_749;
import class_753;
import class_802;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.network.objects.NetworkSegmentProvider;
import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.Command;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestSegmentDataUnblocked
  extends Command
{
  public RequestSegmentDataUnblocked()
  {
    this.mode = 0;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface arg3, short paramShort)
  {
    paramShort = ((Integer)paramArrayOfObject[0]).intValue();
    int i = ((Integer)paramArrayOfObject[1]).intValue();
    int j = ((Integer)paramArrayOfObject[2]).intValue();
    paramArrayOfObject = ((Integer)paramArrayOfObject[3]).intValue();
    try
    {
      paramServerProcessor = null;
      class_753 localclass_753;
      if ((localclass_753 = (paramServerProcessor = (class_749)paramServerProcessor.getClient().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramShort)).a48()) == null) {
        throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + paramShort + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
      }
      if ((paramShort = (class_672)localclass_753.getSegmentFromCache(i, j, paramArrayOfObject)) != null)
      {
        paramServerProcessor = paramServerProcessor.a47();
        (paramArrayOfObject = new class_802(new class_48(paramShort.field_34), paramShort.a44(), paramShort.a15().getId(), paramShort.g())).field_1059 = localclass_753;
        synchronized (paramServerProcessor)
        {
          paramServerProcessor.segmentBuffer.add(new RemoteSegmentRemoteObj(paramArrayOfObject, paramServerProcessor));
          return;
        }
      }
      paramServerProcessor = paramServerProcessor.a47();
      ((class_1041)???).a59().a47(localclass_753, new class_48(i, j, paramArrayOfObject), paramServerProcessor);
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentDataUnblocked
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */