package net.rudp;

public class ReliableSocketProfile
{
  public static final int MAX_SEND_QUEUE_SIZE = 32;
  public static final int MAX_RECV_QUEUE_SIZE = 32;
  public static final int MAX_SEGMENT_SIZE = 128;
  public static final int MAX_OUTSTANDING_SEGS = 3;
  public static final int MAX_RETRANS = 3;
  public static final int MAX_CUMULATIVE_ACKS = 3;
  public static final int MAX_OUT_OF_SEQUENCE = 3;
  public static final int MAX_AUTO_RESET = 3;
  public static final int NULL_SEGMENT_TIMEOUT = 2000;
  public static final int RETRANSMISSION_TIMEOUT = 600;
  public static final int CUMULATIVE_ACK_TIMEOUT = 300;
  private int _maxSendQueueSize;
  private int _maxRecvQueueSize;
  private int _maxSegmentSize;
  private int _maxOutstandingSegs;
  private int _maxRetrans;
  private int _maxCumulativeAcks;
  private int _maxOutOfSequence;
  private int _maxAutoReset;
  private int _nullSegmentTimeout;
  private int _retransmissionTimeout;
  private int _cumulativeAckTimeout;
  
  public ReliableSocketProfile()
  {
    this(32, 32, 128, 3, 0, 3, 3, 3, 2000, 600, 300);
  }
  
  public ReliableSocketProfile(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11)
  {
    checkValue("maxSendQueueSize", paramInt1, 1, 255);
    checkValue("maxRecvQueueSize", paramInt2, 1, 255);
    checkValue("maxSegmentSize", paramInt3, 22, 65535);
    checkValue("maxOutstandingSegs", paramInt4, 1, 255);
    checkValue("maxRetrans", paramInt5, 0, 255);
    checkValue("maxCumulativeAcks", paramInt6, 0, 255);
    checkValue("maxOutOfSequence", paramInt7, 0, 255);
    checkValue("maxAutoReset", paramInt8, 0, 255);
    checkValue("nullSegmentTimeout", paramInt9, 0, 65535);
    checkValue("retransmissionTimeout", paramInt10, 100, 65535);
    checkValue("cumulativeAckTimeout", paramInt11, 100, 65535);
    this._maxSendQueueSize = paramInt1;
    this._maxRecvQueueSize = paramInt2;
    this._maxSegmentSize = paramInt3;
    this._maxOutstandingSegs = paramInt4;
    this._maxRetrans = paramInt5;
    this._maxCumulativeAcks = paramInt6;
    this._maxOutOfSequence = paramInt7;
    this._maxAutoReset = paramInt8;
    this._nullSegmentTimeout = paramInt9;
    this._retransmissionTimeout = paramInt10;
    this._cumulativeAckTimeout = paramInt11;
  }
  
  public int maxSendQueueSize()
  {
    return this._maxSendQueueSize;
  }
  
  public int maxRecvQueueSize()
  {
    return this._maxRecvQueueSize;
  }
  
  public int maxSegmentSize()
  {
    return this._maxSegmentSize;
  }
  
  public int maxOutstandingSegs()
  {
    return this._maxOutstandingSegs;
  }
  
  public int maxRetrans()
  {
    return this._maxRetrans;
  }
  
  public int maxCumulativeAcks()
  {
    return this._maxCumulativeAcks;
  }
  
  public int maxOutOfSequence()
  {
    return this._maxOutOfSequence;
  }
  
  public int maxAutoReset()
  {
    return this._maxAutoReset;
  }
  
  public int nullSegmentTimeout()
  {
    return this._nullSegmentTimeout;
  }
  
  public int retransmissionTimeout()
  {
    return this._retransmissionTimeout;
  }
  
  public int cumulativeAckTimeout()
  {
    return this._cumulativeAckTimeout;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    localStringBuilder.append(this._maxSendQueueSize).append(", ");
    localStringBuilder.append(this._maxRecvQueueSize).append(", ");
    localStringBuilder.append(this._maxSegmentSize).append(", ");
    localStringBuilder.append(this._maxOutstandingSegs).append(", ");
    localStringBuilder.append(this._maxRetrans).append(", ");
    localStringBuilder.append(this._maxCumulativeAcks).append(", ");
    localStringBuilder.append(this._maxOutOfSequence).append(", ");
    localStringBuilder.append(this._maxAutoReset).append(", ");
    localStringBuilder.append(this._nullSegmentTimeout).append(", ");
    localStringBuilder.append(this._retransmissionTimeout).append(", ");
    localStringBuilder.append(this._cumulativeAckTimeout);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  private void checkValue(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {
      throw new IllegalArgumentException(paramString);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.ReliableSocketProfile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */