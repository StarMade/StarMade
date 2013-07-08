/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import org.schema.schine.network.Command;
/*  5:   */import org.schema.schine.network.NetworkStateContainer;
/*  6:   */import org.schema.schine.network.client.ClientStateInterface;
/*  7:   */import org.schema.schine.network.objects.Sendable;
/*  8:   */import org.schema.schine.network.server.ServerProcessor;
/*  9:   */import org.schema.schine.network.server.ServerStateInterface;
/* 10:   */
/* 16:   */public class KillCharacter
/* 17:   */  extends Command
/* 18:   */{
/* 19:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
/* 20:   */  
/* 21:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
/* 22:   */  {
/* 23:23 */    paramServerProcessor = ((Integer)???[0]).intValue();
/* 24:24 */    synchronized (paramServerStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 25:25 */      if (paramServerStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(paramServerProcessor)) {
/* 26:26 */        ((Sendable)paramServerStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(paramServerProcessor)).setMarkedForDeleteVolatile(true);
/* 27:   */      }
/* 28:   */      return;
/* 29:   */    }
/* 30:   */  }
/* 31:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.KillCharacter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */