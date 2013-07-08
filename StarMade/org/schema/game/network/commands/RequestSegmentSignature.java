package org.schema.game.network.commands;

import class_1041;
import class_1204;
import class_672;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.network.Command;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestSegmentSignature
  extends Command
{
  public RequestSegmentSignature()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    Object localObject = (class_1041)paramServerStateInterface;
    int i = ((Integer)paramArrayOfObject[0]).intValue();
    int j = ((Integer)paramArrayOfObject[1]).intValue();
    int k = ((Integer)paramArrayOfObject[2]).intValue();
    paramArrayOfObject = ((Integer)paramArrayOfObject[3]).intValue();
    try
    {
      if ((localObject = (SegmentController)((class_1041)localObject).getLocalAndRemoteObjectContainer().getLocalObjects().get(i)) == null) {
        throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + i + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
      }
      class_672 localclass_672;
      if ((localclass_672 = (class_672)((SegmentController)localObject).getSegmentFromCache(j, k, paramArrayOfObject)) != null)
      {
        System.currentTimeMillis();
        class_672.d();
        createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(localclass_672.a44()), Boolean.valueOf(localclass_672.g()) });
        return;
      }
      paramArrayOfObject = (class_672)((class_1204)((SegmentController)localObject).getSegmentProvider()).a12(j, k, paramArrayOfObject);
      System.currentTimeMillis();
      class_672.d();
      createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(paramArrayOfObject.a44()), Boolean.valueOf(paramArrayOfObject.g()) });
      return;
    }
    catch (Exception localException)
    {
      createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Integer.valueOf(-1), Boolean.valueOf(true) });
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentSignature
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */