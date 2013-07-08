/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import kc;
/*  5:   */import org.schema.schine.network.Command;
/*  6:   */import org.schema.schine.network.NetworkStateContainer;
/*  7:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  8:   */import org.schema.schine.network.client.ClientStateInterface;
/*  9:   */import org.schema.schine.network.server.ServerProcessor;
/* 10:   */import org.schema.schine.network.server.ServerStateInterface;
/* 11:   */
/* 12:   */public class RequestInventoriesUnblocked extends Command
/* 13:   */{
/* 14:   */  public RequestInventoriesUnblocked()
/* 15:   */  {
/* 16:16 */    this.mode = 0;
/* 17:   */  }
/* 18:   */  
/* 22:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
/* 23:   */  
/* 27:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 28:   */  {
/* 29:29 */    paramArrayOfObject = ((Integer)paramArrayOfObject[0]).intValue();
/* 30:   */    
/* 36:   */    try
/* 37:   */    {
/* 38:38 */      paramServerProcessor = null;
/* 39:   */      
/* 41:41 */      if ((paramServerProcessor = (kc)paramServerProcessor.getClient().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramArrayOfObject)).a() == null) {
/* 42:42 */        throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + paramArrayOfObject + ". This CAN happen, when the SegmentController for this SendableSegmentProvider was deleted and the PRIVATE sendable segment controller was still udpating");
/* 43:   */      }
/* 44:44 */      paramServerProcessor.b(); return;
/* 45:45 */    } catch (Exception localException) { 
/* 46:   */      
/* 47:47 */        localException;
/* 48:   */    }
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestInventoriesUnblocked
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */