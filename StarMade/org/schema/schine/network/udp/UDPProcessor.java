package org.schema.schine.network.udp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.schema.schine.network.IdGen;

public class UDPProcessor
{
  private Map socketEndpoints = new ConcurrentHashMap();
  private InetSocketAddress address;
  private UDPProcessor.HostThread thread;
  private ExecutorService writer;
  
  public static void main(String[] paramArrayOfString)
  {
    new Thread(new UDPProcessor.1()).start();
    new Thread(new UDPProcessor.2()).start();
  }
  
  public UDPProcessor(InetAddress paramInetAddress, int paramInt)
  {
    this(new InetSocketAddress(paramInetAddress, paramInt));
  }
  
  public UDPProcessor(InetSocketAddress paramInetSocketAddress)
  {
    this.address = paramInetSocketAddress;
  }
  
  public UDPProcessor(int paramInt)
  {
    this(new InetSocketAddress(paramInt));
  }
  
  public void broadcast(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.socketEndpoints.values().iterator();
    while (localIterator.hasNext())
    {
      UDPEndpoint localUDPEndpoint = (UDPEndpoint)localIterator.next();
      System.err.println("SEDING TO ENDPOINT");
      localUDPEndpoint.send(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  protected void closeEndpoint(UDPEndpoint paramUDPEndpoint)
  {
    if (this.socketEndpoints.remove(paramUDPEndpoint.getRemoteAddress()) == null) {}
  }
  
  protected UDPProcessor.HostThread createHostThread()
  {
    return new UDPProcessor.HostThread(this);
  }
  
  public void enqueueWrite(UDPEndpoint paramUDPEndpoint, DatagramPacket paramDatagramPacket)
  {
    this.writer.execute(new UDPProcessor.MessageWriter(this, paramUDPEndpoint, paramDatagramPacket));
  }
  
  protected UDPEndpoint getEndpoint(SocketAddress paramSocketAddress, boolean paramBoolean)
  {
    UDPEndpoint localUDPEndpoint;
    if (((localUDPEndpoint = (UDPEndpoint)this.socketEndpoints.get(paramSocketAddress)) == null) && (paramBoolean))
    {
      localUDPEndpoint = new UDPEndpoint(this, IdGen.getIndependentId(), paramSocketAddress, this.thread.getSocket());
      this.socketEndpoints.put(paramSocketAddress, localUDPEndpoint);
    }
    return localUDPEndpoint;
  }
  
  public void initialize()
  {
    if (this.thread != null) {
      throw new IllegalStateException("Kernel already initialized.");
    }
    this.writer = Executors.newFixedThreadPool(2, new NamedThreadFactory(toString() + "-writer"));
    this.thread = createHostThread();
    try
    {
      this.thread.connect();
      this.thread.start();
      return;
    }
    catch (IOException localIOException)
    {
      throw new UDPException("Error hosting:" + this.address, localIOException);
    }
  }
  
  protected void newData(DatagramPacket paramDatagramPacket)
  {
    getEndpoint(paramDatagramPacket.getSocketAddress(), true);
    System.err.println("RECEIVED " + Arrays.toString(paramDatagramPacket.getData()));
  }
  
  public void terminate()
  {
    if (this.thread == null) {
      throw new IllegalStateException("Kernel not initialized.");
    }
    try
    {
      this.thread.close();
      this.writer.shutdown();
      this.thread = null;
      return;
    }
    catch (IOException localIOException)
    {
      throw new UDPException("Error closing host connection:" + this.address, localIOException);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */