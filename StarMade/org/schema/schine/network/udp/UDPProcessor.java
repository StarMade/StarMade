/*   1:    */package org.schema.schine.network.udp;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.net.DatagramPacket;
/*   6:    */import java.net.InetAddress;
/*   7:    */import java.net.InetSocketAddress;
/*   8:    */import java.net.SocketAddress;
/*   9:    */import java.util.Arrays;
/*  10:    */import java.util.Map;
/*  11:    */import java.util.concurrent.ConcurrentHashMap;
/*  12:    */import java.util.concurrent.ExecutorService;
/*  13:    */import java.util.concurrent.Executors;
/*  14:    */import org.schema.schine.network.IdGen;
/*  15:    */
/* 114:    */public class UDPProcessor
/* 115:    */{
/* 116:    */  public static void main(String[] paramArrayOfString)
/* 117:    */  {
/* 118:118 */    new Thread(new UDPProcessor.1()).start();
/* 119:    */    
/* 140:140 */    new Thread(new UDPProcessor.2()).start();
/* 141:    */  }
/* 142:    */  
/* 159:159 */  private Map socketEndpoints = new ConcurrentHashMap();
/* 160:    */  
/* 161:    */  private InetSocketAddress address;
/* 162:    */  
/* 163:    */  private UDPProcessor.HostThread thread;
/* 164:    */  
/* 165:    */  private ExecutorService writer;
/* 166:    */  
/* 168:    */  public UDPProcessor(InetAddress paramInetAddress, int paramInt)
/* 169:    */  {
/* 170:170 */    this(new InetSocketAddress(paramInetAddress, paramInt));
/* 171:    */  }
/* 172:    */  
/* 173:    */  public UDPProcessor(InetSocketAddress paramInetSocketAddress)
/* 174:    */  {
/* 175:175 */    this.address = paramInetSocketAddress;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public UDPProcessor(int paramInt)
/* 179:    */  {
/* 180:180 */    this(new InetSocketAddress(paramInt));
/* 181:    */  }
/* 182:    */  
/* 196:    */  public void broadcast(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 197:    */  {
/* 198:198 */    for (UDPEndpoint localUDPEndpoint : this.socketEndpoints.values())
/* 199:    */    {
/* 200:200 */      System.err.println("SEDING TO ENDPOINT");
/* 201:201 */      localUDPEndpoint.send(paramArrayOfByte, paramInt1, paramInt2);
/* 202:    */    }
/* 203:    */  }
/* 204:    */  
/* 210:    */  protected void closeEndpoint(UDPEndpoint paramUDPEndpoint)
/* 211:    */  {
/* 212:212 */    if (this.socketEndpoints.remove(paramUDPEndpoint.getRemoteAddress()) == null) {}
/* 213:    */  }
/* 214:    */  
/* 233:    */  protected UDPProcessor.HostThread createHostThread()
/* 234:    */  {
/* 235:235 */    return new UDPProcessor.HostThread(this);
/* 236:    */  }
/* 237:    */  
/* 238:238 */  public void enqueueWrite(UDPEndpoint paramUDPEndpoint, DatagramPacket paramDatagramPacket) { this.writer.execute(new UDPProcessor.MessageWriter(this, paramUDPEndpoint, paramDatagramPacket)); }
/* 239:    */  
/* 242:    */  protected UDPEndpoint getEndpoint(SocketAddress paramSocketAddress, boolean paramBoolean)
/* 243:    */  {
/* 244:    */    UDPEndpoint localUDPEndpoint;
/* 245:    */    
/* 246:246 */    if (((localUDPEndpoint = (UDPEndpoint)this.socketEndpoints.get(paramSocketAddress)) == null) && (paramBoolean)) {
/* 247:247 */      localUDPEndpoint = new UDPEndpoint(this, IdGen.getIndependentId(), paramSocketAddress, this.thread.getSocket());
/* 248:248 */      this.socketEndpoints.put(paramSocketAddress, localUDPEndpoint);
/* 249:    */    }
/* 250:    */    
/* 253:253 */    return localUDPEndpoint;
/* 254:    */  }
/* 255:    */  
/* 256:    */  public void initialize()
/* 257:    */  {
/* 258:258 */    if (this.thread != null) {
/* 259:259 */      throw new IllegalStateException("Kernel already initialized.");
/* 260:    */    }
/* 261:261 */    this.writer = Executors.newFixedThreadPool(2, new NamedThreadFactory(toString() + "-writer"));
/* 262:    */    
/* 263:263 */    this.thread = createHostThread();
/* 264:    */    try
/* 265:    */    {
/* 266:266 */      this.thread.connect();
/* 267:267 */      this.thread.start(); return;
/* 268:    */    } catch (IOException localIOException) {
/* 269:269 */      throw new UDPException("Error hosting:" + this.address, localIOException);
/* 270:    */    }
/* 271:    */  }
/* 272:    */  
/* 278:    */  protected void newData(DatagramPacket paramDatagramPacket)
/* 279:    */  {
/* 280:280 */    getEndpoint(paramDatagramPacket.getSocketAddress(), true);
/* 281:    */    
/* 283:283 */    System.err.println("RECEIVED " + Arrays.toString(paramDatagramPacket.getData()));
/* 284:    */  }
/* 285:    */  
/* 291:    */  public void terminate()
/* 292:    */  {
/* 293:293 */    if (this.thread == null) {
/* 294:294 */      throw new IllegalStateException("Kernel not initialized.");
/* 295:    */    }
/* 296:    */    try {
/* 297:297 */      this.thread.close();
/* 298:298 */      this.writer.shutdown();
/* 299:299 */      this.thread = null; return;
/* 300:    */    } catch (IOException localIOException) {
/* 301:301 */      throw new UDPException("Error closing host connection:" + this.address, localIOException);
/* 302:    */    }
/* 303:    */  }
/* 304:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */