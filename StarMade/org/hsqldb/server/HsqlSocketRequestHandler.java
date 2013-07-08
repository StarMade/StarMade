package org.hsqldb.server;

import java.net.Socket;

public abstract interface HsqlSocketRequestHandler
{
  public abstract void handleConnection(Socket paramSocket);
  
  public abstract void signalCloseAllServerConnections();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.server.HsqlSocketRequestHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */