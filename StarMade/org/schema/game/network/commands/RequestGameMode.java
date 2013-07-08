/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import kC;
/*  5:   */import org.schema.game.common.data.world.Universe;
/*  6:   */import org.schema.schine.network.Command;
/*  7:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  8:   */import org.schema.schine.network.client.ClientStateInterface;
/*  9:   */import org.schema.schine.network.server.ServerStateInterface;
/* 10:   */
/* 11:   */public class RequestGameMode extends Command
/* 12:   */{
/* 13:   */  public RequestGameMode()
/* 14:   */  {
/* 15:15 */    this.mode = 1;
/* 16:   */  }
/* 17:   */  
/* 19:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 20:   */  {
/* 21:21 */    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/* 22:   */  }
/* 23:   */  
/* 27:   */  public void serverProcess(org.schema.schine.network.server.ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 28:   */  {
/* 29:29 */    paramArrayOfObject = (vg)paramServerStateInterface;
/* 30:   */    
/* 32:   */    Object localObject;
/* 33:   */    
/* 35:35 */    if ((localObject = paramServerProcessor.getClient()) != null) {
/* 36:36 */      localObject = (lE)((RegisteredClientOnServer)localObject).getPlayerObject();
/* 37:   */      
/* 38:38 */      localObject = paramArrayOfObject.a().getSector(((lE)localObject).a());
/* 39:   */      
/* 40:40 */      createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramArrayOfObject.a().toString(), Integer.valueOf(((mx)localObject).a()), Integer.valueOf(((mx)localObject).a.a), Integer.valueOf(((mx)localObject).a.b), Integer.valueOf(((mx)localObject).a.c), paramArrayOfObject.a(), paramArrayOfObject.b() });
/* 41:   */      
/* 49:49 */      return; }
/* 50:50 */    System.err.println("[SERVER][REQUESTGAMEMODE] not sending gameMode to invalid client");
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void execute() {}
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestGameMode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */