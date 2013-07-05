/*     */ package org.schema.schine.network.udp;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import org.schema.schine.network.IdGen;
/*     */ 
/*     */ public class UDPProcessor
/*     */ {
/* 159 */   private Map socketEndpoints = new ConcurrentHashMap();
/*     */   private InetSocketAddress address;
/*     */   private UDPProcessor.HostThread thread;
/*     */   private ExecutorService writer;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 118 */     new Thread(new UDPProcessor.1()).start();
/*     */ 
/* 140 */     new Thread(new UDPProcessor.2()).start();
/*     */   }
/*     */ 
/*     */   public UDPProcessor(InetAddress paramInetAddress, int paramInt)
/*     */   {
/* 170 */     this(new InetSocketAddress(paramInetAddress, paramInt));
/*     */   }
/*     */ 
/*     */   public UDPProcessor(InetSocketAddress paramInetSocketAddress)
/*     */   {
/* 175 */     this.address = paramInetSocketAddress;
/*     */   }
/*     */ 
/*     */   public UDPProcessor(int paramInt)
/*     */   {
/* 180 */     this(new InetSocketAddress(paramInt));
/*     */   }
/*     */ 
/*     */   public void broadcast(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */   {
/* 198 */     for (UDPEndpoint localUDPEndpoint : this.socketEndpoints.values())
/*     */     {
/* 200 */       System.err.println("SEDING TO ENDPOINT");
/* 201 */       localUDPEndpoint.send(paramArrayOfByte, paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void closeEndpoint(UDPEndpoint paramUDPEndpoint)
/*     */   {
/* 212 */     if (this.socketEndpoints.remove(paramUDPEndpoint.getRemoteAddress()) == null);
/*     */   }
/*     */ 
/*     */   protected UDPProcessor.HostThread createHostThread()
/*     */   {
/* 235 */     return new UDPProcessor.HostThread(this);
/*     */   }
/*     */   public void enqueueWrite(UDPEndpoint paramUDPEndpoint, DatagramPacket paramDatagramPacket) {
/* 238 */     this.writer.execute(new UDPProcessor.MessageWriter(this, paramUDPEndpoint, paramDatagramPacket));
/*     */   }
/*     */ 
/*     */   protected UDPEndpoint getEndpoint(SocketAddress paramSocketAddress, boolean paramBoolean)
/*     */   {
/*     */     UDPEndpoint localUDPEndpoint;
/* 246 */     if (((
/* 246 */       localUDPEndpoint = (UDPEndpoint)this.socketEndpoints.get(paramSocketAddress)) == null) && 
/* 246 */       (paramBoolean)) {
/* 247 */       localUDPEndpoint = new UDPEndpoint(this, IdGen.getIndependentId(), paramSocketAddress, this.thread.getSocket());
/* 248 */       this.socketEndpoints.put(paramSocketAddress, localUDPEndpoint);
/*     */     }
/*     */ 
/* 253 */     return localUDPEndpoint;
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 258 */     if (this.thread != null) {
/* 259 */       throw new IllegalStateException("Kernel already initialized.");
/*     */     }
/* 261 */     this.writer = Executors.newFixedThreadPool(2, new NamedThreadFactory(toString() + "-writer"));
/*     */ 
/* 263 */     this.thread = createHostThread();
/*     */     try {
/* 266 */       this.thread.connect();
/* 267 */       this.thread.start();
/*     */       return; } catch (IOException localIOException) { throw new UDPException("Error hosting:" + this.address, localIOException); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected void newData(DatagramPacket paramDatagramPacket)
/*     */   {
/* 280 */     getEndpoint(paramDatagramPacket.getSocketAddress(), true);
/*     */ 
/* 283 */     System.err.println("RECEIVED " + Arrays.toString(paramDatagramPacket.getData()));
/*     */   }
/*     */ 
/*     */   public void terminate()
/*     */   {
/* 293 */     if (this.thread == null)
/* 294 */       throw new IllegalStateException("Kernel not initialized."); try {
/* 297 */       this.thread.close();
/* 298 */       this.writer.shutdown();
/* 299 */       this.thread = null;
/*     */       return; } catch (IOException localIOException) { throw new UDPException("Error closing host connection:" + this.address, localIOException); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor
 * JD-Core Version:    0.6.2
 */