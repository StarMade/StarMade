/*   1:    */package org.schema.schine.network.udp;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */
/* 116:    */final class UDPProcessor$1
/* 117:    */  implements Runnable
/* 118:    */{
/* 119:    */  public final void run()
/* 120:    */  {
/* 121:    */    try
/* 122:    */    {
/* 123:    */      UDPProcessor localUDPProcessor;
/* 124:124 */      (localUDPProcessor = new UDPProcessor(4242)).initialize();
/* 125:    */      for (;;) {
/* 126:126 */        System.err.println("Broadcasting");
/* 127:127 */        localUDPProcessor.broadcast(new byte[] { 10 }, 0, 1);
/* 128:    */        try {
/* 129:129 */          Thread.sleep(1000L);
/* 130:130 */        } catch (InterruptedException localInterruptedException) { 
/* 131:    */          
/* 132:132 */            localInterruptedException;
/* 133:    */        }
/* 134:    */      }
/* 135:    */    } catch (IOException localIOException) {
/* 136:134 */      localIOException;
/* 137:    */    }
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */