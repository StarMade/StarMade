/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import org.schema.schine.network.Command;
/*  4:   */import org.schema.schine.network.client.ClientStateInterface;
/*  5:   */import org.schema.schine.network.server.ServerProcessor;
/*  6:   */import org.schema.schine.network.server.ServerStateInterface;
/*  7:   */import xu;
/*  8:   */
/* 61:   */public class Pause
/* 62:   */  extends Command
/* 63:   */{
/* 64:   */  public Pause()
/* 65:   */  {
/* 66:66 */    this.mode = 1;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 70:   */  {
/* 71:71 */    xu.B.a(((Boolean)paramArrayOfObject[0]).booleanValue());
/* 72:   */  }
/* 73:   */  
/* 75:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 76:   */  {
/* 77:77 */    paramArrayOfObject = ((Boolean)paramArrayOfObject[0]).booleanValue();
/* 78:78 */    paramServerStateInterface.setPaused(paramArrayOfObject);
/* 79:79 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Boolean.valueOf(paramArrayOfObject) });
/* 80:   */  }
/* 81:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.Pause
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */