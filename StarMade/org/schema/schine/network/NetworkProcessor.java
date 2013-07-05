package org.schema.schine.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface NetworkProcessor
{
  public abstract void closeSocket();

  public abstract void flushDoubleOutBuffer();

  public abstract int getCurrentSize();

  public abstract DataInputStream getIn();

  public abstract InputStream getInRaw();

  public abstract Object getLock();

  public abstract DataOutputStream getOut();

  public abstract OutputStream getOutRaw();

  public abstract StateInterface getState();

  public abstract boolean isAlive();

  public abstract void notifyPacketReceived(short paramShort, Command paramCommand);

  public abstract void resetDoubleOutBuffer();

  public abstract void updatePing();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetworkProcessor
 * JD-Core Version:    0.6.2
 */