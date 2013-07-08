/*   1:    */package org.schema.schine.network.udp;
/*   2:    */
/*   3:    */import java.net.DatagramPacket;
/*   4:    */import java.net.DatagramSocket;
/*   5:    */
/*  86:    */public class UDPProcessor$MessageWriter
/*  87:    */  implements Runnable
/*  88:    */{
/*  89:    */  private UDPEndpoint endpoint;
/*  90:    */  private DatagramPacket packet;
/*  91:    */  
/*  92:    */  public UDPProcessor$MessageWriter(UDPProcessor paramUDPProcessor, UDPEndpoint paramUDPEndpoint, DatagramPacket paramDatagramPacket)
/*  93:    */  {
/*  94: 94 */    this.endpoint = paramUDPEndpoint;
/*  95: 95 */    this.packet = paramDatagramPacket;
/*  96:    */  }
/*  97:    */  
/* 101:    */  public void run()
/* 102:    */  {
/* 103:103 */    if (!this.endpoint.isConnected()) {
/* 104:104 */      return;
/* 105:    */    }
/* 106:    */    try
/* 107:    */    {
/* 108:108 */      UDPProcessor.access$100(this.this$0).getSocket().send(this.packet); return;
/* 109:    */    }
/* 110:    */    catch (Exception localException) {
/* 111:111 */      new UDPException("Error sending datagram to:" + UDPProcessor.access$000(this.this$0), localException).fillInStackTrace();
/* 112:112 */      localException.printStackTrace();
/* 113:    */    }
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.MessageWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */