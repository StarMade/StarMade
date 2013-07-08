/*   1:    */package org.schema.schine.network.udp;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.net.InetSocketAddress;
/*   5:    */
/* 138:    */final class UDPProcessor$2
/* 139:    */  implements Runnable
/* 140:    */{
/* 141:    */  public final void run()
/* 142:    */  {
/* 143:    */    try
/* 144:    */    {
/* 145:    */      UDPProcessor localUDPProcessor;
/* 146:146 */      (localUDPProcessor = new UDPProcessor(4244)).initialize();
/* 147:147 */      localUDPProcessor.getEndpoint(new InetSocketAddress(4242), true)
/* 148:148 */        .send(new byte[13], 0, 1); return;
/* 149:149 */    } catch (IOException localIOException) { 
/* 150:    */      
/* 151:151 */        localIOException;
/* 152:    */    }
/* 153:    */  }
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */