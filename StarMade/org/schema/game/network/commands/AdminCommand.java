/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import org.schema.game.server.controller.GameServerController;
/*  4:   */import org.schema.schine.network.Command;
/*  5:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  6:   */import org.schema.schine.network.client.ClientStateInterface;
/*  7:   */import org.schema.schine.network.server.ServerProcessor;
/*  8:   */import org.schema.schine.network.server.ServerStateInterface;
/*  9:   */import vg;
/* 10:   */
/* 21:   */public class AdminCommand
/* 22:   */  extends Command
/* 23:   */{
/* 24:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
/* 25:   */  
/* 26:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 27:   */  {
/* 28:28 */    if (((vg)paramServerStateInterface).a().isAdmin(paramServerProcessor.getClient()))
/* 29:   */    {
/* 30:30 */      paramServerProcessor = paramServerProcessor.getClient();
/* 31:31 */      paramShort = (Integer)paramArrayOfObject[0];
/* 32:32 */      paramShort = org.schema.game.server.data.admin.AdminCommands.values()[paramShort.intValue()];
/* 33:33 */      Object[] arrayOfObject = new Object[paramArrayOfObject.length - 1];
/* 34:   */      
/* 35:35 */      for (int i = 0; i < arrayOfObject.length; i++) {
/* 36:36 */        arrayOfObject[i] = paramArrayOfObject[(i + 1)];
/* 37:   */      }
/* 38:38 */      ((vg)paramServerStateInterface).a().a(paramServerProcessor, paramShort, arrayOfObject);
/* 39:39 */      return; }
/* 40:40 */    paramServerProcessor.getClient().serverMessage("[ADMIN COMMAND] [ERROR] YOU ARE NOT AN ADMIN!");
/* 41:   */  }
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.AdminCommand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */