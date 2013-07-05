/*    */ package org.schema.schine.network;
/*    */ 
/*    */ import java.io.BufferedOutputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ 
/*    */ public class MultiBufferedOutputStream extends BufferedOutputStream
/*    */ {
/*    */   private ServerStateInterface state;
/*    */ 
/*    */   public MultiBufferedOutputStream(ServerStateInterface paramServerStateInterface)
/*    */   {
/* 13 */     super(null);
/* 14 */     this.state = paramServerStateInterface;
/*    */   }
/*    */ 
/*    */   public synchronized void flush()
/*    */   {
/* 22 */     for (Iterator localIterator = this.state.getClients().values().iterator(); localIterator.hasNext(); ) ((RegisteredClientOnServer)localIterator.next())
/* 23 */         .getProcessor().getOut().flush();
/*    */   }
/*    */ 
/*    */   public void write(byte[] paramArrayOfByte)
/*    */   {
/* 32 */     for (Iterator localIterator = this.state.getClients().values().iterator(); localIterator.hasNext(); ) ((RegisteredClientOnServer)localIterator.next())
/* 33 */         .getProcessor().getOut().write(paramArrayOfByte);
/*    */   }
/*    */ 
/*    */   public synchronized void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*    */   {
/* 43 */     for (Iterator localIterator = this.state.getClients().values().iterator(); localIterator.hasNext(); ) ((RegisteredClientOnServer)localIterator.next())
/* 44 */         .getProcessor().getOut().write(paramArrayOfByte, paramInt1, paramInt2);
/*    */   }
/*    */ 
/*    */   public synchronized void write(int paramInt)
/*    */   {
/* 53 */     for (Iterator localIterator = this.state.getClients().values().iterator(); localIterator.hasNext(); ) ((RegisteredClientOnServer)localIterator.next())
/* 54 */         .getProcessor().getOut().write(paramInt);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.MultiBufferedOutputStream
 * JD-Core Version:    0.6.2
 */