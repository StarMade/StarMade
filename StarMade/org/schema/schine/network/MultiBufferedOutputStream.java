/*  1:   */package org.schema.schine.network;
/*  2:   */
/*  3:   */import java.io.BufferedOutputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.server.ServerProcessor;
/*  6:   */
/*  7:   */public class MultiBufferedOutputStream extends BufferedOutputStream
/*  8:   */{
/*  9:   */  private org.schema.schine.network.server.ServerStateInterface state;
/* 10:   */  
/* 11:   */  public MultiBufferedOutputStream(org.schema.schine.network.server.ServerStateInterface paramServerStateInterface)
/* 12:   */  {
/* 13:13 */    super(null);
/* 14:14 */    this.state = paramServerStateInterface;
/* 15:   */  }
/* 16:   */  
/* 20:   */  public synchronized void flush()
/* 21:   */  {
/* 22:22 */    for (java.util.Iterator localIterator = this.state.getClients().values().iterator(); localIterator.hasNext();) {
/* 23:23 */      ((RegisteredClientOnServer)localIterator.next()).getProcessor().getOut().flush();
/* 24:   */    }
/* 25:   */  }
/* 26:   */  
/* 30:   */  public void write(byte[] paramArrayOfByte)
/* 31:   */  {
/* 32:32 */    for (java.util.Iterator localIterator = this.state.getClients().values().iterator(); localIterator.hasNext();) {
/* 33:33 */      ((RegisteredClientOnServer)localIterator.next()).getProcessor().getOut().write(paramArrayOfByte);
/* 34:   */    }
/* 35:   */  }
/* 36:   */  
/* 41:   */  public synchronized void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 42:   */  {
/* 43:43 */    for (java.util.Iterator localIterator = this.state.getClients().values().iterator(); localIterator.hasNext();) {
/* 44:44 */      ((RegisteredClientOnServer)localIterator.next()).getProcessor().getOut().write(paramArrayOfByte, paramInt1, paramInt2);
/* 45:   */    }
/* 46:   */  }
/* 47:   */  
/* 51:   */  public synchronized void write(int paramInt)
/* 52:   */  {
/* 53:53 */    for (java.util.Iterator localIterator = this.state.getClients().values().iterator(); localIterator.hasNext();) {
/* 54:54 */      ((RegisteredClientOnServer)localIterator.next()).getProcessor().getOut().write(paramInt);
/* 55:   */    }
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.MultiBufferedOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */